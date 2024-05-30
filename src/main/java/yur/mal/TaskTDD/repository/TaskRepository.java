package yur.mal.TaskTDD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yur.mal.TaskTDD.model.Task;
import yur.mal.TaskTDD.service.TaskService;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findTaskById(int id);
}
