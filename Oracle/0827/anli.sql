SELECT * FROM emp;
/*查询和Smith相同部门的人*/
SELECT emp.ename ,emp.deptno FROM emp WHERE job IN (SELECT job FROM emp WHERE deptno=10);

/*查询必部门20工资要高的人*/
SELECT * FROM emp WHERE sal>
(SELECT MAX(sal) FROM emp WHERE deptno =30);
/*和Smith部门岗位都形同的人*/
SELECT ename,deptno,job FROM emp 
WHERE (deptno,job )= (SELECT deptno,job FROM emp WHERE ename= 'SMITH');
/*查询高于自己部门平均工资的员工信息*/
SELECT e.ename,e.deptno,e.sal,e.deptno,b.avgsal FROM emp e LEFT JOIN
(SELECT deptno,AVG(sal) avgsal FROM emp  GROUP BY deptno) b
ON e.deptno = b.deptno
WHERE e.sal> b.avgsal;
/* */
SELECT *FROM 
(SELECT empno ,ename,deptno,ROWNUM r FROM emp 
WHERE ROWNUM <= 9
)
WHERE r >=7;
