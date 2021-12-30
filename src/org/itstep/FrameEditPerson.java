package org.itstep;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.regex.Pattern;

import static org.itstep.MainForm.mainFrame;
import static org.itstep.MainForm.tableModel;
import static org.itstep.TelephoneBook.persons;
import static org.itstep.TelephoneBook.writeXML;


public class FrameEditPerson implements ActionListener {
    private final JFrame frame;
    // кнопки окна
    private final JButton editPerson;
    // окна для отображения

    private final JTextField phoneNumberHomeEdit;
    private final JTextField phoneNumberWorkEdit;
    private final JTextField phoneNumberMobilEdit;
    private final JTextField faxEdit;
    private final JTextField eMailEdit;
    private static String family;
    private static String name;
    private static String patronymic;
    private static String phoneMobil;
    private static String phoneHome;
    private static String phoneWork;
    private static String fax;
    private static String eMail;

    public FrameEditPerson() {
        // Создание окна - объекта класса JFrame.
        // Текстовый аргумент конструктора задает имя окна:
        frame = new JFrame("Изменение записи ...");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(530, 270);
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocation(x, y);
        Image icon = frame.getToolkit().getImage("src/org/itstep/Icon/add user.png");
        // фамилия
        JLabel familyLabel = new JLabel("Фамилия:");
        familyLabel.setBounds(20, 40, 100, 20);
        frame.add(familyLabel);
        JLabel famLabel = new JLabel(family);
        famLabel.setBounds(85, 40, 100, 20);
        famLabel.setForeground(Color.WHITE);
        frame.add(famLabel);

        // имя
        JLabel nameLabel = new JLabel("Имя: ");
        nameLabel.setBounds(20, 70, 100, 20);
        frame.add(nameLabel);
        JLabel namLabel = new JLabel(name);
        namLabel.setBounds(85, 70, 100, 20);
        namLabel.setForeground(Color.WHITE);
        frame.add(namLabel);

        // отчество
        JLabel patronymicLabel = new JLabel("Отчество:");
        patronymicLabel.setBounds(20, 100, 100, 20);
        frame.add(patronymicLabel);
        JLabel patLabel = new JLabel(patronymic);
        patLabel.setBounds(85, 100, 100, 20);
        patLabel.setForeground(Color.WHITE);
        frame.add(patLabel);

        // мобильный телефон
        JLabel phoneNumberMobilLabel = new JLabel("Моб. телефон:");
        phoneNumberMobilLabel.setBounds(240, 40, 255, 20);
        frame.add(phoneNumberMobilLabel);
        phoneNumberMobilEdit = new JTextField();
        phoneNumberMobilEdit.setText(phoneMobil);
        phoneNumberMobilEdit.setBounds(340, 40, 140, 20);
        phoneNumberMobilEdit.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }
        });
        if (!Objects.equals(phoneNumberMobilEdit.getText(), "")) phoneNumberMobilEdit.setEnabled(true);
        frame.add(phoneNumberMobilEdit);

        // домашний телефон
        JLabel phoneNumberHomeLabel = new JLabel("Дом. телефон:");
        phoneNumberHomeLabel.setBounds(240, 70, 255, 20);
        frame.add(phoneNumberHomeLabel);
        phoneNumberHomeEdit = new JTextField();
        phoneNumberHomeEdit.setText(phoneHome);
        phoneNumberHomeEdit.setBounds(340, 70, 140, 20);
        frame.add(phoneNumberHomeEdit);

        // рабочий телефон
        JLabel phoneNumberWorkLabel = new JLabel("Раб. телефон:");
        phoneNumberWorkLabel.setBounds(240, 100, 255, 20);
        frame.add(phoneNumberWorkLabel);
        phoneNumberWorkEdit = new JTextField();
        phoneNumberWorkEdit.setText(phoneWork);
        phoneNumberWorkEdit.setBounds(340, 100, 140, 20);
        frame.add(phoneNumberWorkEdit);

        // факс
        JLabel faxLabel = new JLabel("Факс:");
        faxLabel.setBounds(240, 130, 255, 20);
        frame.add(faxLabel);
        faxEdit = new JTextField();
        faxEdit.setText(fax);
        faxEdit.setBounds(340, 130, 140, 20);
        frame.add(faxEdit);

        // E-Mail
        JLabel eMailLabel = new JLabel("E-mail:");
        eMailLabel.setBounds(240, 160, 255, 20);
        frame.add(eMailLabel);
        eMailEdit = new JTextField();
        eMailEdit.setText(eMail);
        eMailEdit.setBounds(340, 160, 140, 20);
        frame.add(eMailEdit);

        // кнопка "Добавить"
        editPerson = new JButton("Добавить запись");
        editPerson.setBounds(40, 190, 200, 25);
        editPerson.setIcon(new ImageIcon("src/org/itstep/Icon/add user.png"));
        editPerson.setEnabled(false);
        editPerson.addActionListener(this::actionEditPerson);
        frame.add(editPerson);
        // кнопка "Отмена"
        JButton exitButton = new JButton("Отмена");
        exitButton.setBounds(270, 190, 200, 25);
        exitButton.setIcon(new ImageIcon("src/org/itstep/Icon/delete.png"));
        exitButton.addActionListener(e -> {
            frame.setVisible(false);
            mainFrame.setEnabled(true);
        });
        frame.add(exitButton);
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 505, 220);
        panel.setBackground(Color.gray);
        frame.add(panel);
        frame.setLayout(null);
        frame.setIconImage(icon);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    private void actionEditPerson(ActionEvent e) {
        if (!checkMobilPhone(
                phoneNumberMobilEdit.getText())) {
            JOptionPane.showMessageDialog(
                    null,
                    "Введите корректно номер телефона, например: +375(ХХ)ХХХ-ХХ-ХХ",
                    "Внимание - КОРРЕКТИРОВКА!!!",
                    JOptionPane.INFORMATION_MESSAGE);
            phoneNumberMobilEdit.requestFocus();
            return;
        }
        if (!phoneNumberHomeEdit.getText().equals("")) {
            if (checkCityPhone(phoneNumberHomeEdit.getText())) {
                JOptionPane.showMessageDialog(
                        null,
                        "Введите корректно номер телефона, например: ХХ-ХХ-ХХ",
                        "Внимание - КОРРЕКТИРОВКА!!!",
                        JOptionPane.INFORMATION_MESSAGE);
                phoneNumberHomeEdit.requestFocus();
                return;
            }
        }
        if (!phoneNumberWorkEdit.getText().equals("")) {
            if (checkCityPhone(phoneNumberWorkEdit.getText())) {
                JOptionPane.showMessageDialog(
                        null,
                        "Введите корректно номер телефона, например: ХХ-ХХ-ХХ",
                        "Внимание - КОРРЕКТИРОВКА!!!",
                        JOptionPane.INFORMATION_MESSAGE);
                phoneNumberWorkEdit.requestFocus();
                return;
            }
        }
        if (!faxEdit.getText().equals("")) {
            if (checkCityPhone(faxEdit.getText())) {
                JOptionPane.showMessageDialog(
                        null,
                        "Введите корректно номер телефона, например: ХХ-ХХ-ХХ",
                        "Внимание - КОРРЕКТИРОВКА!!!",
                        JOptionPane.INFORMATION_MESSAGE);
                faxEdit.requestFocus();
                return;
            }
        }
        for (Person person: persons.getPersons()) {
            if (Objects.equals(family, person.getFamily()) &&
                    Objects.equals(name, person.getName()) &&
                    Objects.equals(patronymic, person.getPatronymic())) {
                    person.setPhoneMobil(phoneNumberMobilEdit.getText());
                    person.setPhoneHome(phoneNumberHomeEdit.getText());
                    person.setPhoneWork(phoneNumberWorkEdit.getText());
                    person.setPhoneFax(faxEdit.getText());
                    person.seteMail(eMailEdit.getText());
                    break;
            }
        }
        writeXML();
        MainForm.enterDataTable(tableModel);
        frame.setVisible(false);
        mainFrame.setEnabled(true);
    }

    public static void showEditPerson(String a, String b, String c,String phone1, String phone2, String phone3, String phone4, String eMail1) {
        family = a;
        name = b;
        patronymic = c;
        phoneMobil = phone1;
        phoneHome = phone2;
        phoneWork = phone3;
        fax = phone4;
        eMail = eMail1;
        new FrameEditPerson();
        mainFrame.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
        mainFrame.setEnabled(true);
    }
    private void changed() {
        editPerson.setEnabled((!phoneNumberMobilEdit.getText().equals("")));
    }

    static boolean checkMobilPhone(String sCorrect) {
        return Pattern.matches("[+]\\d{3}[(]\\d{2}[)]\\d{3}-\\d{2}-\\d{2}", sCorrect);
    }

    static boolean checkCityPhone(String sCorrect) {
        return !Pattern.matches("\\d{2}[-]\\d{2}[-]\\d{2}", sCorrect);
    }
}
