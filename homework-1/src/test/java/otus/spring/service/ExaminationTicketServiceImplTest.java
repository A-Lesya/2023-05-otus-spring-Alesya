package otus.spring.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import otus.spring.homework.dao.ExaminationTicketDao;
import otus.spring.homework.domain.ExaminationTicket;
import otus.spring.homework.service.ExaminationTicketServiceImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("class ExaminationTicketServiceImpl")
class ExaminationTicketServiceImplTest {
    private static List<ExaminationTicket> generateRandomExaminationTickets() {
        List<ExaminationTicket> examinationTickets = new LinkedList<>();
        Random random = new Random();
        int size = random.nextInt(10) + 1;
        for (int i = 0; i < size; i++) {
            examinationTickets.add(new ExaminationTicket()
                    .setQuestion(RandomStringUtils.random(10 + random.nextInt(50),
                            true, true))
            );
        }

        return examinationTickets;
    }

    private static String getExpectedSOut(List<ExaminationTicket> examinationTickets) {
        String separator = System.getProperty("line.separator");
        return examinationTickets.stream()
                .map(ExaminationTicket::getQuestion)
                .collect(Collectors.joining(separator)) + separator;
    }

    @DisplayName("prints questions in system out")
    @Test
    public void showQuestionsPrintsInSOut() throws Exception {
        List<ExaminationTicket> examinationTickets = generateRandomExaminationTickets();

        ExaminationTicketDao mock = mock(ExaminationTicketDao.class);
        when(mock.getExaminationTickets(ExaminationTicket.class)).thenReturn(examinationTickets);

        ExaminationTicketServiceImpl service = new ExaminationTicketServiceImpl(mock);

        String sout = tapSystemOut(service::showQuestions);
        String expected = getExpectedSOut(examinationTickets);

        assertEquals(expected, sout);
    }
}