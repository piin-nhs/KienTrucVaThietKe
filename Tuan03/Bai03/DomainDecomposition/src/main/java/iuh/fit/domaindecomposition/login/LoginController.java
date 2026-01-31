package iuh.fit.domaindecomposition.login;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginUseCase loginUseCase;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request) {
        return loginUseCase.execute(request);
    }
}
