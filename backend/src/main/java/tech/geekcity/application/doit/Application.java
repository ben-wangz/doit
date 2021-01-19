package tech.geekcity.application.doit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.geekcity.application.doit.entity.Task;
import tech.geekcity.application.doit.service.TaskManagerService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private final TaskManagerService taskManagerService;

    @Autowired
    public Application(TaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }

    @Override
    public void run(String... args) {
        LOGGER.info("running with arguments: {}", String.join(" ", args));
        Stream.of(
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
        ).forEach(taskManagerService::add);
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }
}
