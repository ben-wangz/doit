package tech.geekcity.application.doit.entity.pojo;


import javax.persistence.*;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "t_word")
public class WordPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String word;
    private String description;

    protected WordPojo() {
    }

    public WordPojo(Long id, String word, String description) {
        this.id = id;
        this.word = word;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}