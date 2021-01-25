package guru.springframework5.sfg_pet_clinic.services.springdatajpa;

import guru.springframework5.sfg_pet_clinic.model.Visit;
import guru.springframework5.sfg_pet_clinic.repositories.VisitRepository;
import guru.springframework5.sfg_pet_clinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class VisitServiceSDJPA extends AbstractServiceSDJPA<Visit, Long> implements VisitService {
    private final VisitRepository visitRepository;

    public VisitServiceSDJPA(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }


    @Override
    CrudRepository<Visit, Long> getCrudRepository() {
        return visitRepository;
    }

//    @Override
//    public Visit save(Visit visit) {
//        if (visit.getPet() == null || visit.getPet().getOwner() == null || visit.getPet().getId() == null ||
//            visit.getPet().getOwner().getId() == null) {
//            throw new RuntimeException("Invalid Visit");
//        }
//        return super.save(visit);
//    }
}
