package br.com.sergioluigi.expensecontrolapi.domain.usecase;

import br.com.sergioluigi.expensecontrolapi.domain.Expense;
import reactor.core.publisher.Flux;

public interface CreateExpenseUseCase {
    Flux<Expense> execute(Expense expense);
}
