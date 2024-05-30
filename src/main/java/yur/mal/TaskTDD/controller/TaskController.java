package yur.mal.TaskTDD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yur.mal.TaskTDD.model.Task;
import yur.mal.TaskTDD.service.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    @Autowired
    private TaskService service;

    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return service.getAllTasks();
    }
    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable int id){
        return service.findTaskById(id);
    }
    @PutMapping("/add")
    public void addTask(@RequestBody Task task){
        service.addTask(task);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteTaskById(@PathVariable int id){
        service.deleteById(id);
    }

}
