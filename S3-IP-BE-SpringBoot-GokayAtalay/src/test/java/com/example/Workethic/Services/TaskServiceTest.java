package com.example.Workethic.Services;

import com.example.Workethic.Models.Task;
import com.example.Workethic.Models.TaskPriority;
import com.example.Workethic.Repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Test
    void createTask() {
        Task task = new Task("bzbz", "some more bzbz", "", "", "",em.getReference(TaskPriority.class,0l));
        Task returnedTask = taskService.createTask(task);

        assert(returnedTask).equals(task);
    }

    @Test
    void getAllTasks() {
        Task task = new Task("gang", "some more gang", "", "", "",em.getReference(TaskPriority.class,0l));
        Task task2 = new Task("gang2", "some more bzbz2", "", "", "",em.getReference(TaskPriority.class,0l));
        taskService.createTask(task);
        taskService.createTask(task2);
        List<Task> allTasks = taskService.getAllTasks();
        int count = allTasks.size();

        Assertions.assertEquals(count, 2);
    }

    @Test
    void getTaskById() {
        Task task = new Task("gang", "some more gang", "", "", "",em.getReference(TaskPriority.class,0l));
        taskService.createTask(task);

        Optional<Task> retrievedTask = taskService.getTaskById(1);
        Optional<Task> retrievedTaskWrong = taskService.getTaskById(5);

        Assertions.assertFalse(retrievedTask.isEmpty());
        Assertions.assertTrue(retrievedTaskWrong.isEmpty());
    }
}