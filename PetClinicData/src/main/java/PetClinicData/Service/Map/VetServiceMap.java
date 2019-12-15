package PetClinicData.Service.Map;

import PetClinicData.Model.Vet;
import PetClinicData.Service.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"map", "default"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    @Override
    public Set<Vet> findAll() throws Exception {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) throws Exception {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) throws Exception {
        return super.save(object);
    }

    @Override
    public void delete(Vet object) throws Exception {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        super.deleteById(id);
    }

    @Override
    public Set<Vet> findByCriteria(String name, Long specialityId) throws Exception {
        return null;
    }
}
