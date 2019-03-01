package ru.job4j.inputoutput;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SearchTest {

    String parent = System.getProperty("java.io.tmpdir") + "/00JavaTest";
    List<String> extensions;

    @Before
    public void init() throws IOException {
        extensions = new ArrayList<>();
        createTestFilesStructure();
    }

    public void createTestFilesStructure() throws IOException {
        new File(parent).mkdirs();
        new File(parent + "/Новый текстовый документ.exe").createNewFile();
        new File(parent + "/Папка01").mkdirs();
        new File(parent + "/Папка01/Новый текстовый документ.txt").createNewFile();
        new File(parent + "/Папка02").mkdirs();
        new File(parent + "/Папка02/Новый текстовый документ.exe").createNewFile();
        new File(parent + "/Папка02/Новый текстовый документ.txt").createNewFile();
        new File(parent + "/Папка01/Папка011").mkdirs();
        new File(parent + "/Папка01/Папка011/Новый текстовый документ.txt").createNewFile();
        new File(parent + "/Папка02/Папка 012").mkdirs();
        new File(parent + "/Папка02/Папка 012/Новый текстовый документ.exe").createNewFile();
        new File(parent + "/Папка02/Папка 012/Новый текстовый документ.txt").createNewFile();
        new File(parent + "/Папка01/Папка011/015").mkdirs();
        new File(parent + "/Папка01/Папка011/015/Новый текстовый документ.txt").createNewFile();
        new File(parent + "/Папка02/Папка 012/022").mkdirs();
        new File(parent + "/Папка02/Папка 012/022/Новый текстовый доку.txt").createNewFile();
        new File(parent + "/Папка02/Папка 012/022/Новый текстовый документ.exe").createNewFile();
    }

//    @Test
//    public void shouldReturn10Files() {
//        extensions.add("exe");
//        extensions.add("txt");
//
//        Search search = new Search();
//        List<File> actual =  search.files(parent, extensions, true);
//
//        File file1 = new File(parent + "/Новый текстовый документ.exe");
//        File file2 = new File(parent + "/Папка01/Новый текстовый документ.txt");
//        File file3 = new File(parent + "/Папка02/Новый текстовый документ.exe");
//        File file4 = new File(parent + "/Папка02/Новый текстовый документ.txt");
//        File file5 = new File(parent + "/Папка01/Папка011/Новый текстовый документ.txt");
//        File file6 = new File(parent + "/Папка02/Папка 012/Новый текстовый документ.exe");
//        File file7 = new File(parent + "/Папка02/Папка 012/Новый текстовый документ.txt");
//        File file8 = new File(parent + "/Папка01/Папка011/015/Новый текстовый документ.txt");
//        File file9 = new File(parent + "/Папка02/Папка 012/022/Новый текстовый доку.txt");
//        File file10 = new File(parent + "/Папка02/Папка 012/022/Новый текстовый документ.exe");
//
//        List<File> expected = List.of(file1, file2, file3, file4, file5, file6, file7, file8, file9, file10);
//        assertThat(actual, is(expected));
//    }

}