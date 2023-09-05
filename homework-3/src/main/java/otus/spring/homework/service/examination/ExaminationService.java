package otus.spring.homework.service.examination;

import otus.spring.homework.model.ExaminationTicketAnswer;

import java.util.Collection;

public interface ExaminationService {
    Collection<ExaminationTicketAnswer> examine();
}
