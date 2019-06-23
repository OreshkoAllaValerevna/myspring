package by.oreshko.myspring.controller;

import by.oreshko.myspring.dto.NewPersonDto;
import by.oreshko.myspring.entity.Person;
import by.oreshko.myspring.exceptions.NoSuchEntityException;
import by.oreshko.myspring.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping
public class PersonController {

    private final PersonService personService;

    // private static final org.slf4j.Logger log =
    // org.slf4j.LoggerFactory.getLogger(MainController.class);
    // Вводится (inject) из application.properties.

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = {"/", "/index"})
    public ModelAndView index (Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);

        log.info("index was called");

        return modelAndView;
    }

    @GetMapping(value = {"/personList"})
    public ModelAndView personList (Model model) {
        List<Person> persons = personService.getAllPerson();

        log.info("person List " + persons);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("personList");
        model.addAttribute("persons", persons);

        log.info("personList was called");

        return modelAndView;
    }

    @GetMapping(value = {"/addPerson"})
    public ModelAndView showAddPerson (Model model) {
        ModelAndView modelAndView = new ModelAndView("addPerson");
        NewPersonDto personForm = new NewPersonDto();
        model.addAttribute("personForm", personForm);

        log.info("addPerson was called (GET) " + personForm);

        return modelAndView;
    }

    @PostMapping(value = {"/addPerson"})
    public ModelAndView savePerson (Model model,
                                    @Valid @ModelAttribute("personForm") NewPersonDto personDto, Errors errors) {
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()){
            modelAndView.setViewName("addPerson");
            log.info("addPerson was called (POST). New person wasn't created " + personDto);
        }
        else {
            modelAndView.setViewName("personList");
            Long id = personDto.getPersonId();
            String firstName = personDto.getFirstName();
            String lastName = personDto.getLastName();
            String street = personDto.getStreet();
            String city = personDto.getCity();
            String zip = personDto.getZip();
            String email = personDto.getEmail();
            String phone = personDto.getPhone();
            Date birthday = (Date) personDto.getBirthday();
            Person newPerson = new Person(id, firstName, lastName, street, city, zip, email, phone, birthday);
            personService.addNewPerson(newPerson);
            model.addAttribute("persons", personService.getAllPerson());

            log.info("addPerson was called (POST). New person was created "+ personDto);

            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/editPerson/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) throws NoSuchEntityException {
        Person person = personService.getById(id).orElseThrow(()-> new NoSuchEntityException("Person not found") );
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPerson");
        modelAndView.addObject("person", person);
        return modelAndView;
    }

    @RequestMapping(value = "/editPerson", method = RequestMethod.POST)
    public ModelAndView editPerson( @Valid @ModelAttribute("person") Person person, Errors errors) {
        log.info("/editPerson - POST was called "+ person);
        personService.addNewPerson(person);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/personList");
        return modelAndView;
    }

    @RequestMapping(value = "/deletePerson/{id}", method = RequestMethod.GET)
    public ModelAndView deletePerson(@PathVariable("id") Long id) throws NoSuchEntityException {
        Person person = personService.getById(id).orElseThrow(()-> new NoSuchEntityException("Person not found") );
        personService.deletePerson(person);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/personList");
        return modelAndView;
    }

}