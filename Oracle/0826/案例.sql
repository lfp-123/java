SELECT * FROM emp;
/*查询年薪和月工资*/
SELECT sal*13+NVL(comm,0)*13 AS "年薪",ename,comm FROM emp;
/*使用列的别名*/
SELECT ename AS "姓名",sal*12 AS "年收入" FROM emp;
/*如何处理null值，使用nvl处理*/
/*使用nvl nvl2函数*/
/*显示工资高于3000的员工*/
SELECT ename AS "姓名",sal FROM emp WHERE sal>3000;
/*查找1982.1.1后入职的员工*/
SELECT ename AS "姓名",hiredate FROM emp WHERE hiredate>'1-1月-1982';
/*如何显示工资在2000到3000*/ 
SELECT ename AS "姓名",sal FROM emp WHERE 2000<sal AND sal<3000;
/*如何使用like操作符*/
/*显示首字符为S的员工姓名和工资*/
SELECT ename,sal FROM emp WHERE ename LIKE 'S%';
/*显示第三个字符为大写O的员工工资和工资*/
SELECT ename,sal FROM emp WHERE ename LIKE '__C%';
/*使用 in 显示多个雇员的情况*/
SELECT *FROM emp WHERE empno IN(7844,7399,123,456);
/*is null 显示没有上级雇员的员工*/
SELECT *FROM emp WHERE mgr IS NULL;
