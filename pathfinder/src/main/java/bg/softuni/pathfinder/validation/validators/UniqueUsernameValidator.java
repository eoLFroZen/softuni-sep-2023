package bg.softuni.pathfinder.validation.validators;

import bg.softuni.pathfinder.service.UserService;
import bg.softuni.pathfinder.validation.anotations.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private String message;
    private final UserService userService;

    public UniqueUsernameValidator (UserService userService) {

        this.userService = userService;
    }

    @Override
    public void initialize (UniqueUsername constraintAnnotation) {

        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid (String value, ConstraintValidatorContext context) {

        if (value == null) {

            return true;
        } else {

            final boolean isUnique = userService.isUniqueUsername(value);

            if (!isUnique) {

                HibernateConstraintValidatorContext hibernateContext =
                        context.unwrap(HibernateConstraintValidatorContext.class);

                hibernateContext
                        .disableDefaultConstraintViolation();
                hibernateContext
                        .buildConstraintViolationWithTemplate(message)
                        .addConstraintViolation();
            }

            return isUnique;
        }

    }
}
