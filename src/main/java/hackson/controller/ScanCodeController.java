package hackson.controller;

import hackson.dao.CommonUserDao;
import hackson.model.CommonUserModel;
import hackson.utils.StringUtil;
import hackson.utils.WechatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whh on 2018/9/14.
 * add_User 主要做这几件事情
 * 1. 授权登录后，请求 token，拉取用户信息
 * 2. 检查数据库中的数据，看是否有此用户信息
 */

@Controller
@RequestMapping("scan_code")
public class ScanCodeController {
    CommonUserDao userDao = new CommonUserDao();

    @RequestMapping(value = "/jump", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> jump(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> mapResult = new HashMap<>();

        //用户授权后会重定向到此页面，并携带 code 和 status
        String code = request.getParameter("code");
        String status = request.getParameter("status");
        String accessToken = WechatUtil.getAccessToken();
        if (StringUtil.isEmpty(accessToken) || StringUtil.isEmpty(code)) {
            mapResult.put("fail", 201);
            mapResult.put("msg", "获取 Token 失败，请刷新页面重试");
            return mapResult;
        }

        String userId = WechatUtil.getUserId(accessToken, code);
        if (StringUtil.isEmpty(userId)) {
            mapResult.put("fail", 202);
            mapResult.put("msg", "获取用户信息失败");
            return mapResult;
        }

        CommonUserModel user = userDao.getUserById(userId);
        if (null == user) {
            request.setAttribute("userId", userId);
            mapResult.put("userId", userId);//用于之后存储 uerId
            mapResult.put("success", 200);
            mapResult.put("msg", "cominfo.jsp");//完善信息页
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);

            mapResult.put("success", 200);
            mapResult.put("msg", "main.jsp");//列表页
        }

        return mapResult;
    }

    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public Map<String, Object> addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> result = new HashMap<>();

        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String phone = request.getParameter("phone");
        if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(userName) || StringUtil.isEmpty(phone)) {
            result.put("success", 201);
            result.put("msg", "信息不完整,无法提交，请重试！");
            return result;
        }

        boolean isAddSuccess = CommonUserDao.getInstance().insertUserInfo(userId, userName, phone);

        if (!isAddSuccess) {
            result.put("success", 202);
            result.put("msg", "用户信息添加失败");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", new CommonUserModel(userId, userName, phone));
            result.put("success", 200);
            result.put("msg", "main.jsp");//列表页
        }
        return result;
    }

    @RequestMapping(value = "/update_user", method = RequestMethod.POST)
    public Map<String, Object> updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> result = new HashMap<>();

        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String phone = request.getParameter("phone");
        if (StringUtil.isEmpty(userId)) {
            result.put("success", 201);
            result.put("msg", "信息不完整,无法修改，请退出重试！");
            return result;
        }

        boolean isAddSuccess = CommonUserDao.getInstance().updateUserInfo(userName, phone, userId);

        if (!isAddSuccess) {
            result.put("success", 202);
            result.put("msg", "用户信息更新失败");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", new CommonUserModel(userId, userName, phone));
            result.put("success", 200);
            result.put("msg", "main.jsp");//列表页
        }
        return result;
    }


}