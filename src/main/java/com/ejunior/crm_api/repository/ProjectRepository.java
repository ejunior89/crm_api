package com.ejunior.crm_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejunior.crm_api.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
