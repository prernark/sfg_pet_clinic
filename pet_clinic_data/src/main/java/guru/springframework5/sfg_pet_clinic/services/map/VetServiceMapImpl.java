package guru.springframework5.sfg_pet_clinic.services.map;

import guru.springframework5.sfg_pet_clinic.model.Vet;

public class VetServiceMapImpl extends AbstractMapService<Vet, Long>{
    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(), object);
    }
}
