package dbservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by SERGEY on 17.05.2017.
 */
public class DBService {
    private final Connection connection;
    public DBService() {
        connection = getPGConnection();
    }

    private Connection getPGConnection(){


        try {
            DriverManager.registerDriver((java.sql.Driver) Class.forName("org.postgresql.Driver").newInstance());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
             System.out.println("InstantiationException");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
            e.printStackTrace();
        }

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5433/intra", "intrauser",
                    "qwerty");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;

        }
        return connection;
    }
}
