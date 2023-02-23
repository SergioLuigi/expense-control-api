package br.com.sergioluigi.expensecontrolapi.infrastructure.database.config.converters.write;

import br.com.sergioluigi.expensecontrolapi.infrastructure.database.entity.ExpenseRepeaterEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class ExpenseRepeaterWritingConverter implements Converter<ExpenseRepeaterEntity, OutboundRow> {

    @Override
    public OutboundRow convert(ExpenseRepeaterEntity source) {
        var row = new OutboundRow();
        if(source.getId() != null){
            row.put("expense_repeater_id", Parameter.from(source.getId()));
        }
        row.put("times", Parameter.from(source.getTimes()));
        row.put("frequency", Parameter.from(source.getFrequency()));
        row.put("created_date", Parameter.from(source.getCreatedDate()));
        row.put("updated_date", Parameter.from(source.getUpdatedDate()));



        return row;
    }
}
