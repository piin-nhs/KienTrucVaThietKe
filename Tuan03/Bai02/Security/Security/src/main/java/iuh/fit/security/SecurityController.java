package iuh.fit.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/public/hello")
    public String publicApi() {
        return "Public API - OK";
    }

    @GetMapping("/secure/data")
    public String secureApi() {
        return "Secure Data - Authorized";
    }
}
