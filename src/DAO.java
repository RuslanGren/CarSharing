package carsharing;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    T get(int id) throws SQLException;

    List<T> getAll() throws SQLException;

    void insert(T t) throws SQLException;
}