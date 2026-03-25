package iuh.fit;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "table_user_02")
@Data
public class UserFemale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String gender = "NU";
}