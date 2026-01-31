package iuh.fit.technicalpartitioning.service;


import iuh.fit.technicalpartitioning.dto.LoginRequest;
import iuh.fit.technicalpartitioning.dto.RegisterRequest;
import iuh.fit.technicalpartitioning.entity.User;
import iuh.fit.technicalpartitioning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username đã tồn tại";
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email đã tồn tại";
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return "Đăng ký thành công";
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElse(null);

        if (user == null) {
            return "Sai username";
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return "Sai mật khẩu";
        }

        return "Đăng nhập thành công";
    }
}

