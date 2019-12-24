package PetClinic.Repositories;

import PetClinic.Model.Pet;
import PetClinic.Model.Visit;
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
class VisitRepositoryIT {

    @Autowired
    VisitRepository visitRepository;

    Long visitId = 1L;
    Visit visit;

    @BeforeEach
    void setUp() {
        visit = new Visit();
    }

    @Test
    void findAll(){
        int counter = 0;
        for (Object i : visitRepository.findAll()) {
            counter++;
        }
        assertEquals(6, counter);
    }

    @Test
    void findById(){
        assertEquals(visitId, visitRepository.findById(visitId).get().getId());
        assertEquals(Optional.empty() ,visitRepository.findById(10L));
    }

    @Test
    void save(){
        visit = visitRepository.findById(visitId).get();
        Visit savedVisit = visitRepository.save(visit);
        assertEquals(visit.getId(), savedVisit.getId()); //While editing id remains same
        visit = new Visit();
        visit.setDate(LocalDate.now());
        visit.setDescription("Description");
        Pet pet = new Pet();
        pet.setId(1L);
        visit.setPet(pet);
        savedVisit = visitRepository.save(visit);
        assertEquals(7, savedVisit.getId()); //While new insertion id should be the next number
    }

    @Test
    void delete(){
        assertEquals(visitId, visitRepository.findById(visitId).get().getId());
        visit.setId(visitId);
        visitRepository.delete(visit);
        assertEquals(Optional.empty() ,visitRepository.findById(visitId));
    }

    @Test
    void deleteById(){
        assertEquals(visitId, visitRepository.findById(visitId).get().getId());
        visitRepository.deleteById(visitId);
        assertEquals(Optional.empty() ,visitRepository.findById(visitId));
    }
}