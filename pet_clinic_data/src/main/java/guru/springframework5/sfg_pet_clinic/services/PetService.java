package guru.springframework5.sfg_pet_clinic.services;

import guru.springframework5.sfg_pet_clinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
    Pet findByLastName(String lastName);
}
