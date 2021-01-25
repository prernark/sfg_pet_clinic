package guru.springframework5.sfg_pet_clinic.services.springdatajpa;

import guru.springframework5.sfg_pet_clinic.model.BaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractServiceSDJPA<T extends BaseEntity, ID extends Long> {
//    private final CrudRepository<T, ID> crudRepository;
//
//    public AbstractServiceSDJPA(CrudRepository<T, ID> crudRepository) {
//        this.crudRepository = crudRepository;
//    }

    abstract CrudRepository<T, Long> getCrudRepository();

    public Set<T> findAll() {
        Set<T> objectSet = new HashSet<>();
//        getCrudRepository().findAll().forEach(x->objectSet.add(x));
        getCrudRepository().findAll().forEach(objectSet::add);
        return objectSet;
    }

    public T findById(ID id) {
//        Optional<T> objectOp = crudRepository.findById(id);
//        if (objectOp.isPresent()){
//            return objectOp;
//        }
//        else{
//            return null;
//        }

        //Instead of above lines, we can write the same thing in one line as below
        return getCrudRepository().findById(id).orElse(null);
    }

    public T save(T object) {
        return getCrudRepository().save(object);
    }

   public void deleteById(ID id) {
        getCrudRepository().deleteById(id);
    }

    public void delete(T object) {
        getCrudRepository().delete(object);
    }

//    private Long getNextId() {
//        Long id = null;
//        try {
//            id = Collections.max(map.keySet()) + 1;
//        } catch (NoSuchElementException e) {
//            id = 1L;
//        }
//        return id;
//    }
}
