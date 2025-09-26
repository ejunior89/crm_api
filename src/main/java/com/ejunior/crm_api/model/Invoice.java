package com.ejunior.crm_api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "invoices")
public class Invoice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Amount is required")
  @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
  @DecimalMax(value = "999999.99", message = "Amount must be less than 1,000,000")
  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal amount;
  
  @NotNull(message = "Due date is required")
  @FutureOrPresent(message = "Due date must be today or in the future")
  @Column(nullable = false)
  private LocalDate dueDate;

  @NotNull(message = "Invoice status is required")
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private InvoiceStatus status;

  @NotNull(message = "Client is required")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;
}
