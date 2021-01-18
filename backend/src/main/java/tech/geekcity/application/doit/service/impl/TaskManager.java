package tech.geekcity.application.doit.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.inferred.freebuilder.FreeBuilder;
import tech.geekcity.application.doit.entity.Task;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
                .filter(StringUtils::isNotBlank)
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
                        .sorted(Comparator.comparing(Task::title)
                                .thenComparingLong(Task::timestampInMs))
                        .map(task -> task.toBuilder().toJsonSilently())
                        .collect(Collectors.joining("\n")));
    }

    public void add(Task task) {
        pool.add(task);
    }

    public List<Task> taskList(int limit) {
        return pool.stream().limit(limit > 0 ? limit : Integer.MAX_VALUE).collect(Collectors.toList());
    }

    public List<Task> todayTaskList(int limit) {
        return pool.stream()
                .collect(Collectors.toMap(
                        Task::title,
                        task -> task,
                        (oldValue, newValue) -> {
                            if (oldValue.timestampInMs() < newValue.timestampInMs())
                                return newValue;
                            else {
                                return oldValue;
                            }
                        }
                )).values()
                .stream()
                .filter(this::shouldBeDoneNow)
                .limit(limit > 0 ? limit : Integer.MAX_VALUE)
                .collect(Collectors.toList());
    }

    private boolean shouldBeDoneNow(Task task) {
        long now = System.currentTimeMillis();
        return task.nextPeriod() == task.unit()
                .convert(now - task.timestampInMs(),
                        TimeUnit.MILLISECONDS);
    }
}
