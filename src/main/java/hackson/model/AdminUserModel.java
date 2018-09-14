package hackson.model;

import hackson.inte.PopulateTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by whh on 2018/9/13.
 */
public class AdminUserModel implements PopulateTemplate<AdminUserModel> {
    private int uid;
    private String username;
    private String password;

    public int getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public AdminUserModel populateFromResultSet(ResultSet rs) {
        try {
            this.uid = rs.getInt("uid");
            this.username = rs.getString("username");
            this.password = rs.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
}
