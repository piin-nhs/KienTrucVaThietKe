package iuh.fit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMaleRepository maleRepository;

    @Autowired
    private UserFemaleRepository femaleRepository;

    public String processAndSaveUser(UserRequest request) {
        String condition = request.getGender().toUpperCase();

        if ("NAM".equals(condition)) {
            // Rẽ nhánh 1: Lưu vào table_user_01
            UserMale male = new UserMale();
            male.setUsername(request.getUsername());
            maleRepository.save(male);
            return "Thành công: Đã lưu user [" + request.getUsername() + "] vào table_user_01 (NAM)";

        } else if ("NU".equals(condition)) {
            // Rẽ nhánh 2: Lưu vào table_user_02
            UserFemale female = new UserFemale();
            female.setUsername(request.getUsername());
            femaleRepository.save(female);
            return "Thành công: Đã lưu user [" + request.getUsername() + "] vào table_user_02 (NỮ)";

        } else {
            throw new IllegalArgumentException("Thất bại: Giới tính không hợp lệ. Chỉ nhận 'NAM' hoặc 'NU'.");
        }
    }
}