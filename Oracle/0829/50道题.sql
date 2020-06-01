SELECT * FROM tblstudent;
SELECT * FROM tblteacher;
SELECT *FROM tblcourse;
SELECT *FROM tblscore;
 


--学生表tblStudent（编号StuId、姓名Stuname、年龄Stuage、性别Stusex）
--课程表tblCourse（课程编号CourseId、课程名称CourseName、教师编号TeaId）
--成绩表tblScore（学生编号StuId、课程编号CourseId、成绩Score）
--教师表tblTeacher（教师编号TeaId、姓名TeaName）
--问题： 
--1、查询“001”课程比“002”课程成绩高的所有学生的学号；
SELECT t1.stuid FROM 
(SELECT *  FROM tblscore WHERE courseid = 001) t1,
(SELECT *  FROM tblscore WHERE courseid = 002) t2 WHERE
 t1.stuid= t2.stuid AND t1.score>t2.score;
   
--2、查询平均成绩大于60分的同学的学号和平均成绩；
SELECT t1.stuid ,t2.avgscore FROM tblstudent t1, 
(SELECT stuid,AVG(score)  avgscore  FROM tblscore  GROUP BY stuid ) t2 
WHERE t1.stuid = t2.stuid AND t2.avgscore>60 ORDER BY t1.stuid ;
 
SELECT stuid,AVG(score)    FROM tblscore  GROUP BY stuid HAVING AVG(score)>60 ORDER BY stuid;


--3、查询所有同学的学号、姓名、选课数、总成绩；
SELECT t1.stuname,t1.stuid,t2.选课数,t2.总成绩 FROM tblstudent t1 FULL OUTER JOIN 
(
 SELECT  stuid, COUNT (courseid)  选课数, SUM (score)  总成绩 FROM tblscore GROUP BY stuid  )  t2 ON
  t1.stuid = t2.stuid ;  

SELECT t1.stuid ,t1.stuname,COUNT (t2.courseid) 选课数,SUM (t2.score) 总成绩 FROM tblstudent  t1
LEFT OUTER JOIN tblscore t2 ON  t1.stuid= t2.stuid GROUP BY t1.stuid,t1.stuname ORDER BY t1.stuid ASC

--4、查询姓“李”的老师的个数； 
 SELECT COUNT(teaname) FROM tblteacher WHERE teaname LIKE '李%'
--5、查询没学过“叶平”老师课的同学的学号、姓名；
SELECT DISTINCT      t1.stuname,t3.stuid FROM tblstudent  t1,
(SELECT  courseid,teaid FROM tblcourse t2 WHERE teaid IN
( SELECT teaid FROM tblteacher WHERE teaname != '叶平'))  t2,
tblscore t3 
WHERE 
t3.courseid= t2.courseid;    


--6、查询学过“001”并且也学过编号“002”课程的同学的学号、姓名； 
SELECT t1.stuid ,t1.stuname FROM tblstudent t1 WHERE  stuid IN (
SELECT t2.stuid FROM 
(SELECT stuid FROM tblscore WHERE courseid = '001') t2,
(SELECT stuid FROM tblscore WHERE courseid = '002') t3 WHERE t2.stuid =t3.stuid );

--7、查询学过“叶平”老师所教的所有课的同学的学号、姓名； 
 SELECT DISTINCT      t1.stuname,t1.stuid FROM tblstudent  t1 WHERE stuid IN  (
 SELECT distinct stuid FROM tblscore WHERE  courseid IN 
(SELECT distinct  courseid FROM tblcourse t2 WHERE teaid IN
( SELECT teaid FROM tblteacher WHERE teaname = '叶平'))) 

  SELECT DISTINCT stuid FROM tblscore WHERE courseid ='016' OR courseid ='001';
