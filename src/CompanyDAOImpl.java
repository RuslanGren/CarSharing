package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOImpl implements CompanyDAO {

    @Override
    public Company get(int id) {
        try (Connection connection = Database.getConnection()) {
            Company company = null;

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID, NAME FROM COMPANY WHERE ID = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int companyId = resultSet.getInt("ID");
                String companyName = resultSet.getString("NAME");

                company = new Company(companyId, companyName);
            }

            return company;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Company> getAll() {
        try (Connection connection = Database.getConnection()) {
            List<Company> list = new ArrayList<>();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT ID, NAME FROM COMPANY");
            while(resultSet.next()) {
                list.add(new Company(resultSet.getInt("ID"), resultSet.getString("NAME")));
            }

            return list;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }

    @Override
    public void insert(Company company) {
        try (Connection connection = Database.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO COMPANY(NAME) VALUES (?)");
            preparedStatement.setString(1, company.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}