 SELECT *FROM t_student;
 SELECT *FROM t_course;
 SELECT *FROM t_teacher;
 SELECT *FROM t_grade;
 SELECT *FROM t_score;
 --20����ѯscore��ѡѧһ�����Ͽγ̵�ͬѧ�з���Ϊ����߷ֳɼ��ļ�¼��
 SELECT * FROM t_score t1 WHERE 
 t1.DEGREE < (Select MAX(t2.DEGREE) FROM t_score t2 WHERE t2.cno = t1.cno) 
 AND sno IN 
        (SELECT sno FROM t_score GROUP BY sno HAVING COUNT(*)>1); 
 
 /*22,��ѯ��ѧ��Ϊ108��ͬѧͬ�����������ѧ����sno��sname��sbirthday�С�*/
 SELECT *FROM t_student WHERE sbirthday = 
 ( SELECT sbirthday FROM t_student WHERE sno =108);

--24����ѯѡ��ĳ�γ̵�ͬѧ��������5�˵Ľ�ʦ������
--�Ӳ�ѯ�ķ�ʽ
  SELECT tname FROM t_teacher WHERE tno IN


--26����ѯ������85�����ϳɼ��Ŀγ�cno.
SELECT t_score.cno FROM t_score WHERE 
t_score.degree>85;

--27����ѯ���������ϵ����ʦ���̿γ̵ĳɼ���
SELECT sno,cno,DEGREE FROM t_score WHERE cno IN 
(SELECT cno FROM t_course WHERE t_course.tno  IN
(SELECT tno FROM t_teacher WHERE depart = '�����ϵ'));


--28����ѯ�������ϵ���롰���ӹ���ϵ����ְͬ�ƵĽ�ʦ��tname��prof��
 SELECT tname,prof,depart FROM t_teacher WHERE depart = '�����ϵ' 
 UNION 
 SELECT tname,prof,depart FROM t_teacher WHERE depart = '���ӹ���ϵ';

--29����ѯѡ�ޱ��Ϊ��3-105���γ��ҳɼ����ٸ���ѡ�ޱ��Ϊ��3-245����ͬѧ��cno��sno��degree,����degree�Ӹߵ��ʹ�������
select Cno,Sno,Degree from t_Score a where (select Degree from t_Score b where Cno='3-105' and b.Sno=a.Sno)>=(select Degree from t_Score c where Cno='3-245' and c.Sno=a.Sno) order by Degree desc

--30����ѯѡ�ޱ��Ϊ��3-105���ҳɼ�����ѡ�ޱ��Ϊ��3-245���γ̵�ͬѧ��cno��sno��degree.
select * from t_Score where Cno='3-105' and Degree >any(select Degree from t_Score where Cno='3-245');

--31����ѯ���н�ʦ��ͬѧ��name��sex��birthday.
SELECT tname,tsex,tbirthday FROM t_teacher;

--32����ѯ���С�Ů����ʦ�͡�Ů��ͬѧ��name��sex��birthday.
SELECT tname,tsex,tbirthday FROM t_teacher WHERE Tsex  ='Ů'
UNION 
SELECT sname,ssex,sbirthday FROM t_student WHERE ssex = 'Ů';

--33����ѯ�ɼ��ȸÿγ�ƽ���ɼ��͵�ͬѧ�ĳɼ���
SELECT * FROM t_score t1, 
 (SELECT cno,AVG(DEGREE) score FROM t_score GROUP BY cno ) t2 WHERE 
 t1.degree<t2.score AND t1.cno = t2.cno;


--34����ѯ�����ον�ʦ��tname��depart.
 SELECT tname,depart FROM t_teacher;

--35 ��ѯ����δ���εĽ�ʦ��tname��depart. 

select Tname,Depart from t_teacher where Tname 
not in (select  tname from t_teacher,t_course,t_score where t_teacher.tno=t_Course.Tno and t_Course.Cno=t_Score.Cno);

--36����ѯ������2�������İ�š�
    SELECT CLASS FROM t_student WHERE 
    ssex = '��' GROUP BY CLASS HAVING COUNT(1)>1;

--37����ѯstudent���в��ա�������ͬѧ��¼��
    SELECT * FROM t_student WHERE sname NOT LIKE '��%';

--38����ѯstudent����ÿ��ѧ�������������䡣
     SELECT sname,sbirthday FROM t_student

--39����ѯstudent����������С��sbirthday����ֵ��
    SELECT MAX(sbirthday),MIN(sbirthday) FROM t_student;

--40���԰�ź�����Ӵ�С��˳���ѯstudent���е�ȫ����¼��
SELECT * FROM t_student ORDER BY CLASS DESC,sbirthday DESC;



--41����ѯ���С���ʦ�������ϵĿγ̡�
SELECT t3.tname, t1.cname FROM t_course t1,t_teacher t3 WHERE 
t1.tno IN (
SELECT t2.tno FROM t_teacher t2 WHERE t2.tsex ='��' 
) AND t3.tno = t1.tno;

--42����ѯ��߷�ͬѧ��sno��cno��degree�С�

SELECT * FROM t_score WHERE 
DEGREE = (SELECT MAX(Degree) FROM t_score);

--43����ѯ�͡������ͬ�Ա������ͬѧ��sname.
SELECT sname FROM t_student WHERE  
ssex = (SELECT ssex FROM t_student WHERE sname='���') 
 
AND sname NOT IN ('���');

--44����ѯ�͡������ͬ�Ա�ͬ���ͬѧsname.
SELECT sname FROM t_student WHERE  
ssex = (SELECT ssex FROM t_student WHERE sname='���') 
AND CLASS = (SELECT CLASS FROM t_student WHERE sname ='���') 
AND sname NOT IN ('���');

SELECT sname FROM t_student WHERE 
ssex = '��'AND CLASS =(SELECT CLASS FROM t_student WHERE sname ='���') AND sname NOT IN '���';

--45����ѯ����ѡ�ޡ���������ۡ��γ̵ġ��С�ͬѧ�ĳɼ���.
SELECT t2.sname,t2.ssex,t3.cname,t1.degree FROM t_score t1,t_student t2 ,t_course t3
WHERE t3.cno = (SELECT t3.cno FROM t_course t3 WHERE cname = '���������')
AND t1.sno = t2.sno AND t2.ssex='��';
