package com.ejunior.crm_api.service;

import java.util.List;
import java.util.Optional;

import com.ejunior.crm_api.model.Task;

public interface TaskService {
  Task save(Task task);
  List<Task> findAll();
  Optional<Task> findById(Long id);
  void deleteById(Long id);
}
