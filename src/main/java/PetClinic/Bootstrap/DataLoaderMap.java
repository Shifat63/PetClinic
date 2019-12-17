package PetClinic.Bootstrap;

import PetClinic.Model.*;
import PetClinic.Service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
@Profile({"map", "default"})
public class DataLoaderMap implements CommandLineRunner {

    private final OwnerService ownerService;
    private final PetService petService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoaderMap(OwnerService ownerService, PetService petService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        //loadData();
    }

    private void loadData() throws Exception{
        //Start PetType data loading
        PetType dogType = new PetType();
        dogType.setName("Dog");
        petTypeService.save(dogType);

        PetType catType = new PetType();
        catType.setName("Cat");
        petTypeService.save(catType);

        System.out.println("PetType data loaded");
        //End PetType data loading

        //Start Speciality data loading
        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology Specialist");
        specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgon");
        specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentist");
        specialityService.save(dentistry);

        System.out.println("Speciality data loaded");
        //End Speciality data loading

        //Start Pet data loading
        Pet tommy = new Pet();
        tommy.setName("Tommy");
        tommy.setBirthDate(LocalDate.now());
        tommy.setPetType(dogType);
        petService.save(tommy);

        Pet hosko = new Pet();
        hosko.setName("Hosko");
        hosko.setBirthDate(LocalDate.now());
        hosko.setPetType(dogType);
        petService.save(hosko);

        Pet fire = new Pet();
        fire.setName("Fire");
        fire.setBirthDate(LocalDate.now());
        fire.setPetType(catType);
        petService.save(fire);

        Pet vulo = new Pet();
        vulo.setName("Vulo");
        vulo.setBirthDate(LocalDate.now());
        vulo.setPetType(catType);
        petService.save(vulo);

        System.out.println("Pet data loaded");
        //End Pet data loading

        //Start Owner data loading
        Owner emtazul = new Owner();
        emtazul.setFirstName("Md Emtazul");
        emtazul.setLastName("Haque");
        emtazul.setAddress("Germany");
        emtazul.setTelephone("123456");
        emtazul.setCity("Bonn");
        Set<Pet> emtazulPets = new HashSet<>();
        emtazulPets.add(tommy);
        tommy.setOwner(emtazul);
        emtazulPets.add(vulo);
        vulo.setOwner(emtazul);
        emtazul.setPets(emtazulPets);
        ownerService.save(emtazul);

        Owner torikul = new Owner();
        torikul.setFirstName("Torikul");
        torikul.setLastName("Islam");
        torikul.setAddress("Bangladesh");
        torikul.setCity("Dhaka");
        torikul.setTelephone("34567");
        Set<Pet> torikulPets = new HashSet<>();
        torikulPets.add(hosko);
        hosko.setOwner(torikul);
        torikul.setPets(torikulPets);
        ownerService.save(torikul);

        Owner ibrahim = new Owner();
        ibrahim.setFirstName("Ibrahim");
        ibrahim.setLastName("Khan");
        ibrahim.setAddress("Bangladesh");
        ibrahim.setCity("Madaripur");
        ibrahim.setTelephone("98765");
        Set<Pet> ibrahimPets = new HashSet<>();
        ibrahimPets.add(fire);
        fire.setOwner(ibrahim);
        ibrahim.setPets(ibrahimPets);
        ownerService.save(ibrahim);

        System.out.println("Owner data loaded");
        //End Owner data loading

        //Start Visit data loading
        Visit tommyVisit1 = new Visit();
        tommyVisit1.setDate(LocalDate.now());
        tommyVisit1.setDescription("Tommy visit 1");
        tommyVisit1.setPet(tommy);
        visitService.save(tommyVisit1);

        Visit tommyVisit2 = new Visit();
        tommyVisit2.setDate(LocalDate.now());
        tommyVisit2.setDescription("Tommy visit 2");
        tommyVisit2.setPet(tommy);
        visitService.save(tommyVisit2);

        Set<Visit> tommyVisits = new HashSet<>();
        tommyVisits.add(tommyVisit1);
        tommyVisits.add(tommyVisit2);
        tommy.setVisits(tommyVisits);

        Visit vuloVisit1 = new Visit();
        vuloVisit1.setDate(LocalDate.now());
        vuloVisit1.setDescription("Vulo visit 1");
        vuloVisit1.setPet(vulo);
        visitService.save(vuloVisit1);

        Set<Visit> vuloVisits = new HashSet<>();
        vuloVisits.add(vuloVisit1);
        vulo.setVisits(vuloVisits);

        Visit hoskoVisit1 = new Visit();
        hoskoVisit1.setDate(LocalDate.now());
        hoskoVisit1.setDescription("Hosko Visit 1");
        hoskoVisit1.setPet(hosko);
        visitService.save(hoskoVisit1);

        Visit hoskoVisit2 = new Visit();
        hoskoVisit2.setDate(LocalDate.now());
        hoskoVisit2.setDescription("Hosko Visit 2");
        hoskoVisit2.setPet(hosko);
        visitService.save(hoskoVisit2);

        Set<Visit> hoskoVisits = new HashSet<>();
        hoskoVisits.add(hoskoVisit1);
        hoskoVisits.add(hoskoVisit2);
        hosko.setVisits(hoskoVisits);

        Visit fireVisit1 = new Visit();
        fireVisit1.setDate(LocalDate.now());
        fireVisit1.setDescription("Fire Visit 1");
        fireVisit1.setPet(fire);
        visitService.save(fireVisit1);

        Set<Visit> fireVisits = new HashSet<>();
        fireVisits.add(fireVisit1);
        fire.setVisits(fireVisits);
        petService.save(fire);

        System.out.println("Visit data loaded");
        //End Visit data loading

        //Start Vet data loading
        Vet tamim = new Vet();
        tamim.setFirstName("Tamim");
        tamim.setLastName("Hawlader");
        Set<Speciality> tamimSpecialities = new HashSet<>();
        tamimSpecialities.add(surgery);
        tamimSpecialities.add(dentistry);
        tamim.setSpecialities(tamimSpecialities);
        vetService.save(tamim);

        Vet saiful = new Vet();
        saiful.setFirstName("Saiful");
        saiful.setLastName("Islam");
        Set<Speciality> saifulSpecialities = new HashSet<>();
        saifulSpecialities.add(surgery);
        saifulSpecialities.add(radiology);
        saiful.setSpecialities(saifulSpecialities);
        vetService.save(saiful);

        System.out.println("Vet data loaded");
        //End Vet data loading
    }
}
