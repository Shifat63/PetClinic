package PetClinicData.Service.Map;

import PetClinicData.Model.BaseEntity;

import java.util.*;

public class AbstractMapService<T extends BaseEntity, Id extends Long> {
    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(Id id){
        return map.get(id);
    }

    T save(T object){
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

    void deleteById(Id id){
        map.remove(id);
    }

    void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId(){
        Long nextId;
        try{
            nextId=Collections.max(map.keySet()) + 1;
        }catch(NoSuchElementException e){
            nextId=1L;
        }
        return nextId;
    }
}
