package ru.advancedtraining.xml.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;



public class XMLreaderJaxb {
    public static void main(String[] args) throws JAXBException {
        ClassLoader classLoader = ru.advancedtraining.xml.sax.XMLreader.class.getClassLoader();

        File fileShare = new File(classLoader.getResource("share.xml").getFile());

        JAXBContext jaxbContext = JAXBContext.newInstance(Share.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Share share = (Share) unmarshaller.unmarshal(fileShare);
        System.out.println(share);

        File fileData = new File(classLoader.getResource("data.xml").getFile());

        JAXBContext jaxbContext2 = JAXBContext.newInstance(Shares.class);
        Unmarshaller unmarshaller2 = jaxbContext2.createUnmarshaller();
        Shares shareList = (Shares) unmarshaller2.unmarshal(fileData);
        System.out.println(shareList);
    }
}
