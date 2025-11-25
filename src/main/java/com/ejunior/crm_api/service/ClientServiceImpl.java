package com.ejunior.crm_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ejunior.crm_api.model.Client;
import com.ejunior.crm_api.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;

  public ClientServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public Client save(Client client) {
    return clientRepository.save(client);
  }

  @Override
  public List<Client> findAll() {
    return clientRepository.findAll();
  }

  @Override
  public Optional<Client> findById(Long id) {
    return clientRepository.findById(id);
  }

  @Override
  public Client update(Long id, Client clientDetails) {
    return clientRepository.findById(id)
        .map(client -> {
          client.setName(clientDetails.getName());
          client.setEmail(clientDetails.getEmail());
          client.setContactPhone(clientDetails.getContactPhone());
          return clientRepository.save(client);
        })
        .orElseThrow(() -> new RuntimeException("Client not found with id " + id));
  }

  @Override
  public void deleteById(Long id) {
    clientRepository.deleteById(id);
  }
}
