package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public Customer get(int id) {
        try (Connection connection = Database.getConnection()) {
            Customer customer = null;

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID, NAME, RENTED_CAR_ID" +
                    "FROM CUSTOMER WHERE ID = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int customerId = resultSet.getInt("ID");
                String customerName = resultSet.getString("NAME");
                Integer rented_car_id = resultSet.getInt("RENTED_CAR_ID");

                customer = new Customer(customerId, customerName, rented_car_id);
            }

            return customer;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Customer> getAll() {
        try (Connection connection = Database.getConnection()) {
            List<Customer> list = new ArrayList<>();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT ID, NAME, RENTED_CAR_ID FROM CUSTOMER");
            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                Integer rented_car_id = resultSet.getInt("RENTED_CAR_ID");
                if (resultSet.wasNull()) {
                    rented_car_id = null;
                }
                list.add(new Customer(id, name, rented_car_id));
            }

            return list;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }

    @Override
    public void insert(Customer customer) {
        try (Connection connection = Database.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CUSTOMER(NAME) VALUES (?)");
            preparedStatement.setString(1, customer.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void returnCar(int id) {
        try (Connection connection = Database.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE CUSTOMER SET RENTED_CAR_ID = NULL WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void rentCar(Customer customer) {
        try (Connection connection = Database.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE CUSTOMER SET RENTED_CAR_ID = ? WHERE ID = ?");
            preparedStatement.setInt(1, customer.getRented_car_id());
            preparedStatement.setInt(2, customer.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}