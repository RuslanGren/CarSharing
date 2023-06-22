import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CarSharingDB {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static String DB_URL;

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public CarSharingDB(String dbName) {
        try {
            DB_URL = "jdbc:h2:./src/carsharing/db/" + dbName;
            Class.forName(JDBC_DRIVER);
            open();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS COMPANY (" + // create a company table
                    "ID INT PRIMARY KEY AUTO_INCREMENT," +
                    "NAME VARCHAR(32) UNIQUE NOT NULL" +
                    ");");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS CAR (" + // create a car table
                    "ID INT PRIMARY KEY AUTO_INCREMENT," +
                    "NAME VARCHAR(32) UNIQUE NOT NULL," +
                    "COMPANY_ID INT NOT NULL," +
                    "CONSTRAINT fk_COMPANY_ID FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID)" +
                    ");");

            close();
        } catch (Exception e) { // Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    protected void addCompany(String name) { // add company to table
        try {
            open();
            preparedStatement = connection.prepareStatement("INSERT INTO COMPANY(NAME) VALUES(?);");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    protected void addCar(String name, int companyId) { // add car to table
        try {
            open();
            preparedStatement = connection.prepareStatement("INSERT INTO CAR(NAME, COMPANY_ID) VALUES(?, ?);");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, companyId);
            preparedStatement.executeUpdate();
            close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    protected HashMap<String, String> getMapCompanies() { // get hashmap of companies
        HashMap<String, String> map = new HashMap<>();
        try {
            open();
            ResultSet rs = statement.executeQuery("SELECT * FROM COMPANY");
            while(rs.next()) {
                map.put(String.valueOf(rs.getInt("ID")), rs.getString("NAME"));
            }
            close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return map;
    }

    protected ArrayList<String> getListOfCars(int companyId) { // get list of cars by companyId
        ArrayList<String> list = new ArrayList<>();
        try {
            open();
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM CAR WHERE COMPANY_ID = %d;", companyId));
            while(rs.next()) {
                list.add(rs.getString("NAME"));
            }
            close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return list;
    }

    private void open() { // open the connection
        try {
            connection = DriverManager.getConnection(DB_URL);
            connection.setAutoCommit(true);
            statement = connection.createStatement();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private void close() { // close the connection
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) { /* Ignored */}
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) { /* Ignored */}
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) { /* Ignored */}
        }
    }

}