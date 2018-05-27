package com.njmsita.exam.base;

import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.model.UserModel;
import com.njmsita.exam.authentic.service.ebi.ResourceEbi;
import com.njmsita.exam.authentic.service.ebi.RoleEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("base")
public class BaseController{

	@Autowired
	private RoleEbi roleEbi;
	@Autowired
	private ResourceEbi resourceEbi;

	//默认页面容量为 10
	public Integer pageCount;

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

	/**
	 * 获取登陆用户的菜单
	 * @param request
	 */
	public void getLoginMenu(HttpServletRequest request){

		UserModel login= (UserModel) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
		TroleVo troleVo=roleEbi.get(login.getUserRole());
		List<TresourceVo> loginMenu=resourceEbi.getMenuByRole(troleVo.getId());

		for (TresourceVo menu : loginMenu)
		{
			Set<TresourceVo> childs=new HashSet<>();
			Set<TresourceVo> oldChilds=menu.getChilds();
			if(oldChilds.size()>0){
				for (TresourceVo child : oldChilds)
				{
					if(child.getResourcetype().getId().equals(SysConsts.RESOURCE_TYPE_MENU_ID)){
						childs.add(child);
					}
				}
				menu.setChilds(childs);
			}
		}
		request.getSession().setAttribute("loginMenu",loginMenu);
	}
}
