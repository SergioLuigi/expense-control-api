package br.com.sergioluigi.expensecontrolapi.infrastructure.exception;

import br.com.sergioluigi.expensecontrolapi.domain.exception.ExpenseControlException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@Component
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected Mono<ResponseEntity<Object>> handleWebExchangeBindException(WebExchangeBindException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatusCode status,
                                                                          ServerWebExchange exchange) {
        log.error("Invalid request exception occurred", ex);

        var validationErrors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .filter(Objects::nonNull)
                .collect(validationErrorMapBuilder());

        var error = ErrorDTO.builder()
                .path(exchange.getRequest().getPath().value())
                .status(status.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .validationErrors(validationErrors)
                .requestId(exchange.getRequest().getId()).build();

        return Mono.just(ResponseEntity.badRequest().body(error));
    }

    @ExceptionHandler(ExpenseControlException.class)
    public Mono<ResponseEntity<ErrorDTO>> expenseControlException(ExpenseControlException ex,
                                                                  ServerWebExchange exchange){
        var error = ErrorDTO.builder()
                .path(exchange.getRequest().getPath().value())
                .status(ex.getStatusCode().value())
                .error(HttpStatus.valueOf(ex.getStatusCode().value()).getReasonPhrase())
                .message(ex.getReason())
                .requestId(exchange.getRequest().getId()).build();

        return Mono.just(ResponseEntity.status(ex.getStatusCode().value()).body(error));
    }

    private static Collector<ObjectError, ?, Map<String, String>> validationErrorMapBuilder() {
        return Collectors.toMap(objectError -> {
            if (objectError instanceof FieldError fe) {
                return fe.getField();
            }
            return objectError.getObjectName();
        }, objectError -> Optional.ofNullable(objectError.getDefaultMessage())
                .orElse("Error not mapped."));
    }
}
