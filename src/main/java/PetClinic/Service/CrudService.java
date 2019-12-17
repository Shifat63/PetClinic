package PetClinic.Service;

import java.util.Set;

public interface CrudService<T, Id> {
    Set<T> findAll() throws Exception;
    T findById(Id id) throws Exception;
    T save(T object) throws Exception;
    void delete(T object) throws Exception;
    void deleteById(Id id) throws Exception;
}
