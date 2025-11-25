package com.ejunior.crm_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ejunior.crm_api.model.Project;
import com.ejunior.crm_api.model.Task;
import com.ejunior.crm_api.repository.ProjectRepository;
import com.ejunior.crm_api.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Task save(Task task) {
        // Garante que o projeto associado exista antes de salvar a tarefa.
        if (task.getProject() != null && task.getProject().getId() != null) {
            Optional<Project> project = projectRepository.findById(task.getProject().getId());
            if (project.isPresent()) {
                task.setProject(project.get());
                return taskRepository.save(task);
            }
        }
        throw new IllegalArgumentException("Project not found. Cannot save task without a valid project.");
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task update(Long id, Task taskDetails) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(taskDetails.getTitle());
                    task.setDescription(taskDetails.getDescription());
                    task.setStatus(taskDetails.getStatus());

                    if (taskDetails.getProject() != null && taskDetails.getProject().getId() != null) {
                        Optional<Project> project = projectRepository.findById(taskDetails.getProject().getId());
                        if (project.isPresent()) {
                            task.setProject(project.get());
                        } else {
                            throw new IllegalArgumentException("Project not found");
                        }
                    }
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
