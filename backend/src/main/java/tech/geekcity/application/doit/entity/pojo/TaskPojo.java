package tech.geekcity.application.doit.entity.pojo;


import javax.persistence.*;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "t_task")
public class TaskPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private long timestampInMs;
    private TimeUnit unit;
    private int nextPeriod;

    protected TaskPojo() {
    }

    public TaskPojo(
            Long id,
            String title,
            String description,
            long timestampInMs,
            TimeUnit unit,
            int nextPeriod) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.timestampInMs = timestampInMs;
        this.unit = unit;
        this.nextPeriod = nextPeriod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimestampInMs() {
        return timestampInMs;
    }

    public void setTimestampInMs(long timestampInMs) {
        this.timestampInMs = timestampInMs;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }

    public int getNextPeriod() {
        return nextPeriod;
    }

    public void setNextPeriod(int nextPeriod) {
        this.nextPeriod = nextPeriod;
    }
}