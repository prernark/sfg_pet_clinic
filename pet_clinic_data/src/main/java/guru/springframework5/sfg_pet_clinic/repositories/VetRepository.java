package guru.springframework5.sfg_pet_clinic.repositories;

import guru.springframework5.sfg_pet_clinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
