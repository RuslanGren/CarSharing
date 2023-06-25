package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDAO {
    @Override
    public Car get(int id) { // get by id
        try (Connection connection = Database.getConnection()) {
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

            return car;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Car> getAll() {
        try (Connection connection = Database.getConnection()) {
            List<Car> list = new ArrayList<>();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT ID, NAME, COMPANY_ID FROM CAR");
            while(resultSet.next()) {
                list.add(new Car(resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getInt("COMPANY_ID")));
            }

            return list;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }

    @Override
    public void insert(Car car) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CAR(NAME, COMPANY_ID) VALUES (?, ?)");

            preparedStatement.setString(1, car.getName());
            preparedStatement.setInt(2, car.getCompany_id());
            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    public List<Car> getByCompanyId(int company_id) {
        try (Connection connection = Database.getConnection()) {
            List<Car> list = new ArrayList<>();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID, NAME, COMPANY_ID FROM CAR WHERE COMPANY_ID = ?");
            preparedStatement.setInt(1, company_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                list.add(new Car(resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getInt("COMPANY_ID")));
            }

            return list;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Car> getNotRentedCars(int company_id) {
        try (Connection connection = Database.getConnection()) {
            List<Car> list = new ArrayList<>();

            String query = "SELECT ID, NAME, COMPANY_ID FROM CAR " +
                    "WHERE COMPANY_ID = ? AND ID NOT IN (SELECT RENTED_CAR_ID FROM CUSTOMER WHERE RENTED_CAR_ID IS NOT NULL)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, company_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                list.add(new Car(resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getInt("COMPANY_ID")));
            }

            return list;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }

}