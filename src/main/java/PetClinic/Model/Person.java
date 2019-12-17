package PetClinic.Model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
public class Person extends BaseEntity {
    @NotBlank(message = "First name must not be empty")
    @Size(min = 1, max = 25, message = "First name must be between 1 to 25 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name must not be empty")
    @Size(min = 1, max = 25, message = "Last name must be between 1 to 25 characters")
    @Column(name = "last_name")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
