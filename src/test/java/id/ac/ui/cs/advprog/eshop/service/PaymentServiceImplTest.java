package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderService orderService;

    Order order;

    @BeforeEach
    void setUp() {
        order = new Order("order-123", new ArrayList<>(), 1708560000L, "Safira Sudrajat");
    }

    @Test
    void testAddPaymentVoucherValid() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        doReturn(null).when(paymentRepository).save(any(Payment.class));
        doReturn(order).when(orderService).updateStatus(order.getId(), PaymentStatus.SUCCESS.getValue());

        Payment result = paymentService.addPayment(order, "VOUCHER_CODE", paymentData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentRepository, times(2)).save(any(Payment.class));
        verify(orderService, times(1)).updateStatus(order.getId(), PaymentStatus.SUCCESS.getValue());
    }

    @Test
    void testAddPaymentVoucherInvalidNumberCount() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOPABCDEFGHJKL");

        doReturn(null).when(paymentRepository).save(any(Payment.class));
        doReturn(order).when(orderService).updateStatus(order.getId(), PaymentStatus.FAILED.getValue());

        Payment result = paymentService.addPayment(order, "VOUCHER_CODE", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        verify(orderService, times(1)).updateStatus(order.getId(), PaymentStatus.FAILED.getValue());
    }

    @Test
    void testAddPaymentVoucherInvalidPrefix() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "PROMO1234ABC5678"); // Prefix salah

        doReturn(null).when(paymentRepository).save(any(Payment.class));
        doReturn(order).when(orderService).updateStatus(order.getId(), PaymentStatus.FAILED.getValue());

        Payment result = paymentService.addPayment(order, "VOUCHER_CODE", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
    }

    @Test
    void testAddPaymentVoucherInvalidLength() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP123");

        doReturn(null).when(paymentRepository).save(any(Payment.class));
        doReturn(order).when(orderService).updateStatus(order.getId(), PaymentStatus.FAILED.getValue());

        Payment result = paymentService.addPayment(order, "VOUCHER_CODE", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
    }

    @Test
    void testSetStatusToSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", paymentData, order);

        doReturn(payment).when(paymentRepository).save(payment);
        doReturn(order).when(orderService).updateStatus(order.getId(), PaymentStatus.SUCCESS.getValue());

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());

        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(orderService, times(1)).updateStatus(order.getId(), PaymentStatus.SUCCESS.getValue());
    }

    @Test
    void testSetStatusToRejected() {
        Map<String, String> paymentData = new HashMap<>();
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", paymentData, order);

        doReturn(payment).when(paymentRepository).save(payment);
        doReturn(order).when(orderService).updateStatus(order.getId(), PaymentStatus.FAILED.getValue());

        Payment result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        verify(orderService, times(1)).updateStatus(order.getId(), PaymentStatus.FAILED.getValue());
    }
}
