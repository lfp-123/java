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

/*7,以class降序查询student表的所有记录。*/
SELECT * FROM t_student ORDER BY CLASS DESC

/*8, 以cno升序、degree降序查询score表的所有记录。*/
SELECT * FROM t_score ORDER BY Cno ,DEGREE DESC

/*9, 查询“95031”班的学生人数。*/
SELECT CLASS, COUNT(1) FROM t_student WHERE CLASS = 95031 GROUP BY CLASS;

/*10,查询score表中最高分的学生学号和课程号*/
SELECT  MAX(sno),MAX(cno) FROM t_score 

/*11、查询‘3-105’号课程的平均分。*/
SELECT cno,AVG(DEGREE) FROM t_score WHERE cno ='3-105' GROUP BY cno;

/*12,查询score表中至少有5名学生选修的并以3开头的课程的平均分数。*/
SELECT AVG(sno),cno FROM t_score WHERE cno LIKE '3%' GROUP BY cno; 

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

/*17、查询“95033”班所选课程的平均分。*/
SELECT  CLASS,AVG(DEGREE) FROM t_score,t_student   GROUP BY CLASS HAVING CLASS=95033

--18、查询所有同学的sno、cno和rank列。
SELECT  t_score.sno,t_score.cno,t_score.degree,t_grade.RANK 
FROM t_score  LEFT JOIN t_grade ON t_score.DEGREE BETWEEN t_grade.low AND t_grade.upp ;

/*19、查询选修“3-105”课程的成绩高于“109”号同学成绩的所有同学的记录。*/
 SELECT * FROM t_course,t_student,t_score 
 WHERE t_student.sno=t_score.sno AND t_course.cno ='3-105' AND
 t_score.degree>(SELECT DEGREE FROM t_score WHERE sno = 109 AND cno = '3-105');
 
 /*21、查询成绩高于学号为“109”、课程号为“3-105”的成绩的所有记录。*/
 SELECT * FROM t_course,t_student,t_score 
 WHERE t_student.sno=t_score.sno  AND
 t_score.degree>(SELECT DEGREE FROM t_score WHERE sno = 109 AND cno = '3-105');

--25、查询95033班和95031班全体学生的记录。
 SELECT * FROM t_student WHERE CLASS=95033 OR CLASS = 95031
 
 /*23、查询“张旭“教师任课的学生成绩。*/
 SELECT sname,DEGREE,Tname FROM t_student,t_score,t_teacher,t_course WHERE 
 t_student.sno = t_score.sno AND t_score.cno = t_course.cno AND t_course.tno = t_teacher.tno AND t_teacher.tname = '张旭';
 

 
 
 
