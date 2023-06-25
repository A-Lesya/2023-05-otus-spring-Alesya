package otus.spring.homework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import otus.spring.homework.service.examination.StudentExaminationService;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        StudentExaminationService service = context.getBean(StudentExaminationService.class);
        service.examineStudent();
    }
}
