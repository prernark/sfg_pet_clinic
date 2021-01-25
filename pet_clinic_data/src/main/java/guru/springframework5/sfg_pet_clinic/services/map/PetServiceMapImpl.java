package guru.springframework5.sfg_pet_clinic.services.map;

import guru.springframework5.sfg_pet_clinic.model.Pet;
import guru.springframework5.sfg_pet_clinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "mapservice"})
public class PetServiceMapImpl extends AbstractMapService<Pet, Long> implements PetService {

    @Override
    public Pet save(Pet object) {
        return super.save(object);
    }
}
