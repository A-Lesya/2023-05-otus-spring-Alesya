package otus.spring.homework.service.examination;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import otus.spring.homework.dao.ExaminationTicketDao;
import otus.spring.homework.model.ExaminationTicket;
import otus.spring.homework.model.ExaminationTicketAnswer;
import otus.spring.homework.model.ExaminationTicketSimple;
import otus.spring.homework.service.io.IOService;

import java.util.Collection;
import java.util.LinkedList;

@RequiredArgsConstructor
@Service
public class ExaminationServiceImpl implements ExaminationService {

    private final ExaminationTicketDao examinationTicketDao;
    private final QuestionAskingService questionAskingService;


    @Override
    public Collection<ExaminationTicketAnswer> examine() {
        val examinationTickets = examinationTicketDao.getExaminationTickets(ExaminationTicketSimple.class);
        return askQuestions(examinationTickets);
    }



//    private void askQuestionAndSaveAnswer(ExaminationTicketSimple ticket,
//                                          Collection<ExaminationTicketAnswer> answers,
//                                          int questionNumber) {
//        val answer = askQuestion(ticket);
//        val answerSimple = new ExaminationTicketAnswerSimple(ticket, answer, questionNumber);
//        answers.add(answerSimple);
//    }

    private Collection<ExaminationTicketAnswer> askQuestions(Collection<ExaminationTicketSimple> examinationTickets) {
        Collection<ExaminationTicketAnswer> answers = new LinkedList<>();
        var questionNumber = 0;

        for (ExaminationTicketSimple examinationTicket : examinationTickets) {
            ExaminationTicketAnswer answer = questionAskingService.askQuestion(examinationTicket, ++questionNumber);
            answers.add(answer);

//            askQuestionAndSaveAnswer(examinationTicket, answers, ++questionNumber);
        }
        return answers;
    }
}
