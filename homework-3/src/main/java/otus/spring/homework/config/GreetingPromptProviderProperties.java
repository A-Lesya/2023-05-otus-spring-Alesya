package otus.spring.homework.config;

import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class GreetingPromptProviderProperties implements GreetingPromptProvider {
    private final String firstNamePrompt;
    private final String surnamePrompt;

    public GreetingPromptProviderProperties(AppProperties appProperties, MessageSource messageSource) {
        this.firstNamePrompt = messageSource.getMessage("greeting.prompt.first-name", null, appProperties.getLocale());
        this.surnamePrompt = messageSource.getMessage("greeting.prompt.surname", null, appProperties.getLocale());
    }
}
