package PetClinic.Converters;

import PetClinic.Model.PetType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPetType implements Converter<String, PetType> {
    @Override
    public PetType convert(String source) {
        try {
            PetType petType = new PetType();
            petType.setId(Long.parseLong(source));
            return petType;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
