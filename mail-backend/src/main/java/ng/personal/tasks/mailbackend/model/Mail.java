package ng.personal.tasks.mailbackend.model;

import lombok.Getter;
import lombok.Setter;
import ng.personal.tasks.mailbackend.util.EnumValidator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Objects;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

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

    @Pattern(regexp = "(([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4}))(((;|,|; | ;| ; | , | ,){1}"
                      + "([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4}))*)", message = "Please use valid email addresses separated with a ';'")
    private String ccEmail;

    @NotBlank(message = "Subject is mandatory.")
    private String subject;

    @ImportanceValue(enumClass = IMPORTANCE.class)
    private String importance;
    private String content;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return id == mail.id && Objects.equals(fromEmail, mail.fromEmail) && Objects.equals(toEmail, mail.toEmail) && Objects.equals(ccEmail, mail.ccEmail) && Objects.equals(subject, mail.subject) && importance == mail.importance && Objects.equals(content, mail.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromEmail, toEmail, ccEmail, subject, importance, content);
    }

    public enum IMPORTANCE {Low, Normal, High}

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @Constraint(validatedBy = EnumValidator.class)
    public @interface ImportanceValue {
        Class<? extends Enum<?>> enumClass();

        String message() default "Field must be any of: Low | Normal | High";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

}
