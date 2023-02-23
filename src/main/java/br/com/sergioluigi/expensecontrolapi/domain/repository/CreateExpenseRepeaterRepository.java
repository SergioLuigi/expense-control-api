package br.com.sergioluigi.expensecontrolapi.domain.repository;

import br.com.sergioluigi.expensecontrolapi.domain.Expense;
import br.com.sergioluigi.expensecontrolapi.domain.ExpenseRepeater;
import reactor.core.publisher.Mono;

import java.util.Collection;

public interface CreateExpenseRepeaterRepository {
    Mono<ExpenseRepeater> execute(ExpenseRepeater expenseRepeater);

}
