package com.pedroha.emailms.appCore.ports;

import com.pedroha.emailms.appCore.domain.Email;
import com.pedroha.emailms.appCore.domain.PageInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmailServicePort {

    Email sendEmail(Email email);
    List<Email> findAll(PageInfo pageInfo);
    Optional<Email> findById(UUID emailId);
    Email save(Email email);

}