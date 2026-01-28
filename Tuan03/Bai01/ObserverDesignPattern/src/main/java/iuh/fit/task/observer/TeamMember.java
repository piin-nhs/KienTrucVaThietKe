package iuh.fit.task.observer;

import org.springframework.stereotype.Component;

@Component
public class TeamMember implements TaskObserver {

    private final String name = "Dev Member";

    @Override
    public void update(String taskName, String status) {
        System.out.println(name +" được thông báo: Task '" + taskName + "' → " + status);
    }
}
