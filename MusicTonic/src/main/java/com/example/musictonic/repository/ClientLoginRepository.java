package com.example.musictonic.repository;

import com.example.musictonic.model.Client;
import com.example.musictonic.model.ClientLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClientLoginRepository extends JpaRepository<ClientLogin, Long> {

  ClientLogin findByClientNameAndPassword(String clientName, String password);

}
