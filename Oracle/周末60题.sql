
SELECT * FROM emp ;
SELECT  * FROM dept;
（1） 查询20部门的所有员工信息。
SELECT  * FROM emp WHERE deptno =20;

（2） 查询所有工种为CLERK的员工的员工号、员工名和部门号。
SELECT empno,ename,mgr FROM emp WHERE job = 'CLERK';
（3） 查询奖金（COMM）高于工资（SAL）的员工信息。
SELECT * FROM EMP WHERE COMM >SAL;

（4） 查询奖金高于工资的20%的员工信息。
;SELECT *FROM emp WHERE comm> (sal*0.2);

（5） 查询10号部门中工种为MANAGER和20部门中工种为CLERK的员工的信息。
SELECT * FROM emp WHERE deptno = 10 AND job ='MANAGER' UNION SELECT * FROM emp WHERE deptno = 20 AND job ='CLERK'

（6） 查询所有工种不是MANAGER和CLERK，
SELECT distinct job FROM emp WHERE job NOT IN ('MANAGER','CLERK');


（7） 查询有奖金的员工的不同工种。

SELECT  Distinct job FROM emp WHERE comm>0;
（8） 查询所有员工工资与奖金的和。 --nvl 函数（）
SELECT ename, sal+ NVL (comm,0) FROM emp ;

（9） 查询没有奖金或奖金低于100的员工信息。
SELECT * FROM emp WHERE comm<100;

（10） 查询各月倒数第3天(倒数第2天)入职的员工信息。




（11） 查询工龄大于或等于25年的员工信息。

SELECT * FROM emp WHERE (SYSDATE-hiredate)/365 >25;

（12） 查询员工信息，要求以首字母大写的方式显示所有员工的姓名。

 SELECT REPLACE(ename,substr(ename,0,1), LOWER(substr(ename,0,1) )) FROM emp;

（13） 查询员工名正好为6个字符的员工的信息。
     SELECT  *FROM emp WHERE LENGTH(ename)=6;    

（14） 查询员工名字中不包含字母“Ｓ”的员工。
     SELECT * FROM emp WHERE ename NOT LIKE '%S%';

（15） 查询员工姓名的第二字母为“M”的员工信息。
SELECT * FROM emp WHERE ename LIKE '_M%';

--（16） 查询所有员工姓名的前三个字符。
SELECT SUBSTR(ename,1,3)FROM emp ;
--（17） 查询所有员工的姓名，如果包含字母“S”，则用“s”替换。

   SELECT REPLACE (ename,'S','s') FROM emp;

（18）查询员工的姓名和入职日期，并按入职日期从先到后进行排序。
SELECT  ename,hiredate FROM emp ORDER BY hiredate ASC; 

（19） 显示所有员工的姓名、工种、工资和奖金，按工种降序排序，
SELECT ename,job,sal,comm FROM emp ORDER BY job DESC;

（20） 显示所有员工的姓名、入职的年份和月份，
--按入职日期所在的月份排序，若月份相同则按入职的年份排序。
SELECT ename,extract(YEAR FROM hiredate) AS 入职年份,EXTRACT(MONTH FROM hiredate) AS 入职月份  FROM emp ORDER BY 入职月份 DESC,入职年份 asc ;

（21） 查询在2月份入职的所有员工信息。
SELECT *FROM emp WHERE EXTRACT(MONTH FROM hiredate) =2;

（22） 查询所有员工入职以来的工作期限，用“XX年XX月XX日”的形式表示。 --biaoji
SELECT ename, 
CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(EXTRACT(YEAR FROM hiredate),
'年'),
EXTRACT(MONTH FROM hiredate)),'月'),
EXTRACT(DAY FROM hiredate)),
'日') FROM emp;
（23.1） 查询至少有一个员工的部门信息。

