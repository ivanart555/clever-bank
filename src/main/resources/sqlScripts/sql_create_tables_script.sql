DROP TABLE IF EXISTS cleverbank.banks CASCADE;

create table cleverbank.banks
(
    id   bigserial not null
        constraint banks_pk
            primary key,
    name varchar   not null
);

DROP TABLE IF EXISTS cleverbank.customers CASCADE;

create table cleverbank.customers
(
    id         bigserial not null
        constraint customers_pk
            primary key,
    first_name varchar   not null,
    last_name  varchar   not null
);

DROP TABLE IF EXISTS cleverbank.accounts CASCADE;

create table cleverbank.accounts
(
    id           bigserial                   not null
        constraint accounts_pkey
            primary key,
    number       varchar                     not null,
    "bank_id"     bigint                      not null
        constraint accounts_banks_id_fk
            references cleverbank.banks,
    "customer_id" bigint                      not null
        constraint accounts_customers_id_fk
            references cleverbank.customers,
    balance      numeric(15, 2) default 0.00 not null
);


DROP TABLE IF EXISTS cleverbank.transactions;

create table cleverbank.transactions
(
    id                       bigserial            not null
        constraint transactions_pk
            primary key,
    date_time                timestamp            not null,
    amount                   numeric default 0.00 not null,
    sender_account_number    varchar,
    recipient_account_number varchar
);


