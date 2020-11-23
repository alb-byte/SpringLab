package by.shymanel.springlab.dto.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class DateConstraintValidator implements ConstraintValidator<DateConstraint, Date> {
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        return date.after(new Date());
    }
}