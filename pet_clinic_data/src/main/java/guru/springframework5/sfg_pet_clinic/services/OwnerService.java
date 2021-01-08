package guru.springframework5.sfg_pet_clinic.services;

import guru.springframework5.sfg_pet_clinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);
}
