package lt.verbus.repository;

import java.util.List;

public interface Repository<T> {

    List<T> findAll();

    T findById(int id);

    void save(T t);

    void delete(T t);
}
