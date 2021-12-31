create table music_video
(
    id        varchar(255) not null,
    video_url varchar(255) not null,
    img_src   varchar(255) not null,
    len_time  varchar(255) not null,
    date      datetime     not null,
    info      varchar(255) not null,
    constraint music_video_id_uindex
        unique (id)
);

alter table music_video
    add primary key (id);

INSERT INTO acg_harmonica_centre.music_video (id, video_url, img_src, len_time, date, info) VALUES ('1399382935424999425', '//www.bilibili.com/video/BV1Gx411p7YB', 'http://localhost:3000/img/video/video1.png', '51:31', '2017-08-04 16:00:00', '【口琴合辑】ACG口琴小苑年度企划第二辑【以琴为契，于茫茫人海中遇见你】');
INSERT INTO acg_harmonica_centre.music_video (id, video_url, img_src, len_time, date, info) VALUES ('1399383046787964929', '//www.bilibili.com/video/BV1T7411i7Uq', 'http://localhost:3000/img/video/video3.png', '65:56', '2020-01-23 16:00:00', 'Re: Monico の 异世界音乐会——2020口琴拜年祭');
INSERT INTO acg_harmonica_centre.music_video (id, video_url, img_src, len_time, date, info) VALUES ('1399383227512135682', '//www.bilibili.com/video/BV1Ds411E7WQ', 'http://localhost:3000/img/video/video2.png', '03:57', '2018-06-13 16:00:00', 'A苑成都线下交流');
INSERT INTO acg_harmonica_centre.music_video (id, video_url, img_src, len_time, date, info) VALUES ('1399383282101002241', '//www.bilibili.com/video/BV11W411t7vi', 'http://localhost:3000/img/video/video4.png', '193:07', '2017-12-25 16:00:00', '【口琴合辑】年度巨制！B站史上最大口琴演奏策划——ACG口琴小苑圣诞祭踏雪相迎！');
INSERT INTO acg_harmonica_centre.music_video (id, video_url, img_src, len_time, date, info) VALUES ('1399386953895481345', '//www.bilibili.com/video/BV11W411t7vi', 'http://localhost:3000/img/video/video4.png', '193:07', '2017-12-25 16:00:00', '【口琴合辑】年度巨制！B站史上最大口琴演奏策划——ACG口琴小苑圣诞祭踏雪相迎！');
INSERT INTO acg_harmonica_centre.music_video (id, video_url, img_src, len_time, date, info) VALUES ('1399386961306816514', '//www.bilibili.com/video/BV11W411t7vi', 'http://localhost:3000/img/video/video4.png', '193:07', '2017-12-25 16:00:00', '【口琴合辑】年度巨制！B站史上最大口琴演奏策划——ACG口琴小苑圣诞祭踏雪相迎！');
INSERT INTO acg_harmonica_centre.music_video (id, video_url, img_src, len_time, date, info) VALUES ('1399386966507753473', '//www.bilibili.com/video/BV11W411t7vi', 'http://localhost:3000/img/video/video4.png', '193:07', '2017-12-25 16:00:00', '【口琴合辑】年度巨制！B站史上最大口琴演奏策划——ACG口琴小苑圣诞祭踏雪相迎！');
INSERT INTO acg_harmonica_centre.music_video (id, video_url, img_src, len_time, date, info) VALUES ('1399386976246927361', '//www.bilibili.com/video/BV11W411t7vi', 'http://localhost:3000/img/video/video4.png', '193:07', '2017-12-25 16:00:00', '【口琴合辑】年度巨制！B站史上最大口琴演奏策划——ACG口琴小苑圣诞祭踏雪相迎！');
INSERT INTO acg_harmonica_centre.music_video (id, video_url, img_src, len_time, date, info) VALUES ('1399386982005706754', '//www.bilibili.com/video/BV11W411t7vi', 'http://localhost:3000/img/video/video4.png', '193:07', '2017-12-25 16:00:00', '【口琴合辑】年度巨制！B站史上最大口琴演奏策划——ACG口琴小苑圣诞祭踏雪相迎！');
INSERT INTO acg_harmonica_centre.music_video (id, video_url, img_src, len_time, date, info) VALUES ('1399386987382804481', '//www.bilibili.com/video/BV11W411t7vi', 'http://localhost:3000/img/video/video4.png', '193:07', '2017-12-25 16:00:00', '【口琴合辑】年度巨制！B站史上最大口琴演奏策划——ACG口琴小苑圣诞祭踏雪相迎！');
INSERT INTO acg_harmonica_centre.music_video (id, video_url, img_src, len_time, date, info) VALUES ('1399386992923480065', '//www.bilibili.com/video/BV11W411t7vi', 'http://localhost:3000/img/video/video4.png', '193:07', '2017-12-25 16:00:00', '【口琴合辑】年度巨制！B站史上最大口琴演奏策划——ACG口琴小苑圣诞祭踏雪相迎！');
INSERT INTO acg_harmonica_centre.music_video (id, video_url, img_src, len_time, date, info) VALUES ('1399386998627733506', '//www.bilibili.com/video/BV11W411t7vi', 'http://localhost:3000/img/video/video4.png', '193:07', '2017-12-25 16:00:00', '【口琴合辑】年度巨制！B站史上最大口琴演奏策划——ACG口琴小苑圣诞祭踏雪相迎！');
INSERT INTO acg_harmonica_centre.music_video (id, video_url, img_src, len_time, date, info) VALUES ('1399387075312193538', '//www.bilibili.com/video/BV11W411t7vi', 'http://localhost:3000/img/video/video4.png', '193:07', '2017-12-25 16:00:00', '【口琴合辑】年度巨制！B站史上最大口琴演奏策划——ACG口琴小苑圣诞祭踏雪相迎！');