/*
子查询:查询嵌套查询,把查询的结果当做新的值
给的另一个查询使用
*/
SELECT ename FROM emp WHERE sal = 
(SELECT MAX(sal) FROM emp );

SELECT * FROM emp WHERE sal > 
(SELECT AVG(sal) FROM emp)
