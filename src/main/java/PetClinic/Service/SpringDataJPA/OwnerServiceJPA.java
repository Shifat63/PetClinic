package PetClinic.Service.SpringDataJPA;

import PetClinic.Model.Owner;
import PetClinic.Repositories.OwnerRepository;
import PetClinic.Service.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"springdatajpa"})
public class OwnerServiceJPA implements OwnerService {
    private OwnerRepository ownerRepository;

    public OwnerServiceJPA(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Set<Owner> findByLastName(String lastName) throws Exception {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Set<Owner> findAll() throws Exception {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long id) throws Exception {
        return ownerRepository.findById(id).get();
    }

    @Override
    public Owner save(Owner object) throws Exception {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) throws Exception {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        ownerRepository.deleteById(id);
    }
}
