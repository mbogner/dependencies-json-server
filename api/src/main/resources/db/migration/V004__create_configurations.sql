create table configurations
(
    id         uuid                        not null
        constraint configurations_pkey primary key,
    created_at timestamp without time zone not null default now_utc(),

    name       varchar(64)                 not null
        constraint configurations_uc__name unique
) without oids;

create trigger configurations_trg__check_created_at_unchanged
    before update
    on configurations
    for each row
execute procedure check_created_at_unchanged();
