create table projects
(
    id         uuid                        not null
        constraint projects_pkey primary key,
    created_at timestamp without time zone not null default now_utc(),

    key        varchar(64)                 not null
        constraint projects_uc__key unique
) without oids;

create trigger projects_trg__check_created_at_unchanged
    before update
    on projects
    for each row
execute procedure check_created_at_unchanged();
