package hackson.model;

import hackson.inte.PopulateTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by whh on 2018/9/14.
 */
public class OrderModel implements PopulateTemplate<OrderModel> {
    private String orderNumber;
    private String userName;
    private String phone;
    private long reachTime;//毫秒

    public OrderModel(String orderNumber, String userName, String phone) {
        this.orderNumber = orderNumber;
        this.userName = userName;
        this.phone = phone;
        this.reachTime = reachTime;
    }

    public OrderModel() {
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getReachTime() {
        return reachTime;
    }

    public void setReachTime(long reachTime) {
        this.reachTime = reachTime;
    }

    @Override
    public OrderModel populateFromResultSet(ResultSet rs) {
        try {
            this.orderNumber = rs.getString("order_number");
            this.userName = rs.getString("user_name");
            this.phone = rs.getString("phone");
            this.reachTime = rs.getLong("reach_time");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
}
