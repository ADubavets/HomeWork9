package org.itstep;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class TelephoneBook {
    public static Persons persons;
    public static void main(String[] args) {
        readXML();
        new MainForm();
    }
    public static void readXML(){
        persons = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            persons = (Persons) jaxbUnmarshaller.unmarshal( new File("src/org/itstep/Data/persons.xml") );
        } catch (JAXBException e) {
            JOptionPane.showMessageDialog(
                    null,
                    " Не удается найти файл Базы Данных телефонного справочника",
                    "Внимание - ОШИБКА!!!",
                    JOptionPane.ERROR_MESSAGE);
            new MainForm();
        }
        for(Person person : persons.getPersons())
            System.out.println(person.toString());
    }
}
