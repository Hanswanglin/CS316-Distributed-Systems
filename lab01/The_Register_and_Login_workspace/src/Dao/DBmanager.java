package Dao;

import Util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by hans on 2018/3/20.
 */
public class DBmanager {

    DBUtil dbutil;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public DBmanager() {
        this.dbutil = new DBUtil();
    }


    // after check there is not exist the same user, we create one and insert into DB
    public int createNewUser(String username, String password) throws Exception {
        int result = 0;
        connection = dbutil.getConnection();
        String sql = "insert into user_info (username, password) values (?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        result = preparedStatement.executeUpdate();
        dbutil.closeDBResource(connection,preparedStatement,resultSet);
        return result;  // insert成功后，即成功插入一行数据，返回值为1；插入失败得到的result值是0
    }


    // 给我传一个username和password，我去数据库select一下，返回没有就是没有这个用户，返回
    public int login(String username, String password) throws Exception {
        int result = 0;
        connection = dbutil.getConnection();
        String sql = "select count(*) from user_info where username=? and password=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result = resultSet.getInt(1);
        }
        return result;
    }
}
