package ng.personal.tasks.mailbackend.controller;

import ng.personal.tasks.mailbackend.model.Mail;
import ng.personal.tasks.mailbackend.repository.MailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/mail")
public class MailController {

    final
    MailRepository repository;

    public MailController(MailRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/send")
    public ResponseEntity<String> sendMail(@Valid @RequestBody Mail mail) {
        repository.save(mail);
        return ResponseEntity.ok("Mail sent!");
    }
}
