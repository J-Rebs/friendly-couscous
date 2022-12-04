package com.example.musictonic.repository;

import com.example.musictonic.model.ClientLogin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for ClientLogin.
 */
public interface ClientLoginRepository extends JpaRepository<ClientLogin, Long> {

  ClientLogin findByClientNameAndPassword(String clientName, String password);

  boolean existsByClientName(String clientName);

  ClientLogin findByClientName(String username);
}
