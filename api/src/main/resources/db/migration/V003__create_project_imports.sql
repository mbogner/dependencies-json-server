create table project_imports
(
    id            uuid                        not null
        constraint project_imports_pkey primary key,
    created_at    timestamp without time zone not null default now_utc(),

    project_id    uuid                        not null
        constraint project_imports_fk__project_id references projects (id) on delete restrict on update restrict,

    maven_group   varchar(128)                not null,
    maven_name    varchar(64)                 not null,
    maven_version varchar(32)                 not null,
    coordinates   varchar(226)                not null,

    import_hash   varchar(512)                not null
        constraint project_imports_uc__import_hash unique
) without oids;

CREATE INDEX project_imports_i__coordinates ON project_imports (coordinates);

create trigger project_imports_trg__check_created_at_unchanged
    before update
    on project_imports
    for each row
execute procedure check_created_at_unchanged();
