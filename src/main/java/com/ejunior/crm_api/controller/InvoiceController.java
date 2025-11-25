package com.ejunior.crm_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejunior.crm_api.model.Invoice;
import com.ejunior.crm_api.service.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

  private final InvoiceService invoiceService;

  public InvoiceController(InvoiceService invoiceService) {
    this.invoiceService = invoiceService;
  }

  @PostMapping
  public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
    try {
      Invoice savedInvoice = invoiceService.save(invoice);
      return new ResponseEntity<>(savedInvoice, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      // Retorna um ResponseEntity com status BAD_REQUEST e sem corpo, evitando o erro
      // de nulidade.
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping
  public ResponseEntity<List<Invoice>> getAllInvoices() {
    List<Invoice> invoices = invoiceService.findAll();
    return new ResponseEntity<>(invoices, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
    Optional<Invoice> invoice = invoiceService.findById(id);
    return invoice.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoiceDetails) {
    try {
      Invoice updatedInvoice = invoiceService.update(id, invoiceDetails);
      return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
    Optional<Invoice> optionalInvoice = invoiceService.findById(id);
    if (optionalInvoice.isPresent()) {
      invoiceService.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

}
