SELECT * FROM emp;
/*��ѯ��н���¹���*/
SELECT sal*13+NVL(comm,0)*13 AS "��н",ename,comm FROM emp;
/*ʹ���еı���*/
SELECT ename AS "����",sal*12 AS "������" FROM emp;
/*��δ���nullֵ��ʹ��nvl����*/
/*ʹ��nvl nvl2����*/
/*��ʾ���ʸ���3000��Ա��*/
SELECT ename AS "����",sal FROM emp WHERE sal>3000;
/*����1982.1.1����ְ��Ա��*/
SELECT ename AS "����",hiredate FROM emp WHERE hiredate>'1-1��-1982';
/*�����ʾ������2000��3000*/ 
SELECT ename AS "����",sal FROM emp WHERE 2000<sal AND sal<3000;
/*���ʹ��like������*/
/*��ʾ���ַ�ΪS��Ա�������͹���*/
SELECT ename,sal FROM emp WHERE ename LIKE 'S%';
/*��ʾ�������ַ�Ϊ��дO��Ա�����ʺ͹���*/
SELECT ename,sal FROM emp WHERE ename LIKE '__C%';
/*ʹ�� in ��ʾ�����Ա�����*/
SELECT *FROM emp WHERE empno IN(7844,7399,123,456);
/*is null ��ʾû���ϼ���Ա��Ա��*/
SELECT *FROM emp WHERE mgr IS NULL;
