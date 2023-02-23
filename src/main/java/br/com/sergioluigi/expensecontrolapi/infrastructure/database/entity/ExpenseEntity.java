package br.com.sergioluigi.expensecontrolapi.infrastructure.database.entity;

import br.com.sergioluigi.expensecontrolapi.domain.Expense;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Table("expense")
@EqualsAndHashCode
@NoArgsConstructor
public class ExpenseEntity {

    @Id
    @Column("expense_id")
    private UUID id;

    @Column("description")
    private String description;

    @Column("payment_date")
    private LocalDate paymentDate;

    @Column("due_date")
    private LocalDate dueDate;

    @Column("value")
    private BigDecimal value;

    @Column("payed")
    private Boolean payed;

    @Column("expense_repeater_id")
    private ExpenseRepeaterEntity expenseRepeaterEntity;


    @CreatedDate
    @Column("created_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column("updated_date")
    private LocalDateTime updatedDate;

    public ExpenseEntity(UUID id, String description, LocalDate paymentDate,
                         LocalDate dueDate, BigDecimal value, Boolean payed,
                         ExpenseRepeaterEntity expenseRepeaterEntity,
                         LocalDateTime createdDate,
                         LocalDateTime updatedDate) {
        this.id = id;
        this.description = description;
        this.paymentDate = paymentDate;
        this.dueDate = dueDate;
        this.value = value;
        this.payed = payed;
        this.expenseRepeaterEntity = expenseRepeaterEntity;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public ExpenseEntity(Expense expense) {
        this.id = expense.getId();
        this.description = expense.getDescription();
        this.paymentDate = expense.getPaymentDate();
        this.dueDate = expense.getDueDate();
        this.value = expense.getValue();
        this.payed = expense.getPayed();
        this.expenseRepeaterEntity = new ExpenseRepeaterEntity(expense.getExpenseRepeater());
        this.createdDate = expense.getCreatedDate();
        this.updatedDate = expense.getUpdatedDate();
    }

    public Expense toExpense(){
        return Expense.builder()
                .id(id)
                .description(description)
                .paymentDate(paymentDate)
                .dueDate(dueDate)
                .value(value)
                .payed(payed)
                .expenseRepeater(this.expenseRepeaterEntity.toExpenseRepeater())
                .createdDate(createdDate)
                .updatedDate(updatedDate).build();
    }
}
