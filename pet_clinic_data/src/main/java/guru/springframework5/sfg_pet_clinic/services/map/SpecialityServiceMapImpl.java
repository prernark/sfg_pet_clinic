package guru.springframework5.sfg_pet_clinic.services.map;

import guru.springframework5.sfg_pet_clinic.model.Speciality;
import guru.springframework5.sfg_pet_clinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "mapservice"})
public class SpecialityServiceMapImpl extends AbstractMapService<Speciality, Long> implements SpecialityService {

}
