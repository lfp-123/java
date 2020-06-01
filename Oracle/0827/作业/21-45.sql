 SELECT *FROM t_student;
 SELECT *FROM t_course;
 SELECT *FROM t_teacher;
 SELECT *FROM t_grade;
 SELECT *FROM t_score;
 --20、查询score中选学一门以上课程的同学中分数为非最高分成绩的记录。
 
 
 /*22,查询和学号为108的同学同年出生的所有学生的sno、sname和sbirthday列。*/
 /*SELECT sno,sname,sbirthday FROM t_student */

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

--29、查询选修编号为“3-105“课程且成绩至少高于选修编号为“3-245”的同学的cno、sno和degree,并按degree从高到低次序排序。

--30、查询选修编号为“3-105”且成绩高于选修编号为“3-245”课程的同学的cno、sno和degree.

--31、查询所有教师和同学的name、sex和birthday.

--32、查询所有“女”教师和“女”同学的name、sex和birthday.

--33、查询成绩比该课程平均成绩低的同学的成绩表。

--34、查询所有任课教师的tname和depart.

--35 查询所有未讲课的教师的tname和depart. 

--36、查询至少有2名男生的班号。

--37、查询student表中不姓“王”的同学记录。


--38、查询student表中每个学生的姓名和年龄。
     SELECT sname,sbirthday FROM t_student

--39、查询student表中最大和最小的sbirthday日期值。


--40、以班号和年龄从大到小的顺序查询student表中的全部记录。


--41、查询“男”教师及其所上的课程。

--42、查询最高分同学的sno、cno和degree列。

--43、查询和“李军”同性别的所有同学的sname.

--44、查询和“李军”同性别并同班的同学sname.

--45、查询所有选修“计算机导论”课程的“男”同学的成绩表
