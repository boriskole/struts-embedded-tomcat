package dev.boriskole.example.controller;

import dev.boriskole.example.domain.Person;
import dev.boriskole.example.service.PersonService;

import java.util.List;

public class PersonAction {

    private final PersonService personService = new PersonService();
    private List<Person> people;

    public String execute() {
        people = personService.generatePeople(10);
        return "success";
    }

    public List<Person> getPeople() {
        return people;
    }

}
