--函数

--转小写和大写
SELECT LOWER('Hello'),UPPER('Hello') FROM dual;
--字符长度个数
SELECT LENGTH('你们好吗') FROM dual;
--截取字符串,从1开始数
SELECT SUBSTR('helloworld', 6, 5) FROM dual;
--获取字符串位置,从第一个位置开始找o出现的位置
SELECT INSTR('helloworld', 'o', 1) FROM dual;
--替换文本
SELECT REPLACE('helloworld','o', '我是老虎') FROM dual;
--保留2位小数四舍五入
SELECT ROUND(22.292111, 2) FROM dual;
--trunc截取数字或日期,不改变数据类型
SELECT TRUNC(SYSDATE),trunc(123.12994, 2) FROM dual;
--取余数
SELECT MOD(8, 3) FROM dual;
--向上取整
SELECT CEIL(1.00001) FROM dual;
--向下取整
SELECT FLOOR(1.999999) FROM dual;
--绝对值
SELECT ABS(100-200) FROM dual;
--取幂
SELECT POWER(2,3) FROM dual;
--当前日期
SELECT SYSDATE,SYSTIMESTAMP FROM dual;
--添加月份
SELECT add_months(SYSDATE,1) FROM dual;
--获取月的最后一天的日期
SELECT last_day(SYSDATE) FROM dual;
