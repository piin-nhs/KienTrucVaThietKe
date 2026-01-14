package iuh.fit.publisher;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ConsoleInputRunner {
    @Bean
    public CommandLineRunner runConsole(MessageProducer producer) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Nhap tin nhan de Sang gui di: ");

            while (true) {
                String msg = scanner.nextLine();

                if ("Thoat".equalsIgnoreCase(msg)) {
                    System.out.println("Thoat roi!");
                    break;
                }

                producer.sendMessage(msg);
            }
        };
    }
}
