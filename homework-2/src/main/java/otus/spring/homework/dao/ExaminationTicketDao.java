package otus.spring.homework.dao;

import java.util.Collection;

public interface ExaminationTicketDao {
    <T> Collection<T> getExaminationTickets(Class<T> clazz);
}
