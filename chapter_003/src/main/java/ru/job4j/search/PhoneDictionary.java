package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

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
        List<Person> result = new ArrayList<>();
        for (Person eachPerson : persons) {
            if (eachPerson.getName().contains(key)
            || eachPerson.getSirname().contains(key)
            || eachPerson.getAddress().contains(key)
            || eachPerson.getPhone().contains(key)) {
                result.add(eachPerson);
            }
        }
        return result;
    }

}
