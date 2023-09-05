package otus.spring.homework.service.examination;

import otus.spring.homework.model.ExamResult;

public interface ExamResultEvaluation {
    boolean examPassed(ExamResult examResult);

    int getExaminationScore(ExamResult examResult);

    int getMaxScore();
}
