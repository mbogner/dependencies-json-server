create table dependencies
(
    id            uuid                        not null
        constraint dependencies_pkey primary key,
    created_at    timestamp without time zone not null default now_utc(),

    maven_group   varchar(128)                not null,
    maven_name    varchar(64)                 not null,
    maven_version varchar(32)                 not null,
    coordinates   varchar(226)                not null
        constraint dependencies_uc__coordinates unique
) without oids;

create trigger dependencies_trg__check_created_at_unchanged
    before update
    on dependencies
    for each row
execute procedure check_created_at_unchanged();
