package guru.springframework5.sfg_pet_clinic.services;

import guru.springframework5.sfg_pet_clinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
