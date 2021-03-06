package com.njmsita.exam.utils.consts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 系统常量
 *
 * @author aoyun
 */
public class SysConsts
{


	/**
	 * error codes
	 */
	public static final int ERROR_CODE_ERROR_WITH_PAYLOAD =417;

	/**
	 * 超级管理员ID
	 */
	public static final String ADMIN_ROLE_ID = "0";


	/**
	 * 登录用户session常量名
	 */
	public static final String USER_LOGIN_OBJECT_NAME = "loginUser";

	/**
	 * 全资源存储常量名
	 */
	public static final String ALL_RESOUCERS_AUTHENTIC_URL_NAME = "allResource";

	public static final String USER_RESOURCE_NAME = "resources";

	/**
	 * 教师角色
	 */
	public static final String TEACHER_ROLE_NAME = "教师";
	public static final String TEACHER_ROLE_ID = "1";

	/**
	 * 学生角色
	 */
	public static final String STUDENT_ROLE_NAME = "学生";
	public static final String STUDENT_ROLE_ID = "2";

	//单选题
	public static final int QUESTION_TYPE_SINGLE_SELECTION=2;
	//多选题
	public static final int QUESTION_TYPE_MUTI_SELECTION=3;
	//简答题
	public static final int QUESTION_TYPE_SHORT_ANSWER=4;
	//单选题
	public static final String QUESTION_TYPE_SINGLE_SELECTION_VIEW="SINGLE_SELECTION";
	//多选题
	public static final String QUESTION_TYPE_MUTI_SELECTION_VIEW="MUTI_SELECTION";
	//简答题
	public static final String QUESTION_TYPE_SHORT_ANSWER_VIEW="SHORT_ANSWER";

	public static final Map<Integer, String> QUESTION_TYPE_VIEW_MAP = new HashMap<>();
	public static String PAPER_TEMPLATE_COLLECTION_NAME = "template_paper";


	static{
		QUESTION_TYPE_VIEW_MAP.put(QUESTION_TYPE_SINGLE_SELECTION, QUESTION_TYPE_SINGLE_SELECTION_VIEW);
		QUESTION_TYPE_VIEW_MAP.put(QUESTION_TYPE_MUTI_SELECTION, QUESTION_TYPE_MUTI_SELECTION_VIEW);
		QUESTION_TYPE_VIEW_MAP.put(QUESTION_TYPE_SHORT_ANSWER, QUESTION_TYPE_SHORT_ANSWER_VIEW);
	}

	public static final Set<Integer> MANUAL_MARK_QUESTION_TYPE_SET = new HashSet<>();

	//批阅完成
	public static final String STUDENT_EXAM_MARK_PROGRESS_COMPELETED = "completed";
	//未开始批阅
	public static final String STUDENT_EXAM_MARK_PROGRESS_NOTSTARTED = "notStarted";
	//未完成批阅
	public static final String STUDENT_EXAM_MARK_PROGRESS_UNFINISHED = "unfinished";
	//未找到结果
	public static final String STUDENT_EXAM_MARK_PROGRESS_NOTFOUND = "notFound";


    static{
		MANUAL_MARK_QUESTION_TYPE_SET.add(QUESTION_TYPE_SHORT_ANSWER);
	}

	public static final Set<String> STRING_EMPTY_SET = new HashSet<>();
	static{
		STRING_EMPTY_SET.add("");
	}

    public static final String SUPER_ADMIN_ID = "0" ;

	/**
	 * 批量导入文件类型
	 */
	public static final String INFO_BULK_INPUT_FILE_CONTENT_TYPE = "application/vnd.ms-excel";

	/**
	 * 性别  男
	 */
	public static final String INFO_TEACHER_OR_SUTDENT_GENDER_MALE = "男";

	/**
	 * 性别  女
	 */
	public static final String INFO_TEACHER_OR_SUTDENT_GENDER_FEMALE = "女";

	/**
	 * 简答题
	 */
	public static final String NO_ANSWER_QUESTION_TYPE_NAME = "简答题";

	/**
	 * 系统菜单
	 */
	public static final String SYSTEM_MENU_ID = "xtcd";
	/**
	 * 菜单类型资源
	 */
	public static final String RESOURCE_TYPE_MENU_ID = "0";

	/**
	 * 考试所包含的试卷存入MongoDB中的Collection名称
	 */
	public static final String EXAM_PAPER_COLLECTION_NAME = "exam_paper";

    public static final String EXAM_REPORT_COLLECTION_NAME="exam_report";

    public static final String STUDENT_EXAM_ARCHIVE_COLLECTION_NAME = "student_exam_archive";


    /**
	 * 考试将允许提交时间放宽3分钟
	 */
    public static final int EXAM_SUBMIT_ADDITIONAL_TIME = 3 * 1000 * 60;


    /**
     * 定时器任务类型
     */
    //考试开始
    public static final int EXAM_SCHEDULE_ACTION_TYPE_EXAM_START = 1;
    //关闭考试入口
    public static final int EXAM_SCHEDULE_ACTION_TYPE_EXAM_CLOSE_ENTRANCE = 2;
    //结束考试
    public static final int EXAM_SCHEDULE_ACTION_TYPE_EXAM_END = 3;


