package com.pedroha.emailms.repositories;

import com.pedroha.emailms.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {
    Optional<EmailModel> findByEmailId(UUID emailId);
}
