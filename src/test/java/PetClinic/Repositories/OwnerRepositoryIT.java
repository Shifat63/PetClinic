package PetClinic.Repositories;

import PetClinic.Model.Owner;
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
class OwnerRepositoryIT {

    @Autowired
    OwnerRepository ownerRepository;

    Long ownerId = 1L;
    Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner();
    }

    @Test
    void findByLastName() {
        String lastName = "Haque";
        for (Owner eaxhOwner : ownerRepository.findByLastName(lastName))
        {
            assertEquals(lastName, eaxhOwner.getLastName());
        }
        assertEquals(0 ,ownerRepository.findByLastName("wrongName").size());
    }

    @Test
    void findAll(){
        int counter = 0;
        for (Object i : ownerRepository.findAll()) {
            counter++;
        }
        assertEquals(3, counter);
    }

    @Test
    void findById(){
        assertEquals(ownerId, ownerRepository.findById(ownerId).get().getId());
        assertEquals(Optional.empty() ,ownerRepository.findById(5L));
    }

    @Test
    void save(){
        owner = ownerRepository.findById(ownerId).get();
        Owner savedOwner = ownerRepository.save(owner);
        assertEquals(owner.getId(), savedOwner.getId()); //While editing id remains same
        owner = new Owner();
        owner.setFirstName("First Name");
        owner.setLastName("Last Name");
        owner.setAddress("Address");
        owner.setCity("City");
        owner.setTelephone("123456789");
        savedOwner = ownerRepository.save(owner);
        assertEquals(4, savedOwner.getId()); //While new insertion id should be the next number
    }

    @Test
    void delete(){
        assertEquals(ownerId, ownerRepository.findById(ownerId).get().getId());
        owner.setId(ownerId);
        ownerRepository.delete(owner);
        assertEquals(Optional.empty() ,ownerRepository.findById(ownerId));
    }

    @Test
    void deleteById(){
        assertEquals(ownerId, ownerRepository.findById(ownerId).get().getId());
        ownerRepository.deleteById(ownerId);
        assertEquals(Optional.empty() ,ownerRepository.findById(ownerId));
    }
}