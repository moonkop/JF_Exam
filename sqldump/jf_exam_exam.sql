CREATE TABLE jf_exam.exam
(
    id char(32) PRIMARY KEY NOT NULL,
    name varchar(100),
    remark text COMMENT '备注',
    subject_id tinyint(4) NOT NULL,
    paper_id char(32),
    open_time bigint(20),
    open_duration bigint(20),
    duration int(11),
    close_time bigint(20),
    exam_status int(8) DEFAULT '101',
    create_teacher_id char(32) NOT NULL
);
INSERT INTO jf_exam.exam (id, name, remark, subject_id, paper_id, open_time, open_duration, duration, close_time, exam_status, create_teacher_id) VALUES ('025459bd9bea41e0909802157792dbb4', '河海大学', '', 9, null, 1528337695000, 0, 0, 1528337771000, 121, '4f9165bedd2b4a309ed3713358f0fc87');
INSERT INTO jf_exam.exam (id, name, remark, subject_id, paper_id, open_time, open_duration, duration, close_time, exam_status, create_teacher_id) VALUES ('05cf605a4a704cb996e49f6c0b6fc132', '通过的考试', 'sdfsdf', 9, null, 1527493157000, 0, 0, 1527745978000, 141, '4f9165bedd2b4a309ed3713358f0fc87');
INSERT INTO jf_exam.exam (id, name, remark, subject_id, paper_id, open_time, open_duration, duration, close_time, exam_status, create_teacher_id) VALUES ('1264882421', '第一个考试', '', 9, null, 1528188046000, 0, 0, 1528270425000, 141, '4f9165bedd2b4a309ed3713358f0fc87');
INSERT INTO jf_exam.exam (id, name, remark, subject_id, paper_id, open_time, open_duration, duration, close_time, exam_status, create_teacher_id) VALUES ('524033404a674a8ba0aa61f2f534bee5', 'java面试题', '', 9, null, 1528293301000, 0, 3, 1528293901000, 121, '4f9165bedd2b4a309ed3713358f0fc87');
INSERT INTO jf_exam.exam (id, name, remark, subject_id, paper_id, open_time, open_duration, duration, close_time, exam_status, create_teacher_id) VALUES ('9c7381f648eb4dab9475c935f86e535b', '123123', '', 9, null, 1527740452000, 0, 0, 1527826845000, 121, '4f9165bedd2b4a309ed3713358f0fc87');
INSERT INTO jf_exam.exam (id, name, remark, subject_id, paper_id, open_time, open_duration, duration, close_time, exam_status, create_teacher_id) VALUES ('c21f004586df41959ef8485d8640347b', '测试考试', '', 9, null, 1527856930000, 0, 0, 1527856930000, 121, '4f9165bedd2b4a309ed3713358f0fc87');
INSERT INTO jf_exam.exam (id, name, remark, subject_id, paper_id, open_time, open_duration, duration, close_time, exam_status, create_teacher_id) VALUES ('c90d8a05489849479f32fb218ba1c7b7', '测试2', '', 9, null, 1528294165000, 0, 5, 1528294652000, 121, '4f9165bedd2b4a309ed3713358f0fc87');
INSERT INTO jf_exam.exam (id, name, remark, subject_id, paper_id, open_time, open_duration, duration, close_time, exam_status, create_teacher_id) VALUES ('d25c7cf033594d6c9f19481fc881fbc6', '没通过的考试', '', 9, null, 1527740295000, 0, 0, 1527768600000, 199, '4f9165bedd2b4a309ed3713358f0fc87');