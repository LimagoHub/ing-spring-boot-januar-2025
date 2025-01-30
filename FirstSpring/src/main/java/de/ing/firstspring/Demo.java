package de.ing.firstspring;

import de.ing.firstspring.translater.Translator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton" )
@RequiredArgsConstructor
public class Demo {

    @Value("${Demo.message}")
    private final String message;

    @Qualifier("upper")
    private final Translator translator;

    @PostConstruct
    void init() {
        System.out.println(translator.translate("PostConstruct demo"));
    }

    @PreDestroy
    public void cleanUp() {
        System.out.println(message);
    }
}
