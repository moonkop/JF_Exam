CREATE TABLE jf_exam.schedule
(
    id varchar(32) PRIMARY KEY NOT NULL,
    jobName varchar(255),
    jobGroup varchar(255),
    jobStatus int(11) DEFAULT '1' COMMENT '0禁用 1启用 2删除',
    cronexpression varchar(20),
    describes text,
    targetVo_id char(32),
    jobType int(11) COMMENT '0开始  1关闭'
);
INSERT INTO jf_exam.schedule (id, jobName, jobGroup, jobStatus, cronexpression, describes, targetVo_id, jobType) VALUES ('0ae92ad4bec243dda6624c4b79bd67e1', '第一个考试_开启考试入口', '1264882421', 0, '46 12 10 6 6 ? 2018', '定时开启考试入口.ExamId：1264882421', '1264882421', 0);
INSERT INTO jf_exam.schedule (id, jobName, jobGroup, jobStatus, cronexpression, describes, targetVo_id, jobType) VALUES ('2189900698a84ad3a9829956ff6e8990', 'java面试题_开启考试入口', '524033404a674a8ba0aa61f2f534bee5', 0, '24 30 21 6 6 ? 2018', '定时开启考试入口.ExamId：524033404a674a8ba0aa61f2f534bee5', '524033404a674a8ba0aa61f2f534bee5', 0);
INSERT INTO jf_exam.schedule (id, jobName, jobGroup, jobStatus, cronexpression, describes, targetVo_id, jobType) VALUES ('5bc1dcc5c1cf4aa987f6b8a77504079d', 'java面试题_考试结束', '524033404a674a8ba0aa61f2f534bee5', 0, '24 30 21 6 6 ? 2018', '定时考试结束.ExamId：524033404a674a8ba0aa61f2f534bee5', '524033404a674a8ba0aa61f2f534bee5', 2);
INSERT INTO jf_exam.schedule (id, jobName, jobGroup, jobStatus, cronexpression, describes, targetVo_id, jobType) VALUES ('83451c8016254bccaa04666ec75ec07f', 'java面试题_考试结束', '524033404a674a8ba0aa61f2f534bee5', 3, '1 55 21 6 6 ? 2018', '定时考试结束.ExamId：524033404a674a8ba0aa61f2f534bee5', '524033404a674a8ba0aa61f2f534bee5', 2);
INSERT INTO jf_exam.schedule (id, jobName, jobGroup, jobStatus, cronexpression, describes, targetVo_id, jobType) VALUES ('91873d7e42d4423e92ce6c45257826bd', 'java面试题_开启考试入口', '524033404a674a8ba0aa61f2f534bee5', 3, '1 55 21 6 6 ? 2018', '定时开启考试入口.ExamId：524033404a674a8ba0aa61f2f534bee5', '524033404a674a8ba0aa61f2f534bee5', 0);
INSERT INTO jf_exam.schedule (id, jobName, jobGroup, jobStatus, cronexpression, describes, targetVo_id, jobType) VALUES ('9b668c7247b043c0aa5998f1062040cd', '测试2_考试结束', 'c90d8a05489849479f32fb218ba1c7b7', 3, '25 9 22 6 6 ? 2018', '定时考试结束.ExamId：c90d8a05489849479f32fb218ba1c7b7', 'c90d8a05489849479f32fb218ba1c7b7', 2);
INSERT INTO jf_exam.schedule (id, jobName, jobGroup, jobStatus, cronexpression, describes, targetVo_id, jobType) VALUES ('9cb574c3a72a4c26a900d0945af5f956', '河海大学_开启考试入口', '025459bd9bea41e0909802157792dbb4', 3, '55 14 10 7 6 ? 2018', '定时开启考试入口.ExamId：025459bd9bea41e0909802157792dbb4', '025459bd9bea41e0909802157792dbb4', 0);
INSERT INTO jf_exam.schedule (id, jobName, jobGroup, jobStatus, cronexpression, describes, targetVo_id, jobType) VALUES ('afcac2ad807740c1ae8cb2dafb1b80f4', '第一个考试_考试结束', '1264882421', 0, '45 33 9 6 6 ? 2018', '定时考试结束.ExamId：1264882421', '1264882421', 2);
INSERT INTO jf_exam.schedule (id, jobName, jobGroup, jobStatus, cronexpression, describes, targetVo_id, jobType) VALUES ('d0229e1163634d7da06bce431e2d4f2e', '河海大学_考试结束', '025459bd9bea41e0909802157792dbb4', 3, '11 16 10 7 6 ? 2018', '定时考试结束.ExamId：025459bd9bea41e0909802157792dbb4', '025459bd9bea41e0909802157792dbb4', 2);
INSERT INTO jf_exam.schedule (id, jobName, jobGroup, jobStatus, cronexpression, describes, targetVo_id, jobType) VALUES ('e7ca3f1d23414225b56870ff4f623fef', '测试2_开启考试入口', 'c90d8a05489849479f32fb218ba1c7b7', 3, '25 9 22 6 6 ? 2018', '定时开启考试入口.ExamId：c90d8a05489849479f32fb218ba1c7b7', 'c90d8a05489849479f32fb218ba1c7b7', 0);