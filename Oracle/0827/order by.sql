SELECT * FROM emp;
SELECT * FROM EMP WHERE (job='MANAGER' OR 
sal > 2500) --AND ename LIKE '%O%' 
ORDER BY sal ASC,hiredate DESC;
/*����:order by ��1,��2. ����������1
����1����õ���������������2.asc����,desc����
*/
SELECT ename, 12 * (sal + nvl(comm,0)) ��н
FROM emp ORDER BY ��н desc;








SELECT * FROM EMP WHERE sal > 2500
