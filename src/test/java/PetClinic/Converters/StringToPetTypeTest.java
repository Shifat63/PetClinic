package PetClinic.Converters;

import PetClinic.Model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class StringToPetTypeTest {

    PetType petType = new PetType();
    StringToPetType stringToPetType = new StringToPetType();

    @BeforeEach
    void setUp() {
        petType.setId(1L);
    }

    @Test
    void convert() {
        assertEquals(petType.getId(), stringToPetType.convert("1").getId());
        assertNull(stringToPetType.convert(""));
        assertNull(stringToPetType.convert("abc"));
    }
}