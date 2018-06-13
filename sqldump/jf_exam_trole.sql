CREATE TABLE jf_exam.trole
(
    ID varchar(36) PRIMARY KEY NOT NULL,
    NAME varchar(100) NOT NULL,
    REMARK varchar(200),
    SEQ int(11),
    PID varchar(36)
);
INSERT INTO jf_exam.trole (ID, NAME, REMARK, SEQ, PID) VALUES ('0', '超管', '超级管理员角色，拥有系统中所有的资源访问权限', 0, null);
INSERT INTO jf_exam.trole (ID, NAME, REMARK, SEQ, PID) VALUES ('1', '教师', '', 1, null);
INSERT INTO jf_exam.trole (ID, NAME, REMARK, SEQ, PID) VALUES ('2', '学生', '', 2, null);