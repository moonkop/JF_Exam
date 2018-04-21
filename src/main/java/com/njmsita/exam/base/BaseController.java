package com.njmsita.exam.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("base")
public class BaseController{

	//默认页码为 1
	public Integer pageNum = 1;

	//默认页面容量为 10
	public Integer pageCount = 10;

	//最大页码数
	public Integer maxPageNum;

	//数据总量
	public Integer dataTotal;

	protected void setDataTotal(int dataTotal){
		this.dataTotal = dataTotal ;
		maxPageNum = (dataTotal + pageCount -1) / pageCount;
	}

	/**
	 * 用户跳转JSP页面
	 *
	 * 此方法不考虑权限控制
	 *
	 * @param folder
	 *            路径
	 * @param jspName
	 *            JSP名称(不加后缀)
	 * @return 指定JSP页面
	 */
	@RequestMapping("/{folder}/{jspName}")
	public String redirectJsp(@PathVariable("folder") String folder, @PathVariable("jspName") String jspName) {
		return folder + "/" + jspName;
	}
}
