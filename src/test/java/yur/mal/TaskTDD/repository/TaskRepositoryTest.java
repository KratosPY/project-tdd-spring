package yur.mal.TaskTDD.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import yur.mal.TaskTDD.model.Task;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testFindTaskById() {
        Task task = new Task(1, "Task1", "Description of Task 1", false);
        taskRepository.save(task);

        Optional<Task> foundTask = taskRepository.findTaskById(1);

        assertTrue(foundTask.isPresent());
        assertEquals(task, foundTask.get());
    }
}

