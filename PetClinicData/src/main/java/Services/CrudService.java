package Services;

import java.util.Set;

public interface CrudService<T, Id> {
    Set<T> finaAll();
    T findById(Id id);
    T save(T object);
    void delete(T object);
    void deleteById(Id id);
}
