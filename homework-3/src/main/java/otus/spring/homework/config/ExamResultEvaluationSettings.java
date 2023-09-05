package otus.spring.homework.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "exam")
public class ExamResultEvaluationSettings implements ExamResultEvaluationSettingsProvider {
    private final int numberOfCorrectAnswersToPass;
    private final int maxScore;

    @ConstructorBinding
    public ExamResultEvaluationSettings(int numberOfCorrectAnswersToPass, int maxScore) {
        this.numberOfCorrectAnswersToPass = numberOfCorrectAnswersToPass;
        this.maxScore = maxScore;
    }
}
