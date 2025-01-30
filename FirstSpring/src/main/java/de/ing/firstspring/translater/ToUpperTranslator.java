package de.ing.firstspring.translater;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Qualifier("upper")
//@Profile("test")
public class ToUpperTranslator implements Translator {
    @Override
    public String translate(final String text) {
        return text.toUpperCase();
    }
}
