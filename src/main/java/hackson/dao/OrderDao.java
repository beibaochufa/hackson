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

    //录入邮件信息
    public boolean insertOrderInfo(String orderNumber, String userName, String phone, long reachTime) {
        String sql = "insert into order_info(order_number,user_name,phone,reach_time) value (?,?,?,?)";
        int ires = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DbUtil.instance().getCon(dbName);
            ps = con.prepareStatement(sql);
            ps.setString(1, orderNumber);
            ps.setString(2, userName);
            ps.setString(3, phone);
            ps.setLong(4, reachTime);
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
        String sql = "select * from order_info order by reach_time Desc";
        return getAllOrdersBySql(sql);
    }

    //用户：按照名字查询快递
    public List<Map<String, Object>> getAllOrdersByUserName(String userName) {
        String sql = "select * from order_info where user_name = ? order by reach_time  Desc";
        return getAllOrdersBySql(sql, userName);
    }

    //用户：按照名字查询快递
    public List<Map<String, Object>> getAllOrdersByPhone(String phone) {
        String sql = "select * from order_info where phone = ? order by reach_time  Desc";
        return getAllOrdersBySql(sql, phone);
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
            for (int i = 1; i <= condi.length; i++) {
                pstmt.setString(i, condi[i]);
            }

            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("orderNumber", rs.getInt("order_number"));
                    map.put("userName", rs.getString("user_name"));
                    map.put("phone", rs.getString("phone"));
                    map.put("reachTime", timeFormat.format(new Date(rs.getLong("reach_time"))));
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

    public String getFormatterTime(long time) {
        Date d = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String data = formatter.format(d);
        return data;
    }
}
