package iuh.fit.lab1redismq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @Autowired
    private StringRedisTemplate template;

    // Gọi link: localhost:8080/send?msg=Hello
    @GetMapping("/send")
    public String sendMessage(@RequestParam String msg) {
        template.convertAndSend("chat_phong_dev", msg);
        return "Đã gửi tin: " + msg;
    }
}