--8、查询课程编号“002”的成绩比课程编号“001”课程低的所有同学的学号、姓名； 
SELECT stuid,stuname FROM tblstudent  WHERE stuid IN (
SELECT DISTINCT t2.stuid FROM 
 (SELECT stuid, score FROM  tblscore WHERE courseid = '002'  ) t2,
  (SELECT stuid, score FROM  tblscore WHERE courseid = '001'  ) t3 WHERE t3.score> t2.score AND t2.stuid = t3.stuid)
  
SELECT * FROM tblscore WHERE courseid = '001' OR courseid ='002'
--9、查询所有课程成绩小于60分的同学的学号、姓名；
SELECT stuname, stuid FROM tblstudent WHERE stuid NOT IN (
SELECT  DISTINCT tblstudent.stuid FROM  tblstudent,tblscore
 WHERE tblstudent.stuid = tblscore.stuid AND  score>60) ;
   
--10、查询没有学全所有课的同学的学号、姓名； 
 
 

--11、查询至少有一门课与学号为“1001”的同学所学相同的同学的学号和姓名；
SELECT t2.stuid,t1.stuname FROM  tblstudent t1,tblscore t2 WHERE t1.stuid = t2.stuid AND t2.courseid IN ( 
 SELECT  courseid FROM tblscore WHERE stuid = '1001') 

--12、查询至少学过学号为“1001”同学所有课程的其他同学学号和姓名； 
SELECT t2.stuid,t1.stuname FROM  tblstudent t1,tblscore t2 WHERE t1.stuid = t2.stuid AND t2.courseid NOT IN ( 
 SELECT  courseid FROM tblscore WHERE stuid = '1001') 

--13、把“SC”表中“叶平”老师教的课的成绩都更改为此课程的平均成绩；
 UPDATE tblscore SET score = （SELECT AVG(score)  FROM  tblscore,tblcourse,tblteacher
  WHERE tblscore.stuid = tblcourse.teaid AND tblcourse.teaid = tblteacher.teaid AND tblteacher.teaname='叶平'）
   
 
--14、查询和“1002”号的同学学习的课程完全相同的其他同学学号和姓名；  
SELECT stuid,COUNT(*) FROM 
(SELECT t1.stuid FROM tblscore t1 JOIN (
SELECT DISTINCT courseid FROM tblscore WHERE stuid ='1002') t2 ON t1.courseid= t2.courseid) GROUP BY stuid
 HAVING COUNT(*) = (SELECT COUNT(*) FROM tblscore WHERE stuid ='1002' )

SELECT stuid,courseid FROM tblscore  WHERE stuid='1002' OR stuid ='1003'  ORDER BY stuid ASC ,courseid ASC;
--15、删除学习“叶平”老师课的SC表记录；


  delect FROM tblscore WHERE tblscore.courseid IN
  ( SELECT  DISTINCT tblscore.courseid FROM tblscore,tblteacher,tblcourse WHERE tblteacher.teaname ='叶平' AND tblteacher.teaid = tblcourse.teaid)
  
--16、向SC表中插入一些记录，这些记录要求符合以下条件：没有上过编号“003”课程的同学学号、'002'号课的平均成绩； 
 
   

--17、按平均成绩从高到低显示所有学生的“数据库”、“企业管理”、“英语”三门的课程成绩，按如下形式显示： 学生ID,,数据库,企业管理,英语,有效课程数,有效平均分 

select t.stuid,
       max(case t.cname when '数据库' then t.score end) 数据库,
       max(case t.cname when '企业管理' then t.score end) 企业管理,
       max(case t.cname when '英语' then t.score end) 英语,
       round(avg(t.score), 2) 平均分,
       count(t.courseid) 有效课程数
  from (
         select s.stuid, s.stuname stuname, sc.courseid, sc.score, c.coursename cname
          from tblstudent s, tblscore sc, tblcourse c
         where s.stuid = sc.stuid
           and sc.courseid = c.courseid
           and c.coursename in ('数据库', '企业管理', '英语')
           ) t
 group by t.stuid
 order by 平均分 desc;  

