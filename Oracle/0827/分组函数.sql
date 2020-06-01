

/*子查询，查询嵌套查询，把查询的结果当作新的值给另一个查询使用*/

/*having 二次查询
where 和having 的区别
where是在分组之前先筛选，不能筛选分组函数
having是二次筛选，在group by之后筛选条件，可以筛选普通列和分组函数
*/
SELECT job,MAX(sal),AVG(sal),COUNT(1) 
FROM emp WHERE deptno =20
GROUP BY job HAVING job='CLERK'

SELECT job,MAX(sal),AVG(sal),COUNT(1) 
FROM emp WHERE (deptno =20 AND job='CLERK')
GROUP BY job 

SELECT job,MAX(sal),AVG(sal),COUNT(1) 
FROM emp WHERE deptno =20
GROUP BY job 

SELECT *FROM dept;
SELECT *FROM emp;
SELECT *FROM salgrade;
SELECT a.ename,b.dname FROM emp a,dept b WHERE a.deptno = b.deptno;

/**/
SELECT e.ename,e.sal,g.grade
FROM emp e LEFT JOIN salgrade g ON e.sal BETWEEN g.losal AND g.hisal;
/*自己和自己连接*/

SELECT e.empno,e.ename FROM emp e
START WITH e.ename = 'FORD'
CONNECT BY PRIOR e.empno = e.mgr;

