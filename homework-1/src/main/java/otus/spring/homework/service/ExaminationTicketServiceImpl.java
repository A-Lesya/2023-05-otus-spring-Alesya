package otus.spring.homework.service;

import otus.spring.homework.dao.ExaminationTicketDao;
import otus.spring.homework.domain.ExaminationTicket;

public class ExaminationTicketServiceImpl implements ExaminationTicketService {
    private final ExaminationTicketDao examinationTicketDao;

    public ExaminationTicketServiceImpl(ExaminationTicketDao examinationTicketDao) {
        this.examinationTicketDao = examinationTicketDao;
    }

    @Override
    public void showQuestions() {
        examinationTicketDao.getExaminationTickets(ExaminationTicket.class)
                .forEach(examinationTicket -> System.out.println(examinationTicket.getQuestion()));
    }
}
