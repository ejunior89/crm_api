package com.ejunior.crm_api.service;

import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ejunior.crm_api.model.Client;
import com.ejunior.crm_api.model.Invoice;
import com.ejunior.crm_api.repository.ClientRepository;
import com.ejunior.crm_api.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, ClientRepository clientRepository) {
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Invoice save(Invoice invoice) {
        // Garante que o cliente associado exista antes de salvar a fatura.
        if (invoice.getClient() != null && invoice.getClient().getId() != null) {
            Optional<Client> client = clientRepository.findById(invoice.getClient().getId());
            if (client.isPresent()) {
                invoice.setClient(client.get());
                return invoiceRepository.save(invoice);
            }
        }
        throw new IllegalArgumentException("Client not found. Cannot save invoice without a valid client.");
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public Invoice update(Long id, Invoice invoiceDetails) {
        return invoiceRepository.findById(id)
                .map(invoice -> {
                    invoice.setAmount(invoiceDetails.getAmount());
                    invoice.setDueDate(invoiceDetails.getDueDate());
                    invoice.setStatus(invoiceDetails.getStatus());

                    if (invoiceDetails.getClient() != null && invoiceDetails.getClient().getId() != null) {
                        Optional<Client> client = clientRepository.findById(invoiceDetails.getClient().getId());
                        if (client.isPresent()) {
                            invoice.setClient(client.get());
                        } else {
                            throw new IllegalArgumentException("Client not found");
                        }
                    }
                    return invoiceRepository.save(invoice);
                })
                .orElseThrow(() -> new RuntimeException("Invoice not found with id " + id));
    }

    @Override
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }
}
