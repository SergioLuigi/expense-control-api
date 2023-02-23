package br.com.sergioluigi.expensecontrolapi.domain.usecase;

import br.com.sergioluigi.expensecontrolapi.domain.Expense;
import br.com.sergioluigi.expensecontrolapi.domain.exception.ExpenseControlException;
import br.com.sergioluigi.expensecontrolapi.domain.repository.CreateExpenseRepeaterRepository;
import br.com.sergioluigi.expensecontrolapi.domain.repository.CreateExpenseRepository;
import br.com.sergioluigi.expensecontrolapi.domain.repository.ExistsExpenseByDescriptionAndDueDateRepository;
import br.com.sergioluigi.expensecontrolapi.domain.repository.JoinExpenseWithExpenseRepeaterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Predicate;

import static br.com.sergioluigi.expensecontrolapi.domain.exception.ExpenseControlError.EXPENSE_ALREADY_EXISTS;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateExpenseUseCaseImpl implements CreateExpenseUseCase{

    private final ExistsExpenseByDescriptionAndDueDateRepository existsExpenseByDescriptionAndDueDate;

    private final CreateExpenseRepository createExpense;

    private final CreateExpenseRepeaterRepository createExpenseRepeater;

    private final JoinExpenseWithExpenseRepeaterRepository joinExpenseWithExpenseRepeater;

    @Override
    @Transactional
    public Flux<Expense> execute(Expense expense) {
        return Flux.just(expense)
                .filterWhen(this::isUnique)
                .switchIfEmpty(Flux.defer(() -> Flux.error(new ExpenseControlException(EXPENSE_ALREADY_EXISTS))))
                .flatMap(this::prepareExpenses)
                .publish(createExpense::execute)
                .publish(createExpenseRepeaterWhenNeeded(expense));
    }

    private Predicate<Expense> isExpenseRepeaterNeeded(Expense expense) {
        return exp -> expense.getExpenseRepeater() != null;
    }

    private Function<Flux<Expense>, Flux<Expense>> createExpenseRepeaterWhenNeeded(Expense expense) {
        return expenseFlux ->
                expenseFlux.filter(isExpenseRepeaterNeeded(expense))
                        .switchIfEmpty(expenseFlux)
                        .collectList()
                        .zipWhen(expenses -> createExpenseRepeater.execute(expense.getExpenseRepeater()))
                        .flatMapMany(tuple2 -> joinExpenseWithExpenseRepeater.execute(tuple2.getT1(),tuple2.getT2()));
    }

    private Mono<Boolean> isUnique(Expense expense) {
        return existsExpenseByDescriptionAndDueDate.execute(expense.getDescription(),expense.getDueDate()).map(aBoolean -> !aBoolean);
    }

    private Flux<Expense> prepareExpenses(Expense expense) {
        return Flux.just(expense)
                .filter(isExpenseRepeaterNeeded(expense))
                .flatMap(this::createNextExpenses)
                .switchIfEmpty(Flux.just(expense));
    }

    private Flux<Expense> createNextExpenses(Expense expense){
        return Flux.range(0, expense.getExpenseRepeater().getTimes())
                .flatMap(index -> {
                    if(index == 0){
                        return Mono.just(expense);
                    }
                    switch (expense.getExpenseRepeater().getFrequency()){
                        case DAILY -> expense.setDueDate(expense.getDueDate().plusDays(1));
                        case WEEKLY -> expense.setDueDate(expense.getDueDate().plusDays(7));
                        case MONTHLY -> expense.setDueDate(expense.getDueDate().plusMonths(1));
                        case YEARLY -> expense.setDueDate(expense.getDueDate().plusYears(1));
                    }
                    return Mono.just(expense);
                });
    }

}
