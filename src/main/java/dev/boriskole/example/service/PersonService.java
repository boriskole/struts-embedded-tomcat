package dev.boriskole.example.service;

import dev.boriskole.example.domain.Person;

import java.time.LocalDate;
import java.time.Month;
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
        int id = random.nextInt(1, 100);
        String name = NAMES[random.nextInt(NAMES.length)];
        String address = ADDRESSES[random.nextInt(ADDRESSES.length)];

        int year = random.nextInt(1960, 2024);
        int month = random.nextInt(1, 13);
        int day = random.nextInt(1, Month.of(month).maxLength());

        return new Person(id, name, address, LocalDate.of(year, month, day));
    }

    public List<Person> generatePeople(int amount) {
        return Stream.generate(this::getRandomPerson)
            .limit(amount)
            .collect(Collectors.toList());
    }

}
