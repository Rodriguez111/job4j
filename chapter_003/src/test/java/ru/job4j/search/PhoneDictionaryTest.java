package ru.job4j.search;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class PhoneDictionaryTest {

    private List<Person> test = new ArrayList<>();

    private void fillPersons() {
        test = List.of(new Person("Иван", "Иванов", "Весенняя 15 кв. 60", "+15246325874"),
                new Person("Петр", "Петров", "Летняя 18 кв. 21", "+15246325458"),
                new Person("Василий", "Васильев", "Зимняя 5 кв. 48", "+15240025974"));
    }

    {
      fillPersons();
    }

    private void addPersons(PhoneDictionary phoneDictionary) {
        for (Person eachPerson : test) {
            phoneDictionary.add(eachPerson);
        }
    }

    @Test public void whenFindBySirnameSuffix() {
        PhoneDictionary phoneDictionary = new PhoneDictionary();
        addPersons(phoneDictionary);
        List<Person> actual = phoneDictionary.find("ов");
        List<Person> expected = List.of(test.get(0), test.get(1));
        assertThat(actual, is(expected));
    }

    @Test public void whenFindByName() {
        PhoneDictionary phoneDictionary = new PhoneDictionary();
        addPersons(phoneDictionary);
        List<Person> actual = phoneDictionary.find("Иван");
        List<Person> expected = List.of(test.get(0));
        assertThat(actual, is(expected));
    }

    @Test public void whenFindPhonePart() {
        PhoneDictionary phoneDictionary = new PhoneDictionary();
        addPersons(phoneDictionary);
        List<Person> actual = phoneDictionary.find("+1");
        List<Person> expected = List.of(test.get(0), test.get(1), test.get(2));
        assertThat(actual, is(expected));
    }

}
