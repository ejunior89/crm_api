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
  public void deleteById(Long id) {
    projectRepository.deleteById(id);
  }
}
