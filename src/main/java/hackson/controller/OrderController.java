package hackson.controller;

import hackson.dao.AdminUserDao;
import hackson.dao.CommonUserDao;
import hackson.dao.OrderDao;
import hackson.model.AdminUserModel;
import hackson.model.CommonUserModel;
import hackson.model.OrderModel;
import hackson.utils.StringUtil;
import hackson.utils.WechatUtil;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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
import java.util.List;
import java.util.Map;

/**
 * Created by whh on 2018/9/14.
 */

@Controller
@RequestMapping("order")
public class OrderController {

    @RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isAdmin = Boolean.valueOf(request.getParameter("isAdmin"));
        if (isAdmin) {//管理员获取全部数据
            return OrderDao.getInstance().getAllOrders();
        } else {
            String userName = request.getParameter("userName");
            if (!StringUtil.isEmpty(userName))
                return OrderDao.getInstance().getAllOrdersByUserName(userName);

            String phone = request.getParameter("phone");
            if (!StringUtil.isEmpty(phone))
                return OrderDao.getInstance().getAllOrdersByPhone(phone);
            return null;
        }
    }


    @RequestMapping(value = "/add_order", method = RequestMethod.POST)
    public Map<String, Object> addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> result = new HashMap<>();

        String orderNumber = request.getParameter("postnumber");
        String userName = request.getParameter("username");
        String phone = request.getParameter("phone");
        long reachTime = System.currentTimeMillis();
        if (StringUtil.isEmpty(phone)) {
            result.put("success", 201);
            result.put("msg", "手机号为必填项，请重新填写");
            return result;
        }


        boolean isAddSuccess = OrderDao.getInstance().insertOrderInfo(orderNumber, userName, phone, reachTime);

        if (!isAddSuccess) {
            result.put("success", 202);
            result.put("msg", "用户信息添加失败");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", new OrderModel(orderNumber, userName, phone));
            result.put("success", 200);
            result.put("msg", "录入成功");//列表页

            WechatUtil.sendText("你的快递已到，请携带工卡前往邮件中心领取。", CommonUserDao.getInstance().getUserByPhone(phone).getUserId());
            System.out.print("before");
        }
        return result;
    }
}
