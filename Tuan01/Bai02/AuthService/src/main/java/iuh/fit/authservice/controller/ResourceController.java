package iuh.fit.authservice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ResourceController {
    @GetMapping("/profile")
    public Map<String, Object> getProfile(Authentication authentication) {
        return Map.of(
                "message", "Trang profile nay da duoc bao mat",
                "username", authentication.getName(),
                "authorities", authentication.getAuthorities()
        );
    }
}
