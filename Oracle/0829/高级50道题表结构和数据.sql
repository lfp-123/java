
 select * from tblstudent  --ѧ����
  select * from tblCourse --�γ̱�
  select * from tblScore --�ɼ���
  select * from tblTeacher --��ʦ��

--ѧ����tblStudent�����StuId������StuName������StuAge���Ա�StuSex�� select * from tblstudent
create table tblStudent
(
 StuId varchar(5) primary key,
 StuName varchar(12),
 StuAge int,
 StuSex nchar(1)
);
TRUNCATE TABLE tblstudent;
--��ʦ��tblTeacher����ʦ���TeaId������TeaName��select * from tblTeacher
create table tblTeacher
(
 TeaId varchar(3) primary key, 
 TeaName varchar(12)
);

--�γ̱�tblCourse���γ̱��CourseId���γ�����CourseName����ʦ���TeaId�� select * from tblCourse
create table tblCourse
(
 CourseId varchar(3) primary key,
 CourseName varchar(21), 
 TeaId varchar(3)
);


--�ɼ���tblScore��ѧ�����StuId���γ̱��CourseId���ɼ�Score��select * from tblScore
create table tblScore
(
 StuId varchar2(5) ,
 CourseId varchar2(3),
 Score float
);

--------------------------------��������-------------------------------------------------
insert into tblStudent
select '1000','���޼�',18,'��' from dual union
select '1001','������',19,'Ů' from dual union
select '1002','���',19,'��' from dual union
select '1003','����',18,'Ů' from dual union
select '1004','С��Ů',17,'Ů' from dual union
select '1005','������',18,'��' from dual union
select '1006','�����',19,'��' from dual union
select '1007','��ӯӯ',20,'Ů' from dual union
select '1008','����ɺ',19,'Ů' from dual union
select '1009','ΤС��',18,'��' from dual union
select '1010','����',17,'Ů' from dual union
select '1011','����',19,'��' from dual union
select '1012','����',18,'Ů' from dual union
select '1013','����',19,'��' from dual union
select '1014','�ܲ�ͨ',19,'��' from dual union
select '1015','����',20,'Ů' from dual union
select '1016','����ˮ',21,'Ů' from dual union
select '1017','��ҩʦ',18,'��' from dual union
select '1018','��Ī��',18,'Ů' from dual union
select '1019','��Ĭ��',17,'��' from dual union
select '1020','������',17,'��' from dual union
select '1021','����',18,'Ů' from dual;
commit;


insert  into tblTeacher
select '001','Ҧ��' from dual union
select '002','Ҷƽ' from dual union
select '003','Ҷ��' from dual union
select '004','���ǻ�' from dual union
select '005','�������' from dual union
select '006','��ǧ��' from dual union
select '007','��ǧ��' from dual union
select '008','��־��' from dual union
select '009','����'  from dual union
select '010','��ܽ��' from dual union
select '011','١����' from dual union
select '012','��չ��' from dual union
select '013','�����' from dual union
select '014','�����' from dual union
select '015','����ȱ' from dual union
select '016','�𲻻�' from dual  union
select '017','�ǵ�' from dual;
commit;


insert into tblCourse
select '001','��ҵ����','002' from dual union
select '002','���˼','008' from dual union
select '003','UML','006' from dual union
select '004','���ݿ�','007' from dual union
select '005','�߼���·','006' from dual union
select '006','Ӣ��','003' from dual union
select '007','���ӵ�·','005' from dual union
select '008','ë��˼�����','004' from dual union
select '009','������ѧʷ','012' from dual union
select '010','���Դ���','017' from dual union
select '011','���������','013' from dual union
select '012','AUTO CAD��ͼ','015' from dual union
select '013','ƽ�����','011' from dual union
select '014','Flash����','001' from dual  union
select '015','Java����','009' from dual union
select '016','C#����','002' from dual union
select '017','Oracl���ݿ�ԭ��','010' from dual;
commit;

insert into tblScore
select '1001','003',90 from dual union
select '1001','002',87 from dual union
select '1001','001',96 from dual union
select '1001','010',85 from dual union
select '1002','003',70 from dual union
select '1002','002',87 from dual union
select '1002','001',42 from dual union
select '1002','010',65 from dual union
select '1003','006',78 from dual union
select '1003','003',70 from dual union
select '1003','005',70 from dual union
select '1003','001',32 from dual union
select '1003','010',85 from dual union
select '1003','011',21 from dual union
select '1004','007',90 from dual union
select '1004','002',87 from dual union
select '1005','001',23 from dual union
select '1006','015',85 from dual union
select '1006','006',46 from dual union
select '1006','003',59 from dual union
select '1006','004',70 from dual union
select '1006','001',99 from dual union
select '1007','011',85 from dual union
select '1007','006',84 from dual union
select '1007','003',72 from dual union
select '1007','002',87 from dual union
select '1008','001',94 from dual union
select '1008','012',85 from dual union
select '1008','006',32 from dual union
select '1009','003',90 from dual union
select '1009','002',82 from dual union
select '1009','001',96 from dual union
select '1009','010',82 from dual union
select '1009','008',92 from dual union
select '1010','003',90 from dual union
select '1010','002',87 from dual union
select '1010','001',96 from dual union
select '1011','009',24 from dual union
select '1011','009',25 from dual union
select '1012','003',30 from dual union
select '1013','002',37 from dual union
select '1013','001',16 from dual union
select '1013','007',55 from dual union
select '1013','006',42 from dual union
select '1013','012',34 from dual union
select '1000','004',16 from dual union
select '1002','004',55 from dual union
select '1004','004',42 from dual union
select '1008','004',34 from dual union
select '1013','016',86 from dual union
select '1013','016',44 from dual union
select '1000','014',75 from dual union
select '1002','016',100 from dual union
select '1004','001',83 from dual  union
select '1008','013',97 from dual;
commit;

       
