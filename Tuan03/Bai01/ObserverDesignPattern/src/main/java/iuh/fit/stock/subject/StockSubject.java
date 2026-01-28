package iuh.fit.stock.subject;

import iuh.fit.stock.observer.StockObserver;

public interface StockSubject {
    void registerObserver(StockObserver observer);
    void notifyObservers();
}

