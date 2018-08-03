package com.njmsita.exam.base;

import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.model.UserModel;
import com.njmsita.exam.authentic.service.ebi.ResourceEbi;
import com.njmsita.exam.authentic.service.ebi.RoleEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.FieldErrorException;
import com.njmsita.exam.utils.json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    @Autowired
    private RoleEbi roleEbi;
    @Autowired
    private ResourceEbi resourceEbi;


    public static boolean CheckErrorFields(BindingResult bindingResult) throws FieldErrorException
    {
        FieldErrorException exception = null;

        if (bindingResult.hasErrors())
        {
            if (exception == null)
            {
                exception = new FieldErrorException();
            }

            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list)
            {
                exception.getErrorMessages().add(fieldError.getField() + " Error:" + fieldError.getDefaultMessage());
                //校验信息，key=属性名+Error
            }
            throw exception;
        }
        return false;
    }

    /**
     * 用户跳转JSP页面
     * <p>
     * 此方法不考虑权限控制
     *
     * @param folder  路径
     * @param jspName JSP名称(不加后缀)
     *
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

        if (loginMenu != null)
        {
            for (int i = 0; i < loginMenu.size(); i++)
            {
                delLoginMenuChilds(loginMenu.get(i));
                //过滤type==1，即资源为功能，不是菜单，不该显示
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
            Set<TresourceVo> set = tresourceVo.getChilds();
            Iterator<TresourceVo> iterator = set.iterator();
            while (iterator.hasNext())
            {
                TresourceVo tresourceVo1 = iterator.next();
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
