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

/*9, ��ѯ��95031�����ѧ��������*/
SELECT sname FROM t_student WHERE CLASS = 95031;

/*10,��ѯscore������߷ֵ�ѧ��ѧ�źͿγ̺�*/
/*SELECT  sno,cno FROM t_score WHERE MAXVALUE DEGREE;/

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

/*19����ѯѡ�ޡ�3-105���γ̵ĳɼ����ڡ�109����ͬѧ�ɼ�������ͬѧ�ļ�¼��*/
 SELECT * FROM t_course,t_student,t_score 
 WHERE t_student.sno=t_score.sno AND t_course.cno ='3-105' AND
 t_score.degree>(SELECT DEGREE FROM t_score WHERE sno = 109 AND cno = '3-105');
 
 /*21����ѯ�ɼ�����ѧ��Ϊ��109�����γ̺�Ϊ��3-105���ĳɼ������м�¼��*/
 SELECT * FROM t_course,t_student,t_score 
 WHERE t_student.sno=t_score.sno  AND
 t_score.degree>(SELECT DEGREE FROM t_score WHERE sno = 109 AND cno = '3-105');

 /*22,��ѯ��ѧ��Ϊ108��ͬѧͬ�����������ѧ����sno��sname��sbirthday�С�*/
 /*SELECT sno,sname,sbirthday FROM t_student */
 
 /*23����ѯ�����񡰽�ʦ�οε�ѧ���ɼ���*/
 SELECT sname,DEGREE,Tname FROM t_student,t_score,t_teacher,t_course WHERE 
 t_student.sno = t_score.sno AND t_score.cno = t_course.cno AND t_course.tno = t_teacher.tno AND t_teacher.tname = '����';
 

--24����ѯѡ��ĳ�γ̵�ͬѧ��������5�˵Ľ�ʦ������
--�Ӳ�ѯ�ķ�ʽ


--25����ѯ95033���95031��ȫ��ѧ���ļ�¼��

--26����ѯ������85�����ϳɼ��Ŀγ�cno.

--27����ѯ���������ϵ����ʦ���̿γ̵ĳɼ���

--28����ѯ�������ϵ���롰���ӹ���ϵ����ְͬ�ƵĽ�ʦ��tname��prof��

--29����ѯѡ�ޱ��Ϊ��3-105���γ��ҳɼ����ٸ���ѡ�ޱ��Ϊ��3-245����ͬѧ��cno��sno��degree,����degree�Ӹߵ��ʹ�������

--30����ѯѡ�ޱ��Ϊ��3-105���ҳɼ�����ѡ�ޱ��Ϊ��3-245���γ̵�ͬѧ��cno��sno��degree.

--31����ѯ���н�ʦ��ͬѧ��name��sex��birthday.

--32����ѯ���С�Ů����ʦ�͡�Ů��ͬѧ��name��sex��birthday.

--33����ѯ�ɼ��ȸÿγ�ƽ���ɼ��͵�ͬѧ�ĳɼ���

--34����ѯ�����ον�ʦ��tname��depart.

--35 ��ѯ����δ���εĽ�ʦ��tname��depart. 

--36����ѯ������2�������İ�š�

--37����ѯstudent���в��ա�������ͬѧ��¼��


--38����ѯstudent����ÿ��ѧ�������������䡣


--39����ѯstudent����������С��sbirthday����ֵ��


--40���԰�ź�����Ӵ�С��˳���ѯstudent���е�ȫ����¼��


--41����ѯ���С���ʦ�������ϵĿγ̡�

--42����ѯ��߷�ͬѧ��sno��cno��degree�С�
--43����ѯ�͡������ͬ�Ա������ͬѧ��sname.
--44����ѯ�͡������ͬ�Ա�ͬ���ͬѧsname.
--45����ѯ����ѡ�ޡ���������ۡ��γ̵ġ��С�ͬѧ�ĳɼ���
 
 
 
