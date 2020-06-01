/*
having二次查询
where和having的区别.
where是在分组之前先筛选,不能筛选分组函数
having是二次筛选,在group by之后筛选条件,
可以筛选普通列和分组函数了
*/
SELECT job,MAX(sal),AVG(sal),COUNT(1)
FROM emp WHERE deptno = 20 
GROUP BY job HAVING job='CLERK' 
