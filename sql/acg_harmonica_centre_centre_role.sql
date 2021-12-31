create table centre_role
(
    role_id   varchar(255) not null,
    role_name varchar(255) not null,
    constraint centre_role_role_name_uindex
        unique (role_id),
    constraint centre_role_role_name_uindex_2
        unique (role_name)
);

alter table centre_role
    add primary key (role_id);

INSERT INTO acg_harmonica_centre.centre_role (role_id, role_name) VALUES ('1391776617839198210', 'admin');
INSERT INTO acg_harmonica_centre.centre_role (role_id, role_name) VALUES ('1391776617839198211', 'test');
INSERT INTO acg_harmonica_centre.centre_role (role_id, role_name) VALUES ('1391776829827764225', 'user');