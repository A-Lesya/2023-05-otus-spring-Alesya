package otus.spring.homework.comparators;

import org.springframework.stereotype.Component;
import otus.spring.homework.model.ExaminationTicket;

import java.util.Comparator;
import java.util.Objects;

@Component
public class ExaminationTicketComparator implements Comparator<ExaminationTicket> {

    @Override
    public int compare(ExaminationTicket o1, ExaminationTicket o2) {
        return Utils.compare(o1, o2, this::equals);
    }

    private boolean equals(ExaminationTicket o1, ExaminationTicket o2) {
        return questionsEquals(o1, o2) && correctAnswersEquals(o1, o2);
    }

    private boolean questionsEquals(ExaminationTicket o1, ExaminationTicket o2) {
        return Objects.equals(o1.getQuestion(), o2.getQuestion());
    }

    private boolean correctAnswersEquals(ExaminationTicket o1, ExaminationTicket o2) {
        return Objects.equals(o1.getCorrectAnswer(), o2.getCorrectAnswer());
    }
}
