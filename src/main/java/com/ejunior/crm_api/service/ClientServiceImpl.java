package com.ejunior.crm_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejunior.crm_api.model.Client;
import com.ejunior.crm_api.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientRepository clientRepository;

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
  public void deleteById(Long id) {
    clientRepository.deleteById(id);
  }
}
