package com.njmsita.exam.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("base")
public class BaseController{
	public Integer pageNum = 1;
	public Integer pageCount = 10;
	public Integer maxPageNum;
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
