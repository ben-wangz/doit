package tech.geekcity.application.doit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.geekcity.application.doit.entity.Task;
import tech.geekcity.application.doit.repository.TaskRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class TaskManagerService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskManagerService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> taskList(int limit) {
        return taskRepository.findAllTask(limit);
    }

    public List<Task> todayTaskList(int limit) {
        return taskRepository.findLatestTaskGroupByTitle(limit)
                .stream()
                .filter(this::shouldBeDoneNow)
                .limit(limit > 0 ? limit : Integer.MAX_VALUE)
                .collect(Collectors.toList());
    }

    public Task add(Task task) {
        return taskRepository.saveTask(task);
    }

    private boolean shouldBeDoneNow(Task task) {
        long now = System.currentTimeMillis();
        return task.nextPeriod() == task.unit()
                .convert(now - task.timestampInMs(),
                        TimeUnit.MILLISECONDS);
    }
}
