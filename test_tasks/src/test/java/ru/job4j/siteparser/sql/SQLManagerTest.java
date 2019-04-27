package ru.job4j.siteparser.sql;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.siteparser.Vacancy;
import ru.job4j.siteparser.args.ArgManager;
import ru.job4j.siteparser.utils.PropertiesManager;

import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SQLManagerTest {

    private final String systemPath = System.getProperty("java.io.tmpdir");
    private final String testFolder = systemPath + "00Test";
    private final String pathToFile = testFolder + "/app.properties";
    private final String pathToDBFile = testFolder + "/database/siteparser.db";
    private final String ls = System.lineSeparator();


    public void createPaths() {
        new File(testFolder).mkdirs();
    }

    @Before
    public void initPropertiesFile() throws IOException {
        createPaths();
        File  file = new File(pathToFile);
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("jdbc.driver=org.sqlite.JDBC")
                .append(ls)
                .append("jdbc.dbpath=" + pathToDBFile)
                .append(ls)
                .append("jdbc.url=jdbc:sqlite:"  + pathToDBFile)
                .append(ls)
                .append("cron.time=0 0 12 1/1 * ? *");
        BufferedInputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(stringBuilder.toString().getBytes()));
        while (inputStream.available() > 0) {
            outputStream.write(inputStream.read());
        }
        outputStream.close();
    }

//    @Test
//   public void whenAdd2EntriesThenReturnListWithSize2() throws IOException {
//        String[] args = {pathToFile};
//        ArgManager argManager = new ArgManager(args);
//        PropertiesManager.setPropertiesFile(argManager.getPropertiesFile());
//         Connection connection = ConnectionRollback.create(SQLConnection.createConnection());
//         SQLManager manager = new SQLManager(connection);
//        List<Vacancy> list = new ArrayList<>();
//        Vacancy vacancy1 = new Vacancy("name1", "text1", "link1", "author1", "2019-03-01 20:40");
//        Vacancy vacancy2 = new Vacancy("name2", "text2", "link2", "author2", "2019-03-02 18:40");
//        list.add(vacancy1);
//        list.add(vacancy2);
//        manager.addEntryList(list);
//
//        List<Vacancy> resultList = manager.selectAll();
//        int actual = resultList.size();
//        assertThat(actual, is(2));
//   }



}