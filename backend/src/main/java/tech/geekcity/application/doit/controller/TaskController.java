package tech.geekcity.application.doit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.geekcity.application.doit.entity.Task;
import tech.geekcity.application.doit.service.TaskManagerService;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TaskManagerService taskManagerService;

    @Autowired
    public TaskController(TaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }

    @RequestMapping("/all")
    public Response all(@RequestParam("limit") int limit) {
        List<Task> taskList = taskManagerService.taskList(limit);
        return Response.Builder.newInstance()
                .data(taskList)
                .build();
    }

    @RequestMapping("/today")
    public Response today(@RequestParam("limit") int limit) {
        List<Task> taskList = taskManagerService.todayTaskList(limit);
        return Response.Builder.newInstance()
                .data(taskList)
                .build();
    }

    @RequestMapping("/add")
    public Response add(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam(name = "timestampInMs", defaultValue = "") long timestampInMs,
            @RequestParam(name = "unit", defaultValue = "DAYS") String unit,
            @RequestParam(name = "period", defaultValue = "1") int period
    ) {
        Task taskAdded = taskManagerService.add(Task.Builder.newInstance()
                .title(title)
                .description(description)
                .timestampInMs(timestampInMs)
                .unit(TimeUnit.valueOf(unit))
                .nextPeriod(period)
                .build());
        return Response.Builder.newInstance()
                .data(taskAdded)
                .build();
    }
}
