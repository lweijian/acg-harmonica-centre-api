create table centre_article
(
    article_id   varchar(255) not null,
    title        varchar(30)  null,
    content      text         null,
    read_number  int          null,
    create_time  datetime     null,
    update_time  datetime     null,
    author_name  varchar(255) null,
    status       int          null,
    img_src      varchar(255) null,
    article_info varchar(255) null,
    constraint article_article_id_uindex
        unique (article_id)
);

