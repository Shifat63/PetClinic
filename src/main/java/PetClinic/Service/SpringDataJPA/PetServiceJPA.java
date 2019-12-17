package PetClinic.Service.SpringDataJPA;

import PetClinic.Model.Pet;
import PetClinic.Repositories.PetRepository;
import PetClinic.Service.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"springdatajpa"})
public class PetServiceJPA implements PetService {
    private PetRepository petRepository;

    public PetServiceJPA(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() throws Exception {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long id) throws Exception {
        return petRepository.findById(id).get();
    }

    @Override
    public Pet save(Pet object) throws Exception {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) throws Exception {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        petRepository.deleteById(id);
    }
}
