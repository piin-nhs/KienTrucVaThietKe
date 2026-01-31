package iuh.fit.modifiability;

import org.springframework.stereotype.Component;

@Component
public class NormalShipping implements ShippingStrategy {

    public int calculateFee(int distance) {
        return distance * 5;
    }

    public String getType() {
        return "NORMAL";
    }
}

