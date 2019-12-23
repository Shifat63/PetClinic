package PetClinic.Service.SpringDataJPA;

import PetClinic.Model.Speciality;
import PetClinic.Model.Vet;
import PetClinic.Repositories.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetServiceJPATest {
    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetServiceJPA vetServiceJPA;

    Vet vet = new Vet();
    Set<Vet> vets = new HashSet<>();
    Long id = 1L;

    @BeforeEach
    void setUp() {
        vet.setId(id);
        vets.add(vet);
    }

    @Test
    void findAll() throws Exception {
        when(vetRepository.findAll()).thenReturn(vets);
        assertNotNull(vetServiceJPA.findAll());
        assertEquals(1, vetServiceJPA.findAll().size());
        verify(vetRepository, times(2)).findAll();
    }

    @Test
    void findById() throws Exception {
        when(vetRepository.findById(any())).thenReturn(Optional.of(vet));
        assertNotNull(vetServiceJPA.findById(id));
        assertEquals(id, vetServiceJPA.findById(id).getId());
        verify(vetRepository, times(2)).findById(id);
    }

    @Test
    void save() throws Exception {
        when(vetRepository.save(any())).thenReturn(vet);
        assertNotNull(vetServiceJPA.save(vet));
        assertEquals(id, vetServiceJPA.save(vet).getId());
        verify(vetRepository, times(2)).save(vet);
    }

    @Test
    void delete() throws Exception {
        vetServiceJPA.delete(vet);
        verify(vetRepository, times(1)).delete(vet);
    }

    @Test
    void deleteById() throws Exception {
        vetServiceJPA.deleteById(id);
        verify(vetRepository, times(1)).deleteById(id);
    }

    @Test
    void findByCriteria() throws Exception {
        String firstName = "Sakib";
        String lastName = "Khan";
        Long specialityId = 1L;
        Set<Speciality> specialities = new HashSet<>();
        Speciality speciality = new Speciality();
        speciality.setId(specialityId);
        specialities.add(speciality);
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vet.setSpecialities(specialities);
        List vetList = new ArrayList();
        vetList.add(vet);

        when(vetRepository.findAll(any())).thenReturn(vetList);
        assertNotNull(vetServiceJPA.findByCriteria(firstName+lastName, specialityId));
        assertEquals(1, vetServiceJPA.findByCriteria(firstName+lastName, specialityId).size());
        for (Vet searchedVet: vetServiceJPA.findByCriteria(firstName+lastName, specialityId))
        {
            assertEquals(id, searchedVet.getId());
            assertEquals(firstName, searchedVet.getFirstName());
            assertEquals(lastName, searchedVet.getLastName());
            for (Speciality specialityOfVet: searchedVet.getSpecialities())
            {
                assertEquals(specialityId, specialityOfVet.getId());
            }
        }

        verify(vetRepository, times(3)).findAll(any());
    }
}