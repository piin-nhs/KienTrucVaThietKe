package iuh.fit.domaindecomposition.register;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterUseCase registerUseCase;

    @PostMapping
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return registerUseCase.execute(request);
    }
}

