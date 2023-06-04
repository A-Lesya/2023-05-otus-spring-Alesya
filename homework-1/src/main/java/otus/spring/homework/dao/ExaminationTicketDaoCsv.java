package otus.spring.homework.dao;

import otus.spring.homework.utils.CsvUtils;

import java.util.List;

public class ExaminationTicketDaoCsv implements ExaminationTicketDao {
    private final String path;

    private final CsvUtils csvUtils;

    public ExaminationTicketDaoCsv(String path, CsvUtils csvUtils) {
        this.path = path;
        this.csvUtils = csvUtils;
    }

    @Override
    public <T> List<T> getExaminationTickets(Class<T> clazz) {
        return csvUtils.parseFromCsv(clazz, path);
    }
}