--18、查询各科成绩最高和最低的分：以如下形式显示：课程ID，最高分，最低分 
SELECT courseid 课程ID,MAX (score) 最高分,MIN(score) 最低分 FROM tblscore GROUP BY courseid;


--19、按各科平均成绩从低到高和及格率的百分数从高到低顺序 (百分数后如何格式化为两位小数??)

SELECT courseid , s 平均分,round(jige /zong,4)  及格率 FROM 
(
SELECT courseid, AVG(score) s  ,ROUND ( COUNT(1),2)  zong, 
SUM (CASE WHEN score>60 THEN 1 ELSE 0  END )  jige 
FROM tblscore GROUP BY courseid ORDER BY avg(score) DESC );

SELECT courseid,score FROM tblscore;
--20、查询如下课程平均成绩和及格率的百分数(用"1行"显示): 企业管理（001），马克思（002），OO&UML （003），数据库（004） 
SELECT t1.courseid,t1.avgs 平均分  ,t2.jige/t1.zong 及格率 from
(SELECT  courseid,avg(score) avgs, COUNT( DISTINCT stuid) zong FROM tblscore WHERE courseid in ('001','002','003','004')  GROUP BY courseid) t1,
(SELECT  courseid,avg(score) avgS, COUNT( DISTINCT stuid) jige FROM tblscore WHERE courseid in ('001','002','003','004') AND score>60 GROUP BY courseid) t2 
WHERE t1.courseid = t2.courseid;

--21、查询不同老师所教不同课程平均分从高到低显示 
SELECT courseid, AVG(score) FROM tblscore GROUP BY courseid ORDER BY AVG(score) DESC; 

--22、查询如下课程成绩第 3 名到第 6 名的学生成绩单：企业管理（001），马克思（002），UML （003），数据库（004） 格式：[学生ID],[学生姓名],企业管理,马克思,UML,数据库,平均成绩 
SELECT r,stuid,avgs FROM (
SELECT stuid,avgs,ROWNUM r FROM (
SELECT stuid,AVG(score) avgs FROM tblscore WHERE courseid IN ('001','002','003','004')  GROUP BY stuid ORDER BY avgs ) WHERE ROWNUM<=6 ) 
WHERE r>=3 ;

--23、统计列印各科成绩,各分数段人数:课程ID,课程名称,[100-85],[85-70],[70-60],[ <60] 

--24、查询学生平均成绩及其名次 
 SELECT stuid,AVG(score) FROM tblscore GROUP BY stuid ORDER BY AVG(score);

--25、查询各科成绩前三名的记录:(不考虑成绩并列情况)
 SELECT tblcourse.courseid,score FROM tblscore,tblcourse WHERE tblscore.courseid= tblcourse.courseid  ;

--26、查询每门课程被选修的学生数 
  SELECT stuid, COUNT(courseid) FROM tblscore GROUP BY stuid ;
--27、查询出只选修了一门课程的全部学生的学号和姓名 
 SELECT stuid, COUNT(tblcourse.courseid) FROM tblscore,tblcourse WHERE tblscore.courseid= tblcourse.courseid GROUP BY stuid ;
 
--28、查询男生、女生人数 
 SELECT 
(SELECT COUNT(1)  FROM tblstudent WHERE stusex='男') 男生人数,
(SELECT COUNT(1) 女生 FROM tblstudent WHERE stusex= '女') 女生人数 FROM dual;
--29、查询姓“张”的学生名单 
SELECT * FROM tblstudent WHERE stuname LIKE '张%';

--30、查询同名同性学生名单，并统计同名人数 
SELECT stuname,COUNT(1) FROM tblstudent GROUP BY stuname HAVING COUNT(1)>1;
--31、1981年出生的学生名单(注：Student表中Sage列的类型是datetime) 
 SELECT * FROM tblstudent WHERE 
 stuage     BETWEEN  to_date('1981-1-1','yyyy-MM-dd') 
   AND to_date('1981-12-31','yyyy-MM-dd');

