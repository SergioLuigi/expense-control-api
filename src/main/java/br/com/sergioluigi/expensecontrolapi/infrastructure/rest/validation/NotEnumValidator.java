package br.com.sergioluigi.expensecontrolapi.infrastructure.rest.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NotEnumValidator implements ConstraintValidator<NotEnum, String> {

    private List<String> validNames;

    @Override
    public void initialize(NotEnum constraintAnnotation) {

        validNames = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value == null){
            return true;
        }

        return validNames.contains(value);
    }
}
