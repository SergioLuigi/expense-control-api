--liquibase formatted sql
--changeset sergioluigi:create-table
CREATE TABLE expense(
    expense_id UUID DEFAULT uuid_generate_v4(),
    description VARCHAR(50) NOT NULL,
    payment_date DATE NOT NULL,
    due_date DATE NOT NULL,
    value NUMERIC(12,2) NOT NULL,
    payed BOOLEAN NOT NULL,
    expense_repeater_id UUID,
    created_date timestamp NOT NULL,
    updated_date timestamp NOT NULL
);

ALTER TABLE expense ADD CONSTRAINT expense_id_pk PRIMARY KEY (expense_id);

ALTER TABLE expense ADD CONSTRAINT expense_expense_repeater_id_FK
    FOREIGN KEY (expense_repeater_id) REFERENCES expense_repeater(expense_repeater_id);

CREATE UNIQUE INDEX expense_description_due_date_unique_index ON expense (description, due_date);