package by.oreshko.myspring.controller;

import by.oreshko.myspring.form.PersonForm;
import by.oreshko.myspring.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping
public class MainController {

    private static List<Person> persons = new ArrayList<Person>();
    static {
        persons.add(new Person("Alla", "Oreshko", "Sovetskaya str.", "Minsk", "123456", "oreshko@tut.by", "1234567", "10/10/2000"));
        persons.add(new Person("Olga", "Lapina", "Surganova str.", "Minsk", "987654", "lapina@tut.by", "9876543", "20/02/2000"));
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

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
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("personList");
        model.addAttribute("persons", persons);

        log.info("personList was called");

        return modelAndView;
    }

    @GetMapping(value = {"/addPerson"})
    public ModelAndView showAddPerson (Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addPerson");
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);

        log.info("addPerson was called (GET)");

        return modelAndView;
    }

    @PostMapping(value = {"/addPerson"})
    public ModelAndView savePerson (Model model,
                                    @Valid @ModelAttribute("personForm") PersonForm personForm, Errors errors) {
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()){
            modelAndView.setViewName("addPerson");
            log.info("addPerson was called (POST). New person wasn't created");
        }
        else {
        modelAndView.setViewName("personList");
        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();
        String street = personForm.getStreet();
        String city = personForm.getCity();
        String zip = personForm.getZip();
        String email = personForm.getEmail();
        String phone = personForm.getPhone();
        String birthday = personForm.getBirthday();
        Person newPerson = new Person(firstName, lastName, street, city, zip, email, phone, birthday);
        persons.add(newPerson);
        model.addAttribute("persons", persons);

        log.info("addPerson was called (POST). New person was created");

        return modelAndView;
    }
        return modelAndView;
    }
}
