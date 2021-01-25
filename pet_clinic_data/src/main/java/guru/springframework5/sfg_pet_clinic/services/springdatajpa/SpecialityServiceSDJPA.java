package guru.springframework5.sfg_pet_clinic.services.springdatajpa;

import guru.springframework5.sfg_pet_clinic.model.Speciality;
import guru.springframework5.sfg_pet_clinic.repositories.SpecialityRepository;
import guru.springframework5.sfg_pet_clinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class SpecialityServiceSDJPA extends AbstractServiceSDJPA<Speciality, Long> implements SpecialityService {
    private final SpecialityRepository specialityRepository;

    public SpecialityServiceSDJPA(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    CrudRepository<Speciality, Long> getCrudRepository() {
        return specialityRepository;
    }
}
