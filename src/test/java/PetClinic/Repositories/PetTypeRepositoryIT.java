package PetClinic.Repositories;

import PetClinic.Model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PetTypeRepositoryIT {

    @Autowired
    PetTypeRepository petTypeRepository;

    Long petTypeId = 1L;
    PetType petType;

    @BeforeEach
    void setUp() {
        petType = new PetType();
    }

    @Test
    void findAll(){
        int counter = 0;
        for (Object i : petTypeRepository.findAll()) {
            counter++;
        }
        assertEquals(2, counter);
    }

    @Test
    void findById(){
        assertEquals(petTypeId, petTypeRepository.findById(petTypeId).get().getId());
        assertEquals(Optional.empty() ,petTypeRepository.findById(5L));
    }

    @Test
    void save(){
        petType = petTypeRepository.findById(petTypeId).get();
        PetType savedPetType = petTypeRepository.save(petType);
        assertEquals(petType.getId(), savedPetType.getId()); //While editing id remains same
        petType = new PetType();
        petType.setName("Name");
        savedPetType = petTypeRepository.save(petType);
        assertEquals(3, savedPetType.getId()); //While new insertion id should be the next number
    }

    @Test
    void delete(){
        assertEquals(petTypeId, petTypeRepository.findById(petTypeId).get().getId());
        petType.setId(petTypeId);
        petTypeRepository.delete(petType);
        assertEquals(Optional.empty() ,petTypeRepository.findById(petTypeId));
    }

    @Test
    void deleteById(){
        assertEquals(petTypeId, petTypeRepository.findById(petTypeId).get().getId());
        petTypeRepository.deleteById(petTypeId);
        assertEquals(Optional.empty() ,petTypeRepository.findById(petTypeId));
    }
}