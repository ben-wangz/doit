package tech.geekcity.application.doit.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sun.istack.Nullable;
import org.inferred.freebuilder.FreeBuilder;
import tech.geekcity.application.doit.entity.pojo.TaskPojo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@FreeBuilder
@JsonDeserialize(builder = Task.Builder.class)
public abstract class Task {
    /**
     * Returns a new {@link Builder} with the same property values as this {@link Task}
     */
    public abstract Builder toBuilder();

    /**
     * Builder of {@link Task} instances
     * auto generated builder className which cannot be modified
     */
    public static class Builder extends Task_Builder {
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

        public Task parseFromJson(String json) throws IOException {
            return objectMapper.readValue(json, Task.class);
        }

        public Task fromPojo(TaskPojo TaskPojo) {
            return this.id(TaskPojo.getId())
                    .title(TaskPojo.getTitle())
                    .description(TaskPojo.getDescription())
                    .timestampInMs(TaskPojo.getTimestampInMs())
                    .unit(TaskPojo.getUnit())
                    .nextPeriod(TaskPojo.getNextPeriod())
                    .build();
        }
    }

    @Nullable
    public abstract Long id();

    public abstract String title();

    public abstract String description();

    public abstract long timestampInMs();

    public abstract TimeUnit unit();

    public abstract int nextPeriod();

    public TaskPojo asPojo() {
        return new TaskPojo(
                id(),
                title(),
                description(),
                timestampInMs(),
                unit(),
                nextPeriod()
        );
    }
}
