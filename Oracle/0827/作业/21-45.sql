 SELECT *FROM t_student;
 SELECT *FROM t_course;
 SELECT *FROM t_teacher;
 SELECT *FROM t_grade;
 SELECT *FROM t_score;
 --20����ѯscore��ѡѧһ�����Ͽγ̵�ͬѧ�з���Ϊ����߷ֳɼ��ļ�¼��
 
 
 /*22,��ѯ��ѧ��Ϊ108��ͬѧͬ�����������ѧ����sno��sname��sbirthday�С�*/
 /*SELECT sno,sname,sbirthday FROM t_student */

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
     SELECT sname,sbirthday FROM t_student

--39����ѯstudent����������С��sbirthday����ֵ��


--40���԰�ź�����Ӵ�С��˳���ѯstudent���е�ȫ����¼��


--41����ѯ���С���ʦ�������ϵĿγ̡�

--42����ѯ��߷�ͬѧ��sno��cno��degree�С�

--43����ѯ�͡������ͬ�Ա������ͬѧ��sname.

--44����ѯ�͡������ͬ�Ա�ͬ���ͬѧsname.

--45����ѯ����ѡ�ޡ���������ۡ��γ̵ġ��С�ͬѧ�ĳɼ���
