package br.com.sergioluigi.expensecontrolapi.infrastructure.rest.model.response;

import br.com.sergioluigi.expensecontrolapi.domain.Expense;
import br.com.sergioluigi.expensecontrolapi.domain.ExpenseRepeater;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


public record ExpenseResponse(
                            UUID id,
                            String description,
                            LocalDate paymentDate,
                            LocalDate dueDate,
                            BigDecimal value,
                            Boolean payed,
                            ExpenseRepeater expenseRepeater,
                            LocalDateTime createdDate,
                            LocalDateTime updatedDate){

    public ExpenseResponse(Expense expense){
        this(expense.getId(),
        expense.getDescription(),
        expense.getPaymentDate(),
        expense.getDueDate(),
        expense.getValue(),
        expense.getPayed(),
        expense.getExpenseRepeater(),
        expense.getCreatedDate(),
        expense.getUpdatedDate());
    }
}
