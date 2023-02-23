package br.com.sergioluigi.expensecontrolapi.infrastructure.database.config.converters.write;

import br.com.sergioluigi.expensecontrolapi.infrastructure.database.entity.ExpenseEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class ExpenseWritingConverter  implements Converter<ExpenseEntity, OutboundRow> {

    @Override
    public OutboundRow convert(ExpenseEntity source) {
        var row = new OutboundRow();
        if(source.getId() != null){
            row.put("expense_id", Parameter.from(source.getId()));
        }

        if(source.getExpenseRepeaterEntity() != null && source.getExpenseRepeaterEntity().getId() != null){
            row.put("expense_repeater_id", Parameter.from(source.getExpenseRepeaterEntity().getId()));
        }


        row.put("description", Parameter.from(source.getDescription()));
        row.put("payment_date", Parameter.from(source.getPaymentDate()));
        row.put("due_date", Parameter.from(source.getDueDate()));
        row.put("payed", Parameter.from(source.getPayed()));
        row.put("value", Parameter.from(source.getValue()));
        row.put("created_date", Parameter.from(source.getCreatedDate()));
        row.put("updated_date", Parameter.from(source.getUpdatedDate()));

        return row;
    }
}
