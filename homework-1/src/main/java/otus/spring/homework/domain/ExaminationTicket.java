package otus.spring.homework.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ExaminationTicket {

    @CsvBindByName(required = true)
    private String question;

    @CsvBindByName(required = true)
    private String answer;
}
