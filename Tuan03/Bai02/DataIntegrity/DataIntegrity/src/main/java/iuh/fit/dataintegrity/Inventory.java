package iuh.fit.dataintegrity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Inventory {

    @Id
    private Long id;

    private int stock;

    public Inventory() {}

    public Inventory(Long id, int stock) {
        this.id = id;
        this.stock = stock;
    }

    public void decrease(int quantity) {
        if (stock < quantity) {
            throw new RuntimeException("Not enough stock");
        }
        stock -= quantity;
    }

    public int getStock() {
        return stock;
    }

}
