package otus.spring.homework.model;

import java.util.Collection;

public interface ExamResult {
    Collection<ExaminationTicketAnswer> getAnswers();

    Student getStudent();
}
