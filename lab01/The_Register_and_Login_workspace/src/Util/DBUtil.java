package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by hans on 2018/3/20.
 */
public class DBUtil {

    /**
     * create the data base connection
     * @return
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
        Connection connection;
        String diverClass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/PCC_lab";
        String name = "root";
        String password = "qazwsxedc";
        Class.forName(diverClass);
        connection = DriverManager.getConnection(url,name,password);
//        System.out.println("connect ok");
        return connection;
    }

    /**
     * close the database connection
     * @param connection
     * @param preparedStatement
     * @param resultSet
     * @throws Exception
     */
    public void closeDBResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws Exception {
        if (resultSet != null) {
            try {
                resultSet.close();
            } finally {
                resultSet=null;
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } finally {
                preparedStatement=null;
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } finally {
                connection=null;
            }
        }
    }
}
