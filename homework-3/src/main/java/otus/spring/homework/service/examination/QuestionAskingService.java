package otus.spring.homework.service.examination;

import otus.spring.homework.model.ExaminationTicket;
import otus.spring.homework.model.ExaminationTicketAnswer;

public interface QuestionAskingService {

     ExaminationTicketAnswer askQuestion(ExaminationTicket ticket, int questionNumber);
}
