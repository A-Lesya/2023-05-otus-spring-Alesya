package otus.spring.homework.service.examination;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import otus.spring.homework.model.ExaminationTicket;
import otus.spring.homework.model.ExaminationTicketAnswer;
import otus.spring.homework.model.ExaminationTicketAnswerImpl;
import otus.spring.homework.service.io.IOService;

@RequiredArgsConstructor
@Service
public class QuestionAskingServiceSimple implements QuestionAskingService {

    private final IOService ioService;

    @Override
    public ExaminationTicketAnswer askQuestion(ExaminationTicket ticket, int questionNumber) {
        val answer = askQuestion(ticket);
        return new ExaminationTicketAnswerImpl(ticket, answer, questionNumber);
    }

    private String askQuestion(ExaminationTicket ticket) {
        String question = ticket.getQuestion();
        return ioService.readStringWithPrompt(question);
    }
}
