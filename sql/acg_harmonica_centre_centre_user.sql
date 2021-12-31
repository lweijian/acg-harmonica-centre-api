create table centre_user
(
    user_id   varchar(255) not null,
    username  varchar(255) not null,
    password  varchar(255) not null,
    role_id   varchar(255) not null,
    role_name varchar(255) not null,
    constraint centre_user_user_id_uindex
        unique (user_id),
    constraint centre_user_user_name_uindex
        unique (username)
);

alter table centre_user
    add primary key (user_id);

INSERT INTO acg_harmonica_centre.centre_user (user_id, username, password, role_id, role_name) VALUES ('1391777610421297153', 'admin', '$2a$10$eD.oly3neLqy/hrn51j/6eXc8bspuKk8yRoOjaNN4pIlC/c91bVmm', '1391776617839198210', 'admin');
INSERT INTO acg_harmonica_centre.centre_user (user_id, username, password, role_id, role_name) VALUES ('1391777761105932290', 'user', '$2a$10$8tKq.R/8SAU4Nswgyym.Euy090F5.OwuFAU8ah8lJI7jgWr.gbQ3S', '1391776617839198211', 'user');
INSERT INTO acg_harmonica_centre.centre_user (user_id, username, password, role_id, role_name) VALUES ('1412013300773863425', 'test', '$2a$10$/i2WIywmFEIezqPwEyack.l.E5OUjwMytBkLNjUaLXYHOztcFG/vO', '1391776829827764225', 'test');