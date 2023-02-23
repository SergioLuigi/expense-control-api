package br.com.sergioluigi.expensecontrolapi.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Expense implements Cloneable{

    private UUID id;

    private String description;

    private LocalDate paymentDate;

    private LocalDate dueDate;

    private BigDecimal value;

    private Boolean payed;

    private ExpenseRepeater expenseRepeater;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @Override
    public Expense clone() {
        try {
            return (Expense) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
