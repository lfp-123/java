CREATE TABLE student(
s_name VARCHAR(20),
s_subject VARCHAR(20),
s_score NUMBER(10,1),
s_studio NUMBER(10,1)
);
INSERT INTO student(s_name,s_subject,s_score,s_studio) VALUES('����','��ѧ',89,1);
INSERT INTO student(s_name,s_subject,s_score,s_studio) VALUES('����','����',80,1);
INSERT INTO student(s_name,s_subject,s_score,s_studio) VALUES('����','Ӣ��',70,1);
INSERT INTO student(s_name,s_subject,s_score,s_studio) VALUES('����','��ѧ',90,2);
INSERT INTO student(s_name,s_subject,s_score,s_studio) VALUES('����','����',70,2);
INSERT INTO student(s_name,s_subject,s_score,s_studio) VALUES('����','Ӣ��',80,2);
DELETE student;
SELECT * FROM student;
COMMIT
/*a.����ÿ���˵��ܳɼ������� ��ʾ �ɼ�����*/
SELECT s_name,sum(s_score) FROM student GROUP BY s_name ORDER BY SUM(s_score) ASC; 
/*b.����ÿ���˵��ܳɼ������� ��ʾ  �ɼ����� ѧ��*/
SELECT s_name,sum(s_score),s_studio FROM student GROUP BY s_name,s_studio ORDER BY SUM(s_score) ; 
/*c.����ÿ���˵��Ƶ���߳ɼ�����ʾ ���� �γ� ѧ�� ��߳ɼ�*/

SELECT t1.s_studio,t1.s_name,t1.s_subject,t1.s_score FROM student  t1,
(SELECT s_name,s_studio,MAX(s_score) AS maxscore FROM student GROUP BY s_name,s_studio) t2
WHERE t1.s_studio=t2.s_studio AND t1.s_score = t2.maxscore;
