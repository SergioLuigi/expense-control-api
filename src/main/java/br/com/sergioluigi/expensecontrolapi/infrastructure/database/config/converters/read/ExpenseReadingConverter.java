package br.com.sergioluigi.expensecontrolapi.infrastructure.database.config.converters.read;

import br.com.sergioluigi.expensecontrolapi.domain.Frequency;
import br.com.sergioluigi.expensecontrolapi.infrastructure.database.entity.ExpenseEntity;
import br.com.sergioluigi.expensecontrolapi.infrastructure.database.entity.ExpenseRepeaterEntity;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@ReadingConverter
public class ExpenseReadingConverter implements Converter<Row, ExpenseEntity> {

    @Override
    public ExpenseEntity convert(Row source) {
        var repeater = ExpenseRepeaterEntity.builder()
                .id(source.get("repeater_id", UUID.class))
                .times(source.get("times",Integer.class))
                .frequency(source.get("frequency", Frequency.class))
                .createdDate(source.get("created_date", LocalDateTime.class))
                .updatedDate(source.get("updated_time",LocalDateTime.class))
                .build();

        return ExpenseEntity.builder()
                .id(source.get("expense_id",UUID.class))
                .description(source.get("description",String.class))
                .value(source.get("value", BigDecimal.class))
                .dueDate(source.get("due_date", LocalDate.class))
                .paymentDate(source.get("payment_date", LocalDate.class))
                .createdDate(source.get("created_date", LocalDateTime.class))
                .updatedDate(source.get("updated_time",LocalDateTime.class))
                .expenseRepeaterEntity(repeater)
                .build();
    }
}
