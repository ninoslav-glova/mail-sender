package ng.personal.tasks.mailbackend.controller;

import ng.personal.tasks.mailbackend.model.Mail;
import ng.personal.tasks.mailbackend.repository.MailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin
public class MailController {

    final
    MailRepository repository;

    public MailController(MailRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "mail/send", produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> sendMail(@Valid @RequestBody Mail mail) {
        repository.save(mail);
        String response = "{\"response\":\"Mail Sent!\"}";
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/mail")
    public ResponseEntity<List<Mail>> sendMail() {

        return ResponseEntity.ok(repository.findAll());
    }
}
