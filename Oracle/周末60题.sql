
SELECT * FROM emp ;
SELECT  * FROM dept;
��1�� ��ѯ20���ŵ�����Ա����Ϣ��
SELECT  * FROM emp WHERE deptno =20;

��2�� ��ѯ���й���ΪCLERK��Ա����Ա���š�Ա�����Ͳ��źš�
SELECT empno,ename,mgr FROM emp WHERE job = 'CLERK';
��3�� ��ѯ����COMM�����ڹ��ʣ�SAL����Ա����Ϣ��
SELECT * FROM EMP WHERE COMM >SAL;

��4�� ��ѯ������ڹ��ʵ�20%��Ա����Ϣ��
;SELECT *FROM emp WHERE comm> (sal*0.2);

��5�� ��ѯ10�Ų����й���ΪMANAGER��20�����й���ΪCLERK��Ա������Ϣ��
SELECT * FROM emp WHERE deptno = 10 AND job ='MANAGER' UNION SELECT * FROM emp WHERE deptno = 20 AND job ='CLERK'

��6�� ��ѯ���й��ֲ���MANAGER��CLERK��
SELECT distinct job FROM emp WHERE job NOT IN ('MANAGER','CLERK');


��7�� ��ѯ�н����Ա���Ĳ�ͬ���֡�

SELECT  Distinct job FROM emp WHERE comm>0;
��8�� ��ѯ����Ա�������뽱��ĺ͡� --nvl ��������
SELECT ename, sal+ NVL (comm,0) FROM emp ;

��9�� ��ѯû�н���򽱽����100��Ա����Ϣ��
SELECT * FROM emp WHERE comm<100;

��10�� ��ѯ���µ�����3��(������2��)��ְ��Ա����Ϣ��




��11�� ��ѯ������ڻ����25���Ա����Ϣ��

SELECT * FROM emp WHERE (SYSDATE-hiredate)/365 >25;

��12�� ��ѯԱ����Ϣ��Ҫ��������ĸ��д�ķ�ʽ��ʾ����Ա����������

 SELECT REPLACE(ename,substr(ename,0,1), LOWER(substr(ename,0,1) )) FROM emp;

��13�� ��ѯԱ��������Ϊ6���ַ���Ա������Ϣ��
     SELECT  *FROM emp WHERE LENGTH(ename)=6;    

��14�� ��ѯԱ�������в�������ĸ���ӡ���Ա����
     SELECT * FROM emp WHERE ename NOT LIKE '%S%';

��15�� ��ѯԱ�������ĵڶ���ĸΪ��M����Ա����Ϣ��
SELECT * FROM emp WHERE ename LIKE '_M%';

--��16�� ��ѯ����Ա��������ǰ�����ַ���
SELECT SUBSTR(ename,1,3)FROM emp ;
--��17�� ��ѯ����Ա�������������������ĸ��S�������á�s���滻��

   SELECT REPLACE (ename,'S','s') FROM emp;

��18����ѯԱ������������ְ���ڣ�������ְ���ڴ��ȵ����������
SELECT  ename,hiredate FROM emp ORDER BY hiredate ASC; 

��19�� ��ʾ����Ա�������������֡����ʺͽ��𣬰����ֽ�������
SELECT ename,job,sal,comm FROM emp ORDER BY job DESC;

��20�� ��ʾ����Ա������������ְ����ݺ��·ݣ�
--����ְ�������ڵ��·��������·���ͬ����ְ���������
SELECT ename,extract(YEAR FROM hiredate) AS ��ְ���,EXTRACT(MONTH FROM hiredate) AS ��ְ�·�  FROM emp ORDER BY ��ְ�·� DESC,��ְ��� asc ;

��21�� ��ѯ��2�·���ְ������Ա����Ϣ��
SELECT *FROM emp WHERE EXTRACT(MONTH FROM hiredate) =2;

��22�� ��ѯ����Ա����ְ�����Ĺ������ޣ��á�XX��XX��XX�ա�����ʽ��ʾ�� --biaoji
SELECT ename, 
CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(EXTRACT(YEAR FROM hiredate),
'��'),
EXTRACT(MONTH FROM hiredate)),'��'),
EXTRACT(DAY FROM hiredate)),
'��') FROM emp;
��23.1�� ��ѯ������һ��Ա���Ĳ�����Ϣ��

SELECT *FROM dept  t1,(
SELECT deptno AS de, count(deptno) FROM emp GROUP BY deptno) t2 WHERE t1.deptno =t2.de;
��23.2�� ��ѯ����������Ա���Ĳ�����Ϣ��
SELECT *FROM dept  t1,(
SELECT deptno AS de, count(deptno) FROM emp GROUP BY deptno) t2 WHERE t1.deptno =t2.de;

