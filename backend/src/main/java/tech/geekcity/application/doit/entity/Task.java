package tech.geekcity.application.doit.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.inferred.freebuilder.FreeBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@FreeBuilder
@JsonDeserialize(builder = Task.Builder.class)
public interface Task {
    /**
     * Returns a new {@link Builder} with the same property values as this {@link Task}
     */
    Builder toBuilder();

    /**
     * Builder of {@link Task} instances
     * auto generated builder className which cannot be modified
     */
    class Builder extends Task_Builder {
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
    }

    String title();

    String description();

    long timestampInMs();

    TimeUnit unit();

    int nextPeriod();
}
