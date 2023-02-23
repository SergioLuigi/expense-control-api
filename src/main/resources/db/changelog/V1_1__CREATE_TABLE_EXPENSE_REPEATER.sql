--liquibase formatted sql
--changeset sergioluigi:create-table
CREATE TABLE expense_repeater(
  expense_repeater_id UUID DEFAULT uuid_generate_v4(),
  times INT NOT NULL,
  frequency VARCHAR(50) NOT NULL,
  created_date timestamp NOT NULL,
  updated_date timestamp NOT NULL
);

ALTER TABLE expense_repeater ADD CONSTRAINT expense_repeater_id_pk PRIMARY KEY (expense_repeater_id);