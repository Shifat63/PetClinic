package PetClinic.Repositories;

import PetClinic.Model.Owner;
import PetClinic.Model.Pet;
import PetClinic.Model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PetRepositoryIT {
    @Autowired
    PetRepository petRepository;

    Long petId = 1L;
    Pet pet;

    @BeforeEach
    void setUp() {
        pet = new Pet();
    }

    @Test
    void findAll(){
        int counter = 0;
        for (Object i : petRepository.findAll()) {
            counter++;
        }
        assertEquals(4, counter);
    }

    @Test
    void findById(){
        assertEquals(petId, petRepository.findById(petId).get().getId());
        assertEquals(Optional.empty() ,petRepository.findById(5L));
    }

    @Test
    void save(){
        pet = petRepository.findById(petId).get();
        Pet savedPet = petRepository.save(pet);
        assertEquals(pet.getId(), savedPet.getId()); //While editing id remains same
        pet = new Pet();
        pet.setBirthDate(LocalDate.now());
        pet.setName("Name");
        Owner owner = new Owner();
        owner.setId(1L);
        pet.setOwner(owner);
        PetType petType = new PetType();
        petType.setId(1L);
        pet.setPetType(petType);
        savedPet = petRepository.save(pet);
        assertEquals(5, savedPet.getId()); //While new insertion id should be the next number
    }

    @Test
    void delete(){
        assertEquals(petId, petRepository.findById(petId).get().getId());
        pet.setId(petId);
        petRepository.delete(pet);
        assertEquals(Optional.empty() ,petRepository.findById(petId));
    }

    @Test
    void deleteById(){
        assertEquals(petId, petRepository.findById(petId).get().getId());
        petRepository.deleteById(petId);
        assertEquals(Optional.empty() ,petRepository.findById(petId));
    }
}