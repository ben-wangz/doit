package tech.geekcity.application.doit.service.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.geekcity.application.doit.service.impl.TaskManager;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Configuration
public class TaskManagerConfiguration {
    private static final String CLASS_PATH_SCHEMA = "classpath://";
    @Value("${doit.data.path:backend/data/username.data}")
    private String dataPath;

    @Bean
    public TaskManager transferService() throws IOException {
        TaskManager taskManager = TaskManager.Builder.newInstance()
                .dataFile(new File(parseDataPath(dataPath)))
                .build();
        taskManager.configure();
        return taskManager;
    }

    private String parseDataPath(String dataPath) {
        if (dataPath.startsWith(CLASS_PATH_SCHEMA)) {
            return Objects.requireNonNull(
                    getClass().getClassLoader().getResource(dataPath.substring(CLASS_PATH_SCHEMA.length()))
            ).getPath();
        }
        return dataPath;
    }
}