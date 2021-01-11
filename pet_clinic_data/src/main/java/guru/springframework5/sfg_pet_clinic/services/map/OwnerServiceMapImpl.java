package guru.springframework5.sfg_pet_clinic.services.map;

import guru.springframework5.sfg_pet_clinic.model.Owner;

public class OwnerServiceMapImpl extends AbstractMapService<Owner, Long> {

    @Override
    public Owner save(Owner object) {
        return super.save(object.getId(), object);
    }

}
