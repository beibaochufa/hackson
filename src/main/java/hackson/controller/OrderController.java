package hackson.controller;

import hackson.dao.AdminUserDao;
import hackson.dao.CommonUserDao;
import hackson.dao.OrderDao;
import hackson.model.AdminUserModel;
import hackson.model.CommonUserModel;
import hackson.model.OrderModel;
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
import java.util.List;
import java.util.Map;

/**
 * Created by whh on 2018/9/14.
 */

@Controller
@RequestMapping("/order")
public class OrderController {

    @RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isAdmin = Boolean.valueOf(request.getParameter("isAdmin"));
        if (isAdmin) {//管理员获取全部数据
            return OrderDao.getInstance().getAllOrders();
        } else {
            String userId = request.getParameter("userId");
            if (!StringUtil.isEmpty(userId)) {
                return OrderDao.getInstance().getAllOrdersByUserID(userId);
            }
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
    @ResponseBody
    public Map<String, Object> addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> result = new HashMap<>();

        String postNumber = request.getParameter("postNumber");
        String userName = request.getParameter("userName");
        String phone = request.getParameter("phone");
        long reachTime = System.currentTimeMillis();
        if (StringUtil.isEmpty(phone)) {
            result.put("success", 201);
            result.put("msg", "手机号为必填项，请重新填写");
            return result;
        }

        CommonUserModel commonUser = CommonUserDao.getInstance().getUserByPhone(phone);
        String userId = "";
        if (null != commonUser) {
            userId = commonUser.getUserId();
        }

        boolean isAddSuccess = OrderDao.getInstance().insertOrderInfo(postNumber, userName, userId, phone, reachTime);

        if (!isAddSuccess) {
            result.put("success", 202);
            result.put("msg", "用户信息添加失败");
        } else {
            String netRs = WechatUtil.sendText("你的快递已到，请携带工卡前往22楼前台领取。", CommonUserDao.getInstance().getUserByPhone(phone).getUserId());
            if (null != netRs && !"".equals(netRs)) {
                result.put("success", 200);
                result.put("msg", "发送消息成功");//列表页
            } else {
                result.put("success", 203);
                result.put("msg", "用户快递信息添加成功，但发送通知消息失败");
            }
        }
        return result;
    }

    @RequestMapping(value = "/send_message")
    @ResponseBody
    public Map<String, Object> sendMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        String phone = request.getParameter("phone");
        if (StringUtil.isEmpty(phone)) {
            result.put("success", 201);
            result.put("msg", "手机号为必填项，请重新填写");
            return result;
        }
        String netRs = WechatUtil.sendText("你的快递已到，请尽快来前台领取。", CommonUserDao.getInstance().getUserByPhone(phone).getUserId());
        if (null != netRs && !"".equals(netRs)) {
            result.put("success", 200);
            result.put("msg", "发送消息成功");//列表页
        } else {
            result.put("success", 203);
            result.put("msg", "发送通知消息失败，可能手机号填写错误");
        }
        return result;
    }

    @RequestMapping(value = "/handle_accepted")
    @ResponseBody
    public Map<String, Object> handleAccepted(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        String orderId = request.getParameter("orderId");
        int operation = Integer.valueOf(request.getParameter("operation"));
        if (StringUtil.isEmpty(orderId)) {
            result.put("success", 201);
            result.put("msg", "出错了");
            return result;
        }
        boolean isHandleSuccess = OrderDao.getInstance().updateOrderInfoByOrderID(orderId, operation);
        if (!isHandleSuccess) {
            result.put("success", 202);
            result.put("msg", "邮件状态更新失败");
        } else {
            result.put("success", 200);
            result.put("msg", "邮件状态更新成功");
        }
        return result;

    }

    public static void main(String[] args) {
        List<Map<String, Object>> ll = OrderDao.getInstance().getAllOrdersByUserID("WangHuanHuan");
        System.out.println(ll.toString());
    }
}
