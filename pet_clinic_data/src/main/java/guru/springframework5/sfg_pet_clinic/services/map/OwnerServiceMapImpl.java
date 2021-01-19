package guru.springframework5.sfg_pet_clinic.services.map;

import guru.springframework5.sfg_pet_clinic.model.Owner;
import guru.springframework5.sfg_pet_clinic.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class OwnerServiceMapImpl extends AbstractMapService<Owner, Long> implements OwnerService {

    @Override
    public Owner save(Owner object) {
        return super.save(object);
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
