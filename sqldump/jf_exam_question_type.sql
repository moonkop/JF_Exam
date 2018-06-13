CREATE TABLE jf_exam.question_type
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(45),
    score float
);
INSERT INTO jf_exam.question_type (id, name, score) VALUES (2, '单选题', 2);
INSERT INTO jf_exam.question_type (id, name, score) VALUES (3, '多选题', 5);
INSERT INTO jf_exam.question_type (id, name, score) VALUES (4, '简答题', 10);
INSERT INTO jf_exam.question_type (id, name, score) VALUES (7, '判断题', 1.5);
INSERT INTO jf_exam.question_type (id, name, score) VALUES (8, '送分题', 1);