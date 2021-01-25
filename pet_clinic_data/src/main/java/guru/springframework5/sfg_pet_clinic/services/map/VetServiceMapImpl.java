package guru.springframework5.sfg_pet_clinic.services.map;

import guru.springframework5.sfg_pet_clinic.model.Speciality;
import guru.springframework5.sfg_pet_clinic.model.Vet;
import guru.springframework5.sfg_pet_clinic.services.SpecialityService;
import guru.springframework5.sfg_pet_clinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "mapservice"})
public class VetServiceMapImpl extends AbstractMapService<Vet, Long> implements VetService {
    private final SpecialityService specialityService;

    public VetServiceMapImpl(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Vet save(Vet object) {
        if (object != null){
            if (object.getSpecialitySet() != null ){
                object.getSpecialitySet().forEach(speciality -> {
                    if (speciality.getId() == null){
                        Speciality savedSp = specialityService.save(speciality);
                        speciality.setId(savedSp.getId());
                    }
                });
            }
        }
        return super.save(object);
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
