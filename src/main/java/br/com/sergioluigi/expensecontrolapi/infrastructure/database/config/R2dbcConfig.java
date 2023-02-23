package br.com.sergioluigi.expensecontrolapi.infrastructure.database.config;

import br.com.sergioluigi.expensecontrolapi.infrastructure.database.config.converters.read.ExpenseReadingConverter;
import br.com.sergioluigi.expensecontrolapi.infrastructure.database.config.converters.write.ExpenseRepeaterWritingConverter;
import br.com.sergioluigi.expensecontrolapi.infrastructure.database.config.converters.write.ExpenseWritingConverter;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.ReactiveTransactionManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableR2dbcAuditing
@EnableR2dbcRepositories
public class R2dbcConfig {

    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
            return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public R2dbcCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new ExpenseReadingConverter());
        converters.add(new ExpenseWritingConverter());
        converters.add(new ExpenseRepeaterWritingConverter());
        return R2dbcCustomConversions.of(PostgresDialect.INSTANCE, converters);
    }

    @Bean
    public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                .namedParameters(true)
                .build();
    }
}
