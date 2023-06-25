package carsharing;

import java.sql.SQLException;
import java.util.List;

public interface CarDAO extends DAO<Car> {
    List<Car> getByCompanyId(int company_id);

    List<Car> getNotRentedCars(int company_id);
}
