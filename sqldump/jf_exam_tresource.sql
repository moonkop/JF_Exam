CREATE TABLE jf_exam.tresource
(
    ID varchar(36) PRIMARY KEY NOT NULL,
    ICON varchar(100),
    NAME varchar(100) NOT NULL,
    REMARK varchar(200),
    SEQ int(11),
    URL varchar(200),
    PID varchar(36),
    TRESOURCETYPE_ID varchar(36) NOT NULL
);
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('7ed80ac9a0b14fb0ad5b01d2e04d7b90', 'fa fa-table', '学生管理', '', 80100, '/student/manage', 'xtgl', '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('b89c8eeea7ed4869820a7a3be974fee7', 'fa fa-table', '教师管理', '', 80200, '/teacher/manage', 'xtgl', '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('classroom_manage', 'fa fa-table', '班级管理', '', 80300, '/manage/classroom', 'xtgl', '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('createPaper', 'fa fa-bar-chart-o', '我的试卷', '', 2, '/paper', 'xtcd', '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('exam_manage_detail', null, '查看详情', '', 100, '/exam/manage/detail', 'myExam', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('jsgl', 'fa fa-table', '角色管理', '', 80500, '/manage/role', 'xtgl', '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('jsglAdd', null, '教师添加/编辑页面', '', 80201, '/teacher/manage/edit', 'b89c8eeea7ed4869820a7a3be974fee7', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('jsglAddPage', 'wrench', '角色添加/编辑页面', '', 80501, '/manage/role/edit', 'jsgl', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('jsglDelete', 'wrench', '角色删除', '', 80504, '/manage/role/delete', 'jsgl', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('jsglEdit', 'wrench', '角色详情', '', 80503, '/manage/role/detail', 'jsgl', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('jsglGrantPage', 'wrench', '角色添加/编辑', '', 80502, '/manage/role/edit.do', 'jsgl', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_bank_question', null, '题目管理', '', 90400, '/manage/bank/question', 'tkgl', '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_bank_questionType', null, '题目类型管理', '', 90300, '/manage/bank/questionType', 'tkgl', '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_bank_subject', null, '科目管理', '', 90100, '/manage/bank/subject', 'tkgl', '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_bank_topic', null, '知识点管理', '', 90200, '/manage/bank/topic', 'tkgl', '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_classroom_delete', null, '班级删除', '', 80304, '/manage/classroom/delete', 'classroom_manage', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_classroom_detail', null, '班级详情', '', 80303, '/manage/classroom/detail', 'classroom_manage', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_classroom_edit', null, '班级添加/编辑页面', '', 80301, '/manage/classroom/edit', 'classroom_manage', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_classroom_edit.do', null, '班级添加/编辑', '', 80302, '/manage/classroom/edit.do', 'classroom_manage', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_resource', null, '资源管理', '', 80600, '/manage/resource', 'xtgl', '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_resource_delete', null, '资源删除', '', 80604, '/manage/resource/delete', 'manage_resource', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_resource_detail', null, '资源详情', '', 80603, '/manage/resource/detail', 'manage_resource', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_resource_edit', null, '资源添加/编辑页面', '', 80601, '/manage/resource/edit', 'manage_resource', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_resource_edit.do', null, '资源添加/编辑', '', 80602, '/manage/resource/edit.do', 'manage_resource', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_school_delete.do', null, '学校删除', '', 80404, '/manage/school/delete.do', 'school_manage', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_school_detail', null, '学校详情', '', 80403, '/manage/school/detail', 'school_manage', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_school_edit', null, '学校添加/编辑页面', '', 80401, '/manage/school/edit', 'school_manage', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('manage_school_edit.do', null, '学校添加/编辑', '', 80402, '/manage/school/edit.do', 'school_manage', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('myExam', 'fa fa-table', '我的考试', '', 4, '/exam/manage', 'xtcd', '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('school_manage', 'fa fa-table', '学校管理', '', 80400, '/manage/school', 'xtgl', '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('student_manage_delete.do', null, '学生删除', '', 80106, '/student/manage/delete.do', '7ed80ac9a0b14fb0ad5b01d2e04d7b90', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('student_manage_detail', null, '学生详情', '', 80103, '/student/manage/detail', '7ed80ac9a0b14fb0ad5b01d2e04d7b90', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('student_manage_edit', null, '学生添加/编辑页面', '', 80101, '/student/manage/edit', '7ed80ac9a0b14fb0ad5b01d2e04d7b90', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('student_manage_edit.do', null, '学生添加/编辑', '', 80102, '/student/manage/edit.do', '7ed80ac9a0b14fb0ad5b01d2e04d7b90', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('student_manage_import.do', null, '学生批量导入', '', 80105, '/student/manage/import.do', '7ed80ac9a0b14fb0ad5b01d2e04d7b90', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('student_manage_resetPassword.do', null, '学生重置密码', '', 80104, '/student/manage/resetPassword.do', '7ed80ac9a0b14fb0ad5b01d2e04d7b90', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('student_manage_welcome', null, '欢迎页', '', 1, '/student/welcome', 'xtcd', '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('stuexam', null, '我的考试', '', 100, '/exam/student', 'xtcd', '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('teacher_manage_delete.do', null, '教师删除', '', 80206, '/teacher/manage/delete.do', 'b89c8eeea7ed4869820a7a3be974fee7', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('teacher_manage_detail', null, '教师详情', '', 80203, '/teacher/manage/detail', 'b89c8eeea7ed4869820a7a3be974fee7', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('teacher_manage_edit.do', null, '教师添加/编辑', '', 80202, '/teacher/manage/edit.do', 'b89c8eeea7ed4869820a7a3be974fee7', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('teacher_manage_import.do', null, '教师批量导入', '', 80205, '/teacher/manage/import.do', 'b89c8eeea7ed4869820a7a3be974fee7', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('teacher_manage_resetPassword.do', null, '教师密码重置', '', 80204, '/teacher/manage/resetPassword.do', 'b89c8eeea7ed4869820a7a3be974fee7', '1');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('teacher_welcome', 'fa fa-dashboard', '欢迎页', '', 1, '/teacher/welcome', 'xtcd', '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('tkgl', 'fa fa-sitemap', '题库管理', null, 9, null, 'xtcd', '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('xtcd', '', '系统菜单', null, 0, null, null, '0');
INSERT INTO jf_exam.tresource (ID, ICON, NAME, REMARK, SEQ, URL, PID, TRESOURCETYPE_ID) VALUES ('xtgl', 'fa fa-sitemap', '系统管理', null, 8, '', 'xtcd', '0');