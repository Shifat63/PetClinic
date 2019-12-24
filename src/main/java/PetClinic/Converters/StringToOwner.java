package PetClinic.Converters;

import PetClinic.Model.Owner;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToOwner implements Converter<String, Owner> {

    @Override
    public Owner convert(String source) {
        try {
            Owner owner = new Owner();
            owner.setId(Long.parseLong(source));
            return owner;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
