package PetClinic.Converters;

import PetClinic.Model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class StringToOwnerTest {

    Owner owner = new Owner();
    StringToOwner stringToOwner = new StringToOwner();

    @BeforeEach
    void setUp() {
        owner.setId(1L);
    }

    @Test
    void convert() {
        assertEquals(owner.getId(), stringToOwner.convert("1").getId());
        assertNull(stringToOwner.convert(""));
        assertNull(stringToOwner.convert("abc"));
    }
}