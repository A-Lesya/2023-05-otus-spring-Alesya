package otus.spring.homework;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import otus.spring.homework.service.ExaminationTicketService;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        ExaminationTicketService service = context.getBean(ExaminationTicketService.class);
        service.showQuestions();
    }
}
