/*
�Ӳ�ѯ:��ѯǶ�ײ�ѯ,�Ѳ�ѯ�Ľ�������µ�ֵ
������һ����ѯʹ��
*/
SELECT ename FROM emp WHERE sal = 
(SELECT MAX(sal) FROM emp );

SELECT * FROM emp WHERE sal > 
(SELECT AVG(sal) FROM emp)
