create table sheet_music
(
    id           varchar(255)  not null,
    read_number  int default 0 not null,
    img_src      varchar(255)  null,
    music_name   varchar(255)  not null,
    music_author varchar(255)  null,
    music_href   varchar(255)  not null,
    constraint sheet_music_id_uindex
        unique (id)
);

alter table sheet_music
    add primary key (id);

INSERT INTO acg_harmonica_centre.sheet_music (id, read_number, img_src, music_name, music_author, music_href) VALUES ('1399265938552930306', 5238, 'http://www.skizhou.com/statics/notation/images/201907022afe009e.jpeg', 'Secret Base', '那朵花ED', 'http://www.skizhou.com/statics/notation/images/20190702d634e930.png');
INSERT INTO acg_harmonica_centre.sheet_music (id, read_number, img_src, music_name, music_author, music_href) VALUES ('1399266487327277057', 2949, 'http://www.skizhou.com/statics/notation/images/20190702f87aff6c.jpeg', '突然起来的爱情', '东京爱情故事', 'http://www.skizhou.com/statics/notation/images/201907022c45c5cf.png');
INSERT INTO acg_harmonica_centre.sheet_music (id, read_number, img_src, music_name, music_author, music_href) VALUES ('1399266719607832577', 5238, 'http://www.skizhou.com/statics/notation/images/2019070235c3574d.jpg', '樱花樱花想见你', '清明樱花祭', 'http://www.skizhou.com/statics/notation/images/20190702ee76b33b.png');
INSERT INTO acg_harmonica_centre.sheet_music (id, read_number, img_src, music_name, music_author, music_href) VALUES ('1399267157539307521', 3686, 'http://www.skizhou.com/statics/notation/images/2019070324c03d2b.jpeg', 'ヤキモチ', '高桥优', 'http://www.skizhou.com/statics/notation/images/20190703b60b12ae.png');
INSERT INTO acg_harmonica_centre.sheet_music (id, read_number, img_src, music_name, music_author, music_href) VALUES ('1399267732955873281', 8055, 'http://www.skizhou.com/statics/notation/images/2019070307a55cb5.jpg', '宫崎骏组曲', '宫崎骏组曲', 'http://www.skizhou.com/statics/notation/images/2019070370ddf56f.png');
INSERT INTO acg_harmonica_centre.sheet_music (id, read_number, img_src, music_name, music_author, music_href) VALUES ('1399267908688822273', 1706, 'http://www.skizhou.com/statics/notation/images/20190703efce26d4.jpeg', '镇命歌', '晨曦时梦见兮ED', 'http://www.skizhou.com/statics/notation/images/201907030f8f7410.png');
INSERT INTO acg_harmonica_centre.sheet_music (id, read_number, img_src, music_name, music_author, music_href) VALUES ('1399268355856154626', 3238, 'http://www.skizhou.com/statics/notation/images/201907036dc8a71c.jpeg', 'My heart will go on', '泰坦尼克号', 'http://www.skizhou.com/statics/notation/images/20190703509ae64f.png');
INSERT INTO acg_harmonica_centre.sheet_music (id, read_number, img_src, music_name, music_author, music_href) VALUES ('1399268539629584386', 2588, 'http://www.skizhou.com/statics/notation/images/2019070308211502.jpeg', 'La Partida', 'La Partida', 'http://www.skizhou.com/statics/notation/images/201907037807d005.png');
INSERT INTO acg_harmonica_centre.sheet_music (id, read_number, img_src, music_name, music_author, music_href) VALUES ('1399290445627932673', 4393, 'http://www.skizhou.com/statics/notation/images/20190703d45a60a8.jpeg', '美丽之物', '美しきもの', 'http://www.skizhou.com/statics/notation/images/201907036ac21b82.png');
INSERT INTO acg_harmonica_centre.sheet_music (id, read_number, img_src, music_name, music_author, music_href) VALUES ('1399290709197996033', 1706, 'http://www.skizhou.com/statics/notation/images/20190703efce26d4.jpeg', '镇命歌', '晨曦时梦见兮ED', 'http://www.skizhou.com/statics/notation/images/201907030f8f7410.png');
INSERT INTO acg_harmonica_centre.sheet_music (id, read_number, img_src, music_name, music_author, music_href) VALUES ('1399298241467531266', 1056, 'http://www.skizhou.com/statics/notation/images/201907030ab4b19a.jpeg', '夕颜', 'every dog has his day', 'http://www.skizhou.com/statics/notation/images/2019070338222e5b.png');