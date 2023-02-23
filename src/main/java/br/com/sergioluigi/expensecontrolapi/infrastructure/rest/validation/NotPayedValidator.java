package br.com.sergioluigi.expensecontrolapi.infrastructure.rest.validation;

import br.com.sergioluigi.expensecontrolapi.infrastructure.rest.model.request.CreateExpenseRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotPayedValidator implements ConstraintValidator<NotPayed, CreateExpenseRequest> {
    @Override
    public boolean isValid(CreateExpenseRequest value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }

        context.disableDefaultConstraintViolation();

        if(value.paymentDate() != null && !value.payed()){
            context.buildConstraintViolationWithTemplate("The expense was not payed but paymentDate is not null.")
                    .addConstraintViolation();
            return false;
        }

        if(value.paymentDate() == null && value.payed()){
            context.buildConstraintViolationWithTemplate("The expense was payed but paymentDate is null.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
