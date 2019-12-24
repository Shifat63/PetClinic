package PetClinic.Repositories;

import PetClinic.Model.Vet;
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
class VetRepositoryIT {

    @Autowired
    VetRepository vetRepository;

    Long vetId = 1L;
    Vet vet;

    @BeforeEach
    void setUp() {
        vet = new Vet();
    }

    @Test
    void findAll(){
        int counter = 0;
        for (Object i : vetRepository.findAll()) {
            counter++;
        }
        assertEquals(2, counter);
    }

    @Test
    void findById(){
        assertEquals(vetId, vetRepository.findById(vetId).get().getId());
        assertEquals(Optional.empty() ,vetRepository.findById(5L));
    }

    @Test
    void save(){
        vet = vetRepository.findById(vetId).get();
        Vet savedVet = vetRepository.save(vet);
        assertEquals(vet.getId(), savedVet.getId()); //While editing id remains same
        vet = new Vet();
        vet.setFirstName("First Name");
        vet.setLastName("Last Name");
        savedVet = vetRepository.save(vet);
        assertEquals(3, savedVet.getId()); //While new insertion id should be the next number
    }

    @Test
    void delete(){
        assertEquals(vetId, vetRepository.findById(vetId).get().getId());
        vet.setId(vetId);
        vetRepository.delete(vet);
        assertEquals(Optional.empty() ,vetRepository.findById(vetId));
    }

    @Test
    void deleteById(){
        assertEquals(vetId, vetRepository.findById(vetId).get().getId());
        vetRepository.deleteById(vetId);
        assertEquals(Optional.empty() ,vetRepository.findById(vetId));
    }
}