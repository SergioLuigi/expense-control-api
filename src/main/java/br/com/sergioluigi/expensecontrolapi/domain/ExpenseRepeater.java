package br.com.sergioluigi.expensecontrolapi.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ExpenseRepeater {
    private UUID id;


    private int times;

    private Frequency frequency;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
