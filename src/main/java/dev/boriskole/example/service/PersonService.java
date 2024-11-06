package dev.boriskole.example.service;

import dev.boriskole.example.domain.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonService {

    private final Random random = new Random();
    private static final String[] NAMES = {"Jack", "Donald", "Paul", "John"};
    private static final String[] ADDRESSES = {
        "123 Main St, Springfield",
        "456 Oak Ave, Shelbyville",
        "789 Pine Rd, Ogdenville",
        "101 Maple Dr, Capital City"
    };

    private Person getRandomPerson() {
        return new Person(
            random.nextInt(1, 100),
            NAMES[random.nextInt(NAMES.length)],
            ADDRESSES[random.nextInt(ADDRESSES.length)],
            LocalDate.of(
                random.nextInt(1950, 2024),
                random.nextInt(1, 13),
                random.nextInt(1, 29)
            )
        );
    }

    public List<Person> generatePeople(int amount) {
        return Stream.generate(this::getRandomPerson)
            .limit(amount)
            .collect(Collectors.toList());
    }

}
