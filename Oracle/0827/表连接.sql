/*
��ϵ: 1��1, 1�Զ�, ���1, ��Զ�
���: �߼�������������
�߼���������ݿⲻ�������,��Ϊ��֪������,û��Լ��
������������ݿⴴ��,����Լ��.
������
1. ������ ������Ĺ����ֶ�ƥ������ݲ���ʾ,��ƥ��Ͷ�����ʾ
select a.*,b.* from a,b where a.key=b.key 
select a.*,b.* from a inner join b on a.key=b.key
2. ������
     ����: ��һ�ű�Ϊ����,�����ӵı�Ϊ����,����
     ����һ����ʾ,�������ݱ��ؼ���ƥ�����ʾ,��ƥ��
     ��ʾΪnull
     SELECT e.ename,d.dname FROM 
      emp e, dept d 
      WHERE e.deptno = d.deptno(+)
     
    SELECT e.ename,d.dname FROM 
    emp e LEFT [outer] JOIN dept d 
    ON e.deptno = d.deptno
     ����
     ��һ�ű�Ϊ����,�����ӵı�Ϊ����,����
     ����һ����ʾ,�������ݱ��ؼ���ƥ�����ʾ,��ƥ��
     ��ʾΪnull
     
     SELECT e.ename,d.dname FROM 
      emp e, dept d 
      WHERE e.deptno(+) = d.deptno
     
     SELECT e.ename,d.dname FROM 
    emp e RIGHT [outer] JOIN dept d 
    ON e.deptno = d.deptno
     ȫ�� :���ű�����ݶ�����ʾ,ƥ�����һ����ʾ
     ��ƥ�����ͬ����ʾΪnull
     SELECT e.ename,d.dname FROM 
    emp e FULL OUTER JOIN dept d 
    ON e.deptno = d.deptno
3. ��������  �޹�������,������ѿ�����
select a.*, b.* from a ,b
select a.*, b.* from a cross join b
*/


