package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:mysql://localhost/db2?allowPublicKeyRetrieval=true&useSSL=false",
                "root", "tipoparol");
    }
}
