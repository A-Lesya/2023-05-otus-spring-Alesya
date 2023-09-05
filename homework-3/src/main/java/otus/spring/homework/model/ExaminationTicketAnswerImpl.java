package otus.spring.homework.model;

import lombok.Getter;
import otus.spring.homework.exceptions.ExaminationException;

@Getter
public class ExaminationTicketAnswerImpl implements ExaminationTicketAnswer {

    private final ExaminationTicket examinationTicket;

    private final String answer;

    private final int questionNumber;

    public ExaminationTicketAnswerImpl(ExaminationTicket examinationTicket,
                                       String answer,
                                       int questionNumber) {
        if (examinationTicket == null) {
            throw new ExaminationException("Examination ticket can't be null");
        }
        this.examinationTicket = examinationTicket;
        this.answer = answer;
        this.questionNumber = questionNumber;
    }

    @Override
    public boolean isCorrect() {
        if (answer == null) {
            return false;
        }

        String correctAnswer = examinationTicket.getCorrectAnswer();
        return answer.equalsIgnoreCase(correctAnswer);
    }
}
