package ru.job4j.trackersql;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.StartUI;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TrackerSQLTest {
    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

    @Test
    public void checkConnection2() {
        TrackerSQL sql = new TrackerSQL();
        StartUI startUI = new StartUI();
        startUI.init();
    }



}