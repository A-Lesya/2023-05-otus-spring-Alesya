package otus.spring.homework.model;

public interface ExaminationTicket {
    <T> T getQuestion();

    <T> T getCorrectAnswer();
}