��24�� ��ѯ���ʱ�
SMITHԱ������
�ߵ�����Ա����Ϣ��
SELECT *FROM emp WHERE sal>(
SELECT sal FROM emp WHERE  ename ='SMITH') ;


��25�� ��ѯ����Ա������������ֱ���ϼ���������
SELECT *FROM emp;
SELECT ename Ա��������,(SELECT ename FROM emp e2 WHERE e1.mgr= e2.empno) ֱ���ϼ� FROM emp e1;

��26�� ��ѯ��ְ����������ֱ���ϼ��쵼������Ա����Ϣ�� --biaoji

select ename Ա��������,hiredate ��ְ����,(
select ename from emp e2 where e2.empno=e1.mgr
)  AS ֱ���ϼ�,(
select hiredate from emp e2 where e2.empno=e1.mgr
)  ֱ���ϼ���ְ����
from emp e1
where e1.hiredate<(select hiredate 
from emp e2 where e2.empno=e1.mgr
)


��27�� ��ѯ���в��ż���Ա����Ϣ��������Щû��Ա���Ĳ��š� 
SELECT *  FROM emp e1 RIGHT JOIN dept d2 ON e1.deptno = d2.deptno;
��28�� ��ѯ����Ա�����䲿����Ϣ��������Щ���������κβ��ŵ�Ա����
select dept.dname,emp.ename 
from emp
left outer join dept on emp.deptno=dept.deptno;

��29�� ��ѯ���й���ΪCLERK��Ա�����������䲿�����ơ�
SELECT emp.ename,emp.deptno,dept.dname FROM emp JOIN dept ON emp.job ='CLERK' AND emp.deptno = dept.deptno ;

��30�� ��ѯ��͹��ʴ���2500�ĸ��ֹ�����
SELECT job FROM emp WHERE sal>2500;
-----------------------------------------------------------------
��31�� ��ѯƽ�����ʵ���2000�Ĳ��ż���Ա����Ϣ��
SELECT * FROM emp WHERE deptno  = (
SELECT deptno FROM emp GROUP BY deptno HAVING AVG(sal)>2500) ;

��32�� ��ѯ��SALES���Ź�����Ա����������Ϣ��
SELECT ename FROM emp WHERE  deptno = (
SELECT deptno FROM dept WHERE dname = 'SALES') ;

��33�� ��ѯ���ʸ��ڹ�˾ƽ�����ʵ�����Ա����Ϣ��
SELECT * FROM emp WHERE sal > (SELECT AVG(sal) FROM  emp );

��34�� ��ѯ����SMITHԱ��������ͬ����������Ա����Ϣ��
SELECT * FROM emp WHERE job = (SELECT job FROM emp WHERE ename = 'SMITH');

��35�� �г����ʵ���30������ĳ��Ա���Ĺ��ʵ�����Ա���������͹��ʡ�
SELECT ename,sal FROM emp WHERE sal IN (SELECT sal FROM emp WHERE deptno = 30 )  ;

��36�� ��ѯ���ʸ���30���Ź���������Ա���Ĺ��ʵ�Ա�������͹��ʡ�
SELECT ename,sal FROM emp WHERE sal > (SELECT max(sal) FROM emp WHERE deptno = 30 )  ;

��37�� ��ѯÿ�������е�Ա��������ƽ�����ʺ�ƽ���������ޡ�------biaoji

SELECT 

��38�� ��ѯ����ͬһ�ֹ�����������ͬһ���ŵ�Ա��
��Ϣ��

SELECT e2.ename ,e1.job ,e1.deptno FROM emp e1,emp e2 WHERE e1.job = e2.job AND e1.deptno!=e2.deptno;


��39�� ��ѯ�������ŵ���ϸ��Ϣ�Լ���������������ƽ�����ʡ�
SELECT round(AVG(sal)) ����ƽ������ ,COUNT(deptno) ����,deptno ���� FROM emp GROUP BY deptno ;

��40�� ��ѯ���ֹ�������͹��ʡ�
SELECT MIN(sal) ,job FROM emp GROUP BY job;


��41�� ��ѯ���������в�ͬ���ֵ���߹��ʡ�---biaoji
select dname ��������,job ����,max(NVL(sal,0)) ��߹���
from dept d left join emp e on d.deptno=e.deptno
GROUP BY job,dname;

