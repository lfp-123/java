﻿create table students(
sno varchar2(10) primary key,
sname varchar2(20),
sage number(2),
ssex varchar2(5)
);
create table teacher(
tno varchar2(10) primary key,
tname varchar2(20)
);
create table course(
cno varchar2(10),
cname varchar2(20),
tno varchar2(20),
constraint pk_course primary key (cno,tno)
);
create table sc(
sno varchar2(10),
cno varchar2(10),
score number(4,2),
constraint pk_sc primary key (sno,cno)
);
/*******初始化学生表的数据******/
insert into students values ('s001','张三',23,'男');
insert into students values ('s002','李四',23,'男');
insert into students values ('s003','吴鹏',25,'男');
insert into students values ('s004','琴沁',20,'女');
insert into students values ('s005','王丽',20,'女');
insert into students values ('s006','李波',21,'男');
insert into students values ('s007','刘玉',21,'男');
insert into students values ('s008','萧蓉',21,'女');
insert into students values ('s009','陈萧晓',23,'女');
insert into students values ('s010','陈美',22,'女');
commit;
/******************初始化教师表***********************/
insert into teacher values ('t001', '刘阳');
insert into teacher values ('t002', '谌燕');
insert into teacher values ('t003', '胡明星');
commit;
/***************初始化课程表****************************/
insert into course values ('c001','J2SE','t002');
insert into course values ('c002','Java Web','t002');
insert into course values ('c003','SSH','t001');
insert into course values ('c004','Oracle','t001');
insert into course values ('c005','SQL SERVER 2005','t003');
insert into course values ('c006','C#','t003');
insert into course values ('c007','JavaScript','t002');
insert into course values ('c008','DIV+CSS','t001');
insert into course values ('c009','PHP','t003');
insert into course values ('c010','EJB3.0','t002');
commit;
/***************初始化成绩表***********************/
insert into sc values ('s001','c001',78.9);
insert into sc values ('s002','c001',80.9);
insert into sc values ('s003','c001',81.9);
insert into sc values ('s004','c001',60.9);
insert into sc values ('s001','c002',82.9);
insert into sc values ('s002','c002',72.9);
insert into sc values ('s003','c002',81.9);
insert into sc values ('s001','c003','70.3');
insert into sc values ('s005','c004','85');
insert into sc values ('s006','c007','52');
insert into sc values ('s007','c009','66.5');
insert into sc values ('s005','c001','40');
commit;
 
 
练习：
注意：以下练习中的数据是根据初始化到数据库中的数据来写的SQL 语句，请大家务必注意。
 
SELECT * FROM students;
SELECT *FROM teacher;
SELECT *FROM sc;
SELECT * FROM course;

1、查询“c001”课程比“c002”课程成绩高的所有学生的学号；
select a.sid, a.score as score1,b.score as score2 from (select * from scoretable where cid='001') a 
left join 
 (select * from scoretable where cid='002')b
on a.sid=b.sid
where a.score>b.score
2、查询平均成绩大于60 分的同学的学号和平均成绩；
SELECT sno,AVG(score)  FROM sc  GROUP BY sno HAVING  AVG(score)>60 

3、查询所有同学的学号、姓名、选课数、总成绩；
SELECT s1.sname,s2.学号,s2.选课数,s2.总成绩  FROM students  s1  , (
SELECT sno AS 学号, COUNT(cno) 选课数, SUM(score)  总成绩 FROM sc  GROUP BY sno  ) s2 WHERE  s1.sno = s2.学号;

SELECT sname ,sno,s2.选课数,s2.总成绩 FROM students  s1  LEFT OUTER   JOIN (
SELECT sno AS 学号, COUNT(cno) 选课数, SUM(score)  总成绩 FROM sc  GROUP BY sno  ) s2 on  s1.sno = s2.学号;
4、查询姓“刘”的老师的个数；
SELECT  COUNT(tname) FROM teacher  WHERE  tname LIKE '刘%' GROUP BY tno;

