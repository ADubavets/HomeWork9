package org.itstep;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable,Comparable {
    private String family;
    private String name;
    private String patronymic;
    private String nickname;
    private Date birthday;
    private String sBirthday;
    private String eMail;
    private String phoneMobil;
    private String phoneHome;
    private String phoneWork;
    private String phoneFax;
    private List<String> phones;

    Person(){}

    public Person(String family,
                  String name,
                  String patronymic,
                  String nickname,
                  String sBirthday,
                  String eMail,
                  String phoneMobil,
                  String phoneHome,
                  String phoneWork,
                  String phoneFax
    ) throws ParseException {
        this.family = family;
        this.name = name;
        this.patronymic = patronymic;
        this.nickname = nickname;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.birthday = dateFormat.parse(sBirthday);
        this.eMail = eMail;
        this.phoneMobil = phoneMobil;
        this.phoneHome = phoneHome;
        this.phoneWork = phoneWork;
        this.phoneFax = phoneFax;
    }
    public void setPhones(List<String> phones) {
        this.phones = phones;
    }
    public String toString(){

        final StringBuffer buffer = new StringBuffer("");
        buffer.append(family + "/");
        buffer.append(name + "/");
        buffer.append(patronymic + "/");
        buffer.append(nickname + "/");
        buffer.append(birthday + "/");
        buffer.append(eMail + "/");
        buffer.append(phoneMobil + "/");
        buffer.append(phoneHome + "/");
        buffer.append(phoneWork + "/");
        buffer.append(phoneFax);
        return buffer.toString();

    }

    @Override
    public int compareTo(Object o) {
        int result = this.family.compareTo(o.toString());
        return result;
    }

}
