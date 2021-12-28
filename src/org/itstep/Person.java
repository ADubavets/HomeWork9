package org.itstep;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable,Comparable {
    private static int count = 0;
    private final int id = ++count;
    private String family;
    private String name;
    private String patronymic;
    private Date birthday;
    private String sBirthday;
    private String eMail;
    private String phoneMobil;
    private String phoneHome;
    private String phoneWork;
    private String phoneFax;

    Person(){}

    public Person(String family,
                  String name,
                  String patronymic,
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.birthday = dateFormat.parse(sBirthday);
        this.eMail = eMail;
        this.phoneMobil = phoneMobil;
        this.phoneHome = phoneHome;
        this.phoneWork = phoneWork;
        this.phoneFax = phoneFax;
    }
    private int getCount(){
        return count++;
    }

    public String toString(){

        final StringBuffer buffer = new StringBuffer();
        buffer.append(family);
        if (!name.equals(""))
            buffer.append(" ").append(name);
        if (!patronymic.equals(""))
            buffer.append(" ").append(patronymic);
        buffer.append(". День рождения: ").append(birthday).append(";");
        if (!eMail.equals(""))
            buffer.append("; E-mail: ").append(eMail).append(";");
        buffer.append(" моб. телефон: ").append(phoneMobil);
        if (!phoneHome.equals(""))
            buffer.append("; дом. телефон: ").append(phoneHome);
        if (!phoneWork.equals(""))
            buffer.append("; раб. телефон: ").append(phoneWork);
        if (!phoneFax.equals(""))
            buffer.append("; факс: ").append(phoneFax);
        return buffer.toString();
    }

    @Override
    public int compareTo(Object o) {
        int result = this.family.compareTo(o.toString());
        return result;
    }

    public String getFamily() {
        return family;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getsBirthday() {
        return sBirthday;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPhoneMobil() {
        return phoneMobil;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getPhoneWork() {
        return phoneWork;
    }

    public String getPhoneFax() {
        return phoneFax;
    }
}
