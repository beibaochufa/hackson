package hackson.dao;

import hackson.db.DbUtil;
import hackson.model.AdminUserModel;
import hackson.model.CommonUserModel;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by whh on 2018/9/14.
 */
public class CommonUserDao {
    private final static CommonUserDao instance = new CommonUserDao();
    public static final String dbName = "hackson";

    public static CommonUserDao getInstance() {
        return instance;
    }

    public CommonUserModel getUserById(String userId) {
        CommonUserModel commonUser = null;
        if (StringUtils.isEmpty(userId)) {
            return commonUser;
        } else {
            String sql = "select * from user_info where user_id=? ";
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                con = DbUtil.instance().getCon(dbName);
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, userId);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        commonUser = new CommonUserModel().populateFromResultSet(rs);
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
        return commonUser;
    }

    public CommonUserModel getUserByPhone(String phone) {
        CommonUserModel commonUser = null;
        if (StringUtils.isEmpty(phone)) {
            return commonUser;
        } else {
            String sql = "select * from user_info where phone=? ";
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                con = DbUtil.instance().getCon(dbName);
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, phone);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        commonUser = new CommonUserModel().populateFromResultSet(rs);
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
        return commonUser;
    }

    public boolean insertUserInfo(String userId, String username, String phone) {
        String sql = "insert into user_info(user_id,user_name,phone)value(?,?,?)";
        int ires = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbUtil.instance().getCon(dbName);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, username);
            pstmt.setString(3, phone);
            ires = pstmt.executeUpdate();
            if (ires != 1) {
                System.out.println("insertUserInfo->插入insertUserInfo失败");
                return false;
            }
        } catch (Exception e) {
            System.out.println("insertUserInfo->Exception：" + e.getMessage());
            return false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e2) {
                System.out.println("insertUserInfo->finally->Exception：" + e2.getMessage());
            }
        }
        return true;
    }

    public boolean updateUserInfo(String username, String phone, String userId) {

        String sql = "update user_info set user_name = ?, phone = ? where user_id = ?";
        int ires = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbUtil.instance().getCon(dbName);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, phone);
            pstmt.setString(3, userId);
            ires = pstmt.executeUpdate();
            if (ires != 1) {
                System.out.println("updateUserInfo->updateUserInfo");
                return false;
            }
        } catch (Exception e) {
            System.out.println("updateUserInfo->Exception：" + e.getMessage());
            return false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e2) {
                System.out.println("updateUserInfo->finally->Exception：" + e2.getMessage());
            }
        }
        return true;
    }
}
