-- 创建物理表空间,用于存放数据对象
create tablespace sc1907_data
logging  
datafile 'C:\oraclexe\app\oracle\oradata\XE\sc1907_data.dbf' 
size 10m  
autoextend on  
next 2m maxsize 50m;  
-- 创建临时表空间,用于存放临时数据
create temporary tablespace sc1907_temp  
tempfile 'C:\oraclexe\app\oracle\oradata\XE\sc1907_temp.dbf' 
size 5m  
autoextend on  
next 1m maxsize 20m ;  
-- 创建用户
create user bigpig identified by tianxia  
default tablespace sc1907_data  
temporary tablespace sc1907_temp; 

--授权
GRANT CONNECT,RESOURCE TO bigpig;
