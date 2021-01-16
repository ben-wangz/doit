package tech.geekcity.application.doit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TaskManagerTest {
    @Test
    public void testTodayTaskList() throws IOException {
        File dataFile = File.createTempFile("ben.wangz.", ".data");
        dataFile.deleteOnExit();
        TaskManager taskManager = TaskManager.Builder.newInstance()
                .dataFile(dataFile)
                .build();
        taskManager.configure();
        taskManager.add(Task.Builder.newInstance()
                .title("this is a title")
                .description("no description")
                .timestampInMs(System.currentTimeMillis() - 24 * 3600 * 1000)
                .unit(TimeUnit.DAYS)
                .nextPeriod(1)
                .build());
        List<Task> todayTaskList = taskManager.todayTaskList();
        todayTaskList.forEach(task -> System.out.println(task.toBuilder().toJsonSilently()));
        Assert.assertEquals(1, todayTaskList.size());
        Assert.assertEquals(2, todayTaskList.get(0).nextPeriod());
        taskManager.flush();
        System.out.println(FileUtils.readFileToString(dataFile));
    }
}
