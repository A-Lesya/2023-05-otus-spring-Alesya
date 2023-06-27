package otus.spring.homework.service.examination;

import org.springframework.stereotype.Service;
import otus.spring.homework.dao.ExaminationTicketDao;
import otus.spring.homework.model.ExaminationTicket;
import otus.spring.homework.model.ExaminationTicketAnswer;
import otus.spring.homework.model.ExaminationTicketAnswerSimple;
import otus.spring.homework.model.ExaminationTicketSimple;
import otus.spring.homework.service.io.IOService;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ExaminationServiceImpl implements ExaminationService {
    private final IOService ioService;

    private final ExaminationTicketDao examinationTicketDao;

    public ExaminationServiceImpl(IOService ioService, ExaminationTicketDao examinationTicketDao) {
        this.ioService = ioService;
        this.examinationTicketDao = examinationTicketDao;
    }

    @Override
    public Collection<ExaminationTicketAnswer> examine() {
        var examinationTickets = examinationTicketDao.getExaminationTickets(ExaminationTicketSimple.class);
        var i = new AtomicInteger();
        return examinationTickets.stream()
                .map(examinationTicket -> {
                    var answer = askQuestion(examinationTicket);
                    return new ExaminationTicketAnswerSimple(examinationTicket, answer, i.incrementAndGet());
                })
                .collect(Collectors.toList());
    }

    private String askQuestion(ExaminationTicket ticket) {
        String question = ticket.getQuestion();
        return ioService.readStringWithPrompt(question);
    }

}
