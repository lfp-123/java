/*
����: group by д��where�ĺ���,���н��з���
�ۺ�/���麯��: ���麯����ѯ��ʱ��,
���������ͨ��,��ô�����ͨ�оͱ��������
group by��..
*/
SELECT  
job ��λ, MAX(sal) ��߹���,
min(sal) ��͹���,AVG(sal) ƽ������,
SUM(sal) �ܺ͹���,COUNT(1) ��λ���� FROM emp
GROUP BY job;

SELECT job,deptno FROM emp
GROUP BY job, deptno;
