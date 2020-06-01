SELECT * FROM emp;
SELECT * FROM EMP WHERE (job='MANAGER' OR 
sal > 2500) --AND ename LIKE '%O%' 
ORDER BY sal ASC,hiredate DESC;
/*排序:order by 列1,列2. 优先排序列1
在列1排序好的数据下在排序列2.asc升序,desc降序
*/
SELECT ename, 12 * (sal + nvl(comm,0)) 年薪
FROM emp ORDER BY 年薪 desc;








SELECT * FROM EMP WHERE sal > 2500
