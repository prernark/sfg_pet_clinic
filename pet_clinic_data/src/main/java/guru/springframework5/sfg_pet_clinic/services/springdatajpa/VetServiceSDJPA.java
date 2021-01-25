package guru.springframework5.sfg_pet_clinic.services.springdatajpa;

import guru.springframework5.sfg_pet_clinic.model.Vet;
import guru.springframework5.sfg_pet_clinic.repositories.VetRepository;
import guru.springframework5.sfg_pet_clinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class VetServiceSDJPA extends AbstractServiceSDJPA<Vet, Long> implements VetService {
    private final VetRepository vetRepository;

    public VetServiceSDJPA(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    CrudRepository<Vet, Long> getCrudRepository() {
        return vetRepository;
    }
}
