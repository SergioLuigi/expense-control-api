package br.com.sergioluigi.expensecontrolapi.infrastructure.database.repository;

import br.com.sergioluigi.expensecontrolapi.domain.ExpenseRepeater;
import br.com.sergioluigi.expensecontrolapi.domain.repository.CreateExpenseRepeaterRepository;
import br.com.sergioluigi.expensecontrolapi.infrastructure.database.entity.ExpenseRepeaterEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CreateExpenseRepeaterRepositoryImpl implements CreateExpenseRepeaterRepository {

    private final ExpenseRepeaterRepository expenseRepeaterRepository;

    @Override
    public Mono<ExpenseRepeater> execute(ExpenseRepeater expenseRepeater) {

        return Mono.just(expenseRepeater)
                .map(ExpenseRepeaterEntity::new)
                .flatMap(expenseRepeaterRepository::save)
                .map(ExpenseRepeaterEntity::toExpenseRepeater);




    }
}
