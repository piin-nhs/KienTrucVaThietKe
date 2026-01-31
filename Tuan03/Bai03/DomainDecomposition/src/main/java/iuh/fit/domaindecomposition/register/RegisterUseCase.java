package iuh.fit.domaindecomposition.register;

import iuh.fit.domaindecomposition.user.User;
import iuh.fit.domaindecomposition.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUseCase {

    private final UserRepository userRepository;

    public RegisterResponse execute(RegisterRequest request) {

        User user = new User(
                null,
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
        userRepository.save(user);


        return new RegisterResponse("Register success");
    }
}


