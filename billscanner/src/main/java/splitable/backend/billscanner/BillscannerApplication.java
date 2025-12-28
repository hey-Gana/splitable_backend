package splitable.backend.billscanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import splitable.backend.billscanner.service.VisionService;

@SpringBootApplication
public class BillscannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillscannerApplication.class, args);
    }

    @Bean
    CommandLineRunner testVisionCredentials() {
        return args -> {
            System.out.println("Testing Google Vision credentials");
            //System.out.println(VisionService.getCredentials());
			VisionService.getCredentials();
            System.out.println("Google Vision credentials loaded successfully!");
        };
    }
}