    /**
	 * 考试状态码int
	 */
	//todo 补充每一位状态的意意义
	public final static int EXAM_STATUS_NO_CHECK = 101;
	public final static int EXAM_STATUS_WAITING = 111;
	public final static int EXAM_STATUS_NO_PASS = 112;
	public final static int EXAM_STATUS_OPEN = 121;
	public final static int EXAM_STATUS_CLOSED = 122;
	public final static int EXAM_STATUS_SUBMITTED = 123;
	public final static int EXAM_STATUS_MARKING = 131;
	public final static int EXAM_STATUS_CANCELED = 141;
	public final static int EXAM_STATUS_OUTDATED = 151;
	public final static int EXAM_STATUS_ENDING = 199;
	/**
     * 考试状态视图
     */
	public static final String EXAM_STATUS_PENDING_VIEW = "待审核";
    public static final String EXAM_STATUS_WAITING_VIEW = "未开始";
    public static final String EXAM_STATUS_REJECTED_VIEW = "待修改";
    public static final String EXAM_STATUS_OPEN_VIEW = "进行中";
    public static final String EXAM_STATUS_CLOSED_VIEW = "答题中";
    public static final String EXAM_STATUS_SUBMITTED_VIEW = "已交卷";
    public static final String EXAM_STATUS_MARKING_VIEW = "阅卷中";
    public static final String EXAM_STATUS_CANCELED_VIEW = "已取消";
    public static final String EXAM_STATUS_OUTDATED_VIEW = "已过时";
    public static final String EXAM_STATUS_ENDING_VIEW = "已结束";

    public final static Map<Integer, String> ExamStatusViewMap = new HashMap<>();
    static {
        ExamStatusViewMap.put(SysConsts.EXAM_STATUS_NO_CHECK,SysConsts.EXAM_STATUS_PENDING_VIEW );
        ExamStatusViewMap.put(SysConsts.EXAM_STATUS_WAITING,SysConsts.EXAM_STATUS_WAITING_VIEW);
        ExamStatusViewMap.put(SysConsts.EXAM_STATUS_NO_PASS,SysConsts.EXAM_STATUS_REJECTED_VIEW);
        ExamStatusViewMap.put(SysConsts.EXAM_STATUS_OPEN,SysConsts.EXAM_STATUS_OPEN_VIEW );
        ExamStatusViewMap.put(SysConsts.EXAM_STATUS_CLOSED,SysConsts.EXAM_STATUS_CLOSED_VIEW);
        ExamStatusViewMap.put(SysConsts.EXAM_STATUS_SUBMITTED,SysConsts.EXAM_STATUS_SUBMITTED_VIEW );
        ExamStatusViewMap.put(SysConsts.EXAM_STATUS_MARKING,SysConsts.EXAM_STATUS_MARKING_VIEW);
        ExamStatusViewMap.put(SysConsts.EXAM_STATUS_CANCELED,SysConsts.EXAM_STATUS_CANCELED_VIEW);
        ExamStatusViewMap.put(SysConsts.EXAM_STATUS_ENDING,SysConsts.EXAM_STATUS_ENDING_VIEW );
        ExamStatusViewMap.put(SysConsts.EXAM_STATUS_OUTDATED,SysConsts.EXAM_STATUS_OUTDATED_VIEW);
    }


	/**
	 * 考试操作
	 */
	public static final String EXAM_OPERATION_VIEW = "view";
	public static final String EXAM_OPERATION_EDIT = "edit";
	public static final String EXAM_OPERATION_CANCEL = "cancel";
	public static final String EXAM_OPERATION_ADD_MARK_TEACHER = "addMarkTeacher";
	public static final String EXAM_OPERATION_RESULT = "result";
	public static final String EXAM_OPERATION_REVIEW = "review";
	public static final String EXAM_OPERATION_DELETE = "delete";
	public static final String EXAM_OPERATION_MARK = "mark";
	public static final String EXAM_OPERATION_SUBMIT_MARK = "submitMark";
	public static final String EXAM_OPERATION_ENTER = "enter";
	public static final String EXAM_OPERATION_PREVIEW_VISIBLE = "preview"; //显示在list上
	public static final String EXAM_OPERATION_PREVIEW = "preview_permission";//真正的预览权限
    public static final String EXAM_OPERATION_STOP = "stop";
    public static final String EXAM_OPERATION_REPORT = "report";



