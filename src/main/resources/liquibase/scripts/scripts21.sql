-- liquibase formatted sql

-- changeset annaa:1

CREATE TABLE notification_task (
    id SERIAL unique PRIMARY KEY,
    chat_id SERIAL,
    text_msg TEXT,
    notification_time TIMESTAMP
);