package ru.job4j.xml;

import java.io.File;
import java.util.List;

public class Main {
    private static final Config CONFIG = new Config();


    public static void main(String[] args) {
        StoreSQL storeSQL = new StoreSQL();
        long beginTime = System.currentTimeMillis();
        storeSQL.generateLessThenMillionEntries(1000);
        //storeSQL.generateMoreThenMillionEntries(100000);

        List<Entry> list = storeSQL.selectAllToList();
        Element element = new Element(list);
        String primaryXML = StoreXML.class.getClassLoader().getResource("").getFile() + CONFIG.get("fileXMLSource");
        StoreXML storeXML = new StoreXML(new File(primaryXML));
        storeXML.save(element);

        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(new File(convertXSQT.getSource()), new File(convertXSQT.getOutput()), new File(convertXSQT.getTransformPatternXsl()));

        ParseOutputResult parseOutputResult = new ParseOutputResult();
        parseOutputResult.parse();
        parseOutputResult.outputResult();

        long endTime = System.currentTimeMillis();
        System.out.println((endTime - beginTime) / 1000 + "sec");
    }
}
