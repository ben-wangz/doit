package tech.geekcity.application.doit.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sun.istack.Nullable;
import org.inferred.freebuilder.FreeBuilder;
import tech.geekcity.application.doit.entity.pojo.WordPojo;

import java.io.IOException;

@FreeBuilder
@JsonDeserialize(builder = Word.Builder.class)
public abstract class Word {
    /**
     * Returns a new {@link Builder} with the same property values as this {@link Word}
     */
    public abstract Builder toBuilder();

    /**
     * Builder of {@link Word} instances
     * auto generated builder className which cannot be modified
     */
    public static class Builder extends Word_Builder {
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

        public Word parseFromJson(String json) throws IOException {
            return objectMapper.readValue(json, Word.class);
        }

        public Word fromPojo(WordPojo wordPojo) {
            return this.id(wordPojo.getId())
                    .word(wordPojo.getWord())
                    .description(wordPojo.getDescription())
                    .build();
        }
    }

    @Nullable
    public abstract Long id();

    public abstract String word();

    public abstract String description();

    public WordPojo asPojo() {
        return new WordPojo(
                id(),
                word(),
                description()
        );
    }
}
