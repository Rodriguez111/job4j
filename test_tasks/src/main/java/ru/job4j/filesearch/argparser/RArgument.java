package ru.job4j.filesearch.argparser;

import java.util.Objects;

public class RArgument {
    private String value;
    private String description;

    public RArgument(String value) {
        this.value = value;
    }

    public RArgument(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getArgumentGuide() {
        return "    " + value + ": " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RArgument that = (RArgument) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}