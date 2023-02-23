package br.com.sergioluigi.expensecontrolapi.infrastructure.database.repository;

import br.com.sergioluigi.expensecontrolapi.domain.Expense;
import br.com.sergioluigi.expensecontrolapi.domain.ExpenseRepeater;
import br.com.sergioluigi.expensecontrolapi.domain.repository.JoinExpenseWithExpenseRepeaterRepository;
import br.com.sergioluigi.expensecontrolapi.infrastructure.database.entity.ExpenseEntity;
import br.com.sergioluigi.expensecontrolapi.infrastructure.database.entity.ExpenseRepeaterEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Collection;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JoinExpenseWithExpenseRepeaterRepositoryImpl implements JoinExpenseWithExpenseRepeaterRepository {

    private final DatabaseClient databaseClient;

    @Override
    public Flux<Expense> execute(Collection<Expense> expenses, ExpenseRepeater expenseRepeater) {
        return databaseClient.sql("UPDATE expense SET expense_repeater_id = :expenseRepeaterId where expense_id in (:ids)")
                .bind("expenseRepeaterId",expenseRepeater.getId())
                .bind("ids",expenses.stream().map(Expense::getId).toList())
                .fetch()
                .rowsUpdated()
                .doOnNext(aLong -> expenses.forEach(expense -> expense.setExpenseRepeater(expenseRepeater)))
                .flatMapIterable(aLong -> expenses);
    }

}
