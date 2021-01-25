package guru.springframework5.sfg_pet_clinic.services.springdatajpa;

import guru.springframework5.sfg_pet_clinic.model.PetType;
import guru.springframework5.sfg_pet_clinic.repositories.PetTypeRepository;
import guru.springframework5.sfg_pet_clinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class PetTypeServiceSDJPA extends AbstractServiceSDJPA<PetType, Long> implements PetTypeService {
    private final PetTypeRepository petTypeRepository;

    public PetTypeServiceSDJPA(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    CrudRepository<PetType, Long> getCrudRepository() {
        return petTypeRepository;
    }
}