SELECT *FROM dept  t1,(
SELECT deptno AS de, count(deptno) FROM emp GROUP BY deptno) t2 WHERE t1.deptno =t2.de;
（23.2） 查询至少有两个员工的部门信息。
SELECT *FROM dept  t1,(
SELECT deptno AS de, count(deptno) FROM emp GROUP BY deptno) t2 WHERE t1.deptno =t2.de;

（24） 查询工资比
SMITH员工工资
高的所有员工信息。
SELECT *FROM emp WHERE sal>(
SELECT sal FROM emp WHERE  ename ='SMITH') ;


（25） 查询所有员工的姓名及其直接上级的姓名。
SELECT *FROM emp;
SELECT ename 员工的姓名,(SELECT ename FROM emp e2 WHERE e1.mgr= e2.empno) 直接上级 FROM emp e1;

（26） 查询入职日期早于其直接上级领导的所有员工信息。 --biaoji

select ename 员工的姓名,hiredate 入职日期,(
select ename from emp e2 where e2.empno=e1.mgr
)  AS 直接上级,(
select hiredate from emp e2 where e2.empno=e1.mgr
)  直接上级入职日期
from emp e1
where e1.hiredate<(select hiredate 
from emp e2 where e2.empno=e1.mgr
)


（27） 查询所有部门及其员工信息，包括那些没有员工的部门。 
SELECT *  FROM emp e1 RIGHT JOIN dept d2 ON e1.deptno = d2.deptno;
（28） 查询所有员工及其部门信息，包括那些还不属于任何部门的员工。
select dept.dname,emp.ename 
from emp
left outer join dept on emp.deptno=dept.deptno;

（29） 查询所有工种为CLERK的员工的姓名及其部门名称。
SELECT emp.ename,emp.deptno,dept.dname FROM emp JOIN dept ON emp.job ='CLERK' AND emp.deptno = dept.deptno ;

（30） 查询最低工资大于2500的各种工作。
SELECT job FROM emp WHERE sal>2500;
-----------------------------------------------------------------
（31） 查询平均工资低于2000的部门及其员工信息。
SELECT * FROM emp WHERE deptno  = (
SELECT deptno FROM emp GROUP BY deptno HAVING AVG(sal)>2500) ;

（32） 查询在SALES部门工作的员工的姓名信息。
SELECT ename FROM emp WHERE  deptno = (
SELECT deptno FROM dept WHERE dname = 'SALES') ;

（33） 查询工资高于公司平均工资的所有员工信息。
SELECT * FROM emp WHERE sal > (SELECT AVG(sal) FROM  emp );

（34） 查询出与SMITH员工从事相同工作的所有员工信息。
SELECT * FROM emp WHERE job = (SELECT job FROM emp WHERE ename = 'SMITH');

（35） 列出工资等于30部门中某个员工的工资的所有员工的姓名和工资。
SELECT ename,sal FROM emp WHERE sal IN (SELECT sal FROM emp WHERE deptno = 30 )  ;

（36） 查询工资高于30部门工作的所有员工的工资的员工姓名和工资。
SELECT ename,sal FROM emp WHERE sal > (SELECT max(sal) FROM emp WHERE deptno = 30 )  ;

（37） 查询每个部门中的员工数量、平均工资和平均工作年限。------biaoji

SELECT 

（38） 查询从事同一种工作但不属于同一部门的员工
信息。

SELECT e2.ename ,e1.job ,e1.deptno FROM emp e1,emp e2 WHERE e1.job = e2.job AND e1.deptno!=e2.deptno;


（39） 查询各个部门的详细信息以及部门人数、部门平均工资。
SELECT round(AVG(sal)) 部门平均工资 ,COUNT(deptno) 人数,deptno 部门 FROM emp GROUP BY deptno ;

（40） 查询各种工作的最低工资。
SELECT MIN(sal) ,job FROM emp GROUP BY job;


（41） 查询各个部门中不同工种的最高工资。---biaoji
select dname 部门名称,job 工种,max(NVL(sal,0)) 最高工资
from dept d left join emp e on d.deptno=e.deptno
GROUP BY job,dname;

