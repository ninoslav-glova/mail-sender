package ng.personal.tasks.mailbackend.repository;

import ng.personal.tasks.mailbackend.model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail, Long> {
}
