package carsharing;

import java.sql.SQLException;

public interface CustomerDAO extends DAO<Customer> {
    void returnCar(int id);
    void rentCar(Customer customer);
}
