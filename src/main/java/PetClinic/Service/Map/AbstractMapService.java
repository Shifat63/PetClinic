package PetClinic.Service.Map;

import PetClinic.Model.BaseEntity;

import java.util.*;

public class AbstractMapService<T extends BaseEntity, Id extends Long> {
    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll() throws Exception{
        return new HashSet<>(map.values());
    }

    T findById(Id id) throws Exception{
        return map.get(id);
    }

    T save(T object) throws Exception{
        if(object!=null)
        {
            if(object.getId()==null)
            {
                object.setId(getNextId());
            }
            map.put(object.getId(), object);
        }
        else
        {
            throw new RuntimeException("Object can not be null");
        }
        return object;
    }

    void deleteById(Id id) throws Exception{
        map.remove(id);
    }

    void delete(T object) throws Exception{
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId() throws Exception{
        Long nextId;
        try{
            nextId=Collections.max(map.keySet()) + 1;
        }catch(NoSuchElementException e){
            nextId=1L;
        }
        return nextId;
    }
}
