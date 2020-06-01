SELECT * FROM tblstudent;
SELECT * FROM tblteacher;
SELECT *FROM tblcourse;
SELECT *FROM tblscore;
 


--ѧ����tblStudent�����StuId������Stuname������Stuage���Ա�Stusex��
--�γ̱�tblCourse���γ̱��CourseId���γ�����CourseName����ʦ���TeaId��
--�ɼ���tblScore��ѧ�����StuId���γ̱��CourseId���ɼ�Score��
--��ʦ��tblTeacher����ʦ���TeaId������TeaName��
--���⣺ 
--1����ѯ��001���γ̱ȡ�002���γ̳ɼ��ߵ�����ѧ����ѧ�ţ�
SELECT t1.stuid FROM 
(SELECT *  FROM tblscore WHERE courseid = 001) t1,
(SELECT *  FROM tblscore WHERE courseid = 002) t2 WHERE
 t1.stuid= t2.stuid AND t1.score>t2.score;
   
--2����ѯƽ���ɼ�����60�ֵ�ͬѧ��ѧ�ź�ƽ���ɼ���
SELECT t1.stuid ,t2.avgscore FROM tblstudent t1, 
(SELECT stuid,AVG(score)  avgscore  FROM tblscore  GROUP BY stuid ) t2 
WHERE t1.stuid = t2.stuid AND t2.avgscore>60 ORDER BY t1.stuid ;
 
SELECT stuid,AVG(score)    FROM tblscore  GROUP BY stuid HAVING AVG(score)>60 ORDER BY stuid;


--3����ѯ����ͬѧ��ѧ�š�������ѡ�������ܳɼ���
SELECT t1.stuname,t1.stuid,t2.ѡ����,t2.�ܳɼ� FROM tblstudent t1 FULL OUTER JOIN 
(
 SELECT  stuid, COUNT (courseid)  ѡ����, SUM (score)  �ܳɼ� FROM tblscore GROUP BY stuid  )  t2 ON
  t1.stuid = t2.stuid ;  

SELECT t1.stuid ,t1.stuname,COUNT (t2.courseid) ѡ����,SUM (t2.score) �ܳɼ� FROM tblstudent  t1
LEFT OUTER JOIN tblscore t2 ON  t1.stuid= t2.stuid GROUP BY t1.stuid,t1.stuname ORDER BY t1.stuid ASC

--4����ѯ�ա������ʦ�ĸ����� 
 SELECT COUNT(teaname) FROM tblteacher WHERE teaname LIKE '��%'
--5����ѯûѧ����Ҷƽ����ʦ�ε�ͬѧ��ѧ�š�������
SELECT DISTINCT      t1.stuname,t3.stuid FROM tblstudent  t1,
(SELECT  courseid,teaid FROM tblcourse t2 WHERE teaid IN
( SELECT teaid FROM tblteacher WHERE teaname != 'Ҷƽ'))  t2,
tblscore t3 
WHERE 
t3.courseid= t2.courseid;    


--6����ѯѧ����001������Ҳѧ����š�002���γ̵�ͬѧ��ѧ�š������� 
SELECT t1.stuid ,t1.stuname FROM tblstudent t1 WHERE  stuid IN (
SELECT t2.stuid FROM 
(SELECT stuid FROM tblscore WHERE courseid = '001') t2,
(SELECT stuid FROM tblscore WHERE courseid = '002') t3 WHERE t2.stuid =t3.stuid );

--7����ѯѧ����Ҷƽ����ʦ���̵����пε�ͬѧ��ѧ�š������� 
 SELECT DISTINCT      t1.stuname,t1.stuid FROM tblstudent  t1 WHERE stuid IN  (
 SELECT distinct stuid FROM tblscore WHERE  courseid IN 
(SELECT distinct  courseid FROM tblcourse t2 WHERE teaid IN
( SELECT teaid FROM tblteacher WHERE teaname = 'Ҷƽ'))) 

  SELECT DISTINCT stuid FROM tblscore WHERE courseid ='016' OR courseid ='001';
--8����ѯ�γ̱�š�002���ĳɼ��ȿγ̱�š�001���γ̵͵�����ͬѧ��ѧ�š������� 
SELECT stuid,stuname FROM tblstudent  WHERE stuid IN (
SELECT DISTINCT t2.stuid FROM 
 (SELECT stuid, score FROM  tblscore WHERE courseid = '002'  ) t2,
  (SELECT stuid, score FROM  tblscore WHERE courseid = '001'  ) t3 WHERE t3.score> t2.score AND t2.stuid = t3.stuid)
  
