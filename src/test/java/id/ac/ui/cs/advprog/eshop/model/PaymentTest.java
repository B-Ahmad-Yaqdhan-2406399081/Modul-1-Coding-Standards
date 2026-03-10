package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Order order;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                new ArrayList<>(), 1708560000L, "Safira Sudrajat");
        this.paymentData = new HashMap<>();
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreatePayment() {
        Payment payment = new Payment("payment-123", "VOUCHER_CODE", paymentData, order);

        assertEquals("payment-123", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(order, payment.getOrder());
        assertNull(payment.getStatus()); // Karena default belum di-set di konstruktor ini
    }

    @Test
    void testSetStatus() {
        Payment payment = new Payment("payment-123", "VOUCHER_CODE", paymentData, order);
        payment.setStatus("SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
    }
}
