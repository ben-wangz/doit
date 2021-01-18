package tech.geekcity.application.doit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tech.geekcity.application.doit.entity.Task;
import tech.geekcity.application.doit.entity.pojo.TaskPojo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface TaskRepository extends CrudRepository<TaskPojo, Long> {

    default List<Task> findTaskByTitle(String title) {
        return findByTitle(title).stream()
                .map(TaskPojo -> Task.Builder.newInstance().fromPojo(TaskPojo))
                .collect(Collectors.toList());
    }

    default List<Task> findAllTask(int limit) {
        return findAllBy()
                .limit(limit > 0 ? limit : Integer.MAX_VALUE)
                .map(TaskPojo -> Task.Builder.newInstance().fromPojo(TaskPojo))
                .collect(Collectors.toList());
    }

    default Task saveTask(Task task) {
        return Task.Builder.newInstance()
                .fromPojo(save(task.asPojo()));
    }

    List<TaskPojo> findByTitle(String title);

    Stream<TaskPojo> findAllBy();

    @Query(value = "select max(id) from TTask group by title", nativeQuery = true)
    Stream<TaskPojo> findLatestGroupByTitle(int limit);
}