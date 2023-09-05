package otus.spring.homework.config;

public interface ExamResultEvaluationSettingsProvider {
    int getNumberOfCorrectAnswersToPass();

    int getMaxScore();
}
