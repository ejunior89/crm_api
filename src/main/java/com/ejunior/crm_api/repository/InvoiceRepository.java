package com.ejunior.crm_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejunior.crm_api.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
