package PetClinic.Bootstrap;

import PetClinicData.Model.Owner;
import PetClinicData.Model.Vet;
import PetClinicData.Service.OwnerService;
import PetClinicData.Service.PetService;
import PetClinicData.Service.VetService;
import org.springframework.boot.CommandLineRunner;

public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final PetService petService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, PetService petService, VetService vetService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("Md Emtazul");
        owner.setLastName("Haque");
        ownerService.save(owner);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Torikul");
        owner2.setLastName("Islam");
        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setId(3L);
        owner3.setFirstName("Ibrahim");
        owner3.setLastName("Khan");
        ownerService.save(owner3);

        Vet vet = new Vet();
        vet.setId(1L);
        vet.setFirstName("Tamim");
        vet.setLastName("Haqlader");
        vetService.save(vet);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Saiful");
        vet2.setLastName("Islam");
        vetService.save(vet2);

    }
}
