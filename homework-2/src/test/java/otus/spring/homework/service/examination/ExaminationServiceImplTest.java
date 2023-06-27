package otus.spring.homework.service.examination;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import otus.spring.homework.comparators.ExaminationTicketAnswerComparator;
import otus.spring.homework.comparators.ExaminationTicketComparator;
import otus.spring.homework.dao.ExaminationTicketDao;
import otus.spring.homework.model.ExaminationTicket;
import otus.spring.homework.model.ExaminationTicketAnswer;
import otus.spring.homework.model.ExaminationTicketSimple;
import otus.spring.homework.service.io.IOService;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.contains;

@SpringJUnitConfig(classes = {ExaminationTicketAnswerComparator.class, ExaminationTicketComparator.class})
@DisplayName("service ExaminationServiceImpl ")
class ExaminationServiceImplTest {
    @Autowired
    private ExaminationTicketAnswerComparator examinationTicketAnswerComparator;

    private static Collection<ExaminationTicket> generateRandomExaminationTickets() {
        List<ExaminationTicket> examinationTickets = new LinkedList<>();
        Random random = new Random();
        int size = random.nextInt(10) + 1;
        for (int i = 0; i < size; i++) {
            examinationTickets.add(new ExaminationTicketSimple()
                    .setQuestion(generateString(random))
                    .setCorrectAnswer(generateString(random))
            );
        }

        return examinationTickets;
    }

    private static String generateString(Random random) {
        return RandomStringUtils.random(10 + random.nextInt(50), true, true);
    }

    private static String generateRandomAnswer(ExaminationTicket examinationTicket, Random random) {
        return (random.nextBoolean()) ? examinationTicket.getCorrectAnswer() : generateString(random);
    }

    private static String generateIncorrectAnswer(ExaminationTicket examinationTicket, Random random) {
        return generateString(random) + examinationTicket.getCorrectAnswer();
    }

    private static String generateStudentAnswer(ExaminationTicket examinationTicket,
                                                Random random,
                                                CorrectnessOfAnswers correctness) {
        return switch (correctness) {
            case ALL_CORRECT -> examinationTicket.getCorrectAnswer();
            case RANDOM -> generateRandomAnswer(examinationTicket, random);
            case ALL_INCORRECT -> generateIncorrectAnswer(examinationTicket, random);
        };
    }


    private static ExaminationTicketDao prepareExaminationTicketDao(Collection<ExaminationTicket> examinationTickets) {
        var mock = Mockito.mock(ExaminationTicketDao.class);
        Mockito.when(mock.getExaminationTickets(ArgumentMatchers.<Class<ExaminationTicket>>any()))
                .thenReturn(examinationTickets);

        return mock;
    }

    private static IOService prepareIOService(Collection<ExaminationTicketAnswer> examinationTicketAnswers) {
        var mock = Mockito.mock(IOService.class);

        examinationTicketAnswers.forEach(examinationTicketAnswer -> {
            String question = examinationTicketAnswer.getExaminationTicket().getQuestion();
            String answer = examinationTicketAnswer.getAnswer();
            Mockito.when(mock.readStringWithPrompt(contains(question)))
                    .thenReturn(answer);
        });

        return mock;
    }

    @DisplayName("returns exactly the answers given by the student")
    @ParameterizedTest
    @EnumSource(CorrectnessOfAnswers.class)
    void examine(CorrectnessOfAnswers correctness) {
        Collection<ExaminationTicket> examinationTickets = generateRandomExaminationTickets();
        Collection<ExaminationTicketAnswer> expectedAnswers = generateAnswers(examinationTickets, correctness);

        ExaminationTicketDao examinationTicketDao = prepareExaminationTicketDao(examinationTickets);
        IOService ioService = prepareIOService(expectedAnswers);
        ExaminationServiceImpl service = new ExaminationServiceImpl(ioService, examinationTicketDao);

        Collection<ExaminationTicketAnswer> actualAnswers = service.examine();

        assertThat(actualAnswers)
                .usingElementComparator(examinationTicketAnswerComparator)
                .isEqualTo(expectedAnswers);
    }

    private Collection<ExaminationTicketAnswer> generateAnswers(Collection<ExaminationTicket> examinationTickets,
                                                                CorrectnessOfAnswers correctness) {
        Random random = new Random();
        AtomicInteger i = new AtomicInteger(0);

        return examinationTickets
                .stream()
                .map(examinationTicket -> {
                    ExaminationTicketAnswer answer = Mockito.mock(ExaminationTicketAnswer.class);
                    Mockito.when(answer.getExaminationTicket())
                            .thenReturn(examinationTicket);
                    Mockito.when(answer.getQuestionNumber())
                            .thenReturn(i.incrementAndGet());
                    Mockito.when(answer.getAnswer())
                            .thenReturn(generateStudentAnswer(examinationTicket, random, correctness));

                    return answer;
                })
                .collect(Collectors.toList());
    }

    private enum CorrectnessOfAnswers {
        ALL_CORRECT,
        RANDOM,
        ALL_INCORRECT
    }
}