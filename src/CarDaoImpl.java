package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDAO {
    @Override
    public Car get(int id) throws SQLException { // get by id
        Connection connection = Database.getConnection();
        Car car = null;

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID, NAME, COMPANY_ID FROM CAR WHERE ID = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int carId = resultSet.getInt("ID");
            String carName = resultSet.getString("NAME");
            int company_id = resultSet.getInt("COMPANY_ID");

            car = new Car(carId, carName, company_id);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return car;
    }

    @Override
    public List<Car> getAll() throws SQLException {
        List<Car> list = new ArrayList<>();
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT ID, NAME, COMPANY_ID FROM CAR");
        while(resultSet.next()) {
            list.add(new Car(resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getInt("COMPANY_ID")));
        }

        statement.close();
        connection.close();

        return list;
    }

    @Override
    public void insert(Car car) throws SQLException {
        Connection connection = Database.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CAR(NAME, COMPANY_ID) VALUES (?, ?)");
        preparedStatement.setString(1, car.getName());
        preparedStatement.setInt(2, car.getCompany_id());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public List<Car> getByCompanyId(int company_id) throws SQLException {
        List<Car> list = new ArrayList<>();
        Connection connection = Database.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID, NAME, COMPANY_ID FROM CAR WHERE COMPANY_ID = ?");
        preparedStatement.setInt(1, company_id);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            list.add(new Car(resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getInt("COMPANY_ID")));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return list;
    }

}