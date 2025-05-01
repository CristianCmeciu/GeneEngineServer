package geneEngine.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private String jdbcUrl=null;
    private Properties properties;

    public JdbcUtils(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public JdbcUtils(Properties properties) {
        this.properties = properties;
    }

    private Connection instance=null;

    private Connection getPropsConnection() {
        String url = properties.getProperty("jdbc.url");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return conn;
    }

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

    public Connection getLocalConnection() {
        try {
            if (instance==null || instance.isClosed())
                instance=getPropsConnection();
        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        return instance;
    }
}
