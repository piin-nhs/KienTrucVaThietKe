package iuh.fit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "perfume_core")
@Data

public class PerfumeCore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private Double price;

    @JsonIgnore
    @OneToOne(mappedBy = "perfumeCore", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PerfumeDetail detail;
}