��42�� ��ѯ10�Ų���Ա�������쵼����Ϣ��
SELECT ename Ա��������,(SELECT ename FROM emp e2 WHERE e1.mgr= e2.empno) �ϼ� FROM emp e1 WHERE deptno  =10 ;

��43�� ��ѯ�������ŵ�������ƽ�����ʡ�
SELECT deptno,AVG(sal),COUNT(deptno) FROM emp GROUP BY deptno;

��44�� ��ѯ����Ϊĳ������ƽ�����ʵ�Ա������Ϣ
SELECT * FROM emp WHERE sal IN (SELECT AVG(sal) FROM emp GROUP BY deptno); 

��45�� ��ѯ���ʸ��ڱ�����ƽ�����ʵ�Ա������Ϣ��

SELECT * FROM emp e1 JOIN (SELECT deptno deps,AVG(sal) avgs FROM emp GROUP BY deptno) e2 ON e1.deptno  = e2.deps AND e1.sal >e2.avgs;
��46�� ��ѯ���ʸ��ڱ�����ƽ�����ʵ�Ա������Ϣ���䲿�ŵ�ƽ�����ʡ�
SELECT e1.*,(select avg(sal) from emp e2 where e2.deptno=e1.deptno) ����ƽ������
from emp e1
where sal>(
select avg(sal) from emp e2 where e2.deptno=e1.deptno);

��47�� ��ѯ���ʸ���20�Ų���ĳ��Ա�����ʵ�Ա������Ϣ��--�ʼ� any ����
SELECT * FROM emp WHERE sal > ANY(
SELECT sal FROM emp WHERE deptno = 20);


��48��ͳ�Ƹ������ֵ�Ա��������ƽ�����ʡ�
SELECT COUNT(job),AVG(sal) FROM emp GROUP BY job;

��49�� ͳ��ÿ�������и����ֵ�������ƽ�����ʡ�
SELECT  deptno ����,avg(sal) AS ����ƽ������, COUNT(job) AS �����ֵ�����,job AS ���� FROM emp GROUP BY job,deptno  ORDER BY deptno DESC;

��50�� ��ѯ���������й��ʡ�������30�Ų���ĳԱ�����ʡ�--��Ŀ�������ɣ�����

��51�� ��ѯ������������5�Ĳ��ŵ�Ա����Ϣ��
SELECT * FROM emp WHERE deptno=(
SELECT deptno FROM emp GROUP BY deptno HAVING COUNT(deptno)>5);


��52�� ��ѯ����Ա�����ʶ�����1000�Ĳ��ŵ���Ϣ��
SELECT * FROM dept WHERE deptno  IN (
SELECT deptno FROM emp GROUP BY deptno HAVING MIN(sal)>1000);

��53�� ��ѯ����Ա�����ʶ�����1000�Ĳ��ŵ���Ϣ����Ա����Ϣ��----��չ
select *
from dept d left outer join emp e on d.deptno=e.deptno
where e.deptno in (
select deptno from emp e1
group by deptno
having min(sal)>1000
)

��54�� ��ѯ����Ա�����ʶ���900��3000֮��Ĳ��ŵ���Ϣ��
SELECT * FROM dept WHERE deptno IN( 
SELECT deptno FROM emp GROUP BY deptno,sal HAVING  sal BETWEEN 900 AND 3000);

��55�� ��ѯ�й�����900��3000֮���Ա�����ڲ��ŵ�Ա����Ϣ��
SELECT * FROM emp WHERE deptno IN(
SELECT deptno FROM emp GROUP BY deptno,sal HAVING sal BETWEEN 900 AND 3000);

��56�� ��ѯÿ��Ա�����쵼���ڲ��ŵ���Ϣ��
SELECT * FROM dept JOIN (
SELECT e1.ename Ա�� , e2.ename Ա���쵼,e2.deptno AS �쵼���ڲ��� FROM emp e2,emp e1 WHERE e1.mgr = e2.empno) e2 ON dept.deptno = e2.�쵼���ڲ���;

��57�� ��ѯ�������Ĳ�����Ϣ��
SELECT MAX(COUNT(deptno)) FROM emp GROUP BY deptno;

��58�� ��ѯ30�Ų����й�������ǰ3����Ա����Ϣ��  ---
select TOPIC(3) * FROM emp WHERE deptno=30 ORDER by -sal 

��59�� ��ѯ����Ա���й���������5��10��֮���Ա����Ϣ��


��60�� ��ѯָ�����֮����ְ��Ա����Ϣ��(1980-1985)
select *FROM emp  WHERE extract(YEAR FROM hiredate) between 1980 and 1985;
