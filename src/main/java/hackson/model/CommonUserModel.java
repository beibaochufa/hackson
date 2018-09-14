package hackson.model;

import hackson.inte.PopulateTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by whh on 2018/9/14.
 */
public class CommonUserModel implements PopulateTemplate<CommonUserModel> {
    private String userId;//必须
    private String username;
    private String phone;//必须

    public CommonUserModel(String userId, String username, String phone) {
        this.userId = userId;
        this.username = username;
        this.phone = phone;
    }

    public CommonUserModel() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public CommonUserModel populateFromResultSet(ResultSet rs) {
        try {
            this.userId = rs.getString("user_id");
            this.username = rs.getString("user_name");
            this.phone = rs.getString("phone");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
}
