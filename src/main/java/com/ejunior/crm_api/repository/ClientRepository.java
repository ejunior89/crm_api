package com.ejunior.crm_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejunior.crm_api.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
