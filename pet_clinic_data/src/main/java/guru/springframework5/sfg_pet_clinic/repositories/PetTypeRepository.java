package guru.springframework5.sfg_pet_clinic.repositories;

import guru.springframework5.sfg_pet_clinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
