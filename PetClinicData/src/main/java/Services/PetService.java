package Services;

import Model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(long Id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}
