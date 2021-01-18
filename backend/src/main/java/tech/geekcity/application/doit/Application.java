package tech.geekcity.application.doit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    @Value("${doit.data.path:backend/data}")
    private String dataPath;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("running with arguments: {}", String.join(" ", args));
        showTasks();
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }

    private void showTasks() throws IOException {
        System.out.println(System.getProperty("user.dir"));
        File dataFile = new File(String.format("%s/ben.wangz.data", dataPath));
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
