package iuh.fit.dataintegrity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final InventoryRepository inventoryRepo;

    public OrderService(InventoryRepository inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    @Transactional
    public void createOrder(int quantity) {
        Inventory inv = inventoryRepo.findById(1L).orElseThrow();

        inv.decrease(quantity);
        System.out.println("Stock left: " + inv.getStock());

        if (quantity > 5) {
            throw new RuntimeException("Payment failed");
        }
    }
}
