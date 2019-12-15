package PetClinicData.Service.Map;

import PetClinicData.Model.Pet;
import PetClinicData.Service.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"map", "default"})
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Set<Pet> findAll() throws Exception {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) throws Exception {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object) throws Exception {
        return super.save(object);
    }

    @Override
    public void delete(Pet object) throws Exception {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        super.deleteById(id);
    }
}
