package iuh.fit;
import lombok.Data;

@Data
public class PerfumeRequestDTO {
    private String name;
    private String brand;
    private Double price;

    private String description;
    private String ingredients;
}