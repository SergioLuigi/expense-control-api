package br.com.sergioluigi.expensecontrolapi.infrastructure.rest.model.request;

import br.com.sergioluigi.expensecontrolapi.domain.ExpenseRepeater;
import br.com.sergioluigi.expensecontrolapi.domain.Frequency;
import br.com.sergioluigi.expensecontrolapi.infrastructure.rest.validation.NotEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record CreateExpenseRepeaterRequest(

        @Positive
        int times,

        @NotNull
        @NotEnum(enumClass = Frequency.class, message = "invalid frequency")
        String frequency
){
    public ExpenseRepeater toExpenseRepeater(){
        return ExpenseRepeater.builder()
                .times(times)
                .frequency(Frequency.valueOf(frequency))
                .build();

    }
}
