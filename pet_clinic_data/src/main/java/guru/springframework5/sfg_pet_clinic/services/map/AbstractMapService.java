package guru.springframework5.sfg_pet_clinic.services.map;

import guru.springframework5.sfg_pet_clinic.model.BaseEntity;

import java.util.*;

//We know all our classes so far extend BaseEntity so we can say that T extends BaseEntity. Not needed but more specific
public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {//implements CrudService<T, ID> {
    protected Map<Long, T> map = new HashMap<>();

    public Set<T> findAll(){
//        return (Set<T>)map.entrySet();
        return new HashSet<>(map.values());
    }

    public T findById(ID id){
        return map.get(id);
    }

    public T save(T object){
        if (object != null){
            if (object.getId() == null){
                object.setId(getNextId());
            }
            map.put(object.getId(), object);
        }
        else { throw new RuntimeException("Object Cannot be null");}
//        return (save(getNextId(), object));
        return object;
    }

//    private T save(Long id, T object){
//        map.put(id, object);
//        return object;
//    }

    public void deleteById(ID id){
        map.remove(id);
    }

    public void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId(){
        Long id = null;
        try {
            id = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException e){
            id = 1L;
        }
        return id;
    }
}
