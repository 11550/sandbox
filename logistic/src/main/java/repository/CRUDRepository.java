package repository;

import java.util.List;

public interface CRUDRepository<T> {
    List<T> findAll() throws Exception;

    T findById(int id) throws Exception;

    T findByName(String name) throws Exception;

    int insert(T entity) throws Exception;

    int update(T entity) throws Exception;

    int delete(int id) throws Exception;
}
