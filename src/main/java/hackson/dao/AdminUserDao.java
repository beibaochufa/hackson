package hackson.dao;

import hackson.db.DbUtil;
import hackson.usermodel.AdminUserModel;
import hackson.utils.EncryptUtils;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            String sql = "select uid,username,password from admin_user where username=? and password=?";
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


    /**
     * 获取所有后台管理人员列表
     *
     * @return
     */
    public List<Map<String, Object>> getAdminList() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        String sql = "select au.uid, au.username, au.role_id, ar.role_name, au.reg_time, au.login_time, au.isvalid, au.createUid from admin_user au, admin_role ar where au.role_id = ar.role_id";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DbUtil.instance().getCon(dbName);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("uid", rs.getInt("uid"));
                    map.put("username", rs.getString("username"));
                    map.put("role_id", rs.getInt("role_id"));
                    map.put("role_name", rs.getString("role_name"));
                    map.put("reg_time", rs.getInt("reg_time"));
                    map.put("login_time", rs.getInt("login_time"));
                    map.put("isvalid", rs.getBoolean("isvalid"));
                    map.put("createUid", rs.getInt("createUid"));

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

    /**
     * 获取所有后台管理人员姓名列表
     *
     * @return
     */
    public List<Map<String, Object>> getAdminNameList() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        String sql = "select au.uid, au.username from admin_user au";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DbUtil.instance().getCon(dbName);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("uid", rs.getInt("uid"));
                    map.put("username", rs.getString("username"));

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

    /**
     * 获取所有后台管理人员列表
     *
     * @return
     */
    public AdminUserModel getAdminInfo(int uid) {
        AdminUserModel aUserModel = null;

        String sql = "select uid,username,password,role_id,reg_time,login_time,isvalid,createUid from admin_user where uid=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DbUtil.instance().getCon(dbName);
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, uid);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    aUserModel = new AdminUserModel().populateFromResultSet(rs);
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

        return aUserModel;
    }

    /**
     * 设置用户是否有效
     *
     * @param uid
     * @param isvalid ＝false无效 ＝true有效
     * @return
     */
    public int setIsValid(int uid, boolean isvalid) {
        int ires = 0;
        String sql = "update admin_user set isvalid=? where uid=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbUtil.instance().getCon(dbName);
            pstmt = con.prepareStatement(sql);
            pstmt.setBoolean(1, isvalid);
            pstmt.setInt(2, uid);
            ires = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
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
        return ires;
    }

    /**
     * 修改密码
     */
    public int setPassword(int uid, String password) {
        int ires = 0;
        String sql = "update admin_user set password=? where uid=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        String pwdMd5 = EncryptUtils.md5Encrypt(password);
        try {
            con = DbUtil.instance().getCon(dbName);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pwdMd5);
            pstmt.setInt(2, uid);
            ires = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
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
        return ires;
    }

    /**
     * 修改用户名
     */
    public int setUsername(int uid, String username) {
        int ires = 0;
        String sql = "update admin_user set username=? where uid=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DbUtil.instance().getCon(dbName);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setInt(2, uid);
            ires = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
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
        return ires;
    }
}