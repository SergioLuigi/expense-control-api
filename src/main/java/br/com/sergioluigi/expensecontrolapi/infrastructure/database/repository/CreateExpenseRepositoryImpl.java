package br.com.sergioluigi.expensecontrolapi.infrastructure.database.repository;

import br.com.sergioluigi.expensecontrolapi.domain.Expense;
import br.com.sergioluigi.expensecontrolapi.domain.repository.CreateExpenseRepository;
import br.com.sergioluigi.expensecontrolapi.infrastructure.database.entity.ExpenseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CreateExpenseRepositoryImpl implements CreateExpenseRepository {

    private final ExpenseRepository expenseRepository;

    @Override
    public Flux<Expense> execute(Flux<Expense> expenseFlux) {
        return expenseFlux
                .map(ExpenseEntity::new)
                .publish(expenseRepository::saveAll)
                .map(ExpenseEntity::toExpense);
    }
}
