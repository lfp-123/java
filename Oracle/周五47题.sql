��Ŀ��
1�� ��ѯstudent���е����м�¼��sname��ssex��class�С�
   SELECT sname,ssex,CLASS FROM student;
2�� ��ѯ��ʦ���еĵ�λ�����ظ���depart�С�
   SELECT distinct depart FROM T_teacher;
3�� ��ѯstudent������м�¼��
SELECT * FROM student;
4�� ��ѯscore���гɼ���60��80֮������м�¼��
SELECT * FROM t_score WHERE DEGREE>60 AND DEGREE<80;
5�� ��ѯscore���гɼ�Ϊ85��86��88�ļ�¼��
SELECT * FROM t_score WHERE DEGREE = 85 OR DEGREE = 86 OR DEGREE= 88
6�� ��ѯstudent���С�95031������Ա�Ϊ��Ů����ͬѧ��¼��
SELECT * FROM student WHERE CLASS = 95035 OR ssex = 'Ů';
7�� ��class�����ѯstudent������м�¼�� 
SELECT * FROM student ORDER BY CLASS DESC; --asc ����
8�� ��cno����degree�����ѯscore������м�¼��
SELECT cno ,DEGREE FROM t_score ORDER BY cno ASC ,DEGREE DESC;
9�� ��ѯ��95031�����ѧ��������
SELECT COUNT(CLASS)  FROM student WHERE CLASS=95031;
SELECT * FROM t_score;
10����ѯscore���е���߷ֵ�ѧ��ѧ�źͿγ̺š�
SELECT sno,cno FROM t_score WHERE DEGREE =(
SELECT MAX(DEGREE) FROM t_score);
11����ѯ��3-105���ſγ̵�ƽ���֡�

SELECT AVG(DEGREE) FROM t_score WHERE cno ='3-105' 
12����ѯscore����������5��ѧ��ѡ�޵Ĳ���3��ͷ�Ŀγ̵�ƽ��������
SELECT cno,  AVG(DEGREE) FROM t_score  GROUP BY cno HAVING  cno LIKE '3%'  AND COUNT (cno)>=5;
13����ѯ��ͷִ���70����߷�С��90��sno�С�
SELECT sno  FROM t_score 
GROUP BY sno HAVING MAX(DEGREE)<90 AND MIN(DEGREE)>70;
14����ѯ����ѧ����sname��cno��degree�С�
SELECT s1.sname ,s2.cno,s2.degree  FROM student s1 LEFT JOIN IN 

SELECT * FROM t_score;
15����ѯ����ѧ����sno��cname��degree�С�
SELECT Sno,Cname,Degree
FROM t_Score FULL JOIN t_Course
ON(t_Score.Cno=t_Course.Cno)
ORDER BY Sno;
SELECT * FROM t_score;
16����ѯ����ѧ����sname��cname��degree�С�
SELECT s1.sname,s2.cname,s3.degree FROM student s1,t_course s2 ,t_score s3 WHERE
s1.sno = s3.sno AND s2.cno=s3.cno;
17����ѯ��95033������ѡ�γ̵�ƽ���֡�
SELECT avg(DEGREE) FROM t_score WHERE sno  IN(
SELECT sno FROM student WHERE CLASS =95033)
18����ѯ����ͬѧ��sno��cno��rank�С�------biaoji
SELECT Sno,Cno,rank
FROM t_Score INNER JOIN t_grade
ON(t_Score.Degree>=t_grade.low AND t_Score.Degree<=t_grade.upp)
ORDER BY Sno;
19����ѯѡ�ޡ�3-105���γ̵ĳɼ����ڡ�109����ͬѧ�γ���߳ɼ�������ͬѧ�ļ�¼��
SELECT * FROM t_score WHERE cno='3-105' AND DEGREE> 
(SELECT MAX(DEGREE) FROM t_score WHERE sno= 109) ;
20����ѯscore��ѡѧһ�����Ͽγ̵�ͬѧ�з���Ϊ����߷ֳɼ��ļ�¼��--------biaoji
SELECT *  FROM t_score GROUP BY sno HAVING COUNT(cno)>1 AND DEGREE!=MAX(DEGREE);
21����ѯ�ɼ�����ѧ��Ϊ��109�����γ̺�Ϊ��3-105���ĳɼ������м�¼��
SELECT * FROM t_score WHERE DEGREE>(
SELECT DEGREE FROM t_score WHERE cno = '3-105' AND sno=109);
22����ѯ��ѧ��Ϊ108��ͬѧͬ�����������ѧ����sno��sname��sbirthday�С�
SELECT sno,sname,sbirthday FROM student WHERE  EXTRACT(YEAR FROM  sbirthday) =(  
SELECT EXTRACT(YEAR FROM  sbirthday)   FROM student WHERE sno = 108);
23����ѯ�����񡰽�ʦ�οε�ѧ���ɼ���
SELECT s1.degree FROM t_score s1,t_course s2 ,t_teacher s3 WHERE 
s1.cno= s2.cno AND s2.tno = s3.tno AND s3.tname = '����';
24����ѯѡ��ĳ�γ̵�ͬѧ��������5�˵Ľ�ʦ������
SELECT s1.tname FROM t_teacher s1 ,(
SELECT cno �γ̺�,COUNT(cno) FROM  t_score GROUP BY cno HAVING COUNT(cno)>5) s2 ,t_course  s3 WHERE 
s3.cno = s2.�γ̺� AND s1.tno = s3.tno ;
25����ѯ95033���95031��ȫ��ѧ���ļ�¼��
--Ч�ʸ�
SELECT * FROM student WHERE 
EXISTS  (
SELECT *FROM student WHERE CLASS = 95033  OR CLASS = 95031);
26����ѯ������85�����ϳɼ��Ŀγ�cno.
SELECT  cno,DEGREE FROM t_score WHERE DEGREE>85;
27����ѯ���������ϵ����ʦ���̿γ̵ĳɼ���
SELECT * FROM t_score WHERE cno IN (
SELECT cno FROM t_course WHERE tno IN(
SELECT tno FROM t_teacher  WHERE depart ='�����ϵ'));
28����ѯ�������ϵ���롰���ӹ���ϵ����ְͬ�ƵĽ�ʦ��tname��prof��
SELECT Tname,Prof
FROM t_teacher
WHERE Depart='�����ϵ' AND Prof NOT IN(
    SELECT DISTINCT Prof
    FROM t_teacher
    WHERE Depart='���ӹ���ϵ');
