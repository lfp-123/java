SELECT * FROM emp;
/*��ѯ��Smith��ͬ���ŵ���*/
SELECT emp.ename ,emp.deptno FROM emp WHERE job IN (SELECT job FROM emp WHERE deptno=10);

/*��ѯ�ز���20����Ҫ�ߵ���*/
SELECT * FROM emp WHERE sal>
(SELECT MAX(sal) FROM emp WHERE deptno =30);
/*��Smith���Ÿ�λ����ͬ����*/
SELECT ename,deptno,job FROM emp 
WHERE (deptno,job )= (SELECT deptno,job FROM emp WHERE ename= 'SMITH');
/*��ѯ�����Լ�����ƽ�����ʵ�Ա����Ϣ*/
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
