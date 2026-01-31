package iuh.fit.performance;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Cacheable("products")
    public String fetchProducts() {
        String url = "https://fakestoreapi.com/products";
        return restTemplate.getForObject(url, String.class);
    }
}

