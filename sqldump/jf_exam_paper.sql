CREATE TABLE jf_exam.paper
(
    id char(32) PRIMARY KEY NOT NULL,
    title varchar(255),
    comment varchar(1000),
    create_teacher_id char(32) NOT NULL,
    subject_id tinyint(4) NOT NULL,
    questionContain text
);