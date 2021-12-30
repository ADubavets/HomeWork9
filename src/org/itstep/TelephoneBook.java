package org.itstep;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class TelephoneBook {
    public static Persons persons;

    public static void main(String[] args) {

        readXML();
        new MainForm();
    }

    public static void readXML() {
        persons = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            persons = (Persons) jaxbUnmarshaller.unmarshal(new File("src/org/itstep/Data/persons.xml"));
            persons.sortPersons();
        } catch (JAXBException e) {
            JOptionPane.showMessageDialog(
                    null,
                    " Не удается найти файл Базы Данных телефонного справочника",
                    "Внимание - ОШИБКА!!!",
                    JOptionPane.ERROR_MESSAGE);
            persons = new Persons();
            new MainForm();
        }
    }

    public static void writeXML() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
            //Print XML String to file
            jaxbMarshaller.marshal(persons, new File("src/org/itstep/Data/persons.xml"));

        } catch (JAXBException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Произошла ошибка при сохраниении данных в файл persons.xml",
                    "Внимание - ОШИБКА!!!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
