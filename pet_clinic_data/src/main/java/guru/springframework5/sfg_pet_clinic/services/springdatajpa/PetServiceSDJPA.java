package guru.springframework5.sfg_pet_clinic.services.springdatajpa;

import guru.springframework5.sfg_pet_clinic.model.Pet;
import guru.springframework5.sfg_pet_clinic.repositories.PetRepository;
import guru.springframework5.sfg_pet_clinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class PetServiceSDJPA extends AbstractServiceSDJPA<Pet, Long> implements PetService {
    private final PetRepository petRepository;

    public PetServiceSDJPA(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    CrudRepository<Pet, Long> getCrudRepository() {
        return petRepository;
    }
}
