package ru.job4j.xml;

import java.io.File;
import java.util.List;

public class Main {
    private static final Config CONFIG = new Config();


    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        StoreSQL storeSQL = new StoreSQL();

        storeSQL.generateLessThenMillionEntries(10000);
        //storeSQL.generateMoreThenMillionEntries(100000);

        List<Entry> list = storeSQL.selectAllToList();
        Element element = new Element(list);
        String primaryXML = CONFIG.getSource();
        StoreXML storeXML = new StoreXML(new File(primaryXML));
        storeXML.save(element);

        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(new File(CONFIG.getSource()), new File(CONFIG.getOutput()), new File(CONFIG.getTransformPatternXsl()));

        ParseOutputResult parseOutputResult = new ParseOutputResult();
        parseOutputResult.parse();
        parseOutputResult.outputResult();

        long endTime = System.currentTimeMillis();
        System.out.println((endTime - beginTime) / 1000 + "sec");
    }
}
