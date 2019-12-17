package PetClinic.Service.Map;

import PetClinic.Model.Owner;
import PetClinic.Service.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"map", "default"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    @Override
    public Set<Owner> findAll() throws Exception {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) throws Exception {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) throws Exception {
        return super.save(object);
    }

    @Override
    public void delete(Owner object) throws Exception {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        super.deleteById(id);
    }

    @Override
    public Set<Owner> findByLastName(String lastName) throws Exception {
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
