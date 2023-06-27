package otus.spring.homework.model;

import java.util.Objects;

public interface ExaminationTicketAnswer {
    ExaminationTicket getExaminationTicket();

    int getQuestionNumber();

    <T> T getAnswer();

    default boolean isCorrect() {
        return Objects.equals(getAnswer(), getExaminationTicket().getCorrectAnswer());
    }
}
