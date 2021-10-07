package com.pedroha.emailms.appCore.services;

import com.pedroha.emailms.appCore.domain.Email;
import com.pedroha.emailms.appCore.domain.PageInfo;
import com.pedroha.emailms.appCore.domain.enums.StatusEmailEnum;
import com.pedroha.emailms.appCore.ports.EmailRepositoryPort;
import com.pedroha.emailms.appCore.ports.EmailServicePort;
import com.pedroha.emailms.appCore.ports.SendEmailServicePort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EmailServiceImpl implements EmailServicePort {

    private final EmailRepositoryPort emailRepositoryPort;
    private final SendEmailServicePort sendEmailServicePort;

    public EmailServiceImpl(final EmailRepositoryPort emailRepositoryPort, final SendEmailServicePort sendEmailServicePort) {
        this.emailRepositoryPort = emailRepositoryPort;
        this.sendEmailServicePort = sendEmailServicePort;
    }

    @Override
    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try{
            sendEmailServicePort.sendEmailSmtp(email);
            email.setStatusEmail(StatusEmailEnum.SENT);
        } catch (Exception e){
            email.setStatusEmail(StatusEmailEnum.ERROR);
        } finally {
            return save(email);
        }
    }

    @Override
    public List<Email> findAll(PageInfo pageInfo) {
        //inserir manipulação de dados/regras
        return  emailRepositoryPort.findAll(pageInfo);
    }

    @Override
    public Optional<Email> findById(UUID emailId) {
        //inserir manipulação de dados/regras
        return emailRepositoryPort.findById(emailId);
    }

    @Override
    public Email save(Email email) {
        return emailRepositoryPort.save(email);
    }

}