5、查询没学过谌燕”“老师课的同学的学号、姓名；
SELECT  sno ,sname FROM students WHERE sno IN (
SELECT sno FROM  sc  WHERE cno NOT IN (
SELECT cno FROM course WHERE tno = (
SELECT  tno FROM teacher WHERE tname = '谌燕')));
6、查询所有课程成绩小于60 分的同学的学号、姓名；
SELECT sno ,sname FROM students WHERE sno IN (
SELECT sno FROM sc  GROUP BY sno HAVING MAX(score)<60);

7、把“SC”表中“谌燕”老师教的课的成绩都更改为此课程的平均成绩；
update sc c set score=(select avg(c.score)  from course a,teacher b
     where a.tno=b.tno and b.tname='谌燕'  and a.cno=c.cno
     group by c.cno)WHERE cno in(
 select cno from course a,teacher b where a.tno=b.tno and b.tname='谌燕')

8、查询各科成绩最高和最低的分：以如下形式显示：课程ID，最高分，最低分
 SELECT   cno 课程ID , MAX(score) 最高分,MIN(score) 最低分 FROM sc GROUP BY cno ; 

9、求各科的及格率，按百分比从高到低显示
SELECT cno, SUM( CASE  WHEN  score>60 THEN 1 ELSE 0 END )/COUNT(cno) AS  及格率  FROM sc GROUP BY cno ORDER BY 及格率 DESC ;

10、查询每门课程被选修的学生数
SELECT cno, COUNT(cno) FROM sc GROUP BY cno;
11、查询男生、女生人数
SELECT  SUM(CASE WHEN ssex = '男' THEN 1 ELSE 0 END ) AS 男生人数，SUM(CASE WHEN ssex = '女' THEN 1 ELSE 0 END ) AS 女生人数 FROM students
12、查询同名同性学生名单，并统计同名人数
SELECT sname,COUNT(sname) FROM students  GROUP BY sname HAVING COUNT(sname)>1;
13、1981 年出生的学生名单(注：Student 表中Sage 列的类型是number)
SELECT*  FROM students WHERE to_number(SYSDATE ,'yyyy') - sage = 1981; 
14、查询每门课程的平均成绩，结果按平均成绩升序排列，平均成绩相同时，按课程号降序排列
SELECT AVG(score) AS 平均成绩，cno FROM sc GROUP BY cno ORDER  BY 平均成绩 DESC ,cno ASC;
15、查询平均成绩大于85 的所有学生的学号、姓名和平均成绩
SELECT s2.sname,s1.平均成绩,s1.学号  FROM students  s2,
(SELECT AVG(score) AS 平均成绩 ,sno AS  学号 FROM sc GROUP BY sno HAVING AVG(score)>65 ) 
 s1 WHERE  s2.sno = s1.学号；

16、查询课程名称为“数据库”，且分数低于60 的学生姓名和分数
SELECT sname FROM student t1 JOIN 
(SELECT sno,score FROM sc WHERE score <90 AND cno = ( 
SELECT cno FROM course WHERE cname ='Oracle') )  t2 ON t1.sno = t2.sno;
 select sname,score from student st,sc,course c
where st.sno=sc.sno and sc.cno=c.cno and c.cname='Oracle' and sc.score<90
17、查询选修“谌燕”老师所授课程的学生中，成绩最高的学生姓名及其成绩
SELECT s1.sname ,s2.score FROM sc s2 ,students s1 WHERE s2.score = (
SELECT  MAX(score) FROM sc  WHERE cno IN  (
SELECT cno FROM course,teacher WHERE tname = '谌燕' AND course.tno = teacher.tno )) AND s2.sno = s1.sno;

18、查询各个课程及相应的选修人数
SELECT cno ,COUNT(cno) FROM sc GROUP BY cno;
19、查询没学过“谌燕”老师讲授的任一门课程的学生姓名
select st.sname from students st
 where st.sno not in
(select distinct sc.sno from sc,course c,teacher t
 where sc.cno=c.cno and c.tno=t.tno and t.tname='谌燕')
20、删除“s002”同学的“c001”课程的成绩
delete from sc where sno='s002' and cno='c001';
