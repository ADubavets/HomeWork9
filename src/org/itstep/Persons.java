package org.itstep;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@XmlRootElement(name = "persons")
@XmlAccessorType( XmlAccessType.FIELD)
public class Persons extends ArrayList implements Serializable {
    @XmlElement(name = "person")
    private final List<Person> persons = new ArrayList<>();

    public void addPerson(Person person){
        persons.add(person);
    }

    public void removePerson(Person person){
        persons.remove(person);
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void sortPersons(){
        Collections.sort(persons);
    }
}
