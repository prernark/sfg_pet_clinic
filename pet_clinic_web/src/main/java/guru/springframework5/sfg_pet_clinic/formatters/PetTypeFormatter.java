package guru.springframework5.sfg_pet_clinic.formatters;

import guru.springframework5.sfg_pet_clinic.model.PetType;
import guru.springframework5.sfg_pet_clinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class PetTypeFormatter implements Formatter<PetType> {
    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String s, Locale locale) throws ParseException {
        Optional<PetType> petTypeOptional = petTypeService.findAll()
                             .stream()
                             .filter(petType -> petType.getName().equalsIgnoreCase(s))
                             .findFirst();
        if (petTypeOptional.isPresent())
            return petTypeOptional.get();
        else
            throw new ParseException("Type not found", 0);
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
