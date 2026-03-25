package iuh.fit;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "table_user_01")
@Data
public class UserMale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String gender = "NAM";
}