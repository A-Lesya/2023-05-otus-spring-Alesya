package otus.spring.homework.config;

import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ResultReportingSettingsProviderProperties implements ResultReportingSettingsProvider {
    private final String successMessage;
    private final String failMessage;
    private final String scoreMessage;

    public ResultReportingSettingsProviderProperties(AppProperties appProperties, MessageSource messageSource) {
        this.successMessage = messageSource.getMessage("result-reporting.success", null, appProperties.getLocale());
        this.failMessage = messageSource.getMessage("result-reporting.fail", null, appProperties.getLocale());
        this.scoreMessage = messageSource.getMessage("result-reporting.score", null, appProperties.getLocale());
    }
}
