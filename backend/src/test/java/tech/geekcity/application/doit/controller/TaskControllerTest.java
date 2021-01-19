package tech.geekcity.application.doit.controller;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.geekcity.application.doit.entity.Task;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootTest
public class TaskControllerTest {
    private List<Task> taskListAdded;
    @Autowired
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        List<Task> originTaskList = ImmutableList.of(
                Task.Builder.newInstance()
                        .title("title of task today")
                        .description("no description 01")
                        .timestampInMs(System.currentTimeMillis() - 24 * 3600 * 1000)
                        .unit(TimeUnit.DAYS)
                        .nextPeriod(1)
                        .build(),
                Task.Builder.newInstance()
                        .title("this is a title 02")
                        .description("no description 02")
                        .timestampInMs(System.currentTimeMillis())
                        .unit(TimeUnit.DAYS)
                        .nextPeriod(4)
                        .build()
        );
        taskListAdded = originTaskList.stream()
                .map(task -> (Task) taskController.add(
                        task.title(),
                        task.description(),
                        task.timestampInMs(),
                        task.unit().name(),
                        task.nextPeriod()
                ).data()).collect(Collectors.toList());
    }

    @Test
    public void all() {
        Assertions.assertEquals(
                taskListAdded,
                taskController.all(3).data());
        Assertions.assertEquals(
                taskListAdded.stream()
                        .filter(task -> StringUtils.equals("title of task today", task.title()))
                        .collect(Collectors.toList()),
                taskController.today(3).data());
    }
}