SELECT * FROM tblscore WHERE courseid = '001' OR courseid ='002'
--9����ѯ���пγ̳ɼ�С��60�ֵ�ͬѧ��ѧ�š�������
SELECT stuname, stuid FROM tblstudent WHERE stuid NOT IN (
SELECT  DISTINCT tblstudent.stuid FROM  tblstudent,tblscore
 WHERE tblstudent.stuid = tblscore.stuid AND  score>60) ;
   
--10����ѯû��ѧȫ���пε�ͬѧ��ѧ�š������� 
 
 

--11����ѯ������һ�ſ���ѧ��Ϊ��1001����ͬѧ��ѧ��ͬ��ͬѧ��ѧ�ź�������
SELECT t2.stuid,t1.stuname FROM  tblstudent t1,tblscore t2 WHERE t1.stuid = t2.stuid AND t2.courseid IN ( 
 SELECT  courseid FROM tblscore WHERE stuid = '1001') 

--12����ѯ����ѧ��ѧ��Ϊ��1001��ͬѧ���пγ̵�����ͬѧѧ�ź������� 
SELECT t2.stuid,t1.stuname FROM  tblstudent t1,tblscore t2 WHERE t1.stuid = t2.stuid AND t2.courseid NOT IN ( 
 SELECT  courseid FROM tblscore WHERE stuid = '1001') 

--13���ѡ�SC�����С�Ҷƽ����ʦ�̵Ŀεĳɼ�������Ϊ�˿γ̵�ƽ���ɼ���
 UPDATE tblscore SET score = ��SELECT AVG(score)  FROM  tblscore,tblcourse,tblteacher
  WHERE tblscore.stuid = tblcourse.teaid AND tblcourse.teaid = tblteacher.teaid AND tblteacher.teaname='Ҷƽ'��
   
 
--14����ѯ�͡�1002���ŵ�ͬѧѧϰ�Ŀγ���ȫ��ͬ������ͬѧѧ�ź�������  
SELECT stuid,COUNT(*) FROM 
(SELECT t1.stuid FROM tblscore t1 JOIN (
SELECT DISTINCT courseid FROM tblscore WHERE stuid ='1002') t2 ON t1.courseid= t2.courseid) GROUP BY stuid
 HAVING COUNT(*) = (SELECT COUNT(*) FROM tblscore WHERE stuid ='1002' )

SELECT stuid,courseid FROM tblscore  WHERE stuid='1002' OR stuid ='1003'  ORDER BY stuid ASC ,courseid ASC;
--15��ɾ��ѧϰ��Ҷƽ����ʦ�ε�SC���¼��


  delect FROM tblscore WHERE tblscore.courseid IN
  ( SELECT  DISTINCT tblscore.courseid FROM tblscore,tblteacher,tblcourse WHERE tblteacher.teaname ='Ҷƽ' AND tblteacher.teaid = tblcourse.teaid)
  
--16����SC���в���һЩ��¼����Щ��¼Ҫ���������������û���Ϲ���š�003���γ̵�ͬѧѧ�š�'002'�ſε�ƽ���ɼ��� 
 
   

--17����ƽ���ɼ��Ӹߵ�����ʾ����ѧ���ġ����ݿ⡱������ҵ��������Ӣ����ŵĿγ̳ɼ�����������ʽ��ʾ�� ѧ��ID,,���ݿ�,��ҵ����,Ӣ��,��Ч�γ���,��Чƽ���� 

select t.stuid,
       max(case t.cname when '���ݿ�' then t.score end) ���ݿ�,
       max(case t.cname when '��ҵ����' then t.score end) ��ҵ����,
       max(case t.cname when 'Ӣ��' then t.score end) Ӣ��,
       round(avg(t.score), 2) ƽ����,
       count(t.courseid) ��Ч�γ���
  from (
         select s.stuid, s.stuname stuname, sc.courseid, sc.score, c.coursename cname
          from tblstudent s, tblscore sc, tblcourse c
         where s.stuid = sc.stuid
           and sc.courseid = c.courseid
           and c.coursename in ('���ݿ�', '��ҵ����', 'Ӣ��')
           ) t
 group by t.stuid
 order by ƽ���� desc;  

--18����ѯ���Ƴɼ���ߺ���͵ķ֣���������ʽ��ʾ���γ�ID����߷֣���ͷ� 
SELECT courseid �γ�ID,MAX (score) ��߷�,MIN(score) ��ͷ� FROM tblscore GROUP BY courseid;


