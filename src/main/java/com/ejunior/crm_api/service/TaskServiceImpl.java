package com.ejunior.crm_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejunior.crm_api.model.Project;
import com.ejunior.crm_api.model.Task;
import com.ejunior.crm_api.repository.ProjectRepository;
import com.ejunior.crm_api.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private ProjectRepository projectRepository;


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
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
