package otus.spring.homework.exceptions;

public class ReadingTicketsFromCsvException extends RuntimeException {
    public ReadingTicketsFromCsvException(Throwable cause) {
        super(cause);
    }

    public ReadingTicketsFromCsvException(String message) {
        super(message);
    }
}
