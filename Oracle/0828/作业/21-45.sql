 SELECT *FROM t_student;
 SELECT *FROM t_course;
 SELECT *FROM t_teacher;
 SELECT *FROM t_grade;
 SELECT *FROM t_score;
 --20、查询score中选学一门以上课程的同学中分数为非最高分成绩的记录。
 SELECT * FROM t_score t1 WHERE 
 t1.DEGREE < (Select MAX(t2.DEGREE) FROM t_score t2 WHERE t2.cno = t1.cno) 
 AND sno IN 
        (SELECT sno FROM t_score GROUP BY sno HAVING COUNT(*)>1); 
 
 /*22,查询和学号为108的同学同年出生的所有学生的sno、sname和sbirthday列。*/
 SELECT *FROM t_student WHERE sbirthday = 
 ( SELECT sbirthday FROM t_student WHERE sno =108);

--24、查询选修某课程的同学人数多于5人的教师姓名。
--子查询的方式
  SELECT tname FROM t_teacher WHERE tno IN


--26、查询存在有85分以上成绩的课程cno.
SELECT t_score.cno FROM t_score WHERE 
t_score.degree>85;

--27、查询出“计算机系“教师所教课程的成绩表。
SELECT sno,cno,DEGREE FROM t_score WHERE cno IN 
(SELECT cno FROM t_course WHERE t_course.tno  IN
(SELECT tno FROM t_teacher WHERE depart = '计算机系'));


--28、查询“计算机系”与“电子工程系“不同职称的教师的tname和prof。
 SELECT tname,prof,depart FROM t_teacher WHERE depart = '计算机系' 
 UNION 
 SELECT tname,prof,depart FROM t_teacher WHERE depart = '电子工程系';

--29、查询选修编号为“3-105“课程且成绩至少高于选修编号为“3-245”的同学的cno、sno和degree,并按degree从高到低次序排序。
select Cno,Sno,Degree from t_Score a where (select Degree from t_Score b where Cno='3-105' and b.Sno=a.Sno)>=(select Degree from t_Score c where Cno='3-245' and c.Sno=a.Sno) order by Degree desc

--30、查询选修编号为“3-105”且成绩高于选修编号为“3-245”课程的同学的cno、sno和degree.
select * from t_Score where Cno='3-105' and Degree >any(select Degree from t_Score where Cno='3-245');

--31、查询所有教师和同学的name、sex和birthday.
SELECT tname,tsex,tbirthday FROM t_teacher;

--32、查询所有“女”教师和“女”同学的name、sex和birthday.
SELECT tname,tsex,tbirthday FROM t_teacher WHERE Tsex  ='女'
UNION 
SELECT sname,ssex,sbirthday FROM t_student WHERE ssex = '女';

--33、查询成绩比该课程平均成绩低的同学的成绩表。
SELECT * FROM t_score t1, 
 (SELECT cno,AVG(DEGREE) score FROM t_score GROUP BY cno ) t2 WHERE 
 t1.degree<t2.score AND t1.cno = t2.cno;


--34、查询所有任课教师的tname和depart.
 SELECT tname,depart FROM t_teacher;

--35 查询所有未讲课的教师的tname和depart. 

select Tname,Depart from t_teacher where Tname 
not in (select  tname from t_teacher,t_course,t_score where t_teacher.tno=t_Course.Tno and t_Course.Cno=t_Score.Cno);

--36、查询至少有2名男生的班号。
    SELECT CLASS FROM t_student WHERE 
    ssex = '男' GROUP BY CLASS HAVING COUNT(1)>1;

--37、查询student表中不姓“王”的同学记录。
    SELECT * FROM t_student WHERE sname NOT LIKE '王%';

--38、查询student表中每个学生的姓名和年龄。
     SELECT sname,sbirthday FROM t_student

--39、查询student表中最大和最小的sbirthday日期值。
    SELECT MAX(sbirthday),MIN(sbirthday) FROM t_student;

--40、以班号和年龄从大到小的顺序查询student表中的全部记录。
SELECT * FROM t_student ORDER BY CLASS DESC,sbirthday DESC;



--41、查询“男”教师及其所上的课程。
SELECT t3.tname, t1.cname FROM t_course t1,t_teacher t3 WHERE 
t1.tno IN (
SELECT t2.tno FROM t_teacher t2 WHERE t2.tsex ='男' 
) AND t3.tno = t1.tno;

--42、查询最高分同学的sno、cno和degree列。

SELECT * FROM t_score WHERE 
DEGREE = (SELECT MAX(Degree) FROM t_score);

--43、查询和“李军”同性别的所有同学的sname.
SELECT sname FROM t_student WHERE  
ssex = (SELECT ssex FROM t_student WHERE sname='李军') 
 
AND sname NOT IN ('李军');

--44、查询和“李军”同性别并同班的同学sname.
SELECT sname FROM t_student WHERE  
ssex = (SELECT ssex FROM t_student WHERE sname='李军') 
AND CLASS = (SELECT CLASS FROM t_student WHERE sname ='李军') 
AND sname NOT IN ('李军');

SELECT sname FROM t_student WHERE 
ssex = '男'AND CLASS =(SELECT CLASS FROM t_student WHERE sname ='李军') AND sname NOT IN '李军';

--45、查询所有选修“计算机导论”课程的“男”同学的成绩表.
SELECT t2.sname,t2.ssex,t3.cname,t1.degree FROM t_score t1,t_student t2 ,t_course t3
WHERE t3.cno = (SELECT t3.cno FROM t_course t3 WHERE cname = '计算机导论')
AND t1.sno = t2.sno AND t2.ssex='男';
