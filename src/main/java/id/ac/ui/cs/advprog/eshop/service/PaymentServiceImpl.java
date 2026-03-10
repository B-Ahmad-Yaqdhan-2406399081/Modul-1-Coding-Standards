package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderService orderService;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String paymentId = UUID.randomUUID().toString();
        Payment payment = new Payment(paymentId, method, paymentData, order);

        String status = "REJECTED";

        if ("VOUCHER_CODE".equals(method)) {
            String voucherCode = paymentData.get("voucherCode");
            if (voucherCode != null &&
                    voucherCode.length() == 16 &&
                    voucherCode.startsWith("ESHOP")) {

                long numericCount = voucherCode.chars().filter(Character::isDigit).count();
                if (numericCount == 8) {
                    status = "SUCCESS";
                }
            }
        }

        payment.setStatus(status);
        paymentRepository.save(payment);

        setStatus(payment, status);

        return payment;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        paymentRepository.save(payment);

        if ("SUCCESS".equals(status)) {
            orderService.updateStatus(payment.getOrder().getId(), "SUCCESS");
        } else if ("REJECTED".equals(status)) {
            orderService.updateStatus(payment.getOrder().getId(), "FAILED");
        }

        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
