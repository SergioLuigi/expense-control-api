package br.com.sergioluigi.expensecontrolapi.domain.exception;

import org.springframework.web.server.ResponseStatusException;

public class ExpenseControlException extends ResponseStatusException {

    public ExpenseControlException(final ExpenseControlError expenseControlError) {
        super(expenseControlError.getHttpStatus(), expenseControlError.getReason());
    }
}
