package otus.spring.homework.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import otus.spring.homework.service.io.IOService;
import otus.spring.homework.service.io.IOServiceStreams;

@Configuration
@EnableConfigurationProperties({CsvSettings.class, ExamResultEvaluationSettings.class, AppProperties.class})
public class AppConfig {
    @Bean
    public IOService ioService() {
        return new IOServiceStreams(System.out, System.in);
    }
}
