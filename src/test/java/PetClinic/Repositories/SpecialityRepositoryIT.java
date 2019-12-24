package PetClinic.Repositories;

import PetClinic.Model.Speciality;
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
class SpecialityRepositoryIT {

    @Autowired
    SpecialityRepository specialityRepository;

    Long specialityId = 1L;
    Speciality speciality;

    @BeforeEach
    void setUp() {
        speciality = new Speciality();
    }

    @Test
    void findAll(){
        int counter = 0;
        for (Object i : specialityRepository.findAll()) {
            counter++;
        }
        assertEquals(3, counter);
    }

    @Test
    void findById(){
        assertEquals(specialityId, specialityRepository.findById(specialityId).get().getId());
        assertEquals(Optional.empty() ,specialityRepository.findById(5L));
    }

    @Test
    void save(){
        speciality = specialityRepository.findById(specialityId).get();
        Speciality savedSpeciality = specialityRepository.save(speciality);
        assertEquals(speciality.getId(), savedSpeciality.getId()); //While editing id remains same
        speciality = new Speciality();
        speciality.setDescription("Description");
        savedSpeciality = specialityRepository.save(speciality);
        assertEquals(4, savedSpeciality.getId()); //While new insertion id should be the next number
    }

    @Test
    void delete(){
        assertEquals(specialityId, specialityRepository.findById(specialityId).get().getId());
        speciality.setId(specialityId);
        specialityRepository.delete(speciality);
        assertEquals(Optional.empty() ,specialityRepository.findById(specialityId));
    }

    @Test
    void deleteById(){
        assertEquals(specialityId, specialityRepository.findById(specialityId).get().getId());
        specialityRepository.deleteById(specialityId);
        assertEquals(Optional.empty() ,specialityRepository.findById(specialityId));
    }
}