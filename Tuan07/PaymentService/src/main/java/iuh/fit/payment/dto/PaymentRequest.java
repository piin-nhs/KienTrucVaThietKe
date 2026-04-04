package iuh.fit.payment.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String orderId;
    private String username;
    private String paymentMethod;
    private Double amount;
}