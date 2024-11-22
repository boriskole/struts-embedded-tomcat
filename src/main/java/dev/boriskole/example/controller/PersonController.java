package dev.boriskole.example.controller;

import dev.boriskole.example.domain.Person;
import dev.boriskole.example.service.PersonService;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

import java.util.List;

public class PersonController {

    private final PersonService personService = new PersonService();
    private List<Person> people;

    public String execute() {
        people = personService.generatePeople(10);
        return "success";
    }

    @StrutsParameter
    public List<Person> getPeople() {
        return people;
    }

}
