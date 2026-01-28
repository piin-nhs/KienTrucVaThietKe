package iuh.fit.task.subject;

import iuh.fit.task.observer.TaskObserver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Task {

    private String taskName;
    private String status;

    private final List<TaskObserver> observers = new ArrayList<>();

    public void register(TaskObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        observers.forEach(o -> o.update(taskName, status));
    }

    public void updateStatus(String taskName, String status) {
        this.taskName = taskName;
        this.status = status;
        notifyObservers();
    }
}
