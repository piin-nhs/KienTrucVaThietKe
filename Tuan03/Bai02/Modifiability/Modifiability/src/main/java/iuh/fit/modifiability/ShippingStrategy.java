package iuh.fit.modifiability;

public interface ShippingStrategy {
    int calculateFee(int distance);
    String getType();
}
