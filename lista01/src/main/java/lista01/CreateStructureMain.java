package lista01;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStructureMain {

    public static void main(String[] args) {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:./test");
        ds.setUser("sa");
        ds.setPassword("sa");

        try {
            Connection conn = ds.getConnection();
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS PUBLIC.USER (username VARCHAR(255), password VARCHAR(255), PRIMARY KEY(username));");
            statement.execute("INSERT INTO PUBLIC.USER VALUES ('orlando.krause', '12345678');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
