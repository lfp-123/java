/*14��
��һ��SQL����ѯÿ�ſδ���80�ֵ�ѧ��������
*/
CREATE TABLE new_students(
  s_name VARCHAR2(20),
  s_course VARCHAR(20),
  s_score NUMBER(10,1)
);
INSERT INTO NEW_students(s_name,s_course,s_score) VALUES('����','����',81);
INSERT INTO NEW_students(s_name,s_course,s_score) VALUES('����','��ѧ',75);
INSERT INTO NEW_students(s_name,s_course,s_score) VALUES('����','����',76);
INSERT INTO NEW_students(s_name,s_course,s_score) VALUES('����','��ѧ',90);
INSERT INTO NEW_students(s_name,s_course,s_score) VALUES('����','����',81);
INSERT INTO NEW_students(s_name,s_course,s_score) VALUES('����','��ѧ',100);
INSERT INTO NEW_students(s_name,s_course,s_score) VALUES('����','Ӣ��',90);
COMMIT;
  SELECT S_name FROM NEW_students GROUP BY s_name HAVING MIN(s_score)>80; 


