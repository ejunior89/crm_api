package com.ejunior.crm_api.controller;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejunior.crm_api.model.Project;
import com.ejunior.crm_api.model.Task;
import com.ejunior.crm_api.service.ProjectService;
import com.ejunior.crm_api.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @Autowired
  private ProjectService projectService;

  @PostMapping
  public ResponseEntity<Task> createTask(@RequestBody Task task) {
    try {
      Task savedTask = taskService.save(task);
      return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping
  public ResponseEntity<List<Task>> getAllTasks() {
    List<Task> tasks = taskService.findAll();
    return new ResponseEntity<>(tasks, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
    Optional<Task> task = taskService.findById(id);
    return task.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
    Optional<Task> optionalTask = taskService.findById(id);
    if (optionalTask.isPresent()) {
      Task task = optionalTask.get();
      task.setTitle(taskDetails.getTitle());
      task.setDescription(taskDetails.getDescription());
      task.setStatus(taskDetails.getStatus());

      // Valida o projeto, caso tenha sido enviado no corpo
      if (taskDetails.getProject() != null && taskDetails.getProject().getId() != null) {
        Optional<Project> project = projectService.findById(taskDetails.getProject().getId());
        if (project.isPresent()) {
          task.setProject(project.get());
        } else {
          return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
      }

      Task updatedTask = taskService.save(task);
      return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
    Optional<Task> optionalTask = taskService.findById(id);
    if (optionalTask.isPresent()) {
      taskService.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
