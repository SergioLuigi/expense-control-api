package br.com.sergioluigi.expensecontrolapi.infrastructure.rest;

import br.com.sergioluigi.expensecontrolapi.domain.usecase.CreateExpenseUseCase;
import br.com.sergioluigi.expensecontrolapi.infrastructure.rest.model.request.CreateExpenseRequest;
import br.com.sergioluigi.expensecontrolapi.infrastructure.rest.model.response.ExpenseResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping("/expenses")
public class ExpenseController {

    private final CreateExpenseUseCase createExpenseUseCase;

    @PostMapping
    @ResponseStatus(CREATED)
    public Flux<ExpenseResponse> create(@RequestBody @Valid CreateExpenseRequest createExpenseRequest){
        return Mono.just(createExpenseRequest)
                .map(CreateExpenseRequest::toExpense)
                .flatMapMany(createExpenseUseCase::execute)
                .map(ExpenseResponse::new);
    }

}
