package iuh.fit.payment.service;

import iuh.fit.payment.dto.PaymentRequest;
import iuh.fit.payment.model.Payment;
import iuh.fit.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;

    @Value("${order-service.url}")
    private String orderServiceUrl;

    public PaymentService(PaymentRepository paymentRepository, RestTemplate restTemplate) {
        this.paymentRepository = paymentRepository;
        this.restTemplate = restTemplate;
    }

    public Payment processPayment(PaymentRequest request) {
        try {
            String updateOrderEndpoint = orderServiceUrl + "/api/orders/"
                    + request.getOrderId() + "/status";

            restTemplate.put(updateOrderEndpoint, "PAID");

            Payment payment = new Payment();
            payment.setOrderId(request.getOrderId());
            payment.setUsername(request.getUsername());
            payment.setPaymentMethod(request.getPaymentMethod());
            payment.setAmount(request.getAmount());
            payment.setStatus("SUCCESS");
            payment.setPaymentTime(LocalDateTime.now());

            Payment savedPayment = paymentRepository.save(payment);

            System.out.println(request.getUsername() +
                    " đã đặt đơn #" + request.getOrderId() + " thành công");

            return savedPayment;

        } catch (Exception e) {
            System.err.println("Lỗi khi xử lý payment: " + e.getMessage());
            throw new RuntimeException("Thanh toán thất bại do không cập nhật được Order!", e);
        }
    }


    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(String id) {
        return paymentRepository.findById(id).orElse(null);
    }
}