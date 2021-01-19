package tech.geekcity.application.doit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tech.geekcity.application.doit.entity.Task;
import tech.geekcity.application.doit.entity.pojo.TaskPojo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface TaskRepository extends CrudRepository<TaskPojo, Long> {

    default List<Task> findTaskByTitle(String title) {
        return findByTitle(title).stream()
                .map(taskPojo -> Task.Builder.newInstance().fromPojo(taskPojo))
                .collect(Collectors.toList());
    }

    @Transactional
    default List<Task> findAllTask(int limit) {
        return findAllBy()
                .limit(limit > 0 ? limit : Integer.MAX_VALUE)
                .map(taskPojo -> Task.Builder.newInstance().fromPojo(taskPojo))
                .collect(Collectors.toList());
    }

    default Task saveTask(Task task) {
        return Task.Builder.newInstance()
                .fromPojo(save(task.asPojo()));
    }

    @Transactional
    default List<Task> findLatestTaskGroupByTitle(int limit) {
        return findLatestGroupByTitle(limit)
                .map(taskPojo -> Task.Builder.newInstance().fromPojo(taskPojo))
                .collect(Collectors.toList());
    }

    List<TaskPojo> findByTitle(String title);

    Stream<TaskPojo> findAllBy();

    @Query(value = "select * from t_task t_left " +
            "    inner join (" +
            "        select max(id) as id from t_task group by title" +
            "    ) t_right " +
            "    on t_left.id = t_right.id",
            nativeQuery = true)
    Stream<TaskPojo> findLatestGroupByTitle(int limit);
}