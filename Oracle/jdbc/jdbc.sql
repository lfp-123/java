CREATE TABLE USERS(
     ID NUMBER(4) PRIMARY KEY,
     username VARCHAR2(100),
     PASSWORD VARCHAR2(100),
     birthday DATE,
     sex CHAR(1),
     age NUMBER(3)
     )
     SELECT * FROM USERS;
     CREATE SEQUENCE id_seq INCREMENT BY 1  START WITH 1
     INSERT INTO USERS VALUES(id_seq.nextval,'admin','12345',SYSDATE,'1',23);
 INSERT INTO USERS VALUES(id_seq.nextval,'������','12345',SYSDATE,'1',75);
     
 
SELECT * FROM  Employee;
CREATE TABLE Employee(
  ID NUMBER(4) PRIMARY KEY,
     username VARCHAR2(100),
     dates DATE,
     sex VARCHAR2(5),
     salary NUMBER(10),
     department VARCHAR2(10)
     )
      INSERT INTO Employee VALUES(id_seq.nextval,'������',SYSDATE,'��',10000,'�䵱��');
  INSERT INTO Employee VALUES(id_seq.nextval,'���޼�',SYSDATE,'��',6000,'�䵱��');
   INSERT INTO Employee VALUES(id_seq.nextval,'�Ŵ�ɽ',SYSDATE,'��',5000,'�䵱��');
          INSERT INTO Employee VALUES(id_seq.nextval,'��üʦ̫',SYSDATE,'Ů',8000,'��ü��');
        INSERT INTO Employee VALUES(id_seq.nextval,'����ŵ',SYSDATE,'Ů',5000,'��ü��');  
     INSERT INTO Employee VALUES(id_seq.nextval,'������',SYSDATE,'��',12000,'����');
