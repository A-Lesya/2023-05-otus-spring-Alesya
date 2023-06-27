package otus.spring.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import otus.spring.homework.service.io.IOService;
import otus.spring.homework.service.io.IOServiceStreams;

@PropertySource("classpath:application.properties")
@Configuration
public class AppConfig {

    @Bean
    public IOService ioService() {
        return new IOServiceStreams(System.out, System.in);
    }
}
