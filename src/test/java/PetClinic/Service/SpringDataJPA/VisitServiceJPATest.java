package PetClinic.Service.SpringDataJPA;

import PetClinic.Model.Visit;
import PetClinic.Repositories.VisitRepository;
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
class VisitServiceJPATest {
    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitServiceJPA visitServiceJPA;

    Visit visit = new Visit();
    Set<Visit> visits = new HashSet<>();
    Long id = 1L;

    @BeforeEach
    void setUp() {
        visit.setId(id);
        visits.add(visit);
    }

    @Test
    void findAll() throws Exception {
        when(visitRepository.findAll()).thenReturn(visits);
        assertNotNull(visitServiceJPA.findAll());
        assertEquals(1, visitServiceJPA.findAll().size());
        verify(visitRepository, times(2)).findAll();
    }

    @Test
    void findById() throws Exception {
        when(visitRepository.findById(any())).thenReturn(Optional.of(visit));
        assertNotNull(visitServiceJPA.findById(id));
        assertEquals(id, visitServiceJPA.findById(id).getId());
        verify(visitRepository, times(2)).findById(id);
    }

    @Test
    void save() throws Exception {
        when(visitRepository.save(any())).thenReturn(visit);
        assertNotNull(visitServiceJPA.save(visit));
        assertEquals(id, visitServiceJPA.save(visit).getId());
        verify(visitRepository, times(2)).save(visit);
    }

    @Test
    void delete() throws Exception {
        visitServiceJPA.delete(visit);
        verify(visitRepository, times(1)).delete(visit);
    }

    @Test
    void deleteById() throws Exception {
        visitServiceJPA.deleteById(id);
        verify(visitRepository, times(1)).deleteById(id);
    }
}