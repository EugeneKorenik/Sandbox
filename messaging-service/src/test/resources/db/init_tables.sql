create table users
(
    id       uuid primary key,
    login    varchar(256) unique not null,
    password varchar(256)        not null
);

create table account
(
    id          uuid primary key,
    first_name  varchar(256) not null,
    second_name varchar(256) not null,
    birthday    date,
    user_id     uuid references users (id)
);

create table dialogue
(
    id         uuid primary key,
    name       varchar(256) not null,
    created_by uuid references account (id)
);

create table message
(
    id                uuid primary key,
    text              text not null,
    dialogue_id       uuid references dialogue (id),
    sender_account_id uuid references account (id),
    parent_message_id uuid references message (id)
);

create table message_watcher
(
    target_id  uuid references message (id),
    watcher_id uuid references account (id),
    deleted    bool not null default false
);

create table dialogue_watcher
(
    target_id  uuid not null references dialogue (id),
    watcher_id uuid not null references account (id),
    deleted    bool not null default false
);