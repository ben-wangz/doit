package tech.geekcity.application.doit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.io.FileUtils;
import org.inferred.freebuilder.FreeBuilder;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.stream.Collectors;

@FreeBuilder
@JsonDeserialize(builder = TaskManager.Builder.class)
public abstract class TaskManager {
    private transient List<Task> pool;

    /**
     * Returns a new {@link Builder} with the same property values as this {@link TaskManager}
     */
    public abstract Builder toBuilder();

    /**
     * Builder of {@link TaskManager} instances
     * auto generated builder className which cannot be modified
     */
    public static class Builder extends TaskManager_Builder {
        private final ObjectMapper objectMapper = new ObjectMapper();

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder() {
        }

        public String toJson() throws JsonProcessingException {
            return objectMapper.writeValueAsString(build());
        }

        public String toJsonSilently() {
            try {
                return objectMapper.writeValueAsString(build());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        public TaskManager parseFromJson(String json) throws IOException {
            return objectMapper.readValue(json, TaskManager.class);
        }
    }

    public abstract File dataFile();

    public void configure() throws IOException {
        pool = FileUtils.readLines(dataFile())
                .stream()
                .map(jsonLine -> {
                    try {
                        return Task.Builder.newInstance().parseFromJson(jsonLine);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }).collect(Collectors.toList());
    }

    public void flush() throws IOException {
        FileUtils.writeStringToFile(
                dataFile(), pool.stream()
                        .map(task -> task.toBuilder().toJsonSilently())
                        .collect(Collectors.joining("\n")));
    }

    public void add(Task task) {
        pool.add(task);
    }
}
