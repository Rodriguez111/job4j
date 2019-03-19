package ru.job4j.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Entry {

    private int value;

    public Entry() {
    }

    public Entry(int value) {
        this.value = value;
    }


    public int getValue() {
        return value;
    }

    @XmlElement
    public void setValue(int value) {
        this.value = value;
    }
}
