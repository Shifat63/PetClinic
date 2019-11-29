package PetClinic.Bootstrap;

import PetClinicData.Model.Owner;
import PetClinicData.Model.PetType;
import PetClinicData.Model.Vet;
import PetClinicData.Service.OwnerService;
import PetClinicData.Service.PetService;
import PetClinicData.Service.PetTypeService;
import PetClinicData.Service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final PetService petService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, PetService petService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType petType = new PetType();
        petType.setName("Dog");
        petTypeService.save(petType);

        PetType petType1 = new PetType();
        petType1.setName("Cat");
        petTypeService.save(petType1);

        System.out.println("PetType data loaded");

        Owner owner = new Owner();
        owner.setFirstName("Md Emtazul");
        owner.setLastName("Haque");
        ownerService.save(owner);

        Owner owner2 = new Owner();
        owner2.setFirstName("Torikul");
        owner2.setLastName("Islam");
        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setFirstName("Ibrahim");
        owner3.setLastName("Khan");
        ownerService.save(owner3);

        System.out.println("Owner data loaded");

        Vet vet = new Vet();
        vet.setFirstName("Tamim");
        vet.setLastName("Haqlader");
        vetService.save(vet);

        Vet vet2 = new Vet();
        vet2.setFirstName("Saiful");
        vet2.setLastName("Islam");
        vetService.save(vet2);

        System.out.println("Vet data loaded");

    }
}
