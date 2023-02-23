package br.com.sergioluigi.expensecontrolapi.infrastructure.database.repository;

import br.com.sergioluigi.expensecontrolapi.infrastructure.database.entity.ExpenseRepeaterEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExpenseRepeaterRepository extends R2dbcRepository<ExpenseRepeaterEntity, UUID> {
}
