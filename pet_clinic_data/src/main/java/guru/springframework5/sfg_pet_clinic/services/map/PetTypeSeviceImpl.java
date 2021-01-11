package guru.springframework5.sfg_pet_clinic.services.map;

import guru.springframework5.sfg_pet_clinic.model.PetType;

public class PetTypeSeviceImpl extends AbstractMapService<PetType, Long>{

    @Override
    public PetType save(PetType object) {
        return super.save(object.getId(), object);
    }
}
