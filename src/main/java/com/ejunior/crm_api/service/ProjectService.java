package com.ejunior.crm_api.service;

import java.util.List;
import java.util.Optional;

import com.ejunior.crm_api.model.Project;

public interface ProjectService {
  Project save(Project project);
  List<Project> findAll();
  Optional<Project> findById(Long id);
  void deleteById(Long id);
}
