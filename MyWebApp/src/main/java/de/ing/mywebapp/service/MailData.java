package de.ing.mywebapp.service;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class MailData {

    private final String smtp;
    private final String user;
    private final String passwort;
    private final String protokoll;
}
