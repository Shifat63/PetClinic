package PetClinicData.Service.SpringDataJPA;

import PetClinicData.Model.Owner;
import PetClinicData.Repositories.OwnerRepository;
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
class OwnerServiceJPATest {
    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerServiceJPA ownerServiceJPA;

    Owner owner = new Owner();
    Set<Owner> owners = new HashSet<>();

    Long id = 1L;
    String lastName = "Haque";

    @BeforeEach
    void setUp() {
        owner.setId(id);
        owner.setLastName(lastName);
        owners.add(owner);
    }

    @Test
    void findByLastName() throws Exception{
        when(ownerRepository.findByLastName(any())).thenReturn(owners);
        for(Owner eachOwner : ownerServiceJPA.findByLastName(lastName))
        {
            assertEquals(lastName, eachOwner.getLastName());
        }
        verify(ownerRepository, times(1)).findByLastName(any());
    }

    @Test
    void findAll() throws Exception{
        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setLastName("Khan");
        owners.add(owner2);
        when(ownerRepository.findAll()).thenReturn(owners);
        assertNotNull(ownerServiceJPA.findAll().size());
        assertEquals(2, ownerServiceJPA.findAll().size());
        verify(ownerRepository, times(2)).findAll();
    }

    @Test
    void findById() throws Exception{
        when(ownerRepository.findById(any())).thenReturn(Optional.of(owner));
        assertNotNull(ownerServiceJPA.findById(id));
        assertEquals(id, ownerServiceJPA.findById(id).getId());
        verify(ownerRepository, times(2)).findById(any());
    }

    @Test
    void save() throws Exception{
        when(ownerRepository.save(any())).thenReturn(owner);
        assertNotNull(ownerServiceJPA.save(any()));
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() throws Exception{
        ownerServiceJPA.delete(owner);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() throws Exception{
        ownerServiceJPA.deleteById(id);
        verify(ownerRepository, times(1)).deleteById(id);
    }
}