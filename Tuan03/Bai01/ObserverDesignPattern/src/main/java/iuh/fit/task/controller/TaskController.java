package iuh.fit.task.controller;

import iuh.fit.task.observer.TeamMember;
import iuh.fit.task.subject.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private final Task task;

    public TaskController(Task task, TeamMember teamMember) {
        this.task = task;
        task.register(teamMember);
    }

    @GetMapping("/task/update")
    public String updateTask(
            @RequestParam String taskName,
            @RequestParam String status) {

        task.updateStatus(taskName, status);
        return "Task '" + taskName + "' updated to status: " + status;
    }

}
