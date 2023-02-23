package br.com.sergioluigi.expensecontrolapi.domain.repository;

import br.com.sergioluigi.expensecontrolapi.domain.Expense;
import reactor.core.publisher.Flux;

public interface CreateExpenseRepository {
    Flux<Expense> execute(Flux<Expense> expenseFlux);
}
