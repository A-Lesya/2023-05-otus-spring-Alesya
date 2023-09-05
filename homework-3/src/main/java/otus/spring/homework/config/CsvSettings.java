package otus.spring.homework.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "csv")
public class CsvSettings implements ParsingCsvSettingsProvider, ReadingCsvSettingsProvider {
    private final char separator;
    private final String ticketsFilePath;

    @ConstructorBinding
    public CsvSettings(char separator, String ticketsFilePath) {
        this.separator = separator;
        this.ticketsFilePath = ticketsFilePath;
    }
}
