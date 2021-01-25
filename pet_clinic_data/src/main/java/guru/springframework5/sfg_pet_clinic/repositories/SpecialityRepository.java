package guru.springframework5.sfg_pet_clinic.repositories;

import guru.springframework5.sfg_pet_clinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
