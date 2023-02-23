package br.com.sergioluigi.expensecontrolapi.infrastructure.database.repository;

import br.com.sergioluigi.expensecontrolapi.infrastructure.database.entity.ExpenseEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends R2dbcRepository<ExpenseEntity, UUID> {

    Mono<Boolean> existsByDescriptionAndDueDate(String description, LocalDate dueDate);
}
