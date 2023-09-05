package otus.spring.homework.service.examination;

import org.springframework.stereotype.Service;
import otus.spring.homework.config.GreetingPromptProvider;
import otus.spring.homework.config.ResultReportingSettingsProvider;
import otus.spring.homework.model.ExamResult;
import otus.spring.homework.model.ExamResultSimple;
import otus.spring.homework.model.ExaminationTicketAnswer;
import otus.spring.homework.model.Student;
import otus.spring.homework.service.io.IOService;

import java.util.stream.Collectors;

@Service
public class StudentExaminationServiceImpl implements StudentExaminationService {
    private final IOService ioService;

    private final ExaminationService examinationService;

    private final ExamResultEvaluation examResultEvaluation;

    private final GreetingPromptProvider greetingPromptProvider;

    private final ResultReportingSettingsProvider resultReportingSettingsProvider;


    public StudentExaminationServiceImpl(IOService ioService,
                                         ExaminationService examinationService,
                                         ExamResultEvaluation examResultEvaluation,
                                         GreetingPromptProvider greetingPromptProvider,
                                         ResultReportingSettingsProvider resultReportingSettingsProvider) {
        this.ioService = ioService;
        this.examinationService = examinationService;
        this.examResultEvaluation = examResultEvaluation;
        this.greetingPromptProvider = greetingPromptProvider;
        this.resultReportingSettingsProvider = resultReportingSettingsProvider;
    }

    @Override
    public ExamResult examineStudent() {
        var student = defineStudent();
        var answers = examinationService.examine();
        ExamResult examResult = new ExamResultSimple(answers, student);
        reportResult(examResult);
        return examResult;
    }

    private Student defineStudent() {
        var name = getStudentName();
        var surname = getStudentSurname();
        return new Student(name, surname);
    }

    private void reportResult(ExamResult examResult) {
        var examResultInfo = getExamResultInfo(examResult);
        var answersCheckingResult = getAnswersCheckingResult(examResult);

        ioService.outputString(examResultInfo);
        ioService.outputString(answersCheckingResult);
    }

    private String getExamResultInfo(ExamResult examResult) {
        var examPassed = examResultEvaluation.examPassed(examResult);
        var score = examResultEvaluation.getExaminationScore(examResult);
        var maxScore = examResultEvaluation.getMaxScore();
        var result = examPassed ? resultReportingSettingsProvider.getSuccessMessage() : resultReportingSettingsProvider.getFailMessage();

        return result + "\n" +
                java.text.MessageFormat.format(resultReportingSettingsProvider.getScoreMessage(), score, maxScore);

    }

    private String getAnswersCheckingResult(ExamResult examResult) {
        return examResult.getAnswers()
                .stream()
                .map(this::getAnswerCheckingResult)
                .collect(Collectors.joining("\n"));
    }

    private String getAnswerCheckingResult(ExaminationTicketAnswer answer) {
        String mark = answer.isCorrect() ? "v" : "x";
        int questionNumber = answer.getQuestionNumber();
        return mark + "\tquestion №" + questionNumber;
    }

    private String getStudentName() {
        return ioService.readStringWithPrompt(greetingPromptProvider.getFirstNamePrompt());
    }

    private String getStudentSurname() {
        return ioService.readStringWithPrompt(greetingPromptProvider.getSurnamePrompt());
    }
}
