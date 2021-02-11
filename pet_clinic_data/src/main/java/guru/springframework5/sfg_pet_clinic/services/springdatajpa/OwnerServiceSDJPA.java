package guru.springframework5.sfg_pet_clinic.services.springdatajpa;

import guru.springframework5.sfg_pet_clinic.model.Owner;
import guru.springframework5.sfg_pet_clinic.repositories.OwnerRepository;
import guru.springframework5.sfg_pet_clinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springdatajpa")
//@Primary
public class OwnerServiceSDJPA extends AbstractServiceSDJPA<Owner, Long> implements OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerServiceSDJPA(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public CrudRepository<Owner, Long> getCrudRepository() {
        return ownerRepository;
    }

    @Override
    //we will implement this in the OwnerRepository
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }


//
//    @Override
//    public Set<Owner> findAll() {
//        Set<Owner> ownerSet = new HashSet<>();
//        ownerRepository.findAll().forEach(ownerSet::add);
//        return ownerSet;
//    }
//
//    @Override
//    public Owner findById(Long aLong) {
//        return ownerRepository.findById(aLong).orElse(null);
//    }
//
//    @Override
//    public Owner save(Owner object) {
//        return ownerRepository.save(object);
//    }
//
//    @Override
//    public void deleteById(Long aLong) {
//        ownerRepository.deleteById(aLong);
//    }
//
//    @Override
//    public void delete(Owner object) {
//        ownerRepository.delete(object);
//    }
}
