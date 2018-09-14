package hackson.dao;

import hackson.db.DbUtil;
import hackson.model.AdminUserModel;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by whh on 2018/9/13.
 */
public class AdminUserDao {

    private final static AdminUserDao instance = new AdminUserDao();
    public static final String dbName = "hackson";

    public static AdminUserDao getInstance() {
        return instance;
    }

    /**
     * 管理员登录
     *
     * @param uname
     * @param pword
     * @return
     */
    public AdminUserModel login(String uname, String pword) {
        AdminUserModel adminUser = null;
        if (StringUtils.isEmpty(uname) || StringUtils.isEmpty(pword)) {
            return adminUser;
        } else {
            String sql = "select * from admin_user where username=? and password=?";
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                con = DbUtil.instance().getCon(dbName);
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, uname);
                pstmt.setString(2, pword);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        // TODO: 2018/9/14 加解密  后续
                        System.out.println("login->resultset is ok");
                        adminUser = new AdminUserModel().populateFromResultSet(rs);
                    }
                } else {
                    System.out.println("login->resultset is null");
                }
            } catch (Exception e) {
                System.out.println("login->exception:" + e.getMessage());
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pstmt != null) {
                        rs.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (Exception e) {
                    System.out.println("login->finally->exception:" + e.getMessage());
                }
            }
        }
        return adminUser;
    }
}