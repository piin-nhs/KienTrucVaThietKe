package iuh.fit.performance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/cache")
    public String getProducts() {
        long start = System.currentTimeMillis();

        String result = productService.fetchProducts();

        long end = System.currentTimeMillis();
        System.out.println("CACHE TIME = " + (end - start) + " ms");

        return result;
    }
}
