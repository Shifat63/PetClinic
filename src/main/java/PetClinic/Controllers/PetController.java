package PetClinic.Controllers;

import PetClinic.Model.Pet;
import PetClinic.Service.OwnerService;
import PetClinic.Service.PetService;
import PetClinic.Service.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping({"/pets"})
public class PetController {
    private PetService petService;
    private OwnerService ownerService;
    private PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String index(Model model) throws Exception
    {
        model.addAttribute("pets", petService.findAll());
        return "/pets/index";
    }

    @RequestMapping({"/viewPetDetails/{id}"})
    public String viewPetDetails(@PathVariable("id") Long id, Model model) throws Exception
    {
        model.addAttribute("pet", petService.findById(id));
        return "/pets/viewPetDetails";
    }

    @RequestMapping({"/registerPet"})
    public String registerPetGet(Model model) throws Exception
    {
        model.addAttribute("pet", new Pet());
        model.addAttribute("owners", ownerService.findAll());
        model.addAttribute("petTypes", petTypeService.findAll());
        return "/pets/registerPet";
    }

    @RequestMapping(value = {"/registerPet"}, method = RequestMethod.POST)
    public String registerPetPost(@Valid @ModelAttribute("pet") Pet pet, BindingResult bindingResult, Model model) throws Exception
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("owners", ownerService.findAll());
            model.addAttribute("petTypes", petTypeService.findAll());
            return "/pets/registerPet";
        }
        Pet savedPet = petService.save(pet);
        return "redirect:/pets/viewPetDetails/" + savedPet.getId();
    }

    @RequestMapping({"/editPet/{id}"})
    public String editPet(@PathVariable("id") Long id, Model model) throws Exception
    {
        model.addAttribute("pet", petService.findById(id));
        model.addAttribute("owners", ownerService.findAll());
        model.addAttribute("petTypes", petTypeService.findAll());
        return "/pets/registerPet";
    }

    @RequestMapping({"/deletePet/{id}"})
    public String deletePet(@PathVariable("id") Long id) throws Exception
    {
        petService.deleteById(id);
        return "redirect:/pets/index";
    }
}
