package PetClinic.Controllers;

import PetClinicData.Model.Owner;
import PetClinicData.Service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping({"/owners"})
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String index(Model model) throws Exception{
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping({"/registerOwner", "/registerOwner.html"})
    public String registerOwnerGet(Model model) throws Exception
    {
        Owner owner = new Owner();
        model.addAttribute("owner", owner);
        return "owners/registerOwner";
    }

    @RequestMapping(value = {"/registerOwner"}, method = RequestMethod.POST)
    public String registerOwnerPost(@Valid @ModelAttribute("owner") Owner owner, BindingResult bindingResult, Model model) throws Exception
    {
        if(bindingResult.hasErrors())
        {
            return "owners/registerOwner";
        }
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/viewOwnerDetails/"+savedOwner.getId();
    }

    @RequestMapping({"/viewOwnerDetails/{id}"})
    public String viewOwnerDetails(@PathVariable("id") Long id, Model model) throws Exception
    {
        Owner owner = ownerService.findById(id);
        model.addAttribute("owner", owner);
        return "owners/viewOwnerDetails";
    }

    @RequestMapping({"/editOwner/{id}"})
    public String editOwner(@PathVariable("id") Long id, Model model) throws Exception
    {
        model.addAttribute("owner", ownerService.findById(id));
        return "owners/registerOwner";
    }

    @RequestMapping({"/deleteOwner/{id}"})
    public String deleteOwner(@PathVariable("id") Long id, Model model) throws Exception
    {
        ownerService.deleteById(id);
        return "redirect:/owners/index";
    }
}
