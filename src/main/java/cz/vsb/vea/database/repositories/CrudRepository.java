package cz.vsb.vea.database.repositories;

import java.util.List;

public interface CrudRepository<T> {

    List<T> getAll();
    T find(long id);
    void update(long id, T entity);
    void insert(T entity);
    boolean delete(long id);
}
