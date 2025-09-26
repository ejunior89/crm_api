package com.ejunior.crm_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
@Table(name = "projects")
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotBlank(message = "Project name is required")
  @Size(min = 2, max = 150, message = "Project name must be between 2 and 150 characters")
  @Column(nullable = false)
  private String name;
  
  @Size(max = 500, message = "Description must not exceed 500 characters")
  private String description;

  @NotNull(message = "Project status is required")
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProjectStatus status;

  @NotNull(message = "Client is required")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;
}
