package by.oreshko.myspring.repository;

import by.oreshko.myspring.entity.Person;
import by.oreshko.myspring.exceptions.ResourceNotFoundException;
import by.oreshko.myspring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }
    public void addNewPerson(Person person){
        personRepository.save(person);
    }
    public void deletePerson(Person person ){
        personRepository.delete(person);
    }
    public void editPerson(Person person, Long id){
        person.setId(id);
        personRepository.save(person);
    }
    public Person getById(long id) throws ResourceNotFoundException {
        return personRepository.findAllById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
}