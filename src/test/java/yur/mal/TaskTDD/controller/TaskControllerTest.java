package yur.mal.TaskTDD.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import yur.mal.TaskTDD.model.Task;
import yur.mal.TaskTDD.service.TaskService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {
    @Mock
    private TaskService service;

    @InjectMocks
    private TaskController controller;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    void getAllTasks() throws Exception {

        when(service.getAllTasks()).thenReturn(List.of(new Task(1, "Task1", "description of task 1", false),
                new Task(2, "Task2", "description of task 2", true)));

        mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Task1"))
                .andExpect(jsonPath("$[0].description").value("description of task 1"))
                .andExpect(jsonPath("$[0].isSolved").value(false))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Task2"))
                .andExpect(jsonPath("$[1].description").value("description of task 2"))
                .andExpect(jsonPath("$[1].isSolved").value(true));}

    @Test
    void findTaskById() throws Exception {
        Task task1 = new Task(1, "Task1", "description of task 1", false);
        when(service.findTaskById(1)).thenReturn(Optional.of(task1));

        mockMvc.perform(get("/api/v1/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Task1"))
                .andExpect(jsonPath("$.description").value("description of task 1"))
                .andExpect(jsonPath("$.isSolved").value(false));
    }
    @Test
    void addTask() throws Exception {
        Task task1 = new Task(1, "Task1", "description of task 1", false);


        controller.addTask(task1);
        verify(service, times(1)).addTask(task1);


    }

    @Test
    void deleteTaskById() {
        Task task1 = new Task(1, "Task1", "description of task 1", false);
        controller.deleteTaskById(task1.getId());
        verify(service, times(1)).deleteById(task1.getId());
    }
}