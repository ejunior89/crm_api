package com.ejunior.crm_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ejunior.crm_api.model.Client;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    List<Client> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT c FROM Client c WHERE c.contactPhone IS NOT NULL")
    List<Client> findClientsWithPhone();
}
