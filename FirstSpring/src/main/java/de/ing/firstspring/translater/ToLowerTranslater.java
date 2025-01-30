package de.ing.firstspring.translater;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Qualifier("lower")
//@Profile("production")
public class ToLowerTranslater implements Translator {
    @Override
    public String translate(final String text) {
        return text.toLowerCase();
    }
}
