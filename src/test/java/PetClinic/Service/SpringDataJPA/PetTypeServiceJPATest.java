package PetClinic.Service.SpringDataJPA;

import PetClinic.Model.PetType;
import PetClinic.Repositories.PetTypeRepository;
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
class PetTypeServiceJPATest {
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeServiceJPA petTypeServiceJPA;

    PetType petType = new PetType();
    Set<PetType> petTypes = new HashSet<>();
    Long id = 1L;

    @BeforeEach
    void setUp() {
        petType.setId(id);
        petTypes.add(petType);
    }

    @Test
    void findAll() throws Exception {
        when(petTypeRepository.findAll()).thenReturn(petTypes);
        assertNotNull(petTypeServiceJPA.findAll());
        assertEquals(1, petTypeServiceJPA.findAll().size());
        verify(petTypeRepository, times(2)).findAll();
    }

    @Test
    void findById() throws Exception {
        when(petTypeRepository.findById(any())).thenReturn(Optional.of(petType));
        assertNotNull(petTypeServiceJPA.findById(id));
        assertEquals(id, petTypeServiceJPA.findById(id).getId());
        verify(petTypeRepository, times(2)).findById(id);
    }

    @Test
    void save() throws Exception {
        when(petTypeRepository.save(any())).thenReturn(petType);
        assertNotNull(petTypeServiceJPA.save(petType));
        assertEquals(id, petTypeServiceJPA.save(petType).getId());
        verify(petTypeRepository, times(2)).save(petType);
    }

    @Test
    void delete() throws Exception {
        petTypeServiceJPA.delete(petType);
        verify(petTypeRepository, times(1)).delete(petType);
    }

    @Test
    void deleteById() throws Exception {
        petTypeServiceJPA.deleteById(id);
        verify(petTypeRepository, times(1)).deleteById(id);
    }
}