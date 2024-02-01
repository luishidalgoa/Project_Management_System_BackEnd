package com.project.atmiraFCT.controller;

import com.project.atmiraFCT.exception.RecordNotFoundException;
import com.project.atmiraFCT.model.domain.Colaborator;
import com.project.atmiraFCT.model.domain.Project;
import com.project.atmiraFCT.model.domain.Task;
import com.project.atmiraFCT.repository.ColaboratorRepository;
import com.project.atmiraFCT.repository.ProjectRepository;
import com.project.atmiraFCT.repository.TaskRepository;
import com.project.atmiraFCT.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private ColaboratorRepository colaboratorRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/task/all")
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }


    /*@PostMapping("/task/save/{colaboratorId}/{projectId}/task")
    public ResponseEntity<Task> createProjectWithExistingProjectColaborator(
            @PathVariable String colaboratorId,
            @PathVariable Long projectId,
            @RequestBody Task tasks
    ) {
        Task createdTask = taskService.saveTaskexistingProyectColaborator(
                task.getDescription(),
                task.getObjective(),
                task.getClosed(),
                task.getTask(),
                colaboratorId,
                projectId
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }*/


    @GetMapping("task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }
    @DeleteMapping("taskDelete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok("Task with ID " + id + " has been deleted.");
        } catch (RecordNotFoundException e) {
            throw new RecordNotFoundException("No task found with id: " + id);
        }
    }
   /* @PutMapping("/task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task updatedTask) {
        Task updated = taskService.createOrUpdateTask(updatedTask);
        return ResponseEntity.ok(updated);
    }*/

}
