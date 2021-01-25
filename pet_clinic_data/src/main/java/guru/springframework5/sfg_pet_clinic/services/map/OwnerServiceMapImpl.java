package guru.springframework5.sfg_pet_clinic.services.map;

import guru.springframework5.sfg_pet_clinic.model.Owner;
import guru.springframework5.sfg_pet_clinic.model.Pet;
import guru.springframework5.sfg_pet_clinic.services.OwnerService;
import guru.springframework5.sfg_pet_clinic.services.PetService;
import guru.springframework5.sfg_pet_clinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Profile({"default", "mapservice"}) //will be active if profile is mapservice or default which means nothing specified
public class OwnerServiceMapImpl extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMapImpl(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Owner save(Owner object) {
        //BEFORE OWNER IS SAVED, WE NEED TO ENSURE THE PETTYPE AND PET EXIST IN THE MAPS ELSE SAVE THEM ALSO
        if (object != null){
            if (object.getPetSet() != null){
                object.getPetSet().forEach(pet -> {
                    if (pet.getId() == null){
                        //id is null so save the pet
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                    if (pet.getPetType() != null){
                        if (pet.getPetType().getId() == null) {
                            //id is null so save petType
                            petTypeService.save(pet.getPetType());
                        }
                    }
                    else {
                        throw new RuntimeException("PetType is required to save Owners");
                    }
                });
            }
            else {//DO NOTHING
            }
            return super.save(object);
        }
        else {
            return null;
        }
    }

    @Override
    public Owner findByLastName(String lastName) {
//        Predicate<Owner> predicate = owner->owner.getLastName().equalsIgnoreCase(lastName);
//        map.entrySet().stream().filter(predicate).findFirst();
//        map.entrySet().forEach(entry->entry.getValue().getLastName().equalsIgnoreCase(lastName));
        Optional<Map.Entry<Long, Owner>> valueFound = map.entrySet().stream().filter(longOwnerEntry -> longOwnerEntry.getValue().getLastName().equalsIgnoreCase(lastName)).findFirst();
        if (valueFound.isPresent())
            return valueFound.get().getValue();
        return null;
    }

    @Override
    public String toString() {
        String toStr = "";
        for (Long key : map.keySet()) {
            toStr += map.get(key).getFirstName() + " " + map.get(key).getLastName() + "\n";
        }
        return toStr;
    }
}
