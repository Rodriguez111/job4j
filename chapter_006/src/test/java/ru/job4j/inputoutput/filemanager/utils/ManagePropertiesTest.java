package ru.job4j.inputoutput.filemanager.utils;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ManagePropertiesTest {


    @Test
    public void shouldReturnPort() {
        ManageProperties manageProperties = new ManageProperties();
       int actual = manageProperties.getPort();
        int expected = 5008;
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnIP() {
        ManageProperties manageProperties = new ManageProperties();
        String actual = manageProperties.getServerIpAddress();
        String expected = "127.0.0.1";
        assertThat(actual, is(expected));
    }

}