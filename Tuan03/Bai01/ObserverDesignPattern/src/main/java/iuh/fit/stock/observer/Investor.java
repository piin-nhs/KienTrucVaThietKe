package iuh.fit.stock.observer;

import org.springframework.stereotype.Component;

@Component
public class Investor implements StockObserver {

    private final String name = "Nguyễn Hoàng Sang";

    @Override
    public void update(String stockName, double price) {
        System.out.println(name + " nhận thông báo: Giá cổ phiếu " + stockName + " = " + price);
    }
}

