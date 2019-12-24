package PetClinic.Service.SpringDataJPA;

import PetClinic.Model.Speciality;
import PetClinic.Repositories.SpecialityRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialityServiceJPATest {
    @Mock
    SpecialityRepository specialityRepository;

    @InjectMocks
    SpecialityServiceJPA specialityServiceJPA;

    Speciality speciality = new Speciality();
    Set<Speciality> specialities = new HashSet<>();
    Long id = 1L;

    @BeforeEach
    void setUp() {
        speciality.setId(id);
        specialities.add(speciality);
    }

    @Test
    void findAll() throws Exception {
        when(specialityRepository.findAll()).thenReturn(specialities);
        assertNotNull(specialityServiceJPA.findAll());
        assertEquals(1, specialityServiceJPA.findAll().size());
        verify(specialityRepository, times(2)).findAll();
    }

    @Test
    void findById() throws Exception {
        when(specialityRepository.findById(any())).thenReturn(Optional.of(speciality));
        assertNotNull(specialityServiceJPA.findById(id));
        assertEquals(id, specialityServiceJPA.findById(id).getId());
        verify(specialityRepository, times(2)).findById(id);
    }

    @Test
    void save() throws Exception {
        when(specialityRepository.save(any())).thenReturn(speciality);
        assertNotNull(specialityServiceJPA.save(speciality));
        assertEquals(id, specialityServiceJPA.save(speciality).getId());
        verify(specialityRepository, times(2)).save(speciality);
    }

    @Test
    void delete() throws Exception {
        specialityServiceJPA.delete(speciality);
        verify(specialityRepository, times(1)).delete(speciality);
    }

    @Test
    void deleteById() throws Exception {
        specialityServiceJPA.deleteById(id);
        verify(specialityRepository, times(1)).deleteById(id);
    }
}