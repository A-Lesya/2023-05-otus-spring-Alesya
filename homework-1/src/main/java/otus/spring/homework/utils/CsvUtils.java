package otus.spring.homework.utils;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public final class CsvUtils {
    public <T> List<T> parseFromCsv(Class<? extends T> clazz, String path) {
        return new CsvToBeanBuilder<T>(getReader(path))
                .withSeparator(';')
                .withType(clazz)
                .build()
                .parse();
    }

    private Reader getReader(String path) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found: " + path);
        }
        return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    }
}
