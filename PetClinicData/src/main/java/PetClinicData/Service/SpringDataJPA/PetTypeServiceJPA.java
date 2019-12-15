package PetClinicData.Service.SpringDataJPA;

import PetClinicData.Model.PetType;
import PetClinicData.Repositories.PetTypeRepository;
import PetClinicData.Service.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"springdatajpa"})
public class PetTypeServiceJPA implements PetTypeService {
    private PetTypeRepository petTypeRepository;

    public PetTypeServiceJPA(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() throws Exception {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long id) throws Exception {
        return petTypeRepository.findById(id).get();
    }

    @Override
    public PetType save(PetType object) throws Exception {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) throws Exception {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        petTypeRepository.deleteById(id);
    }
}
