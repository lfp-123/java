SELECT * FROM emp;
/*exists �ʺ������С���ڱ������*/
/*in �ʺ������ڱ�С�����*/

--��ѯ��ʷ��˹��ͬ��λ�Ͳ��ŵ���
SELECT m.ename,m.job,m.deptno from emp m WHERE EXISTS 
(
 SELECT 'X' FROM emp c WHERE c.ename = 'SMITH' AND
 m.job = c.job AND m.deptno = c.deptno
 );
      
