package dat109.prosjekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Hovudklassen for feedback-applikasjonen.
 * Utvidar {@link SpringBootServletInitializer} for å støtte WAR-deployment på ekstern TomEE-server.
 */
@SpringBootApplication
public class FeedbackApplikasjon extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FeedbackApplikasjon.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(FeedbackApplikasjon.class, args);
    }
}
