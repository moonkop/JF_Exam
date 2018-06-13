CREATE TABLE jf_exam.classroom
(
    id char(32) PRIMARY KEY NOT NULL,
    school_id char(32),
    name varchar(255),
    CONSTRAINT FKrmu20qkf4r687db7plx94sb73 FOREIGN KEY (school_id) REFERENCES school (id)
);
CREATE INDEX FKrmu20qkf4r687db7plx94sb73 ON jf_exam.classroom (school_id);
INSERT INTO jf_exam.classroom (id, school_id, name) VALUES ('0ad96165d6a54c789020455853714644', '062895615fe4422e9e661765c39ab5e7', 'test中');
INSERT INTO jf_exam.classroom (id, school_id, name) VALUES ('11a6999df5d8423987609de25f2d68a9', '55df0c3d6f7b4a3aa668c4ba20cd24ef', '夏侯1班');
INSERT INTO jf_exam.classroom (id, school_id, name) VALUES ('1d753524669c496fb81d0803f6749785', '55df0c3d6f7b4a3aa668c4ba20cd24ef', '曹家1班');
INSERT INTO jf_exam.classroom (id, school_id, name) VALUES ('1ef69c1deb8c4594bf83fa45e2ba192a', '062895615fe4422e9e661765c39ab5e7', '刘家班');
INSERT INTO jf_exam.classroom (id, school_id, name) VALUES ('58af341a01e7462095694b41294b9a42', '062895615fe4422e9e661765c39ab5e7', '莽夫1班');
INSERT INTO jf_exam.classroom (id, school_id, name) VALUES ('5f1e10288ba14543ae06ea9e2d97a00f', 'ddf34b176cbf4791a802aeab0c031bfc', '阿伟大的');
INSERT INTO jf_exam.classroom (id, school_id, name) VALUES ('756639e82e2d4446965aae3ad8612c0d', '58909e0d9188448a8e67dc01e17678d9', '孙家一班');
INSERT INTO jf_exam.classroom (id, school_id, name) VALUES ('77df232855ce4547829b7a26380a01ca', '062895615fe4422e9e661765c39ab5e7', '诸葛一班');
INSERT INTO jf_exam.classroom (id, school_id, name) VALUES ('79cfabdf36d3409a943feb67c3944440', '55df0c3d6f7b4a3aa668c4ba20cd24ef', '帝国提高班');
INSERT INTO jf_exam.classroom (id, school_id, name) VALUES ('b5ca8c338ebe4b909c2f95dc603dba4a', '062895615fe4422e9e661765c39ab5e7', '智囊2班');
INSERT INTO jf_exam.classroom (id, school_id, name) VALUES ('bc5b235f7b5a47e6b4f80f19104983e5', '55df0c3d6f7b4a3aa668c4ba20cd24ef', '智囊2班');
INSERT INTO jf_exam.classroom (id, school_id, name) VALUES ('d3194b09f5354a40adfa41afb4f46bf6', '1f82ef1ad4134d018a8ff348c8e3d6f4', '河海1班');