package otus.spring.homework.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ExaminationTicketSimple implements ExaminationTicket {

    @CsvBindByName(required = true)
    private String question;

    @CsvBindByName(required = true, column = "answer")
    private String correctAnswer;

    @Override
    public String toString() {
        return "ExaminationTicketSimple{" +
                "question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
