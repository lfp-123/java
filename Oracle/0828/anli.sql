--����

--תСд�ʹ�д
SELECT LOWER('Hello'),UPPER('Hello') FROM dual;
--�ַ����ȸ���
SELECT LENGTH('���Ǻ���') FROM dual;
--��ȡ�ַ���,��1��ʼ��
SELECT SUBSTR('helloworld', 6, 5) FROM dual;
--��ȡ�ַ���λ��,�ӵ�һ��λ�ÿ�ʼ��o���ֵ�λ��
SELECT INSTR('helloworld', 'o', 1) FROM dual;
--�滻�ı�
SELECT REPLACE('helloworld','o', '�����ϻ�') FROM dual;
--����2λС����������
SELECT ROUND(22.292111, 2) FROM dual;
--trunc��ȡ���ֻ�����,���ı���������
SELECT TRUNC(SYSDATE),trunc(123.12994, 2) FROM dual;
--ȡ����
SELECT MOD(8, 3) FROM dual;
--����ȡ��
SELECT CEIL(1.00001) FROM dual;
--����ȡ��
SELECT FLOOR(1.999999) FROM dual;
--����ֵ
SELECT ABS(100-200) FROM dual;
--ȡ��
SELECT POWER(2,3) FROM dual;
--��ǰ����
SELECT SYSDATE,SYSTIMESTAMP FROM dual;
--����·�
SELECT add_months(SYSDATE,1) FROM dual;
--��ȡ�µ����һ�������
SELECT last_day(SYSDATE) FROM dual;
