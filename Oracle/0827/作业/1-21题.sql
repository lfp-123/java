SELECT * FROM t_student
SELECT *FROM t_course
SELECT *FROM t_teacher
SELECT *FROM t_grade
SELECT *FROM t_score
/*1,��ѯstudent���е����м�¼sname,ssex,class*/
SELECT sname,ssex,CLASS FROM t_student;

/*2,��ѯ���в��ظ���depart�н�ʦ*/
SELECT DISTINCT  depart FROM t_teacher;

/*3,��ѯstudent�����м�¼*/
SELECT *FROM t_student;

/*4,��ѯscore���гɼ���60-80�����м�¼*/
SELECT DEGREE FROM t_score WHERE DEGREE>60 AND DEGREE<80;

/*5,��ѯscore���гɼ�Ϊ85��86��88�ļ�¼*/
SELECT DEGREE FROM t_score WHERE  DEGREE IN(88,85,86);

/*6,��ѯstudent���� 95031 ���Ա�ΪŮ��ͬѧ��¼*/
SELECT * FROM t_student WHERE CLASS = 95031 OR ssex = 'Ů';

/*7,��class�����ѯstudent������м�¼��*/
SELECT * FROM t_student ORDER BY CLASS DESC

/*8, ��cno����degree�����ѯscore������м�¼��*/
SELECT * FROM t_score ORDER BY Cno ,DEGREE DESC

/*9, ��ѯ��95031�����ѧ��������*/
SELECT CLASS, COUNT(1) FROM t_student WHERE CLASS = 95031 GROUP BY CLASS;

/*10,��ѯscore������߷ֵ�ѧ��ѧ�źͿγ̺�*/
SELECT  MAX(sno),MAX(cno) FROM t_score 

/*11����ѯ��3-105���ſγ̵�ƽ���֡�*/
SELECT cno,AVG(DEGREE) FROM t_score WHERE cno ='3-105' GROUP BY cno;

/*12,��ѯscore����������5��ѧ��ѡ�޵Ĳ���3��ͷ�Ŀγ̵�ƽ��������*/
SELECT AVG(sno),cno FROM t_score WHERE cno LIKE '3%' GROUP BY cno; 

/*13,��ѯ��ͷִ���70����߷�С��90��sno�� */
SELECT sno,DEGREE FROM t_score WHERE DEGREE>70 AND DEGREE<90;

/*14,��ѯ����ѧ����sname,cno,degree*/
SELECT sname,DEGREE,cno FROM t_score,t_student  WHERE t_student.sno = t_score.sno;

/*15,��ѯ����ѧ����sno��cname��degree�С�*/
SELECT t_student.sno,cname,DEGREE FROM t_student,t_course,t_score
 WHERE t_student.sno=t_score.sno AND t_score.cno= t_course.cno;
 
 /*16,��ѯ����ѧ����sname��cname��degree�С�*/
 SELECT t_student.sname,cname,DEGREE FROM t_student,t_course,t_score 
 WHERE t_student.sno=t_score.sno AND t_score.cno= t_course.cno;

/*17����ѯ��95033������ѡ�γ̵�ƽ���֡�*/
SELECT  CLASS,AVG(DEGREE) FROM t_score,t_student   GROUP BY CLASS HAVING CLASS=95033

--18����ѯ����ͬѧ��sno��cno��rank�С�
SELECT  t_score.sno,t_score.cno,t_score.degree,t_grade.RANK 
FROM t_score  LEFT JOIN t_grade ON t_score.DEGREE BETWEEN t_grade.low AND t_grade.upp ;

/*19����ѯѡ�ޡ�3-105���γ̵ĳɼ����ڡ�109����ͬѧ�ɼ�������ͬѧ�ļ�¼��*/
 SELECT * FROM t_course,t_student,t_score 
 WHERE t_student.sno=t_score.sno AND t_course.cno ='3-105' AND
 t_score.degree>(SELECT DEGREE FROM t_score WHERE sno = 109 AND cno = '3-105');
 
 /*21����ѯ�ɼ�����ѧ��Ϊ��109�����γ̺�Ϊ��3-105���ĳɼ������м�¼��*/
 SELECT * FROM t_course,t_student,t_score 
 WHERE t_student.sno=t_score.sno  AND
 t_score.degree>(SELECT DEGREE FROM t_score WHERE sno = 109 AND cno = '3-105');

--25����ѯ95033���95031��ȫ��ѧ���ļ�¼��
 SELECT * FROM t_student WHERE CLASS=95033 OR CLASS = 95031
 
 /*23����ѯ�����񡰽�ʦ�οε�ѧ���ɼ���*/
 SELECT sname,DEGREE,Tname FROM t_student,t_score,t_teacher,t_course WHERE 
 t_student.sno = t_score.sno AND t_score.cno = t_course.cno AND t_course.tno = t_teacher.tno AND t_teacher.tname = '����';
 

 
 
 
