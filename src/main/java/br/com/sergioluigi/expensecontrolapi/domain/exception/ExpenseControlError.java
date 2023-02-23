package br.com.sergioluigi.expensecontrolapi.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExpenseControlError {

    EXPENSE_ALREADY_EXISTS(HttpStatus.CONFLICT,"expense.already.exists");

    private final HttpStatus httpStatus;

    private final String reason;
}
