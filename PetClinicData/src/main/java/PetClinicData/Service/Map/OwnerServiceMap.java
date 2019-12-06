package PetClinicData.Service.Map;

import PetClinicData.Model.Owner;
import PetClinicData.Service.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"map", "default"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Set<Owner> findByLastName(String lastName) {
        Set<Owner> owners = new HashSet<>();
        for (Owner owner : this.findAll())
        {
            if(owner.getLastName().equalsIgnoreCase(lastName)) {
                owners.add(owner);
            }
        }
        return owners;
    }
}
