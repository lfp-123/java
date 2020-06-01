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
 INSERT INTO USERS VALUES(id_seq.nextval,'张三丰','12345',SYSDATE,'1',75);
     
 
SELECT * FROM  Employee;
CREATE TABLE Employee(
  ID NUMBER(4) PRIMARY KEY,
     username VARCHAR2(100),
     dates DATE,
     sex VARCHAR2(5),
     salary NUMBER(10),
     department VARCHAR2(10)
     )
      INSERT INTO Employee VALUES(id_seq.nextval,'张三丰',SYSDATE,'男',10000,'武当派');
  INSERT INTO Employee VALUES(id_seq.nextval,'张无忌',SYSDATE,'男',6000,'武当派');
   INSERT INTO Employee VALUES(id_seq.nextval,'张翠山',SYSDATE,'男',5000,'武当派');
          INSERT INTO Employee VALUES(id_seq.nextval,'峨眉师太',SYSDATE,'女',8000,'峨眉派');
        INSERT INTO Employee VALUES(id_seq.nextval,'周至诺',SYSDATE,'女',5000,'峨眉派');  
     INSERT INTO Employee VALUES(id_seq.nextval,'阳顶天',SYSDATE,'男',12000,'明教');
