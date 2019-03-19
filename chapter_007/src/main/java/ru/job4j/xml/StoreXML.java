package ru.job4j.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class StoreXML {
   private static final Logger LOG = LoggerFactory.getLogger(StoreXML.class);
   private final File target;
   private JAXBContext context;
   private Marshaller marshaller;

    public StoreXML(File target) {
        this.target = target;
        initJAXB();
    }

    private void initJAXB() {
        try {
            this.context = JAXBContext.newInstance(Element.class);
            this.marshaller = context.createMarshaller();
            this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void save(Element element) {
       LOG.info("Saving data to primary XML file...");
       try {
           marshaller.marshal(element, target);
       } catch (JAXBException e) {
           e.printStackTrace();
       }

   }



}
