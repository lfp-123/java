
 select * from tblstudent  --学生表
  select * from tblCourse --课程表
  select * from tblScore --成绩表
  select * from tblTeacher --教师表

--学生表tblStudent（编号StuId、姓名StuName、年龄StuAge、性别StuSex） select * from tblstudent
create table tblStudent
(
 StuId varchar(5) primary key,
 StuName varchar(12),
 StuAge int,
 StuSex nchar(1)
);
TRUNCATE TABLE tblstudent;
--教师表tblTeacher（教师编号TeaId、姓名TeaName）select * from tblTeacher
create table tblTeacher
(
 TeaId varchar(3) primary key, 
 TeaName varchar(12)
);

--课程表tblCourse（课程编号CourseId、课程名称CourseName、教师编号TeaId） select * from tblCourse
create table tblCourse
(
 CourseId varchar(3) primary key,
 CourseName varchar(21), 
 TeaId varchar(3)
);


--成绩表tblScore（学生编号StuId、课程编号CourseId、成绩Score）select * from tblScore
create table tblScore
(
 StuId varchar2(5) ,
 CourseId varchar2(3),
 Score float
);

--------------------------------插入数据-------------------------------------------------
insert into tblStudent
select '1000','张无忌',18,'男' from dual union
select '1001','周芷若',19,'女' from dual union
select '1002','杨过',19,'男' from dual union
select '1003','赵敏',18,'女' from dual union
select '1004','小龙女',17,'女' from dual union
select '1005','张三丰',18,'男' from dual union
select '1006','令狐冲',19,'男' from dual union
select '1007','任盈盈',20,'女' from dual union
select '1008','岳灵珊',19,'女' from dual union
select '1009','韦小宝',18,'男' from dual union
select '1010','康敏',17,'女' from dual union
select '1011','萧峰',19,'男' from dual union
select '1012','黄蓉',18,'女' from dual union
select '1013','郭靖',19,'男' from dual union
select '1014','周伯通',19,'男' from dual union
select '1015','瑛姑',20,'女' from dual union
select '1016','李秋水',21,'女' from dual union
select '1017','黄药师',18,'男' from dual union
select '1018','李莫愁',18,'女' from dual union
select '1019','冯默风',17,'男' from dual union
select '1020','王重阳',17,'男' from dual union
select '1021','郭襄',18,'女' from dual;
commit;


insert  into tblTeacher
select '001','姚明' from dual union
select '002','叶平' from dual union
select '003','叶开' from dual union
select '004','孟星魂' from dual union
select '005','独孤求败' from dual union
select '006','裘千仞' from dual union
select '007','裘千尺' from dual union
select '008','赵志敬' from dual union
select '009','阿紫'  from dual union
select '010','郭芙蓉' from dual union
select '011','佟湘玉' from dual union
select '012','白展堂' from dual union
select '013','吕轻侯' from dual union
select '014','李大嘴' from dual union
select '015','花无缺' from dual union
select '016','金不换' from dual  union
select '017','乔丹' from dual;
commit;


insert into tblCourse
select '001','企业管理','002' from dual union
select '002','马克思','008' from dual union
select '003','UML','006' from dual union
select '004','数据库','007' from dual union
select '005','逻辑电路','006' from dual union
select '006','英语','003' from dual union
select '007','电子电路','005' from dual union
select '008','毛泽东思想概论','004' from dual union
select '009','西方哲学史','012' from dual union
select '010','线性代数','017' from dual union
select '011','计算机基础','013' from dual union
select '012','AUTO CAD制图','015' from dual union
select '013','平面设计','011' from dual union
select '014','Flash动漫','001' from dual  union
select '015','Java开发','009' from dual union
select '016','C#基础','002' from dual union
select '017','Oracl数据库原理','010' from dual;
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

       
