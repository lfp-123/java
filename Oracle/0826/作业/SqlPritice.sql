SELECT * FROM t_student
SELECT *FROM t_course
SELECT *FROM t_teacher
SELECT *FROM t_grade
SELECT *FROM t_score
/*1,查询student表中的所有记录sname,ssex,class*/
SELECT sname,ssex,CLASS FROM t_student;

/*2,查询所有不重复的depart列教师*/
SELECT DISTINCT  depart FROM t_teacher;

/*3,查询student的所有记录*/
SELECT *FROM t_student;

/*4,查询score表中成绩在60-80的所有记录*/
SELECT DEGREE FROM t_score WHERE DEGREE>60 AND DEGREE<80;

/*5,查询score表中成绩为85，86或88的记录*/
SELECT DEGREE FROM t_score WHERE  DEGREE IN(88,85,86);

/*6,查询student表里 95031 或性别为女的同学记录*/
SELECT * FROM t_student WHERE CLASS = 95031 OR ssex = '女';

/*9, 查询“95031”班的学生人数。*/
SELECT sname FROM t_student WHERE CLASS = 95031;

/*10,查询score表中最高分的学生学号和课程号*/
/*SELECT  sno,cno FROM t_score WHERE MAXVALUE DEGREE;/

/*13,查询最低分大于70，最高分小于90的sno列 */
SELECT sno,DEGREE FROM t_score WHERE DEGREE>70 AND DEGREE<90;

/*14,查询所有学生的sname,cno,degree*/
SELECT sname,DEGREE,cno FROM t_score,t_student  WHERE t_student.sno = t_score.sno;

/*15,查询所有学生的sno、cname和degree列。*/
SELECT t_student.sno,cname,DEGREE FROM t_student,t_course,t_score
 WHERE t_student.sno=t_score.sno AND t_score.cno= t_course.cno;
 
 /*16,查询所有学生的sname、cname和degree列。*/
 SELECT t_student.sname,cname,DEGREE FROM t_student,t_course,t_score 
 WHERE t_student.sno=t_score.sno AND t_score.cno= t_course.cno;

/*19、查询选修“3-105”课程的成绩高于“109”号同学成绩的所有同学的记录。*/
 SELECT * FROM t_course,t_student,t_score 
 WHERE t_student.sno=t_score.sno AND t_course.cno ='3-105' AND
 t_score.degree>(SELECT DEGREE FROM t_score WHERE sno = 109 AND cno = '3-105');
 
 /*21、查询成绩高于学号为“109”、课程号为“3-105”的成绩的所有记录。*/
 SELECT * FROM t_course,t_student,t_score 
 WHERE t_student.sno=t_score.sno  AND
 t_score.degree>(SELECT DEGREE FROM t_score WHERE sno = 109 AND cno = '3-105');

 /*22,查询和学号为108的同学同年出生的所有学生的sno、sname和sbirthday列。*/
 /*SELECT sno,sname,sbirthday FROM t_student */
 
 /*23、查询“张旭“教师任课的学生成绩。*/
 SELECT sname,DEGREE,Tname FROM t_student,t_score,t_teacher,t_course WHERE 
 t_student.sno = t_score.sno AND t_score.cno = t_course.cno AND t_course.tno = t_teacher.tno AND t_teacher.tname = '张旭';
 

--24、查询选修某课程的同学人数多于5人的教师姓名。
--子查询的方式


--25、查询95033班和95031班全体学生的记录。

--26、查询存在有85分以上成绩的课程cno.

--27、查询出“计算机系“教师所教课程的成绩表。

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


--39、查询student表中最大和最小的sbirthday日期值。


--40、以班号和年龄从大到小的顺序查询student表中的全部记录。


--41、查询“男”教师及其所上的课程。

--42、查询最高分同学的sno、cno和degree列。
--43、查询和“李军”同性别的所有同学的sname.
--44、查询和“李军”同性别并同班的同学sname.
--45、查询所有选修“计算机导论”课程的“男”同学的成绩表
 
 
 
