package PetClinic.Service.SpringDataJPA;

import PetClinic.Model.Pet;
import PetClinic.Repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceJPATest {
    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetServiceJPA petServiceJPA;

    Long id=1L;
    Pet pet = new Pet();
    Set<Pet> pets = new HashSet<>();

    @BeforeEach
    void setUp() {
        pet.setId(id);
        pets.add(pet);
    }

    @Test
    void findAll() throws Exception {
        when(petRepository.findAll()).thenReturn(pets);
        assertNotNull(petServiceJPA.findAll());
        assertEquals(1, petServiceJPA.findAll().size());
        verify(petRepository, times(2)).findAll();
    }

    @Test
    void findById() throws Exception {
        when(petRepository.findById(any())).thenReturn(Optional.of(pet));
        assertNotNull(petServiceJPA.findById(id));
        assertEquals(id, petServiceJPA.findById(id).getId());
        verify(petRepository, times(2)).findById(id);
    }

    @Test
    void save() throws Exception {
        when(petRepository.save(any())).thenReturn(pet);
        assertNotNull(petServiceJPA.save(pet));
        assertEquals(id, petServiceJPA.save(pet).getId());
        verify(petRepository, times(2)).save(pet);
    }

    @Test
    void delete() throws Exception {
        petServiceJPA.delete(pet);
        verify(petRepository, times(1)).delete(pet);
    }

    @Test
    void deleteById() throws Exception {
        petServiceJPA.deleteById(id);
        verify(petRepository, times(1)).deleteById(id);
    }
}