package br.com.sergioluigi.expensecontrolapi.infrastructure.database.repository;

import br.com.sergioluigi.expensecontrolapi.domain.repository.ExistsExpenseByDescriptionAndDueDateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExistsExpenseByDescriptionAndDueDateRepositoryImpl implements ExistsExpenseByDescriptionAndDueDateRepository {

    private final ExpenseRepository expenseRepository;

    @Override
    public Mono<Boolean> execute(String description, LocalDate dueDate) {
        return expenseRepository.existsByDescriptionAndDueDate(description,dueDate);
    }
}
