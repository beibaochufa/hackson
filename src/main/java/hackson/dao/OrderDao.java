package hackson.dao;

import hackson.db.DbUtil;
import hackson.model.CommonUserModel;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by whh on 2018/9/14.
 */
public class OrderDao {
    private final static OrderDao instance = new OrderDao();
    public static final String dbName = "hackson";
    public static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static OrderDao getInstance() {
        return instance;
    }

    //根据操作更新邮件的操作信息
    public boolean insertOrderInfo(String postNumber, String userName, String userId, String phone, long reachTime) {
        //刚开始 status 都为 0
        String sql = "insert into order_info(post_number,user_name,user_id,phone,reach_time,status) value (?,?,?,?,?,0)";
        int ires = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DbUtil.instance().getCon(dbName);
            ps = con.prepareStatement(sql);
            ps.setString(1, postNumber);
            ps.setString(2, userName);
            ps.setString(3, userId);
            ps.setString(4, phone);
            ps.setLong(5, reachTime);
            ires = ps.executeUpdate();
            if (ires != 1) {
                System.out.println("insertOrderInfo->插入insertOrderInfo失败");
                return false;
            }
        } catch (Exception e) {
            System.out.println("insertOrderInfo->Exception：" + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e2) {
                System.out.println("insertOrderInfo->finally->Exception：" + e2.getMessage());
            }
        }
        return true;
    }

    //管理员权限：查询所有的快递信息，按照到达时间降序排列
    public List<Map<String, Object>> getAllOrders() {
        //只显示未取件的快递
        String sql = "select * from order_info where status = 0 order by reach_time Desc";
        return getAllOrdersBySql(sql);
    }

    //用户：按照名字查询快递
    public List<Map<String, Object>> getAllOrdersByUserName(String userName) {
        //只显示未取件的快递
        String sql = "select * from order_info where user_id = ? order by reach_time  Desc";
        return getAllOrdersBySql(sql, userName);
    }

    //用户：按照手机号查询快递
    public List<Map<String, Object>> getAllOrdersByPhone(String phone) {
        String sql = "select * from order_info where phone = ? order by reach_time  Desc";
        return getAllOrdersBySql(sql, phone);
    }

    //用户：按照userId查询快递
    public List<Map<String, Object>> getAllOrdersByUserID(String userId) {
        String sql = "select * from order_info where user_id = ? order by reach_time  Desc";
        return getAllOrdersBySql(sql, userId);
    }

    //按照 sql 语句查询
    public List<Map<String, Object>> getAllOrdersBySql(String sql, String... condi) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DbUtil.instance().getCon(dbName);
            pstmt = con.prepareStatement(sql);
            for (int i = 0; i < condi.length; i++) {
                pstmt.setString(i + 1, condi[i]);
            }

            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("orderId", rs.getInt("order_id"));
                    map.put("postNumber", rs.getString("post_number"));
                    map.put("userName", rs.getString("user_name"));
                    map.put("userId", rs.getString("user_id"));
                    map.put("phone", rs.getString("phone"));
                    map.put("reachTime", timeFormat.format(new Date(rs.getLong("reach_time"))));
                    map.put("status", rs.getInt("status"));//0 未领取，1：已领取
                    list.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return list;
    }

    //更新邮件信息
    public boolean updateOrderInfoByOrderID(String orderId, int operation) {
        String sql = "UPDATE order_info SET status = ? WHERE order_id = ?";
        int ires = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DbUtil.instance().getCon(dbName);
            ps = con.prepareStatement(sql);
            ps.setInt(1, operation);
            ps.setString(2, orderId);
            ires = ps.executeUpdate();
            if (ires != 1) {
                System.out.println("updateOrderInfoByOrderID->updateOrderInfoByOrderID 失败");
                return false;
            }
        } catch (Exception e) {
            System.out.println("updateOrderInfoByOrderID->Exception：" + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e2) {
            }
        }
        return true;
    }

    public String getFormatterTime(long time) {
        Date d = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String data = formatter.format(d);
        return data;
    }
}
