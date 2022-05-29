package ng.personal.tasks.mailbackend.model;

import lombok.Getter;
import lombok.Setter;
import ng.personal.tasks.mailbackend.util.EnumValidator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Field must not be blank.")
    @Email(message = "Please enter a valid email address.")
    private String fromEmail;

    @NotBlank(message = "Field must not be blank.")
    @Email(message = "Please enter a valid email address.")
    private String toEmail;

    @Pattern(regexp = "^\\s*$|(([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4}))(((;|,|; | ;| ; | , | ,){1}"
                      + "\\s*$|([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4}))*)", message = "Please use valid email addresses separated with a ';'")
    private String ccEmail;

    @NotBlank(message = "Subject is mandatory.")
    private String subject;

    @EnumValidator.ImportanceValue(enumClass = IMPORTANCE.class)
    private String importance;
    private String mailContent;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return id == mail.id && Objects.equals(fromEmail, mail.fromEmail) && Objects.equals(toEmail, mail.toEmail) && Objects.equals(ccEmail, mail.ccEmail) && Objects.equals(subject, mail.subject) && importance == mail.importance && Objects.equals(mailContent, mail.mailContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromEmail, toEmail, ccEmail, subject, importance, mailContent);
    }

    public enum IMPORTANCE {Low, Normal, High}


}
