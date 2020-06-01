SELECT * FROM emp;
/*exists 适合于外表小，内表大的情况*/
/*in 适合外表大，内表小的情况*/

--查询和史密斯相同岗位和部门的人
SELECT m.ename,m.job,m.deptno from emp m WHERE EXISTS 
(
 SELECT 'X' FROM emp c WHERE c.ename = 'SMITH' AND
 m.job = c.job AND m.deptno = c.deptno
 );
      