（42） 查询10号部门员工及其领导的信息。
SELECT ename 员工的姓名,(SELECT ename FROM emp e2 WHERE e1.mgr= e2.empno) 上级 FROM emp e1 WHERE deptno  =10 ;

（43） 查询各个部门的人数及平均工资。
SELECT deptno,AVG(sal),COUNT(deptno) FROM emp GROUP BY deptno;

（44） 查询工资为某个部门平均工资的员工的信息
SELECT * FROM emp WHERE sal IN (SELECT AVG(sal) FROM emp GROUP BY deptno); 

（45） 查询工资高于本部门平均工资的员工的信息。

SELECT * FROM emp e1 JOIN (SELECT deptno deps,AVG(sal) avgs FROM emp GROUP BY deptno) e2 ON e1.deptno  = e2.deps AND e1.sal >e2.avgs;
（46） 查询工资高于本部门平均工资的员工的信息及其部门的平均工资。
SELECT e1.*,(select avg(sal) from emp e2 where e2.deptno=e1.deptno) 部门平均工资
from emp e1
where sal>(
select avg(sal) from emp e2 where e2.deptno=e1.deptno);

（47） 查询工资高于20号部门某个员工工资的员工的信息。--笔记 any 任意
SELECT * FROM emp WHERE sal > ANY(
SELECT sal FROM emp WHERE deptno = 20);


（48）统计各个工种的员工人数与平均工资。
SELECT COUNT(job),AVG(sal) FROM emp GROUP BY job;

（49） 统计每个部门中各工种的人数与平均工资。
SELECT  deptno 部门,avg(sal) AS 工种平均工资, COUNT(job) AS 各工种的人数,job AS 工种 FROM emp GROUP BY job,deptno  ORDER BY deptno DESC;

（50） 查询其他部门中工资、奖金与30号部门某员工工资、--题目不完整吧？？？

（51） 查询部门人数大于5的部门的员工信息。
SELECT * FROM emp WHERE deptno=(
SELECT deptno FROM emp GROUP BY deptno HAVING COUNT(deptno)>5);


（52） 查询所有员工工资都大于1000的部门的信息。
SELECT * FROM dept WHERE deptno  IN (
SELECT deptno FROM emp GROUP BY deptno HAVING MIN(sal)>1000);

（53） 查询所有员工工资都大于1000的部门的信息及其员工信息。----拓展
select *
from dept d left outer join emp e on d.deptno=e.deptno
where e.deptno in (
select deptno from emp e1
group by deptno
having min(sal)>1000
)

（54） 查询所有员工工资都在900～3000之间的部门的信息。
SELECT * FROM dept WHERE deptno IN( 
SELECT deptno FROM emp GROUP BY deptno,sal HAVING  sal BETWEEN 900 AND 3000);

（55） 查询有工资在900～3000之间的员工所在部门的员工信息。
SELECT * FROM emp WHERE deptno IN(
SELECT deptno FROM emp GROUP BY deptno,sal HAVING sal BETWEEN 900 AND 3000);

（56） 查询每个员工的领导所在部门的信息。
SELECT * FROM dept JOIN (
SELECT e1.ename 员工 , e2.ename 员工领导,e2.deptno AS 领导所在部门 FROM emp e2,emp e1 WHERE e1.mgr = e2.empno) e2 ON dept.deptno = e2.领导所在部门;

（57） 查询人数最多的部门信息。
SELECT MAX(COUNT(deptno)) FROM emp GROUP BY deptno;

（58） 查询30号部门中工资排序前3名的员工信息。  ---
select TOPIC(3) * FROM emp WHERE deptno=30 ORDER by -sal 

（59） 查询所有员工中工资排序在5到10名之间的员工信息。


（60） 查询指定年份之间入职的员工信息。(1980-1985)
select *FROM emp  WHERE extract(YEAR FROM hiredate) between 1980 and 1985;
