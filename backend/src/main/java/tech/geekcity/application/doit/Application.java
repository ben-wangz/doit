package tech.geekcity.application.doit;

import java.io.File;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        File dataFile = new File("backend/data/ben.wangz.data");
        TaskManager taskManager = TaskManager.Builder.newInstance()
                .dataFile(dataFile)
                .build();
        taskManager.configure();
        System.out.println("***************************");
        taskManager.todayTaskList()
                .forEach(task -> System.out.println(task.toBuilder().toJsonSilently()));
        System.out.println("***************************");
        taskManager.flush();
    }
}
