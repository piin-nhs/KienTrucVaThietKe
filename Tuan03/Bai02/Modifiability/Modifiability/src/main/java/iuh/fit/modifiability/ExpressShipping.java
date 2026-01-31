package iuh.fit.modifiability;

import org.springframework.stereotype.Component;

@Component
public class ExpressShipping implements ShippingStrategy {

    public int calculateFee(int distance) {
        return distance * 10;
    }

    public String getType() {
        return "EXPRESS";
    }
}
