package com.pedroha.emailms.appCore.ports;

import com.pedroha.emailms.appCore.domain.Email;

public interface SendEmailServicePort {
    void sendEmailSmtp(Email email);
}