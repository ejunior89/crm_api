package com.ejunior.crm_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ejunior.crm_api.model.Project;
import com.ejunior.crm_api.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;

  public ProjectServiceImpl(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public Project save(Project project) {
    return projectRepository.save(project);
  }

  @Override
  public List<Project> findAll() {
    return projectRepository.findAll();
  }

  @Override
  public Optional<Project> findById(Long id) {
    return projectRepository.findById(id);
  }

  @Override
  public Project update(Long id, Project projectDetails) {
    return projectRepository.findById(id)
        .map(project -> {
          project.setName(projectDetails.getName());
          project.setDescription(projectDetails.getDescription());
          project.setStatus(projectDetails.getStatus());
          // Client is usually not updated here, but if needed, logic should be added
          return projectRepository.save(project);
        })
        .orElseThrow(() -> new RuntimeException("Project not found with id " + id));
  }

  @Override
  public void deleteById(Long id) {
    projectRepository.deleteById(id);
  }
}
