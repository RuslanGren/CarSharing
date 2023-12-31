package carsharing;

import java.sql.*;

public class Database {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./src/carsharing/db/carsharing";

    public static void init() throws SQLException, ClassNotFoundException {
        Connection connection = Database.getConnection();
        Class.forName(JDBC_DRIVER);
        Statement statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS COMPANY (" + // create a COMPANY table
                "ID INT PRIMARY KEY AUTO_INCREMENT," +
                "NAME VARCHAR(32) UNIQUE NOT NULL" +
                ");");

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS CAR (" + // create a CAR table
                "ID INT PRIMARY KEY AUTO_INCREMENT," +
                "NAME VARCHAR(32) UNIQUE NOT NULL," +
                "COMPANY_ID INT NOT NULL," +
                "CONSTRAINT fk_COMPANY_ID FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID)" +
                ");");

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS CUSTOMER (" + // create a CUSTOMER table
                "ID INT PRIMARY KEY AUTO_INCREMENT," +
                "NAME VARCHAR(32) UNIQUE NOT NULL," +
                "RENTED_CAR_ID INT," +
                "CONSTRAINT fk_RENTED_CAR_ID FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID)" +
                ");");

        statement.close();
        connection.close();
    }

    public static Connection getConnection() throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection(DB_URL);
        connection.setAutoCommit(true);

        return connection;
    }

}
