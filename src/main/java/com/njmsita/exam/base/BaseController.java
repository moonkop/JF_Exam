package com.njmsita.exam.base;

public class BaseController{
	public static final String LIST = "list";
	public static final String TO_LIST = "toList";
	public static final String INPUT = "input";
	
	public Integer pageNum = 1;
	public Integer pageCount = 10;
	public Integer maxPageNum;
	public Integer dataTotal;

	protected void setDataTotal(int dataTotal){
		this.dataTotal = dataTotal ;
		maxPageNum = (dataTotal + pageCount -1) / pageCount;
	}
	
	protected EmpModel getLogin(){
		return (EmpModel) getSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
	}
}
