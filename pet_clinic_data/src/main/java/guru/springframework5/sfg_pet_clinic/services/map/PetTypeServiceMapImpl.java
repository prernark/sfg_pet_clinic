package guru.springframework5.sfg_pet_clinic.services.map;

import guru.springframework5.sfg_pet_clinic.model.PetType;
import guru.springframework5.sfg_pet_clinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "mapservice"})
public class PetTypeServiceMapImpl extends AbstractMapService<PetType, Long> implements PetTypeService {

    public String toString(){
        String str = "";
        for (Long key : map.keySet()){
            str += map.get(key).getName() + "\n";
        }
        return str;
    }

}
