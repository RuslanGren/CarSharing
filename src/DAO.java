package carsharing;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    T get(int id);

    List<T> getAll();

    void insert(T t);
}