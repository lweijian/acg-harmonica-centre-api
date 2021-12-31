create table user_info
(
    user_id   varchar(255) not null,
    username  varchar(255) not null,
    signature varchar(255) null,
    role_name varchar(255) not null,
    constraint user_info_user_id_uindex
        unique (user_id),
    constraint user_info_username_uindex
        unique (username),
    constraint user_info_centre_user_user_id_fk
        foreign key (user_id) references centre_user (user_id)
            on update cascade on delete cascade
);

alter table user_info
    add primary key (user_id);

INSERT INTO acg_harmonica_centre.user_info (user_id, username, signature, role_name) VALUES ('1391777610421297153', 'admin', '好运来啊好运来', 'admin');
INSERT INTO acg_harmonica_centre.user_info (user_id, username, signature, role_name) VALUES ('1391777761105932290', 'user', '我是一个user', 'user');
INSERT INTO acg_harmonica_centre.user_info (user_id, username, signature, role_name) VALUES ('1412013300773863425', 'test', 'signature', 'user');