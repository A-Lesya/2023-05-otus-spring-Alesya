package otus.spring.homework.dao;

import java.util.List;

public interface ExaminationTicketDao {
    <T> List<T> getExaminationTickets(Class<T> clazz);
}
