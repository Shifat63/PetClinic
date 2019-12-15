package PetClinicData.Service.Map;

import PetClinicData.Model.Speciality;
import PetClinicData.Service.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"map", "default"})
public class SpecialityServiceMap extends AbstractMapService<Speciality, Long> implements SpecialityService {
    @Override
    public Set<Speciality> findAll() throws Exception {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) throws Exception {
        super.deleteById(id);
    }

    @Override
    public void delete(Speciality object) throws Exception {
        super.delete(object);
    }

    @Override
    public Speciality save(Speciality object) throws Exception {
        return super.save(object);
    }

    @Override
    public Speciality findById(Long id) throws Exception {
        return super.findById(id);
    }
}
