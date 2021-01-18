package tech.geekcity.application.doit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("running with arguments: {}", String.join(" ", args));
        showTasks();
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }

    private void showTasks() throws IOException {
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
