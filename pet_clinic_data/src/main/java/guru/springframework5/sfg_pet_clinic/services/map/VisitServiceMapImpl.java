package guru.springframework5.sfg_pet_clinic.services.map;

import guru.springframework5.sfg_pet_clinic.model.Visit;
import guru.springframework5.sfg_pet_clinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "mapservice"})
public class VisitServiceMapImpl extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public Visit save(Visit visit) {
        if (visit.getPet() == null || visit.getPet().getOwner() == null || visit.getPet().getId() == null ||
            visit.getPet().getOwner().getId() == null){
            throw new RuntimeException("Invalid Visit");
        }
        return super.save(visit);
    }
}
