package dio.jdbc_sample.persistence;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionUtil {
    public static Connection getConnection() throws SQLException {

        return null;

    }

}
