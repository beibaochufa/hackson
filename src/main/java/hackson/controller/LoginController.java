package hackson.controller;

/**
 * Created by whh on 2018/9/13.
 */

import hackson.dao.AdminUserDao;
import hackson.model.AdminUserModel;
import hackson.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "/login")
    @ResponseBody
    public Map<String, Object> userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> mapResult = new HashMap<String, Object>();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        request.setAttribute("userName", userName);
        request.setAttribute("password", password);

        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
            mapResult.put("success", 201);
            mapResult.put("msg", "用户名和密码不能为空");
        } else {
            AdminUserModel adminUser = AdminUserDao.getInstance().login(userName, password);
            if (adminUser == null) {
                mapResult.put("success", 201);
                mapResult.put("msg", userName + password + "sda用户名或密码不正确");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", adminUser);

                mapResult.put("success", 200);
                mapResult.put("msg", "main.jsp");
            }
        }
        return mapResult;
    }

    @RequestMapping(value = "/quit")
    @ResponseBody
    public Map<String, Object> userQuit(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> mapResult = new HashMap<String, Object>();

        try {
            request.getSession().removeAttribute("currentUser");
        } catch (Exception e) {
            mapResult.put("errMsg", "退出异常：" + e.getMessage());
        }
        return mapResult;
    }
}
