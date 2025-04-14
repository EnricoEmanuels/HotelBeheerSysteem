package hotel.systeem.interfaces;

import java.util.List;

public interface DAO<T> {
    void save(T entity);
    List<T> findAll();
    void deleteById(Integer id);
    T findById(Integer id);
    void update(T entity);
}
