package iuh.fit.scalability;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        Thread.sleep(500);
        return "Hello from instance running on port " + port;
    }
}

