package otus.spring.homework.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;
import otus.spring.homework.config.ParsingCsvSettingsProvider;
import otus.spring.homework.exceptions.ReadingTicketsFromCsvException;
import otus.spring.homework.service.io.ReaderService;

import java.io.IOException;
import java.io.Reader;
import java.util.Collection;

@Component
public class ExaminationTicketDaoCsv implements ExaminationTicketDao {

    private final ReaderService readerService;

    private final ParsingCsvSettingsProvider parsingCsvSettingsProvider;

    public ExaminationTicketDaoCsv(ReaderService readerService,
                                   ParsingCsvSettingsProvider parsingCsvSettingsProvider) {
        this.readerService = readerService;
        this.parsingCsvSettingsProvider = parsingCsvSettingsProvider;
    }

    @Override
    public <T> Collection<T> getExaminationTickets(Class<T> clazz) {
        try (Reader reader = readerService.getReader()) {
            return new CsvToBeanBuilder<T>(reader)
                    .withSeparator(parsingCsvSettingsProvider.getSeparator())
                    .withType(clazz)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new ReadingTicketsFromCsvException(e);
        }
    }
}
