create database boardmvcdb05;
use boardmvcdb05;

create table board(
	num int(10),
    writer varchar(20),
    email varchar(20),
    subject varchar(50),
    password varchar(20),
    reg_date date,
    ref int(10),
    re_step int(10),
    re_level int(10),
    readcount int(10),
    content varchar(20)
);

select * from board;