--32、查询每门课程的平均成绩，结果按平均成绩升序排列，平均成绩相同时，按课程号降序排列 
 SELECT courseid ,AVG(score) FROM tblscore  GROUP BY courseid ORDER BY avg(score) DESC ,courseid ASC;

--33、查询平均成绩大于85的所有学生的学号、姓名和平均成绩 
SELECT t1.stuid,t1.stuname,t2.avgs FROM
(SELECT stuid ,AVG(score)  avgs  FROM tblscore GROUP BY stuid) t2 ,
tblstudent t1 WHERE t2.avgs >85 AND t1.stuid =t2.stuid ;
--34、查询课程名称为“数据库”，且分数低于60的学生姓名和分数 
SELECT t1.stuname,t1.stuid,t2.score FROM
 (SELECT stuid,score FROM tblscore,tblcourse WHERE coursename ='数据库' AND score>60 AND tblcourse.courseid= tblscore.courseid) t2,
 tblstudent t1 WHERE  t1.stuid= t2.stuid;

--35、查询所有学生的选课情况； 
   SELECT stu.stuid, stu.stuname,sc.courseid,cs.coursename,sc.score
   FROM tblstudent stu INNER JOIN tblscore sc
    ON stu.stuid = sc.stuid INNER JOIN  tblcourse cs
    ON cs.courseid = sc.courseid ;

--36、查询任何一门课程成绩在70分以上的姓名、课程名称和分数； 

SELECT  stuname,coursename,score FROM tblscore,tblcourse,tblstudent WHERE score>70 AND tblscore.stuid=tblstudent.stuid AND tblcourse.courseid=tblscore.courseid ;
--37、查询不及格的课程，并按课程号从大到小排列 

  SELECT courseid FROM   tblstudent stu INNER JOIN tblscore sc
  ON stu.stuid = sc.stuid 
  WHERE score < 60 
  GROUP BY sc.courseid
  ORDER BY courseid DESC
--38、查询课程编号为003且课程成绩在80分以上的学生的学号和姓名； 
 SELECT t1.stuid,t1.stuname FROM tblscore t2 ,tblstudent t1 WHERE t2.score >60 AND t1.stuid = t2.stuid AND courseid ='003';

--40、查询选修“叶平”老师所授课程的学生中，成绩最高的学生姓名及其成绩 
SELECT t1.stuname,t2.maxscore FROM tblstudent t1 ,
(SELECT tblscore.stuid,max(tblscore.score) maxscore FROM tblscore,tblteacher,tblcourse 
WHERE tblcourse.teaid = tblteacher.teaid AND tblteacher.teaname='叶平' AND tblcourse.courseid = tblscore.courseid 
GROUP BY tblscore.stuid) t2 WHERE t1.stuid = t2.stuid ;

--41、查询各个课程及相应的选修人数 
 SELECT cs.courseid,cs.coursename ,COUNT(1) FROM tblscore sc
  INNER JOIN tblcourse cs
 ON sc.courseid = sc.courseid
  GROUP BY cs.courseid,cs.coursename

--42、查询不同课程成绩相同的学生的学号、课程号、学生成绩 
 SELECT t1.stuid,t1.courseid,t1.score FROM tblscore t1 INNER JOIN  tblscore t2   ON t1.stuid = t2.stuid
   WHERE t1.courseid <> t2.courseid AND t1.score = t2.score
 
--43、查询每门功成绩最好的前两名 
 

--44、统计每门课程的学生选修人数（超过10人的课程才统计）。要求输出课程号和选修人数，查询结果按人数降序排列，若人数相同，按课程号升序排列  
 
--45、检索至少选修两门课程的学生学号 
 

--46、查询全部学生都选修的课程的课程号和课程名 
 

--47、查询没学过“叶平”老师讲授的任一门课程的学生姓名 
 

--48、查询两门以上不及格课程的同学的学号及其平均成绩 
 
--49、检索“004”课程分数小于60，按分数降序排列的同学学号 (ok)
 

--50、删除“002”同学的“001”课程的成绩
