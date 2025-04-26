create table dependency_usages
(
    id                uuid                        not null
        constraint dependency_usages_pkey primary key,
    created_at        timestamp without time zone not null default now_utc(),

    project_import_id uuid                        not null
        constraint dependency_usages_fk__project_import_id references project_imports (id) on delete restrict on update restrict,
    configuration_id  uuid                        not null
        constraint dependency_usages_fk__configuration_id references configurations (id) on delete restrict on update restrict,
    dependency_id     uuid                        not null
        constraint dependency_usages_fk__dependency_id references dependencies (id) on delete restrict on update restrict

) without oids;

create trigger dependency_usages_trg__check_created_at_unchanged
    before update
    on dependency_usages
    for each row
execute procedure check_created_at_unchanged();
