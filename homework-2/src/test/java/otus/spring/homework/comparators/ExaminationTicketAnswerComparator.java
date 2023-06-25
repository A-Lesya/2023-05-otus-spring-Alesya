package otus.spring.homework.comparators;

import org.springframework.stereotype.Component;
import otus.spring.homework.model.ExaminationTicketAnswer;

import java.util.Comparator;
import java.util.Objects;

@Component
public class ExaminationTicketAnswerComparator implements Comparator<ExaminationTicketAnswer> {
    private final ExaminationTicketComparator examinationTicketComparator;

    public ExaminationTicketAnswerComparator(ExaminationTicketComparator examinationTicketComparator) {
        this.examinationTicketComparator = examinationTicketComparator;
    }

    private boolean answersEquals(ExaminationTicketAnswer o1, ExaminationTicketAnswer o2) {
        return Objects.equals(o1.getAnswer(), o2.getAnswer());
    }

    private boolean questionNumbersEquals(ExaminationTicketAnswer o1, ExaminationTicketAnswer o2) {
        return Objects.equals(o1.getQuestionNumber(), o2.getQuestionNumber());
    }

    private boolean equals(ExaminationTicketAnswer o1, ExaminationTicketAnswer o2) {
        return answersEquals(o1, o2) &&
                questionNumbersEquals(o1, o2) &&
                examinationTicketsEquals(o1, o2);
    }

    @Override
    public int compare(ExaminationTicketAnswer o1, ExaminationTicketAnswer o2) {
        return Utils.compare(o1, o2, this::equals);
    }

    private boolean examinationTicketsEquals(ExaminationTicketAnswer o1, ExaminationTicketAnswer o2) {
        return examinationTicketComparator.compare(o1.getExaminationTicket(), o2.getExaminationTicket()) == 0;
    }
}
