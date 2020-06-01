create table student
(
sno varchar2(3) not NULL  PRIMARY KEY , --学号
sname varchar2(20) not null,--学生姓名
ssex varchar2(10) not NULL CHECK(sex IN('男','女'), --性别
sbirthday DATE Check(sbirthday BETWEEN 1900 AND 2019),  --生日
class varchar2(5)，  --所在班级编号
tno varchar2(3)
);
---------课程表
create table t_course
(
cno varchar2(5) not null,--课程编号 
cname varchar2(50) not null, --课程名
tno varchar2(10) not null  --授课老师编号
);
---------------分数
create table t_score 
(
sno varchar2(3) not null, ---学号
cno varchar2(5) not null, ---课程号
degree number(10, 1) not null  --分数
);
---老师表
create table t_teacher 
(
tno varchar2(3) not null,   --老师编号
tname varchar2(20) not null,   --老师姓名
tsex varchar2(10) not null,   --老师性别
tbirthday date not null,   --生日
prof varchar2(20),     --职称
depart varchar2(20) not null  --部门
);

create table t_grade(
  low number(3,0),--低分
  upp number(3),--高分
  rank char(1)--评级
);

insert into student (sno,sname,ssex,sbirthday,class) values (108 ,'曾华' 
,'男' ,to_date('1977-09-01','yyyy-mm-dd'),95033);
insert into student (sno,sname,ssex,sbirthday,class) values (105 ,'匡明' 
,'男' ,to_date('1975-10-02','yyyy-mm-dd'),95031);
insert into student (sno,sname,ssex,sbirthday,class) values (107 ,'王丽' 
,'女' ,to_date('1976-01-23','yyyy-mm-dd'),95033);
insert into student (sno,sname,ssex,sbirthday,class) values (101 ,'李军' 
,'男' ,to_date('1976-02-20','yyyy-mm-dd'),95033);
insert into student (sno,sname,ssex,sbirthday,class) values (109 ,'王芳' 
,'女' ,to_date('1975-02-10','yyyy-mm-dd'),95031);
insert into student (sno,sname,ssex,sbirthday,class) values (103 ,'陆君' 
,'男' ,to_date('1974-06-03','yyyy-mm-dd'),95031);

insert into t_course(cno,cname,tno)values ('3-105' ,'计算机导论',825);
insert into t_course(cno,cname,tno)values ('3-245' ,'操作系统' ,804);
insert into t_course(cno,cname,tno)values ('6-166' ,'数据电路' ,856);
insert into t_course(cno,cname,tno)values ('9-888' ,'高等数学' ,100);

insert into t_score(sno,cno,degree)values (103,'3-245',86);
insert into t_score(sno,cno,degree)values (105,'3-245',75);
insert into t_score(sno,cno,degree)values (109,'3-245',68);
insert into t_score(sno,cno,degree)values (103,'3-105',92);
insert into t_score(sno,cno,degree)values (105,'3-105',88);
insert into t_score(sno,cno,degree)values (109,'3-105',76);
insert into t_score(sno,cno,degree)values (101,'3-105',64);
insert into t_score(sno,cno,degree)values (107,'3-105',91);
insert into t_score(sno,cno,degree)values (108,'3-105',78);
insert into t_score(sno,cno,degree)values (101,'6-166',85);
insert into t_score(sno,cno,degree)values (107,'6-106',79);
insert into t_score(sno,cno,degree)values (108,'6-166',81);

insert into t_teacher(tno,tname,tsex,tbirthday,prof,depart) 
values (804,'李诚','男',to_date('1958-12-02','yyyy-mm-dd'),'副教授','计算机系');