    public static final String EXAM_PREMISSION_GET_PAPER_CONTENT="";
	public static final String EXAM_PREMISSION_GET_MY_ANSWER="";
	public static final String EXAM_PREMISSION_GET_CORRECT_ANSWER="";
	/**
	 * 考试操作
	 */
	public static final String EXAM_OPERATION_VIEW_VIEW = "查看";
	public static final String EXAM_OPERATION_EDIT_VIEW = "编辑";
	public static final String EXAM_OPERATION_CANCEL_VIEW = "取消";
	public static final String EXAM_OPERATION_ADD_MARK_TEACHER_VIEW = "添加批阅教师";
	public static final String EXAM_OPERATION_VIEW_SCORE_VIEW = "查看分数";
	public static final String EXAM_OPERATION_REVIEW_VIEW = "审核";
	public static final String EXAM_OPERATION_DELETE_VIEW = "删除";
	public static final String EXAM_OPERATION_MARK_VIEW = "批阅";
	public static final String EXAM_OPERATION_SUBMIT_MARK_VIEW = "提交批阅";
	public static final String EXAM_OPERATION_ATTEND_VIEW = "参加考试";
	public static final String EXAM_OPERATION_PREVIEW_VIEW = "查看概要";
    public static final String EXAM_OPERATION_STOP_VIEW = "终止";


    public final static Map<String, String> ExamOperationViewMap = new HashMap<>();

    static
    {
        ExamOperationViewMap.put(SysConsts.EXAM_OPERATION_VIEW, SysConsts.EXAM_OPERATION_VIEW_VIEW);
        ExamOperationViewMap.put(SysConsts.EXAM_OPERATION_EDIT, SysConsts.EXAM_OPERATION_EDIT_VIEW);
        ExamOperationViewMap.put(SysConsts.EXAM_OPERATION_CANCEL, SysConsts.EXAM_OPERATION_CANCEL_VIEW);
        ExamOperationViewMap.put(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER, SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER_VIEW);
        ExamOperationViewMap.put(SysConsts.EXAM_OPERATION_RESULT, SysConsts.EXAM_OPERATION_VIEW_SCORE_VIEW);
        ExamOperationViewMap.put(SysConsts.EXAM_OPERATION_REVIEW, SysConsts.EXAM_OPERATION_REVIEW_VIEW);
        ExamOperationViewMap.put(SysConsts.EXAM_OPERATION_DELETE, SysConsts.EXAM_OPERATION_DELETE_VIEW);
        ExamOperationViewMap.put(SysConsts.EXAM_OPERATION_MARK, SysConsts.EXAM_OPERATION_MARK_VIEW);
        ExamOperationViewMap.put(SysConsts.EXAM_OPERATION_SUBMIT_MARK, SysConsts.EXAM_OPERATION_SUBMIT_MARK_VIEW);
        ExamOperationViewMap.put(SysConsts.EXAM_OPERATION_ENTER, SysConsts.EXAM_OPERATION_ATTEND_VIEW);
        ExamOperationViewMap.put(SysConsts.EXAM_OPERATION_PREVIEW_VISIBLE, SysConsts.EXAM_OPERATION_PREVIEW_VIEW);
    }


//    /**
//	 * 定时任务类型
//	 */
//	public static final int SCHEDULEVO_JOB_TYPE_OPEN = 0;
//	public static final int SCHEDULEVO_JOB_TYPE_CLOSE = 1;
//	public static final int SCHEDULEVO_JOB_TYPE_FINISH = 2;

	/**
	 * 定时任务类型视图
	 */
	public static final String SCHEDULEVO_JOB_TYPE_CLOSE_VIEW = "开启考试入口";
	public static final String SCHEDULEVO_JOB_TYPE_OPEN_VIEW = "关闭考试入口";
	public static final String SCHEDULEVO_JOB_TYPE_FINISH_VIEW = "考试结束";

	/**
	 * 定时任务状态
	 */
	public static final int SCHEDULEVO_JOB_STATUS_FORBIDDEN = 0;
	public static final int SCHEDULEVO_JOB_STATUS_START = 1;
	public static final int SCHEDULEVO_JOB_STATUS_DELETE = 2;
	public static final int SCHEDULEVO_JOB_STATUS_OUTMODED = 3;
	public static final int SCHEDULEVO_JOB_STATUS_ONETIME = -1;

	/**
	 * 定时任务类型视图转换
	 */
	public static Map<Integer, String> EXAM_SCHEDULE_ACTION_TYPE_MAP = new HashMap<>();

	static
	{
		EXAM_SCHEDULE_ACTION_TYPE_MAP.put(SysConsts.EXAM_SCHEDULE_ACTION_TYPE_EXAM_START, SysConsts.SCHEDULEVO_JOB_TYPE_CLOSE_VIEW);
		EXAM_SCHEDULE_ACTION_TYPE_MAP.put(SysConsts.EXAM_SCHEDULE_ACTION_TYPE_EXAM_CLOSE_ENTRANCE, SysConsts.SCHEDULEVO_JOB_TYPE_OPEN_VIEW);
		EXAM_SCHEDULE_ACTION_TYPE_MAP.put(SysConsts.EXAM_SCHEDULE_ACTION_TYPE_EXAM_END, SysConsts.SCHEDULEVO_JOB_TYPE_FINISH_VIEW);
	}

	/**
	 * 考试开放时间
	 */

	/**
	 * 学生考试过程操作
	 */
	public static final int STUDENT_EXAM_STATUS_NOT_STARTED=0;//未开始
	public static final int STUDENT_EXAM_STATUS_STARTED = 1;//已开始
	public static final int STUDENT_EXAM_STATUS_SUBMITTED = 2;//已提交
}
