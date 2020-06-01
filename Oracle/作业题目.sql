create table student
(
sno varchar2(3) not NULL  PRIMARY KEY , --ѧ��
sname varchar2(20) not null,--ѧ������
ssex varchar2(10) not NULL CHECK(sex IN('��','Ů'), --�Ա�
sbirthday DATE Check(sbirthday BETWEEN 1900 AND 2019),  --����
class varchar2(5)��  --���ڰ༶���
tno varchar2(3)
);
---------�γ̱�
create table t_course
(
cno varchar2(5) not null,--�γ̱�� 
cname varchar2(50) not null, --�γ���
tno varchar2(10) not null  --�ڿ���ʦ���
);
---------------����
create table t_score 
(
sno varchar2(3) not null, ---ѧ��
cno varchar2(5) not null, ---�γ̺�
degree number(10, 1) not null  --����
);
---��ʦ��
create table t_teacher 
(
tno varchar2(3) not null,   --��ʦ���
tname varchar2(20) not null,   --��ʦ����
tsex varchar2(10) not null,   --��ʦ�Ա�
tbirthday date not null,   --����
prof varchar2(20),     --ְ��
depart varchar2(20) not null  --����
);

create table t_grade(
  low number(3,0),--�ͷ�
  upp number(3),--�߷�
  rank char(1)--����
);

insert into student (sno,sname,ssex,sbirthday,class) values (108 ,'����' 
,'��' ,to_date('1977-09-01','yyyy-mm-dd'),95033);
insert into student (sno,sname,ssex,sbirthday,class) values (105 ,'����' 
,'��' ,to_date('1975-10-02','yyyy-mm-dd'),95031);
insert into student (sno,sname,ssex,sbirthday,class) values (107 ,'����' 
,'Ů' ,to_date('1976-01-23','yyyy-mm-dd'),95033);
insert into student (sno,sname,ssex,sbirthday,class) values (101 ,'���' 
,'��' ,to_date('1976-02-20','yyyy-mm-dd'),95033);
insert into student (sno,sname,ssex,sbirthday,class) values (109 ,'����' 
,'Ů' ,to_date('1975-02-10','yyyy-mm-dd'),95031);
insert into student (sno,sname,ssex,sbirthday,class) values (103 ,'½��' 
,'��' ,to_date('1974-06-03','yyyy-mm-dd'),95031);

insert into t_course(cno,cname,tno)values ('3-105' ,'���������',825);
insert into t_course(cno,cname,tno)values ('3-245' ,'����ϵͳ' ,804);
insert into t_course(cno,cname,tno)values ('6-166' ,'���ݵ�·' ,856);
insert into t_course(cno,cname,tno)values ('9-888' ,'�ߵ���ѧ' ,100);

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
values (804,'���','��',to_date('1958-12-02','yyyy-mm-dd'),'������','�����ϵ');
