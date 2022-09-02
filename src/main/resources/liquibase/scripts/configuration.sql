-- liquibase formatted sql

-- changeset skaplin:1
create table question_answer
(
    id       bigserial
        constraint question_answer_pk
            primary key,
    question text not null,
    answer   text not null
);
create table call_back
(
    id           bigserial
        constraint call_back_pk
            primary key,
    chat_id      bigint not null,
    contact_name text   not null,
    telephone    text   not null,
    is_ok        bool default false
);
create table "user"
(
    id         bigserial
        constraint user_pk
            primary key,
    chat_id    bigint             not null,
    user_name  text               not null,
    date_begin date               not null,
    extra_day  int  default 0,
    is_end     bool default false not null
);

create table report
(
    id         bigserial not null
        constraint id
            primary key,
    "user"     bigint    not null
        constraint user_fk
            references "user" (id),
    photo_path text      not null,
    diet       text,
    mood       text,
    change     text
);
