package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подошедщих пользователей.
     */
    public List<Person> find(String key) {
        return  persons.stream().filter(persons -> persons.getName().contains(key)
        || persons.getSirname().contains(key)
        || persons.getAddress().contains(key)
        || persons.getPhone().contains(key)).collect(Collectors.toList());
    }

}
