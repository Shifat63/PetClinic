package PetClinicData.Service.Map;

import PetClinicData.Model.PetType;
import PetClinicData.Service.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"map", "default"})
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService{
    @Override
    public Set<PetType> findAll() throws Exception {
        return super.findAll();
    }

    @Override
    public PetType findById(Long id) throws Exception {
        return super.findById(id);
    }

    @Override
    public PetType save(PetType object) throws Exception {
        return super.save(object);
    }

    @Override
    public void delete(PetType object) throws Exception {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        super.deleteById(id);
    }
}
