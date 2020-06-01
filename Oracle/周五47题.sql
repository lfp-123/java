题目：
1、 查询student表中的所有记录的sname、ssex和class列。
   SELECT sname,ssex,CLASS FROM student;
2、 查询教师所有的单位即不重复的depart列。
   SELECT distinct depart FROM T_teacher;
3、 查询student表的所有记录。
SELECT * FROM student;
4、 查询score表中成绩在60到80之间的所有记录。
SELECT * FROM t_score WHERE DEGREE>60 AND DEGREE<80;
5、 查询score表中成绩为85，86或88的记录。
SELECT * FROM t_score WHERE DEGREE = 85 OR DEGREE = 86 OR DEGREE= 88
6、 查询student表中“95031”班或性别为“女”的同学记录。
SELECT * FROM student WHERE CLASS = 95035 OR ssex = '女';
7、 以class降序查询student表的所有记录。 
SELECT * FROM student ORDER BY CLASS DESC; --asc 升序
8、 以cno升序、degree降序查询score表的所有记录。
SELECT cno ,DEGREE FROM t_score ORDER BY cno ASC ,DEGREE DESC;
9、 查询“95031”班的学生人数。
SELECT COUNT(CLASS)  FROM student WHERE CLASS=95031;
SELECT * FROM t_score;
10、查询score表中的最高分的学生学号和课程号。
SELECT sno,cno FROM t_score WHERE DEGREE =(
SELECT MAX(DEGREE) FROM t_score);
11、查询‘3-105’号课程的平均分。

SELECT AVG(DEGREE) FROM t_score WHERE cno ='3-105' 
12、查询score表中至少有5名学生选修的并以3开头的课程的平均分数。
SELECT cno,  AVG(DEGREE) FROM t_score  GROUP BY cno HAVING  cno LIKE '3%'  AND COUNT (cno)>=5;
13、查询最低分大于70，最高分小于90的sno列。
SELECT sno  FROM t_score 
GROUP BY sno HAVING MAX(DEGREE)<90 AND MIN(DEGREE)>70;
14、查询所有学生的sname、cno和degree列。
SELECT s1.sname ,s2.cno,s2.degree  FROM student s1 LEFT JOIN IN 

SELECT * FROM t_score;
15、查询所有学生的sno、cname和degree列。
SELECT Sno,Cname,Degree
FROM t_Score FULL JOIN t_Course
ON(t_Score.Cno=t_Course.Cno)
ORDER BY Sno;
SELECT * FROM t_score;
16、查询所有学生的sname、cname和degree列。
SELECT s1.sname,s2.cname,s3.degree FROM student s1,t_course s2 ,t_score s3 WHERE
s1.sno = s3.sno AND s2.cno=s3.cno;
17、查询“95033”班所选课程的平均分。
SELECT avg(DEGREE) FROM t_score WHERE sno  IN(
SELECT sno FROM student WHERE CLASS =95033)
18、查询所有同学的sno、cno和rank列。------biaoji
SELECT Sno,Cno,rank
FROM t_Score INNER JOIN t_grade
ON(t_Score.Degree>=t_grade.low AND t_Score.Degree<=t_grade.upp)
ORDER BY Sno;
19、查询选修“3-105”课程的成绩高于“109”号同学课程最高成绩的所有同学的记录。
SELECT * FROM t_score WHERE cno='3-105' AND DEGREE> 
(SELECT MAX(DEGREE) FROM t_score WHERE sno= 109) ;
20、查询score中选学一门以上课程的同学中分数为非最高分成绩的记录。--------biaoji
SELECT *  FROM t_score GROUP BY sno HAVING COUNT(cno)>1 AND DEGREE!=MAX(DEGREE);
21、查询成绩高于学号为“109”、课程号为“3-105”的成绩的所有记录。
SELECT * FROM t_score WHERE DEGREE>(
SELECT DEGREE FROM t_score WHERE cno = '3-105' AND sno=109);
22、查询和学号为108的同学同年出生的所有学生的sno、sname和sbirthday列。
SELECT sno,sname,sbirthday FROM student WHERE  EXTRACT(YEAR FROM  sbirthday) =(  
SELECT EXTRACT(YEAR FROM  sbirthday)   FROM student WHERE sno = 108);
23、查询“张旭“教师任课的学生成绩。
SELECT s1.degree FROM t_score s1,t_course s2 ,t_teacher s3 WHERE 
s1.cno= s2.cno AND s2.tno = s3.tno AND s3.tname = '张旭';
24、查询选修某课程的同学人数多于5人的教师姓名。
SELECT s1.tname FROM t_teacher s1 ,(
SELECT cno 课程号,COUNT(cno) FROM  t_score GROUP BY cno HAVING COUNT(cno)>5) s2 ,t_course  s3 WHERE 
s3.cno = s2.课程号 AND s1.tno = s3.tno ;
25、查询95033班和95031班全体学生的记录。
--效率高
SELECT * FROM student WHERE 
EXISTS  (
SELECT *FROM student WHERE CLASS = 95033  OR CLASS = 95031);
26、查询存在有85分以上成绩的课程cno.
SELECT  cno,DEGREE FROM t_score WHERE DEGREE>85;
27、查询出“计算机系“教师所教课程的成绩表。
SELECT * FROM t_score WHERE cno IN (
SELECT cno FROM t_course WHERE tno IN(
SELECT tno FROM t_teacher  WHERE depart ='计算机系'));
28、查询“计算机系”与“电子工程系“不同职称的教师的tname和prof。
SELECT Tname,Prof
FROM t_teacher
WHERE Depart='计算机系' AND Prof NOT IN(
    SELECT DISTINCT Prof
    FROM t_teacher
    WHERE Depart='电子工程系');
