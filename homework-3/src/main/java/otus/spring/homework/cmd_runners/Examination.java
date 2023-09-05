package otus.spring.homework.cmd_runners;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import otus.spring.homework.service.examination.StudentExaminationService;

@RequiredArgsConstructor
@Component
public class Examination implements CommandLineRunner {
    private final StudentExaminationService studentExaminationService;

    @Override
    public void run(String... args) {
        studentExaminationService.examineStudent();
    }
}
