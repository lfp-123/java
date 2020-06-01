CREATE TABLE student(
s_name VARCHAR(20),
s_subject VARCHAR(20),
s_score NUMBER(10,1),
s_studio NUMBER(10,1)
);
INSERT INTO student(s_name,s_subject,s_score,s_studio) VALUES('张三','数学',89,1);
INSERT INTO student(s_name,s_subject,s_score,s_studio) VALUES('张三','语文',80,1);
INSERT INTO student(s_name,s_subject,s_score,s_studio) VALUES('张三','英语',70,1);
INSERT INTO student(s_name,s_subject,s_score,s_studio) VALUES('李四','数学',90,2);
INSERT INTO student(s_name,s_subject,s_score,s_studio) VALUES('李四','语文',70,2);
INSERT INTO student(s_name,s_subject,s_score,s_studio) VALUES('李四','英语',80,2);
DELETE student;
SELECT * FROM student;
COMMIT
/*a.计算每个人的总成绩并排名 显示 成绩排名*/
SELECT s_name,sum(s_score) FROM student GROUP BY s_name ORDER BY SUM(s_score) ASC; 
/*b.计算每个人的总成绩并排名 显示  成绩排名 学号*/
SELECT s_name,sum(s_score),s_studio FROM student GROUP BY s_name,s_studio ORDER BY SUM(s_score) ; 
/*c.计算每个人单科的最高成绩，显示 姓名 课程 学号 最高成绩*/

SELECT t1.s_studio,t1.s_name,t1.s_subject,t1.s_score FROM student  t1,
(SELECT s_name,s_studio,MAX(s_score) AS maxscore FROM student GROUP BY s_name,s_studio) t2
WHERE t1.s_studio=t2.s_studio AND t1.s_score = t2.maxscore;
