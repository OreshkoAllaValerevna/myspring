package by.oreshko.myspring.controller;


import by.oreshko.myspring.dto.NewPersonDto;
import by.oreshko.myspring.dto.PersonDto;
import by.oreshko.myspring.entity.Person;
import by.oreshko.myspring.exceptions.ResourceNotFoundException;
import by.oreshko.myspring.service.PersonService;
import by.oreshko.myspring.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
class PersonController {
    private final PersonService personService;
    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
        // this.personRepository = personRepository;
    }
    //, produces = { "application/json" , "application/xml"}
    @GetMapping(value = {"/personList"})
    public List<PersonDto> personList() {
        return Mapper.mapAll(personService.getAllPerson(), PersonDto.class);
    }
    @GetMapping(value = {"/personList/{id}"})
    public PersonDto findById(@PathVariable("id") Long id) throws
            ResourceNotFoundException {
        return Mapper.map(personService.getById(id), PersonDto.class);
    }
    @PutMapping(value = "/editPerson/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editPerson(@PathVariable("id") Long id, @Valid @RequestBody
            PersonDto persondto) throws ResourceNotFoundException {
        personService.getById(id);
        personService.editPerson(Mapper.map(persondto, Person.class),id);
    }
    @PostMapping("/addPerson")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePerson( @Valid @RequestBody NewPersonDto personDto) {
        personService.addNewPerson(Mapper.map(personDto, Person.class));
    }
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(@PathVariable("id") Long id) throws
            ResourceNotFoundException {
        personService.deletePerson(personService.getById(id));
    }
}
