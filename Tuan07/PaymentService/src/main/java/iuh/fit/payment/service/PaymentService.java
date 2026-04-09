package iuh.fit.payment.service;

import iuh.fit.payment.dto.PaymentRequest;
import iuh.fit.payment.model.Payment;
import iuh.fit.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Payment processPayment(PaymentRequest request, String token) {
        try {
            String updateOrderEndpoint = orderServiceUrl + "/api/orders/"
                    + request.getOrderId() + "/status";


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", token);

            Map<String, String> body = new HashMap<>();
            body.put("status", "COMPLETED");

            HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

            restTemplate.exchange(updateOrderEndpoint, HttpMethod.PUT, entity, Void.class);

            Payment payment = new Payment();
            payment.setOrderId(request.getOrderId());
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