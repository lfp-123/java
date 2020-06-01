/*
分组: group by 写在where的后面,对列进行分组
聚合/分组函数: 分组函数查询的时候,
如果带有普通列,那么这个普通列就必须出现在
group by中..
*/
SELECT  
job 岗位, MAX(sal) 最高工资,
min(sal) 最低工资,AVG(sal) 平均工资,
SUM(sal) 总和工资,COUNT(1) 岗位人数 FROM emp
GROUP BY job;

SELECT job,deptno FROM emp
GROUP BY job, deptno;
