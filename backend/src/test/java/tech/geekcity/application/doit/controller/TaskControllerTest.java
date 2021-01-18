package tech.geekcity.application.doit.controller;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import tech.geekcity.application.doit.Application;
import tech.geekcity.application.doit.entity.Task;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(properties = {"doit.data.path=classpath://username.data"}, classes = Application.class)
public class TaskControllerTest {
    private Task todayTask;
    private List<Task> originTaskList;
    @Autowired
    private TaskController taskController;
    @Value("${doit.data.path}")
    private String dataPath;


    @BeforeEach
    void setUp() {
        todayTask = Task.Builder.newInstance()
                .title("this is a title 01")
                .description("no description 01")
                .timestampInMs(System.currentTimeMillis() - 24 * 3600 * 1000)
                .unit(TimeUnit.DAYS)
                .nextPeriod(1)
                .build();
        originTaskList = ImmutableList.of(
                todayTask,
                Task.Builder.newInstance()
                        .title("this is a title 02")
                        .description("no description 02")
                        .timestampInMs(System.currentTimeMillis())
                        .unit(TimeUnit.DAYS)
                        .nextPeriod(4)
                        .build()
        );
        originTaskList.forEach(task -> taskController.add(
                task.title(),
                task.description(),
                task.timestampInMs(),
                task.unit().name(),
                task.nextPeriod()
        ));
    }

    @Test
    public void all() {
        Assertions.assertEquals(
                originTaskList.stream()
                        .map(task -> task.toBuilder().toJsonSilently())
                        .collect(Collectors.toList()),
                taskController.all(3).data());
        Assertions.assertEquals(
                Stream.of(todayTask)
                        .map(task -> task.toBuilder().toJsonSilently())
                        .collect(Collectors.toList()),
                taskController.today(3).data());
    }
}