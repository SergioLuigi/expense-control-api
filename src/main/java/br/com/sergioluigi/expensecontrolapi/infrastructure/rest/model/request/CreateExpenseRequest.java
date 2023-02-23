package br.com.sergioluigi.expensecontrolapi.infrastructure.rest.model.request;

import br.com.sergioluigi.expensecontrolapi.domain.Expense;
import br.com.sergioluigi.expensecontrolapi.infrastructure.rest.validation.NotPayed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@NotPayed
public record CreateExpenseRequest(
        @NotBlank
        @Size(min = 3,max = 50)
        String description,

        LocalDate paymentDate,

        @NotNull
        LocalDate dueDate,

        @Positive
        BigDecimal value,

        @NotNull
        Boolean payed,

        @Valid
        CreateExpenseRepeaterRequest expenseRepeater
) {
    public Expense toExpense(){
        return Expense.builder()
                .description(description)
                .dueDate(dueDate)
                .paymentDate(paymentDate)
                .value(value)
                .payed(payed)
                .expenseRepeater(expenseRepeater.toExpenseRepeater())
                .build();
    }
}
