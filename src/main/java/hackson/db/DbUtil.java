package hackson.db;


import hackson.dao.AdminUserDao;
import hackson.usermodel.AdminUserModel;

import java.sql.*;

public class DbUtil {
    private String dbUrl;
    private String dbUserName;
    private String dbPassword;

    private static DbUtil instance = null;
    private static Object lock = new Object();

    // TODO: 2018/9/13 后续可以放在配置文件中
    private DbUtil() {
        dbUrl = "jdbc:mysql://127.0.0.1:3306/";//后面是数据库名
        dbUserName = "root";
        dbPassword = "8JiXuij^iPtdalC8";
    }

    public static DbUtil instance() {
        synchronized (lock) {
            if (instance == null) {
                instance = new DbUtil();
            }
        }
        return instance;
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取链接
     *
     * @return
     * @throws Exception
     */
    public Connection getCon(String dbName) throws Exception {
        return DriverManager.getConnection(dbUrl + dbName, dbUserName, dbPassword);
    }

    /**
     * 关闭连接
     *
     * @param con
     * @throws Exception
     */
    public void closeCon(Connection con) throws Exception {
        if (con != null) {
            con.close();
        }
    }

    // TODO: 2018/9/13 test
    public static void main(String[] args) {
        AdminUserDao adminUserDao = new AdminUserDao();
        AdminUserModel adminUser = adminUserDao.login("admin","123456");
        if (adminUser == null){
            System.out.println("login->resultset is fail");
        }else {
            System.out.println("login->resultset is ok");
        }
    }
}
