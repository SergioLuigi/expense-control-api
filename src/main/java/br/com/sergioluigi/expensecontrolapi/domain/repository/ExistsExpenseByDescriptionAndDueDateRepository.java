package br.com.sergioluigi.expensecontrolapi.domain.repository;

import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ExistsExpenseByDescriptionAndDueDateRepository {
    Mono<Boolean> execute(String description, LocalDate dueDate);
}
