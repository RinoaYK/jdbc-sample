package dio.jdbc_sample.persistence;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionUtil {
    public static void getConnection() throws SQLException {

        DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc-sample", "root", "RNn8ybK7a7o5XQ6D");
    }
}
