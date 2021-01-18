package tech.geekcity.application.doit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.geekcity.application.doit.entity.Task;
import tech.geekcity.application.doit.service.impl.TaskManager;

import java.util.List;

@Service
public class TaskManagerService {
    private final TaskManager taskManager;

    @Autowired
    public TaskManagerService(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public List<Task> taskList(int limit) {
        return taskManager.taskList(limit);
    }

    public List<Task> todayTaskList(int limit) {
        return taskManager.todayTaskList(limit);
    }

    public void add(Task task) {
        taskManager.add(task);
    }
}
