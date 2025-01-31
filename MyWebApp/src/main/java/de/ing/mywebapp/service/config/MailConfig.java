package de.ing.mywebapp.service.config;

import de.ing.mywebapp.service.MailData;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Setter
@PropertySource(value= "classpath:mail.properties")
@ConfigurationProperties(prefix = "mail")
public class MailConfig {

    private String smtp;
    private String user;
    private String passwort;
    private String protokoll;

    @Bean
    public MailData createMailData() {
        return MailData
                .builder()
                .user(user)
                .passwort(passwort)
                .protokoll(protokoll)
                .smtp(smtp)
                .build();
    }
}
