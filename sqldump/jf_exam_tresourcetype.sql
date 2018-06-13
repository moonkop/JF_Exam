CREATE TABLE jf_exam.tresourcetype
(
    ID varchar(36) PRIMARY KEY NOT NULL,
    NAME varchar(100) NOT NULL
);
INSERT INTO jf_exam.tresourcetype (ID, NAME) VALUES ('0', '菜单');
INSERT INTO jf_exam.tresourcetype (ID, NAME) VALUES ('1', '功能');