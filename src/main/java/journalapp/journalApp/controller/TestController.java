package journalapp.journalApp.controller;

import journalapp.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/test/mail")
    public String testMail() {

        emailService.sendEmail(
                "banusiddharth35@gmail.com",
                "Spring Boot Mail Test",
                "Hello Siddharth, your email service is working!"
        );

        return "Mail Sent Successfully";
    }
}