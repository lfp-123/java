-- ���������ռ�,���ڴ�����ݶ���
create tablespace sc1907_data
logging  
datafile 'C:\oraclexe\app\oracle\oradata\XE\sc1907_data.dbf' 
size 10m  
autoextend on  
next 2m maxsize 50m;  
-- ������ʱ��ռ�,���ڴ����ʱ����
create temporary tablespace sc1907_temp  
tempfile 'C:\oraclexe\app\oracle\oradata\XE\sc1907_temp.dbf' 
size 5m  
autoextend on  
next 1m maxsize 20m ;  
-- �����û�
create user bigpig identified by tianxia  
default tablespace sc1907_data  
temporary tablespace sc1907_temp; 

--��Ȩ
GRANT CONNECT,RESOURCE TO bigpig;
