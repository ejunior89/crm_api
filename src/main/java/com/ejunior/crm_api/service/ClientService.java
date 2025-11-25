package com.ejunior.crm_api.service;

import java.util.List;
import java.util.Optional;

import com.ejunior.crm_api.model.Client;

public interface ClientService {
  Client save(Client client);

  List<Client> findAll();

  Optional<Client> findById(Long id);

  Client update(Long id, Client client);

  void deleteById(Long id);
}
