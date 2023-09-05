package otus.spring.homework.service.io;

import org.springframework.stereotype.Component;
import otus.spring.homework.config.ReadingCsvSettingsProvider;
import otus.spring.homework.exceptions.ReadingTicketsFromCsvException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Component
public class ReaderServiceImpl implements ReaderService {
    private final String path;

    public ReaderServiceImpl(ReadingCsvSettingsProvider readingCsvSettingsProvider) {
        this.path = readingCsvSettingsProvider.getTicketsFilePath();
    }

    @Override
    public Reader getReader() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);

        if (inputStream == null) {
            throw new ReadingTicketsFromCsvException("file not found: " + path);
        }
        return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    }
}
