CREATE TABLE jf_exam.log
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userId char(32) COMMENT '操作者',
    module varchar(255) COMMENT '操作类型',
    method varchar(255) COMMENT '详细操作记录',
    ip char(15),
    time bigint(20) COMMENT '操作时间',
    commite text COMMENT '操作描述',
    response_time bigint(20) COMMENT '响应时间',
    argument text,
    realName varchar(255),
    userRole varchar(32)
);