/*14题
用一条SQL语句查询每门课大于80分的学生的姓名
*/
CREATE TABLE new_students(
  s_name VARCHAR2(20),
  s_course VARCHAR(20),
  s_score NUMBER(10,1)
);
INSERT INTO NEW_students(s_name,s_course,s_score) VALUES('张三','语文',81);
INSERT INTO NEW_students(s_name,s_course,s_score) VALUES('张三','数学',75);
INSERT INTO NEW_students(s_name,s_course,s_score) VALUES('李四','语文',76);
INSERT INTO NEW_students(s_name,s_course,s_score) VALUES('李四','数学',90);
INSERT INTO NEW_students(s_name,s_course,s_score) VALUES('王五','语文',81);
INSERT INTO NEW_students(s_name,s_course,s_score) VALUES('王五','数学',100);
INSERT INTO NEW_students(s_name,s_course,s_score) VALUES('王五','英语',90);
COMMIT;
  SELECT S_name FROM NEW_students GROUP BY s_name HAVING MIN(s_score)>80; 


