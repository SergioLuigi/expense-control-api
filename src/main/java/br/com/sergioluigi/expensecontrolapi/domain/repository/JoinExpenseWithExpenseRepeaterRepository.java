package br.com.sergioluigi.expensecontrolapi.domain.repository;

import br.com.sergioluigi.expensecontrolapi.domain.Expense;
import br.com.sergioluigi.expensecontrolapi.domain.ExpenseRepeater;
import reactor.core.publisher.Flux;

import java.util.Collection;
import java.util.UUID;

public interface JoinExpenseWithExpenseRepeaterRepository {
    Flux<Expense> execute(Collection<Expense> expenses, ExpenseRepeater expenseRepeater);
}
