package iuh.fit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class LoadBalancerApplication {

    private final String[] servers = {
            "http://localhost:8081",
            "http://localhost:8083"
    };

    private int current = 0;

    @GetMapping("/")
    public String balance() {
        RestTemplate restTemplate = new RestTemplate();
        String server = servers[current];
        current = (current + 1) % servers.length;

        try {
            return restTemplate.getForObject(server, String.class);
        } catch (Exception e) {
            return "Server unavailable, please retry...";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(LoadBalancerApplication.class, args);
    }
}
