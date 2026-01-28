package iuh.fit.stock.subject;


import iuh.fit.stock.observer.StockObserver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Stock implements StockSubject {

    private String stockName;
    private double price;

    private final List<StockObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(StockObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(o -> o.update(stockName, price));
    }

    public void setPrice(String stockName, double price) {
        this.stockName = stockName;
        this.price = price;
        notifyObservers();
    }
}
