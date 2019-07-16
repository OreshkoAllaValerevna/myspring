package by.oreshko.myspring.service;

import by.oreshko.myspring.entity.Person;
import by.oreshko.myspring.exceptions.ResourceNotFoundException;

import java.util.List;

public interface PersonService {
    List<Person> getAllPerson();
    void addNewPerson(Person person);
    void deletePerson(Person person );
    void editPerson(Person person, Long id);
    Person getById(long id) throws ResourceNotFoundException;

}
