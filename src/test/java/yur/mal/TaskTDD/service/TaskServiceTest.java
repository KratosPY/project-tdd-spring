package yur.mal.TaskTDD.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import yur.mal.TaskTDD.TaskTddApplication;
import yur.mal.TaskTDD.service.TaskService;
import yur.mal.TaskTDD.repository.TaskRepository;
import yur.mal.TaskTDD.model.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TaskTddApplication.class)
class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    void testAddTask() {
        Task task = new Task(1, "Task1", "description of task 1", false);
        taskService.addTask(task);
        verify(taskRepository).save(task);
    }

    @Test
    void getAllTask() {
        Task task1 = new Task(1, "Task1", "description of task 1", false);
        Task task2 = new Task(2, "Task2", "description of task 2", true);
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task2));
        verify(taskRepository, times(1)).findAll();

    }

    @Test
    void findByTaskId() {
        Task task1 = new Task(1, "Task1", "description of task 1", false);
        when(taskService.findTaskById(task1.getId())).thenReturn(Optional.of(task1));
        Optional<Task> foundTask = taskService.findTaskById(1);
        assertTrue(foundTask.isPresent());
        assertEquals(task1, foundTask.get());
        verify(taskRepository, times(1)).findById(1);
    }

    @Test
    void deleteTaskById() {
        Task task1 = new Task(1, "Task1", "description of task 1", false);
        taskService.deleteById(task1.getId());
        verify(taskRepository, times(1)).deleteById(task1.getId());
    }
}