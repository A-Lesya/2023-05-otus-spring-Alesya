package otus.spring.homework.service.io;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Component
public class ReaderServiceImpl implements ReaderService {
    private final String path;

    public ReaderServiceImpl(@Value("${tickets-file-path}") String path) {
        this.path = path;
    }

    @Override
    public Reader getReader() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found: " + path);
        }
        return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    }
}
