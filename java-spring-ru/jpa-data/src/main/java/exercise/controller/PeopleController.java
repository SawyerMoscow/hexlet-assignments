package exercise.controller;

import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

import exercise.model.Person;

@RestController
public class PeopleController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/people/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Person> show(@PathVariable long id) {

        return personRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/people")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> show() {
        return personRepository.findAll();
    }

    @PostMapping("/people")
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person page) {
        personRepository.save(page);
        return page;
    }

    @DeleteMapping("/people/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void destroy(@PathVariable Long id) {
        personRepository.deleteById(id);
    }
    // END
}
