package iuh.fit.lab1redismq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {
    @Autowired
    private SimpMessagingTemplate template;

    public void receiveMessage(String message) {
        // 1. In ra console để kiểm tra
        System.out.println("📩 Nhận từ Redis: " + message);

        // 2. Bắn tin nhắn tới tất cả những ai đang nghe ở kênh "/topic/chat"
        template.convertAndSend("/topic/chat", message);
    }
}
