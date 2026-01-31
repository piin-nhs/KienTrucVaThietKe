package iuh.fit.modifiability;

import org.springframework.stereotype.Component;

@Component
public class InternationalShipping implements ShippingStrategy {

    public int calculateFee(int distance) {
        return distance * 20;
    }

    public String getType() {
        return "INTERNATIONAL";
    }
}

