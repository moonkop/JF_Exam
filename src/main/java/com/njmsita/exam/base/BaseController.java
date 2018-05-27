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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("base")
public class BaseController
{

    //默认页面容量为 10
    public Integer pageCount;
    //最大页码数
    public Integer maxPageNum;
    //数据总量
    public Integer dataTotal;
    @Autowired
    private RoleEbi roleEbi;
    @Autowired
    private ResourceEbi resourceEbi;

    protected void setDataTotal(int dataTotal)
    {
        this.dataTotal = dataTotal;
        maxPageNum = (dataTotal + pageCount - 1) / pageCount;
    }

    /**
     * 用户跳转JSP页面
     * <p>
     * 此方法不考虑权限控制
     *
     * @param folder  路径
     * @param jspName JSP名称(不加后缀)
     * @return 指定JSP页面
     */
    @RequestMapping("/{folder}/{jspName}")
    public String redirectJsp(@PathVariable("folder") String folder, @PathVariable("jspName") String jspName)
    {
        return folder + "/" + jspName;
    }

    /**
     * 获取登陆用户的菜单
     *
     * @param request
     */
    public void getLoginMenu(HttpServletRequest request)
    {

        UserModel login = (UserModel) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        TroleVo troleVo = roleEbi.get(login.getUserRole());
        List<TresourceVo> loginMenu = resourceEbi.getMenuByRole(troleVo.getId());
        //过滤type==1，即资源为功能，不是菜单，不该显示
        if (loginMenu != null)
        {
            for (int i = 0; i < loginMenu.size(); i++)
            {
                delLoginMenuChilds(loginMenu.get(i));
                if (loginMenu.get(i).getResourcetype().getId().equals("1"))
                {
                    loginMenu.remove(loginMenu.get(i));
                }
            }
        }

        request.getSession().setAttribute("loginMenu", loginMenu);
    }

    public void delLoginMenuChilds(TresourceVo tresourceVo)
    {
        if (tresourceVo.getChilds() != null)
        {
            Set<TresourceVo> set= tresourceVo.getChilds();
            Iterator<TresourceVo> iterator=  set.iterator();
            while (iterator.hasNext())
            {
                TresourceVo tresourceVo1=iterator.next();
                if (tresourceVo1.getResourcetype().getId().equals("1"))
                {
                    iterator.remove();

                } else
                {

                    delLoginMenuChilds(tresourceVo1);
                }
            }
        }
    }

}
