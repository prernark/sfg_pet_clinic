package guru.springframework5.sfg_pet_clinic.services.map;

import guru.springframework5.sfg_pet_clinic.model.PetType;
import guru.springframework5.sfg_pet_clinic.services.PetTypeService;
import org.springframework.stereotype.Service;

@Service
public class PetTypeServiceMapImpl extends AbstractMapService<PetType, Long> implements PetTypeService {

    @Override
    public PetType save(PetType object) {
        return super.save(object.getId(), object);
    }
}
