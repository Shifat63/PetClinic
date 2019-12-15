package PetClinicData.Service.Map;

import PetClinicData.Model.Visit;
import PetClinicData.Service.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"map", "default"})
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService {
    @Override
    public Set<Visit> findAll() throws Exception {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws Exception {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) throws Exception {
        super.delete(object);
    }

    @Override
    public Visit save(Visit object) throws Exception {
        return super.save(object);
    }

    @Override
    public Visit findById(Long id) throws Exception {
        return super.findById(id);
    }
}