29����ѯѡ�ޱ��Ϊ��3-105���γ��ҳɼ����ٸ���ѡ�ޱ��Ϊ��3-245����ͬѧ��cno��sno��degree,����degree�Ӹߵ��ʹ�������

30����ѯѡ�ޱ��Ϊ��3-105���ҳɼ�����ѡ�ޱ��Ϊ��3-245���γ̵�ͬѧ��cno��sno��degree.
SELECT cno,sno,DEGREE FROM t_score 
31����ѯ���н�ʦ��ͬѧ��name��sex��birthday.
SELECT sNAME,ssex,sbirthday FROM student UNION  (
SELECT tname,tsex,tbirthday FROM t_teacher);
32����ѯ���С�Ů����ʦ�͡�Ů��ͬѧ��name��sex��birthday.
SELECT sNAME,ssex,sbirthday FROM student WHERE ssex ='Ů'  
UNION  (
SELECT tname,tsex,tbirthday FROM t_teacher WHERE tsex = 'Ů');
33����ѯ�ɼ��ȸÿγ�ƽ���ɼ��͵�ͬѧ�ĳɼ���
SELECT t1.sno ,t2.cnos,t1.degree,t2.avgs FROM t_score t1, 
(SELECT cno cnos,AVG(DEGREE) avgs  FROM t_score GROUP BY cno)t2
WHERE t1.cno = t2.cnos AND t1.degree< t2.avgs;
34����ѯ�����ον�ʦ��tname��depart.
SELECT tname ,depart FROM t_teacher;
35����ѯ����δ���εĽ�ʦ��tname��depart. 
SELECT *FROM t_teacher WHERE tno =(
SELECT tno FROM t_teacher WHERE tno NOT IN(
SELECT tno FROM t_course));
36����ѯ������2�������İ�š�--biaoji
SELECT Class,COUNT(1) AS boyCount
FROM Student
WHERE Ssex='��'
GROUP BY Class
HAVING boyCount>=2;
37����ѯstudent���в��ա�������ͬѧ��¼��
SELECT * FROM student  WHERE  sname  NOT IN (
SELECT sname  FROM student WHERE sname LIKE  '��%');
38����ѯstudent����ÿ��ѧ�������������䡣--biaoji

select Sname,TRUNC( (Sysdate - sbirthday)/365)  from student

39����ѯstudent����������С��sbirthday����ֵ��
SELECT MAX(sbirthday),MIN(sbirthday) FROM student;
40���԰�ź�����Ӵ�С��˳���ѯstudent���е�ȫ����¼��
SELECT * FROM student ORDER BY CLASS DESC,sbirthday ASC;
41����ѯ���С���ʦ�������ϵĿγ̡�

SELECT cname FROM t_course WHERE tno IN(
SELECT tno FROM t_teacher WHERE tsex ='��');
42����ѯ��߷�ͬѧ��sno��cno��degree�С�
SELECT * FROM t_score WHERE DEGREE =  (SELECT MAX(DEGREE) FROM t_score);
43����ѯ�͡������ͬ�Ա������ͬѧ��sname.
SELECT sname FROM student WHERE ssex = (SELECT ssex FROM student WHERE sname='���');
44����ѯ�͡������ͬ�Ա�ͬ���ͬѧsname.
SELECT sname FROM student WHERE (ssex,CLASS) IN  (SELECT ssex,Class FROM student WHERE sname='���');
45����ѯ����ѡ�ޡ���������ۡ��γ̵ġ��С�ͬѧ�ĳɼ���
SELECT  * FROM  t_score WHERE cno  IN  (SELECT cno FROM t_course WHERE cname = '�����') ; 
46����ѯ��ѡ�޿γ̺�Ϊ3-105��6-166�Ŀγ̵�ѧ��ѧ��������
SELECT sno, sNAME  FROM student  WHERE sno IN( 
SELECT sno  FROM t_score WHERE cno = '3-105' AND sno IN ( SELECT sno  FROM t_score WHERE    cno = '6-166')) ;
47����ѯ��û��ѡ�޿γ̺�Ϊ3-105��6-166�Ŀγ̵�ѧ��ѧ��������
SELECT sno, sNAME  FROM student  WHERE sno NOT IN( 
SELECT sno  FROM t_score WHERE cno = '3-105' AND sno IN ( SELECT sno  FROM t_score WHERE    cno = '6-166')) ;
