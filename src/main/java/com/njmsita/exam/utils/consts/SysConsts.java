package com.njmsita.exam.utils.consts;

/**
 * 系统常量
 * @author aoyun
 *
 */
public class SysConsts {
	/**
	 * 未删除客户
	 */
	public static final int CUSTOMER_UNDELETE = 0;
	/**
	 * 已删除客户
	 */
	public static final int CUSTOMER_DELETED = 1;

	/**
	 * 超级管理员用户名
	 */
	public static final String ADMIN_USER_NAME = "admin";
	/**
	 * 超级管理员ID
	 */
	public static final String ADMIN_USER_ID = "0";


	/**
	 * 教师登录session常量名
	 */
	public static final String TEACHER_LOGIN_TEACHER_OBJECT_NAME ="loginTeacher";

	/**
	 * 学生登录session常量名
	 */
	public static final String STUDENT_LOGIN_TEACHER_OBJECT_NAME ="loginStudent";


	/**
	 * 教师角色名称
	 */
	public static final String TEACHER_ROLE_NAME ="教师";

	/**
	 * 学生角色名称
	 */
	public static final String STUDENT_ROLE_NAME ="学生";

	/**
	 * 批量导入文件类型
	 */
	public static final String INFO_BULK_INPUT_FILE_CONTENT_TYPE="application/vnd.ms-excel";

	/**
	 * 性别  男
	 */
	public static final String INFO_TEACHER_OR_SUTDENT_GENDER_MALE="男";

	/**
	 * 性别  女
	 */
	public static final String INFO_TEACHER_OR_SUTDENT_GENDER_FEMALE="女";
}
