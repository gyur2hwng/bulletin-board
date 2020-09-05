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

insert into board (num, writer, email, subject, password, reg_date, ref, re_step, re_level, readcount, content) 
values (1, "황규리", "hi@gamil.com", "hi", "hi", now(), 1, 1, 1, 0, "hi");
delete from board where num=1;

select * from board;
