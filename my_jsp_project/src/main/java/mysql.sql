-- 2023-09-20
create table board(
bno int auto_increment primary key,
title varchar(200),
writer varchar(100),
content text,
regdate datetime default now(),
moddate datetime default now(),
hit int default 0,
img_file varchar(500));

-- 2023-09-22
create table member(
id varchar(100) primary key,
pw varchar(100) not null,
email varchar(100),
age int,
joinDate datetime default now(),
lastLogin datetime);

-- 2023-09-25
create table comment(
cno int primary key,
cmt_bno int not null,
writer varchar(200),
content varchar(1000),
regdate datetime default now(),
constraint fk_comment foreign key(cmt_bno) references board(bno) on delete cascade);