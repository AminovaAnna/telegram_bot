-- liquibase formatted sql

-- changeset annaa:1

CREATE TABLE notification_task (
    id SERIAL unique PRIMARY KEY,
    chat_id SERIAL,
    text_msg TEXT,
    notification_time TIMESTAMP
);

-- changeset annaa:2
ALTER TABLE notification_task ALTER COLUMN chat_id TYPE BIGINT;
ALTER TABLE notification_task ALTER COLUMN chat_id TYPE VARCHAR;

-- changeset annaa:3
ALTER TABLE notification_task
RENAME COLUMN notification_time TO date;

-- changeset annaa:4
ALTER TABLE notification_task
RENAME COLUMN date TO taskDate;

-- changeset annaa:5
ALTER TABLE notification_task
RENAME COLUMN taskDate TO task_date;

-- changeset annaa:6
ALTER TABLE notification_task
RENAME COLUMN task_date TO date;