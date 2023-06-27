package otus.spring.homework.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import otus.spring.homework.service.io.ReaderService;

import java.util.Collection;

@Component
public class ExaminationTicketDaoCsv implements ExaminationTicketDao {

    private final ReaderService readerService;

    private final char separator;

    public ExaminationTicketDaoCsv(ReaderService readerService,
                                   @Value("${csv-separator}") char separator) {
        this.readerService = readerService;
        this.separator = separator;
    }

    @Override
    public <T> Collection<T> getExaminationTickets(Class<T> clazz) {
        return parseFromCsv(clazz, separator);
    }

    private <T> Collection<T> parseFromCsv(Class<? extends T> clazz, char separator) {
        return new CsvToBeanBuilder<T>(readerService.getReader())
                .withSeparator(separator)
                .withType(clazz)
                .build()
                .parse();
    }
}
