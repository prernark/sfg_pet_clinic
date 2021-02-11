package guru.springframework5.sfg_pet_clinic.services;

import guru.springframework5.sfg_pet_clinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameLike(String lastName);
}
