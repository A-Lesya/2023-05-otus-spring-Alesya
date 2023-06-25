package otus.spring.homework.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import otus.spring.homework.comparators.ExaminationTicketComparator;
import otus.spring.homework.model.ExaminationTicket;
import otus.spring.homework.model.ExaminationTicketSimple;
import otus.spring.homework.service.io.ReaderServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringJUnitConfig(classes = {ExaminationTicketDaoCsv.class, ReaderServiceImpl.class,
        ExaminationTicketComparator.class})
@DisplayName("service ExaminationTicketDaoCsv ")
class ExaminationTicketDaoCsvIntegrationTest {
    @Autowired
    private ExaminationTicketDaoCsv service;

    @Autowired
    private Environment env;

    @Autowired
    private ExaminationTicketComparator comparator;

    @DisplayName("correctly reads questions from given csv")
    @Test
    public void test() throws IOException, URISyntaxException {
        var examinationTickets = getExaminationTicketsFromProperties();
        createCsvFile(examinationTickets);

        var examinationTicketsActual = service.getExaminationTickets(ExaminationTicketSimple.class);

        assertThat(examinationTicketsActual)
                .usingElementComparator(comparator)
                .isEqualTo(examinationTickets);
    }

    private Collection<ExaminationTicketSimple> getExaminationTicketsFromProperties() {
        Collection<ExaminationTicketSimple> tickets = new LinkedList<>();

        for (int i = 1; i <= 5; i++) {
            ExaminationTicketSimple ticket = getExaminationTicketFromProperties(i);
            tickets.add(ticket);
        }

        return tickets;
    }

    private ExaminationTicketSimple getExaminationTicketFromProperties(int i) {
        var question = env.getProperty("question" + i);
        var answer = env.getProperty("answer" + i);

        return new ExaminationTicketSimple()
                .setQuestion(question)
                .setCorrectAnswer(answer);
    }

    private String formCsvLine(ExaminationTicket examinationTicket) {
        var separator = env.getProperty("csv-separator");
        return examinationTicket.getQuestion() + separator + examinationTicket.getCorrectAnswer() + separator;
    }

    private String formFirstCsvLine() {
        return "question;answer;";
    }

    private List<String> formLinesForCsv(Collection<? extends ExaminationTicket> examinationTickets) {
        List<String> result = new LinkedList<>();
        result.add(formFirstCsvLine());
        examinationTickets.forEach(ticket -> result.add(formCsvLine(ticket)));
        return result;
    }

    private void createCsvFile(Collection<? extends ExaminationTicket> examinationTickets)
            throws IOException, URISyntaxException {
        var lines = formLinesForCsv(examinationTickets);
        var path = getCsvFilePath();
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    private Path getCsvFilePath() throws URISyntaxException {
        var resourcePath = getResourcesPath();
        var ticketsFilePath = env.getProperty("tickets-file-path");

        return Paths.get(resourcePath + "/" + ticketsFilePath);
    }

    private Path getResourcesPath() throws URISyntaxException {
        var resourceUri = Objects.requireNonNull(
                        getClass().getResource("/")
                )
                .toURI();
        return Paths.get(resourceUri)
                .toAbsolutePath();
    }
}