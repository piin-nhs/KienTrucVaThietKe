package iuh.fit.payment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "payments")
public class Payment {
    @Id
    private String id;
    private String orderId;
    private String username;
    private String paymentMethod;
    private Double amount;
    private String status;
    private LocalDateTime paymentTime;
}