--19��������ƽ���ɼ��ӵ͵��ߺͼ����ʵİٷ����Ӹߵ���˳�� (�ٷ�������θ�ʽ��Ϊ��λС��??)

SELECT courseid , s ƽ����,round(jige /zong,4)  ������ FROM 
(
SELECT courseid, AVG(score) s  ,ROUND ( COUNT(1),2)  zong, 
SUM (CASE WHEN score>60 THEN 1 ELSE 0  END )  jige 
FROM tblscore GROUP BY courseid ORDER BY avg(score) DESC );

SELECT courseid,score FROM tblscore;
--20����ѯ���¿γ�ƽ���ɼ��ͼ����ʵİٷ���(��"1��"��ʾ): ��ҵ����001�������˼��002����OO&UML ��003�������ݿ⣨004�� 
SELECT t1.courseid,t1.avgs ƽ����  ,t2.jige/t1.zong ������ from
(SELECT  courseid,avg(score) avgs, COUNT( DISTINCT stuid) zong FROM tblscore WHERE courseid in ('001','002','003','004')  GROUP BY courseid) t1,
(SELECT  courseid,avg(score) avgS, COUNT( DISTINCT stuid) jige FROM tblscore WHERE courseid in ('001','002','003','004') AND score>60 GROUP BY courseid) t2 
WHERE t1.courseid = t2.courseid;

--21����ѯ��ͬ��ʦ���̲�ͬ�γ�ƽ���ִӸߵ�����ʾ 
SELECT courseid, AVG(score) FROM tblscore GROUP BY courseid ORDER BY AVG(score) DESC; 

--22����ѯ���¿γ̳ɼ��� 3 ������ 6 ����ѧ���ɼ�������ҵ����001�������˼��002����UML ��003�������ݿ⣨004�� ��ʽ��[ѧ��ID],[ѧ������],��ҵ����,���˼,UML,���ݿ�,ƽ���ɼ� 
SELECT r,stuid,avgs FROM (
SELECT stuid,avgs,ROWNUM r FROM (
SELECT stuid,AVG(score) avgs FROM tblscore WHERE courseid IN ('001','002','003','004')  GROUP BY stuid ORDER BY avgs ) WHERE ROWNUM<=6 ) 
WHERE r>=3 ;

--23��ͳ����ӡ���Ƴɼ�,������������:�γ�ID,�γ�����,[100-85],[85-70],[70-60],[ <60] 

--24����ѯѧ��ƽ���ɼ��������� 
 SELECT stuid,AVG(score) FROM tblscore GROUP BY stuid ORDER BY AVG(score);

--25����ѯ���Ƴɼ�ǰ�����ļ�¼:(�����ǳɼ��������)
 SELECT tblcourse.courseid,score FROM tblscore,tblcourse WHERE tblscore.courseid= tblcourse.courseid  ;

--26����ѯÿ�ſγ̱�ѡ�޵�ѧ���� 
  SELECT stuid, COUNT(courseid) FROM tblscore GROUP BY stuid ;
--27����ѯ��ֻѡ����һ�ſγ̵�ȫ��ѧ����ѧ�ź����� 
 SELECT stuid, COUNT(tblcourse.courseid) FROM tblscore,tblcourse WHERE tblscore.courseid= tblcourse.courseid GROUP BY stuid ;
 
--28����ѯ������Ů������ 
 SELECT 
(SELECT COUNT(1)  FROM tblstudent WHERE stusex='��') ��������,
(SELECT COUNT(1) Ů�� FROM tblstudent WHERE stusex= 'Ů') Ů������ FROM dual;
--29����ѯ�ա��š���ѧ������ 
SELECT * FROM tblstudent WHERE stuname LIKE '��%';

--30����ѯͬ��ͬ��ѧ����������ͳ��ͬ������ 
SELECT stuname,COUNT(1) FROM tblstudent GROUP BY stuname HAVING COUNT(1)>1;
--31��1981�������ѧ������(ע��Student����Sage�е�������datetime) 
 SELECT * FROM tblstudent WHERE 
 stuage     BETWEEN  to_date('1981-1-1','yyyy-MM-dd') 
   AND to_date('1981-12-31','yyyy-MM-dd');

--32����ѯÿ�ſγ̵�ƽ���ɼ��������ƽ���ɼ��������У�ƽ���ɼ���ͬʱ�����γ̺Ž������� 
 SELECT courseid ,AVG(score) FROM tblscore  GROUP BY courseid ORDER BY avg(score) DESC ,courseid ASC;

