CREATE TABLE jf_exam.teacher
(
    id char(32) PRIMARY KEY NOT NULL,
    teacher_id varchar(255),
    name varchar(255),
    mail varchar(255),
    id_card_no char(18),
    telephone char(11),
    password char(32),
    last_login_time bigint(20),
    last_login_ip char(15),
    createtime bigint(20),
    modifytime bigint(20),
    role_id varchar(36),
    gender int(11)
);
INSERT INTO jf_exam.teacher (id, teacher_id, name, mail, id_card_no, telephone, password, last_login_time, last_login_ip, createtime, modifytime, role_id, gender) VALUES ('401fe4303d2f4fbfb7d508724241f4c7', '00010110', '诸葛孔明', 'wolong@126.com', '320503197110070521', '13890650001', '202cb962ac59075b964b07152d234b70', 1526714776372, '0:0:0:0:0:0:0:1', 1524573810090, 1526561657081, '1', 1);
INSERT INTO jf_exam.teacher (id, teacher_id, name, mail, id_card_no, telephone, password, last_login_time, last_login_ip, createtime, modifytime, role_id, gender) VALUES ('4f9165bedd2b4a309ed3713358f0fc87', '0001', '鹿林', '123123456@qq.com', '320502199312041100', '18694907636', '202cb962ac59075b964b07152d234b70', 1528358704425, '0:0:0:0:0:0:0:1', null, 1526628757990, '0', null);
INSERT INTO jf_exam.teacher (id, teacher_id, name, mail, id_card_no, telephone, password, last_login_time, last_login_ip, createtime, modifytime, role_id, gender) VALUES ('65c10b464fe8432288dbd6882496d178', '2115110220', '温小花', 'moonkop@qq.com', '320502199705152254', '18694907636', '65778349c6817c2574aa6a8e2208d874', 1526037154020, '0:0:0:0:0:0:0:1', 1524626052241, 1526037139831, '1', 1);
INSERT INTO jf_exam.teacher (id, teacher_id, name, mail, id_card_no, telephone, password, last_login_time, last_login_ip, createtime, modifytime, role_id, gender) VALUES ('9acecdec887e4a1591c5e5b9d448dbee', '0002', '夏岩', 'xiayan@123.com', '6587512264532', null, '123', null, null, null, 1524464305342, '1', null);
INSERT INTO jf_exam.teacher (id, teacher_id, name, mail, id_card_no, telephone, password, last_login_time, last_login_ip, createtime, modifytime, role_id, gender) VALUES ('c4f78747c8d74399b455c2ae25297a22', '0009', '马孟起', 'chao@132.com', '33426394000000003', '13890650001', '29549a71a57f587d88209b9c1f1b7999', 0, '-', 1524573813259, null, '1', 0);
INSERT INTO jf_exam.teacher (id, teacher_id, name, mail, id_card_no, telephone, password, last_login_time, last_login_ip, createtime, modifytime, role_id, gender) VALUES ('cb42479e4813490bb2c4d583598c13d5', '0010', '徐元直', 'shu@133.com', '33426394000000003', '13890650001', 'fc1198178c3594bfdda3ca2996eb65cb', 0, '-', 1524573813687, null, '1', 0);
INSERT INTO jf_exam.teacher (id, teacher_id, name, mail, id_card_no, telephone, password, last_login_time, last_login_ip, createtime, modifytime, role_id, gender) VALUES ('dbe99e4caa5e4e3c86fcae3de8318157', '0005', '关云长', 'yu@128.com', '33426394000000003', '13890650001', 'd39934ce111a864abf40391f3da9cdf5', 0, '-', 1524573811043, null, '1', 0);
INSERT INTO jf_exam.teacher (id, teacher_id, name, mail, id_card_no, telephone, password, last_login_time, last_login_ip, createtime, modifytime, role_id, gender) VALUES ('f680ef403ded42edacf764d0a959b107', '009', '夏方元', null, null, null, '202cb962ac59075b964b07152d234b70', 1526043959940, '0:0:0:0:0:0:0:1', null, null, '1', 0);