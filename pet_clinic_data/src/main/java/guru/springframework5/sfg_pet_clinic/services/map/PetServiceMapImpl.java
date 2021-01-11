package guru.springframework5.sfg_pet_clinic.services.map;

import guru.springframework5.sfg_pet_clinic.model.Pet;

public class PetServiceMapImpl extends AbstractMapService<Pet, Long> {


    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }
}
