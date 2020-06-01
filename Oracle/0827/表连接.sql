/*
关系: 1对1, 1对多, 多对1, 多对多
外键: 逻辑外键和物理外键
逻辑外键即数据库不创建外键,人为的知道而已,没有约束
物理外键即数据库创建,会有约束.
表连接
1. 内连接 两个表的关联字段匹配的数据才显示,不匹配就都不显示
select a.*,b.* from a,b where a.key=b.key 
select a.*,b.* from a inner join b on a.key=b.key
2. 外连接
     左外: 第一张表为主表,被连接的表为副表,主表
     数据一定显示,副表数据被关键字匹配的显示,不匹配
     显示为null
     SELECT e.ename,d.dname FROM 
      emp e, dept d 
      WHERE e.deptno = d.deptno(+)
     
    SELECT e.ename,d.dname FROM 
    emp e LEFT [outer] JOIN dept d 
    ON e.deptno = d.deptno
     右外
     第一张表为副表,被连接的表为副表,主表
     数据一定显示,副表数据被关键字匹配的显示,不匹配
     显示为null
     
     SELECT e.ename,d.dname FROM 
      emp e, dept d 
      WHERE e.deptno(+) = d.deptno
     
     SELECT e.ename,d.dname FROM 
    emp e RIGHT [outer] JOIN dept d 
    ON e.deptno = d.deptno
     全外 :两张表的数据都会显示,匹配的在一行显示
     不匹配的在同行显示为null
     SELECT e.ename,d.dname FROM 
    emp e FULL OUTER JOIN dept d 
    ON e.deptno = d.deptno
3. 交叉连接  无关联条件,会产生笛卡尔积
select a.*, b.* from a ,b
select a.*, b.* from a cross join b
*/


