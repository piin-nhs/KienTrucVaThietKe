package iuh.fit.modifiability;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShippingService {

    private final Map<String, ShippingStrategy> strategies;

    public ShippingService(List<ShippingStrategy> list) {
        strategies = list.stream()
                .collect(Collectors.toMap(
                        ShippingStrategy::getType,
                        s -> s
                ));
    }

    public int calculate(String type, int distance) {
        return strategies.get(type).calculateFee(distance);
    }
}
