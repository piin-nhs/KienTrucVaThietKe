package iuh.fit.domaindecomposition.login;


import iuh.fit.domaindecomposition.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase {

    private final UserRepository userRepository;

    public LoginResponse execute(LoginRequest request) {

        return userRepository.findByUsername(request.getUsername())
                .filter(user -> user.getPassword().equals(request.getPassword()))
                .map(user -> new LoginResponse(true, "Login success"))
                .orElse(new LoginResponse(false, "Invalid username or password"));
    }
}

