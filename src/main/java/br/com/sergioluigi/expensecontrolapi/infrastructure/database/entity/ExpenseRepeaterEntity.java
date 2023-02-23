package br.com.sergioluigi.expensecontrolapi.infrastructure.database.entity;

import br.com.sergioluigi.expensecontrolapi.domain.ExpenseRepeater;
import br.com.sergioluigi.expensecontrolapi.domain.Frequency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@Table("expense_repeater")
public class ExpenseRepeaterEntity implements Persistable<UUID> {

    @Id
    @Column("expense_repeater_id")
    private UUID id;

    @Column("times")
    private Integer times;

    @Column("frequency")
    private Frequency frequency;

    @Transient
    private Set<ExpenseEntity> expenses;

    @CreatedDate
    @Column("created_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column("updated_date")
    private LocalDateTime updatedDate;

    public ExpenseRepeaterEntity(ExpenseRepeater expenseRepeater) {
        this.id = expenseRepeater.getId();
        this.times = expenseRepeater.getTimes();
        this.frequency = expenseRepeater.getFrequency();
        this.createdDate = expenseRepeater.getCreatedDate();
        this.updatedDate = expenseRepeater.getUpdatedDate();
    }

    public ExpenseRepeater toExpenseRepeater(){
        return ExpenseRepeater.builder()
                .id(id)
                .times(times)
                .frequency(frequency)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }

    @Override
    @Transient
    public boolean isNew() {
        return id == null;
    }
}