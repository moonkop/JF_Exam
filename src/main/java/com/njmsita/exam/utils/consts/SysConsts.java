package com.njmsita.exam.utils.consts;

/**
 * 系统常量
 * @author aoyun
 *
 */
public class SysConsts {

	/**
	 * 超级管理员ID
	 */
	public static final String ADMIN_ROLE_ID = "0";


	/**
	 * 登录用户session常量名
	 */
	public static final String USER_LOGIN_TEACHER_OBJECT_NAME ="loginUser";

	/**
	 * 全资源存储常量名
	 */
	public static final String ALL_RESOUCERS_AUTHENTIC_URL_NAME ="allResource";


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

	/**
	 * 简答题
	 */
	public static final String NO_ANSWER_QUESTION_TYPE_NAME="简答题";

	/**
	 * 考试状态码
	 */
	public static final Integer EXAM_STATUS_NO_CHECK = 101;
	public static final Integer EXAM_STATUS_PASS = 111;
	public static final Integer EXAM_STATUS_NO_PASS = 112;
	public static final Integer EXAM_STATUS_OPEN = 121;
	public static final Integer EXAM_STATUS_CLOSE = 122;
	public static final Integer EXAM_STATUS_IN_MARK = 131;
	public static final Integer EXAM_STATUS_IN_CANCEL = 141;
	public static final Integer EXAM_STATUS_ENDING = 199;

	/**
	 * 考试状态视图
	 */
	public static final String EXAM_STATUS_NO_CHECK_VIEW = "待审核";
	public static final String EXAM_STATUS_PASS_VIEW = "未开始";
	public static final String EXAM_STATUS_NO_PASS_VIEW = "待修改";
	public static final String EXAM_STATUS_OPEN_VIEW = "开启考试";
	public static final String EXAM_STATUS_CLOSE_VIEW = "进行中";
	public static final String EXAM_STATUS_IN_MARK_VIEW = "阅卷中";
	public static final String EXAM_STATUS_IN_CANCEL_VIEW = "取消";
	public static final String EXAM_STATUS_ENDING_VIEW = "结束";

	/**
	 * 考试操作
	 */
	public static final String EXAM_OPERATION_VIEW ="view";
	public static final String EXAM_OPERATION_EDIT ="edit";
	public static final String EXAM_OPERATION_CANCEL ="cancle";
	public static final String EXAM_OPERATION_ADD_MARK_TEACHER ="addMarkTeacher";
	public static final String EXAM_OPERATION_VIEW_SCORE ="viewScore";
	public static final String EXAM_OPERATION_NO_CHECK ="noCheck";
	public static final String EXAM_OPERATION_DELETE ="delete";
	public static final String EXAM_OPERATION_MARK ="mark";
	public static final String EXAM_OPERATION_SUBMIT_MARK ="submitMark";
}
