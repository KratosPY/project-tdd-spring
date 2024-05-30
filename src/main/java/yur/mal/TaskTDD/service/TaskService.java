package yur.mal.TaskTDD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yur.mal.TaskTDD.model.Task;
import yur.mal.TaskTDD.repository.TaskRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public void addTask(Task task){
        taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public Optional<Task> findTaskById(int id){
        Optional<Task> task = taskRepository.findById(id);
        return task;

    }

    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }
}
