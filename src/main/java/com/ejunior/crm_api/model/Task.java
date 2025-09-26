package com.ejunior.crm_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotBlank(message = "Task title is required")
  @Size(min = 2, max = 200, message = "Task title must be between 2 and 200 characters")
  @Column(nullable = false)
  private String title;
  
  @Size(max = 1000, message = "Description must not exceed 1000 characters")
  private String description;

  @NotNull(message = "Task status is required")
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TaskStatus status;

  @NotNull(message = "Project is required")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;
}
