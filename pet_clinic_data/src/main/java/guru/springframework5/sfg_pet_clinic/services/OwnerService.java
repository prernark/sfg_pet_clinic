package guru.springframework5.sfg_pet_clinic.services;

import guru.springframework5.sfg_pet_clinic.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findById(Long id);
    Owner save(Owner owner);
    Set<Owner> findAll();
}
