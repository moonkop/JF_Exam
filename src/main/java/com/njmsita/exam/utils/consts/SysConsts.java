package com.njmsita.exam.utils.consts;

import java.util.HashMap;
import java.util.Map;

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
	 * 教师角色
	 */
	public static final String TEACHER_ROLE_NAME ="教师";
	public static final String TEACHER_ROLE_ID="c2ae048229ae408ea1f3b0bda03d6cc6";

	/**
	 * 学生角色
	 */
	public static final String STUDENT_ROLE_NAME ="学生";
	public static final String STUDENT_ROLE_ID ="c2ae048229ae408ea1f3b0bda03d6cc5";

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
	public static final String EXAM_OPERATION_TO_EDIT ="edit";
	public static final String EXAM_OPERATION_CANCEL ="cancle";
	public static final String EXAM_OPERATION_ADD_MARK_TEACHER ="addMarkTeacher";
	public static final String EXAM_OPERATION_VIEW_SCORE ="viewScore";
	public static final String EXAM_OPERATION_NO_CHECK ="noCheck";
	public static final String EXAM_OPERATION_DELETE ="delete";
	public static final String EXAM_OPERATION_MARK ="mark";
	public static final String EXAM_OPERATION_SUBMIT_MARK ="submitMark";
	public static final String EXAM_OPERATION_ATTEND ="attend";

	/**
	 * 定时任务类型
	 */
	public static final Integer SCHEDULEVO_JOB_TYPE_OPEN =0;
	public static final Integer SCHEDULEVO_JOB_TYPE_CLOSE =1;

	/**
	 * 定时任务类型视图
	 */
	public static final String SCHEDULEVO_JOB_TYPE_CLOSE_VIEW ="开启考试入口";
	public static final String SCHEDULEVO_JOB_TYPE_OPEN_VIEW ="关闭考试入口";
	/**
	 * 定时任务状态
	 */
	public static final Integer SCHEDULEVO_JOB_STATUS_FORBIDDEN =0;
	public static final Integer SCHEDULEVO_JOB_STATUS_START =1;
	public static final Integer SCHEDULEVO_JOB_STATUS_DELETE =2;

	/**
	 * 定时任务类型视图转换
	 */
	public static Map<Integer,String> SCHEDULEVO_JOB_TYPE_MAP=new HashMap<>();
	static {
		SCHEDULEVO_JOB_TYPE_MAP.put(SysConsts.SCHEDULEVO_JOB_TYPE_OPEN,SysConsts.SCHEDULEVO_JOB_TYPE_CLOSE_VIEW);
		SCHEDULEVO_JOB_TYPE_MAP.put(SysConsts.SCHEDULEVO_JOB_TYPE_CLOSE,SysConsts.SCHEDULEVO_JOB_TYPE_OPEN_VIEW);
	}

	/**
	 * 学生考试过程操作
	 */
	public static final Integer STUDENT_EXAM_OPERATION_ARCHIVE=1;//存档
	public static final Integer STUDENT_EXAM_OPERATION_SUBMIT=2;//提交
}
