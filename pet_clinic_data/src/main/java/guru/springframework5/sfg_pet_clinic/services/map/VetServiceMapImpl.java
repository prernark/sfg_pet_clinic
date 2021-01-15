package guru.springframework5.sfg_pet_clinic.services.map;

import guru.springframework5.sfg_pet_clinic.model.Vet;
import guru.springframework5.sfg_pet_clinic.services.VetService;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMapImpl extends AbstractMapService<Vet, Long> implements VetService {
    @Override
    public Vet save(Vet object) {
        return super.save(object);
    }

    @Override
    public String toString() {
        String toStr = "";
        for (Long key: map.keySet()){
            toStr += map.get(key).getFirstName() + " " + map.get(key).getLastName() + "\n";
        }
        return toStr;
    }
}
