package com.njmsita.exam.utils.consts;

/**
 * 系统常量
 * @author aoyun
 *
 */
public class SysConsts {

	/*
	 * 客户删除标识
	 */
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
	 * 新用户默认状态
	 */
	public static final int USER_DEFAULT_STATUS = 0;
	
	/**
	 * 简历状态：有效状态为0
	 */
	public static final int RESUME_VALID_STATE = 0;
	
	/**
	 * 简历状态：无效状态为1
	 */
	public static final int RESUME_INVALID_STATE = 1;
	/**
	 * 简历状态：待定状态为2
	 */
	public static final int RESUME_WAIT_STATE = 2;
	/**
	 * 简历状态：入职状态为3
	 */
	public static final int RESUME_ON_JOB_STATE = 3;
	
	/*
	 * 客户状态
	 */
	/**
	 * 客户状态：0为打开
	 */
	public static final String CUSTOMER_VALID_STATE = "0";
	/**
	 * 客户状态：1为关闭
	 */
	public static final String CUSTOMER_INVALID_STATE = "1";
	
	/**
	 * 简历反馈	0.通过 1.不通过 2.待反馈 3.项目暂停
	 */
	public static final int RECORD_DEFAULT_RESSTATE = 0;

	/**
	 * 教师登录session常量名
	 */
	public static final String TEACHER_LOGIN_TEACHER_OBJECT_NAME ="loginTeacher";
}
