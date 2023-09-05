package otus.spring.homework.model;

import lombok.Getter;
import lombok.NonNull;
import otus.spring.homework.exceptions.ExaminationException;

import java.util.Collection;

@Getter
public class ExamResultSimple implements ExamResult {

    @NonNull
    private final Collection<ExaminationTicketAnswer> answers;

    @NonNull
    private final Student student;

    public ExamResultSimple(@NonNull Collection<ExaminationTicketAnswer> answers, @NonNull Student student) {
        if (answers.size() == 0) {
            throw new ExaminationException("Answers size must be greater than 0");
        }
        this.answers = answers;
        this.student = student;
    }

}
