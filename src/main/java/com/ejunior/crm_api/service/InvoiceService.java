package com.ejunior.crm_api.service;

import java.util.List;
import java.util.Optional;

import com.ejunior.crm_api.model.Invoice;

public interface InvoiceService {
  Invoice save(Invoice invoice);
  List<Invoice> findAll();
  Optional<Invoice> findById(Long id);
  void deleteById(Long id);
}
