package geneEngine.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private final String jdbcUrl;

    public JdbcUtils(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    private Connection instance=null;

    private Connection getNewConnection(){
        Connection con=null;
        try {
            con=DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            System.out.println("Error getting connection "+e);
        }
        return con;
    }

    public Connection getConnection(){
        try {
            if (instance==null || instance.isClosed())
                instance=getNewConnection();

        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        return instance;
    }
}
