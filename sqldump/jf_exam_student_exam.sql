CREATE TABLE jf_exam.student_exam
(
    id char(32) PRIMARY KEY NOT NULL,
    student_id char(32) NOT NULL,
    exam_id char(32) NOT NULL,
    start_time bigint(20),
    submit_time bigint(20),
    totalScore float,
    answer_content mediumtext,
    state int(11) DEFAULT '0' COMMENT '学生操作：0 未参加 1 答题中 2 已提交'
);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('1d0c887bce0144e0b07681141242ee1a', '2b702ae3b3924d68aa663b1243f039b7', 'c90d8a05489849479f32fb218ba1c7b7', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('224a85e226284034aa54565a45031529', '2b702ae3b3924d68aa663b1243f039b7', 'c21f004586df41959ef8485d8640347b', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('3114f5df94cb4f8eb9c0522fb6ea9dde', '2eba002d273943eb9c74470e119102cc', '524033404a674a8ba0aa61f2f534bee5', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('3ad81f36e56c4e55addeb15a42d86934', '2eba002d273943eb9c74470e119102cc', 'c21f004586df41959ef8485d8640347b', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('4f9ae428af874e4197759d90ae639b4c', 'a182083536fc4fb1ad763886b3ebd71f', '025459bd9bea41e0909802157792dbb4', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('5db37957899d4d209c4abd0923d9b6b9', '239bb3b18fc94acd856579820619cd5e', 'd25c7cf033594d6c9f19481fc881fbc6', 1527765865401, null, null, null, 1);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('86533b46d9a343f8a523633de5a731da', '2b702ae3b3924d68aa663b1243f039b7', '9c7381f648eb4dab9475c935f86e535b', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('874828092389473f9c4012d46e7a795c', 'a182083536fc4fb1ad763886b3ebd71f', 'd25c7cf033594d6c9f19481fc881fbc6', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('9208a3e3988c4c54954b4fb71dad6ddd', '2eba002d273943eb9c74470e119102cc', 'c90d8a05489849479f32fb218ba1c7b7', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('96db476ee574489783343bdefb3a46ef', '239bb3b18fc94acd856579820619cd5e', '9c7381f648eb4dab9475c935f86e535b', 1527766396284, null, null, null, 2);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('99d5e681e5814a6e994f51ac3430056a', '11327ce627ab4d23b7f4835a25cb87fe', 'd25c7cf033594d6c9f19481fc881fbc6', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('a219052aa15a41ac979b0596c5e7dd03', '30f78cdc9d714262a0c382a4fd58c87c', 'd25c7cf033594d6c9f19481fc881fbc6', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('a615269708aa4db396143d1248812f4e', '2eba002d273943eb9c74470e119102cc', '9c7381f648eb4dab9475c935f86e535b', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('a83894259d9944e2a6afbccf3e1e2da3', '2b702ae3b3924d68aa663b1243f039b7', '524033404a674a8ba0aa61f2f534bee5', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('b0c2c663364c4d63b58450bf4f1a411d', '2b702ae3b3924d68aa663b1243f039b7', 'd25c7cf033594d6c9f19481fc881fbc6', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('b2192e55a3a14b00a972b35184f66bdc', '239bb3b18fc94acd856579820619cd5e', 'c21f004586df41959ef8485d8640347b', 1527856939537, null, null, null, 2);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('b8a76328b19c485abee945f283e902a2', '11327ce627ab4d23b7f4835a25cb87fe', '025459bd9bea41e0909802157792dbb4', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('bba03564b0cc4f628b09705dceba56c1', '2eba002d273943eb9c74470e119102cc', 'd25c7cf033594d6c9f19481fc881fbc6', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('d0c1b971dd3a42a1bcde76bb67bc0f9b', '30f78cdc9d714262a0c382a4fd58c87c', '9c7381f648eb4dab9475c935f86e535b', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('dea96db0c3b94668aeb0e0103a2f3f18', '30f78cdc9d714262a0c382a4fd58c87c', 'c21f004586df41959ef8485d8640347b', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('e4b8fda0d28d4375b3e95124db44febb', '30f78cdc9d714262a0c382a4fd58c87c', '524033404a674a8ba0aa61f2f534bee5', null, null, null, null, 0);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('e992b85d4f61470b8a11feffaef3e1cc', '239bb3b18fc94acd856579820619cd5e', 'c90d8a05489849479f32fb218ba1c7b7', 1528294168642, null, null, null, 2);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('ecffb1af3c9b44b9a54976701bfb6914', '239bb3b18fc94acd856579820619cd5e', '524033404a674a8ba0aa61f2f534bee5', 1528293302662, null, null, null, 2);
INSERT INTO jf_exam.student_exam (id, student_id, exam_id, start_time, submit_time, totalScore, answer_content, state) VALUES ('edc8540f30c74d05b8848eaa77595204', '30f78cdc9d714262a0c382a4fd58c87c', 'c90d8a05489849479f32fb218ba1c7b7', null, null, null, null, 0);