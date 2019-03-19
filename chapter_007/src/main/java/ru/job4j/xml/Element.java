package ru.job4j.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Element {

    private List<Entry> entryList;

    public Element() {
    }

    public Element(List<Entry> entryList) {
        this.entryList = entryList;
    }

    public List<Entry> getEntryList() {
        return entryList;
    }
    @XmlElement
    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }
}
