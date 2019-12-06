package PetClinicData.Service.Map;

import PetClinicData.Model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {
    OwnerServiceMap ownerServiceMap;
    final Long id = 1L;
    final Long fakeId=5L;
    final String lastName="Haque";
    final String fakeLastName = "Khan";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap();
        Owner owner = new Owner();
        owner.setId(id);
        owner.setLastName(lastName);
        ownerServiceMap.save(owner);
    }

    @Test
    void findAll() {
        assertNotNull(ownerServiceMap.findAll());
        assertEquals(1,ownerServiceMap.findAll().size());
    }

    @Test
    void findById() {
        //Found
        assertNotNull(ownerServiceMap.findById(id));
        assertEquals(id, ownerServiceMap.findById(id).getId());

        //Not found
        assertNull(ownerServiceMap.findById(fakeId));
    }

    @Test
    void save() {
        Long existingId = 2L;
        Long newId = 3L;

        //Save a object that does not have id
        Owner owner2 = new Owner();
        ownerServiceMap.save(owner2);
        assertEquals(2, ownerServiceMap.findAll().size());

        //Save a object that has existing id
        Owner owner3 = new Owner();
        owner3.setId(existingId);
        ownerServiceMap.save(owner3);
        assertEquals(2, ownerServiceMap.findAll().size());

        //Save a object with a new id
        Owner owner4 = new Owner();
        owner4.setId(newId);
        ownerServiceMap.save(owner4);
        assertEquals(3, ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(id));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(id);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        //Found
        assertNotNull(ownerServiceMap.findByLastName(lastName));
        assertEquals(1, ownerServiceMap.findByLastName(lastName).size());
        for(Owner owner : ownerServiceMap.findByLastName(lastName))
        {
            assertEquals(lastName, owner.getLastName());
        }

        //Not found
        assertEquals(0, ownerServiceMap.findByLastName(fakeLastName).size());
    }
}