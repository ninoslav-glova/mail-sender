package ng.personal.tasks.mailbackend.util;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class EnumValidator implements ConstraintValidator<EnumValidator.ImportanceValue, CharSequence> {

    private List<String> acceptedValues;

    @Override
    public void initialize(ImportanceValue annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return acceptedValues.contains(value.toString());
    }

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
