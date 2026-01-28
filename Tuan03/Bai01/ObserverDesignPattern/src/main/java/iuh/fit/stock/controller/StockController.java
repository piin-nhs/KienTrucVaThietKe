package iuh.fit.stock.controller;

import iuh.fit.stock.observer.Investor;
import iuh.fit.stock.subject.Stock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    private final Stock stock;

    public StockController(Stock stock, Investor investor) {
        this.stock = stock;
        stock.registerObserver(investor);
    }

    @GetMapping("/stock/update")
    public String updateStock(
            @RequestParam String name,
            @RequestParam double price) {

        stock.setPrice(name, price);
        return "Stock " + name + " updated to " + price;

    }
}
