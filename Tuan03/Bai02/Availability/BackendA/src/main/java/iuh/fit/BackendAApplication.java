package iuh.fit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BackendAApplication {

    @GetMapping("/")
    public String home() {
        return "Hello from BACKEND A";
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendAApplication.class, args);
    }
}