--33����ѯƽ���ɼ�����85������ѧ����ѧ�š�������ƽ���ɼ� 
SELECT t1.stuid,t1.stuname,t2.avgs FROM
(SELECT stuid ,AVG(score)  avgs  FROM tblscore GROUP BY stuid) t2 ,
tblstudent t1 WHERE t2.avgs >85 AND t1.stuid =t2.stuid ;
--34����ѯ�γ�����Ϊ�����ݿ⡱���ҷ�������60��ѧ�������ͷ��� 
SELECT t1.stuname,t1.stuid,t2.score FROM
 (SELECT stuid,score FROM tblscore,tblcourse WHERE coursename ='���ݿ�' AND score>60 AND tblcourse.courseid= tblscore.courseid) t2,
 tblstudent t1 WHERE  t1.stuid= t2.stuid;

--35����ѯ����ѧ����ѡ������� 
   SELECT stu.stuid, stu.stuname,sc.courseid,cs.coursename,sc.score
   FROM tblstudent stu INNER JOIN tblscore sc
    ON stu.stuid = sc.stuid INNER JOIN  tblcourse cs
    ON cs.courseid = sc.courseid ;

--36����ѯ�κ�һ�ſγ̳ɼ���70�����ϵ��������γ����ƺͷ����� 

SELECT  stuname,coursename,score FROM tblscore,tblcourse,tblstudent WHERE score>70 AND tblscore.stuid=tblstudent.stuid AND tblcourse.courseid=tblscore.courseid ;
--37����ѯ������Ŀγ̣������γ̺ŴӴ�С���� 

  SELECT courseid FROM   tblstudent stu INNER JOIN tblscore sc
  ON stu.stuid = sc.stuid 
  WHERE score < 60 
  GROUP BY sc.courseid
  ORDER BY courseid DESC
--38����ѯ�γ̱��Ϊ003�ҿγ̳ɼ���80�����ϵ�ѧ����ѧ�ź������� 
 SELECT t1.stuid,t1.stuname FROM tblscore t2 ,tblstudent t1 WHERE t2.score >60 AND t1.stuid = t2.stuid AND courseid ='003';

--40����ѯѡ�ޡ�Ҷƽ����ʦ���ڿγ̵�ѧ���У��ɼ���ߵ�ѧ����������ɼ� 
SELECT t1.stuname,t2.maxscore FROM tblstudent t1 ,
(SELECT tblscore.stuid,max(tblscore.score) maxscore FROM tblscore,tblteacher,tblcourse 
WHERE tblcourse.teaid = tblteacher.teaid AND tblteacher.teaname='Ҷƽ' AND tblcourse.courseid = tblscore.courseid 
GROUP BY tblscore.stuid) t2 WHERE t1.stuid = t2.stuid ;

--41����ѯ�����γ̼���Ӧ��ѡ������ 
 SELECT cs.courseid,cs.coursename ,COUNT(1) FROM tblscore sc
  INNER JOIN tblcourse cs
 ON sc.courseid = sc.courseid
  GROUP BY cs.courseid,cs.coursename

--42����ѯ��ͬ�γ̳ɼ���ͬ��ѧ����ѧ�š��γ̺š�ѧ���ɼ� 
 SELECT t1.stuid,t1.courseid,t1.score FROM tblscore t1 INNER JOIN  tblscore t2   ON t1.stuid = t2.stuid
   WHERE t1.courseid <> t2.courseid AND t1.score = t2.score
 
--43����ѯÿ�Ź��ɼ���õ�ǰ���� 
 

--44��ͳ��ÿ�ſγ̵�ѧ��ѡ������������10�˵Ŀγ̲�ͳ�ƣ���Ҫ������γ̺ź�ѡ����������ѯ����������������У���������ͬ�����γ̺���������  
 
--45����������ѡ�����ſγ̵�ѧ��ѧ�� 
 

--46����ѯȫ��ѧ����ѡ�޵Ŀγ̵Ŀγ̺źͿγ��� 
 

--47����ѯûѧ����Ҷƽ����ʦ���ڵ���һ�ſγ̵�ѧ������ 
 

--48����ѯ�������ϲ�����γ̵�ͬѧ��ѧ�ż���ƽ���ɼ� 
 
--49��������004���γ̷���С��60���������������е�ͬѧѧ�� (ok)
 

--50��ɾ����002��ͬѧ�ġ�001���γ̵ĳɼ�
