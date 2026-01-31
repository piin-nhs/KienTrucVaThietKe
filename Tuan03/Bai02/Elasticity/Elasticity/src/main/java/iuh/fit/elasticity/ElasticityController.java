package iuh.fit.elasticity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElasticityController {

    private final AutoScalingService service;

    public ElasticityController(AutoScalingService service) {
        this.service = service;
    }

    @GetMapping("/elastic")
    public String elasticDemo() {
        return service.handleRequest();
    }
}