29、查询选修编号为“3-105“课程且成绩至少高于选修编号为“3-245”的同学的cno、sno和degree,并按degree从高到低次序排序。

30、查询选修编号为“3-105”且成绩高于选修编号为“3-245”课程的同学的cno、sno和degree.
SELECT cno,sno,DEGREE FROM t_score 
31、查询所有教师和同学的name、sex和birthday.
SELECT sNAME,ssex,sbirthday FROM student UNION  (
SELECT tname,tsex,tbirthday FROM t_teacher);
32、查询所有“女”教师和“女”同学的name、sex和birthday.
SELECT sNAME,ssex,sbirthday FROM student WHERE ssex ='女'  
UNION  (
SELECT tname,tsex,tbirthday FROM t_teacher WHERE tsex = '女');
33、查询成绩比该课程平均成绩低的同学的成绩表。
SELECT t1.sno ,t2.cnos,t1.degree,t2.avgs FROM t_score t1, 
(SELECT cno cnos,AVG(DEGREE) avgs  FROM t_score GROUP BY cno)t2
WHERE t1.cno = t2.cnos AND t1.degree< t2.avgs;
34、查询所有任课教师的tname和depart.
SELECT tname ,depart FROM t_teacher;
35、查询所有未讲课的教师的tname和depart. 
SELECT *FROM t_teacher WHERE tno =(
SELECT tno FROM t_teacher WHERE tno NOT IN(
SELECT tno FROM t_course));
36、查询至少有2名男生的班号。--biaoji
SELECT Class,COUNT(1) AS boyCount
FROM Student
WHERE Ssex='男'
GROUP BY Class
HAVING boyCount>=2;
37、查询student表中不姓“王”的同学记录。
SELECT * FROM student  WHERE  sname  NOT IN (
SELECT sname  FROM student WHERE sname LIKE  '王%');
38、查询student表中每个学生的姓名和年龄。--biaoji

select Sname,TRUNC( (Sysdate - sbirthday)/365)  from student

39、查询student表中最大和最小的sbirthday日期值。
SELECT MAX(sbirthday),MIN(sbirthday) FROM student;
40、以班号和年龄从大到小的顺序查询student表中的全部记录。
SELECT * FROM student ORDER BY CLASS DESC,sbirthday ASC;
41、查询“男”教师及其所上的课程。

SELECT cname FROM t_course WHERE tno IN(
SELECT tno FROM t_teacher WHERE tsex ='男');
42、查询最高分同学的sno、cno和degree列。
SELECT * FROM t_score WHERE DEGREE =  (SELECT MAX(DEGREE) FROM t_score);
43、查询和“李军”同性别的所有同学的sname.
SELECT sname FROM student WHERE ssex = (SELECT ssex FROM student WHERE sname='李军');
44、查询和“李军”同性别并同班的同学sname.
SELECT sname FROM student WHERE (ssex,CLASS) IN  (SELECT ssex,Class FROM student WHERE sname='李军');
45、查询所有选修“计算机导论”课程的“男”同学的成绩表
SELECT  * FROM  t_score WHERE cno  IN  (SELECT cno FROM t_course WHERE cname = '计算机') ; 
46、查询出选修课程号为3-105和6-166的课程的学生学号与姓名
SELECT sno, sNAME  FROM student  WHERE sno IN( 
SELECT sno  FROM t_score WHERE cno = '3-105' AND sno IN ( SELECT sno  FROM t_score WHERE    cno = '6-166')) ;
47、查询出没有选修课程号为3-105和6-166的课程的学生学号与姓名
SELECT sno, sNAME  FROM student  WHERE sno NOT IN( 
SELECT sno  FROM t_score WHERE cno = '3-105' AND sno IN ( SELECT sno  FROM t_score WHERE    cno = '6-166')) ;
