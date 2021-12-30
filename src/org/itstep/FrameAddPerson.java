package org.itstep;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Objects;
import java.util.regex.Pattern;

import static javax.swing.JOptionPane.YES_NO_OPTION;
import static org.itstep.MainForm.mainFrame;
import static org.itstep.MainForm.tableModel;
import static org.itstep.TelephoneBook.persons;

public class FrameAddPerson implements ActionListener {
    private final JFrame frame;
    // кнопки окна
    private final JButton addPersonButton;
    private final JButton exitButton;
    // окна для отображения
    private final JTextField nameEdit;
    private final JTextField familyEdit;
    private final JTextField patronymicEdit;
    private final JTextField birthdayEdit;
    private final JTextField phoneNumberHomeEdit;
    private final JTextField phoneNumberWorkEdit;
    private final JTextField phoneNumberMobilEdit;
    private final JTextField faxEdit;
    private final JTextField eMailEdit;

    FrameAddPerson() {
        // Создание окна - объекта класса JFrame.
        // Текстовый аргумент конструктора задает имя окна:
        frame = new JFrame("Добавить запись ...");
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
        familyEdit = new JTextField();
        familyEdit.setBounds(85, 40, 140, 20);
        familyEdit.getDocument().addDocumentListener(new DocumentListener() {
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
        frame.add(familyEdit);
        // имя
        JLabel nameLabel = new JLabel("Имя:");
        nameLabel.setBounds(20, 70, 100, 20);
        frame.add(nameLabel);
        nameEdit = new JTextField();
        nameEdit.setBounds(85, 70, 140, 20);
        frame.add(nameEdit);
        // отчество
        JLabel patronymicLabel = new JLabel("Отчество:");
        patronymicLabel.setBounds(20, 100, 100, 20);
        frame.add(patronymicLabel);
        patronymicEdit = new JTextField();
        patronymicEdit.setBounds(85, 100, 140, 20);
        frame.add(patronymicEdit);

        // Birthday
        JLabel birthdayLabel = new JLabel("День рождения");
        birthdayLabel.setBounds(20, 130, 100, 20);
        frame.add(birthdayLabel);
        birthdayEdit = new JTextField();
        birthdayEdit.setBounds(125, 130, 100, 20);
        birthdayEdit.getDocument().addDocumentListener(new DocumentListener() {
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
        frame.add(birthdayEdit);
        // домашний телефон
        JLabel phoneNumberHomeLabel = new JLabel("Дом. телефон:");
        phoneNumberHomeLabel.setBounds(240, 70, 255, 20);
        frame.add(phoneNumberHomeLabel);
        phoneNumberHomeEdit = new JTextField();
        phoneNumberHomeEdit.setBounds(340, 70, 140, 20);
        frame.add(phoneNumberHomeEdit);
        // рабочий телефон
        JLabel phoneNumberWorkLabel = new JLabel("Раб. телефон:");
        phoneNumberWorkLabel.setBounds(240, 100, 255, 20);
        frame.add(phoneNumberWorkLabel);
        phoneNumberWorkEdit = new JTextField();
        phoneNumberWorkEdit.setBounds(340, 100, 140, 20);
        frame.add(phoneNumberWorkEdit);
        // мобильный телефон
        JLabel phoneNumberMobilLabel = new JLabel("Моб. телефон:");
        phoneNumberMobilLabel.setBounds(240, 40, 255, 20);
        frame.add(phoneNumberMobilLabel);
        phoneNumberMobilEdit = new JTextField();
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
        frame.add(phoneNumberMobilEdit);
        // факс
        JLabel faxLabel = new JLabel("Факс:");
        faxLabel.setBounds(240, 130, 255, 20);
        frame.add(faxLabel);
        faxEdit = new JTextField();
        faxEdit.setBounds(340, 130, 140, 20);
        frame.add(faxEdit);
        // E-Mail
        JLabel eMailLabel = new JLabel("E-mail:");
        eMailLabel.setBounds(240, 160, 255, 20);
        frame.add(eMailLabel);
        eMailEdit = new JTextField();
        eMailEdit.setBounds(340, 160, 140, 20);
        frame.add(eMailEdit);
        // кнопка "Добавить"
        addPersonButton = new JButton("Добавить запись");
        addPersonButton.setBounds(40, 190, 200, 25);
        addPersonButton.setIcon(new ImageIcon("src/org/itstep/Icon/add user.png"));
        addPersonButton.setEnabled(false);
        addPersonButton.addActionListener(this::actionAddPerson);
        frame.add(addPersonButton);
        // кнопка "Отмена"
        exitButton = new JButton("Отмена");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
        mainFrame.setEnabled(true);
    }

    static void showAddPerson() {
        new FrameAddPerson();
        mainFrame.setEnabled(false);
    }

    static boolean checkMobilPhone(String sCorrect) {
        return Pattern.matches("[+]\\d{3}[(]\\d{2}[)]\\d{3}-\\d{2}-\\d{2}", sCorrect);
    }

    static boolean checkCityPhone(String sCorrect) {
        return !Pattern.matches("\\d{2}[-]\\d{2}[-]\\d{2}", sCorrect);
    }

    private void changed() {
        addPersonButton.setEnabled((!familyEdit.getText().equals("")) &&
                (!phoneNumberMobilEdit.getText().equals("")) &&
                (!birthdayEdit.getText().equals("")));
    }

    private void actionAddPerson(ActionEvent e) {
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
        if (searchPerson(phoneNumberMobilEdit.getText()) == true) return;
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
            if (searchPerson(phoneNumberHomeEdit.getText()) == true) return;
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
            if (searchPerson(phoneNumberWorkEdit.getText()) == true) return;
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
            if (searchPerson(faxEdit.getText()) == true) return;
        }
        Person person;
        try {
            person = new Person(
                    familyEdit.getText(),
                    nameEdit.getText(),
                    patronymicEdit.getText(),
                    birthdayEdit.getText(),
                    eMailEdit.getText(),
                    phoneNumberMobilEdit.getText(),
                    phoneNumberHomeEdit.getText(),
                    phoneNumberWorkEdit.getText(),
                    faxEdit.getText()
            );
            persons.addPerson(person);
            persons.sortPersons();
            MainForm.enterDataTable(tableModel);

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Неправильно введена дата или некорректный ввод даты. Например: ХХ.ХХ.ХХ",
                    "Внимание - ОШИБКА!!!",
                    JOptionPane.ERROR_MESSAGE);
            birthdayEdit.requestFocus();
        }
        frame.setVisible(false);
        mainFrame.setEnabled(true);
    }

    public boolean searchPerson(String phone) {
        boolean result = false;
        int question;
        for (Person person : persons.getPersons()) {
            String s = person.toString();
            if (s.contains(phone)) {
                if (Objects.equals(person.getPhoneMobil(), phone)) {
                    question = JOptionPane.showConfirmDialog(
                            null,
                            "Номер телефона " + phone + " повторяется. Вы желаете сохранить в этой записи?",
                            "Внимание!!!",
                            YES_NO_OPTION);
                    if (question == JOptionPane.NO_OPTION) result = true;
                    break;
                }
                if (Objects.equals(person.getPhoneHome(), phone)) {
                    question = JOptionPane.showConfirmDialog(
                            null,
                            "Номер телефона " + phone + " повторяется. Вы желаете сохранить в этой записи?",
                            "Внимание!!!",
                            YES_NO_OPTION);
                    if (question == JOptionPane.NO_OPTION) result = true;
                    break;
                }

                if (Objects.equals(person.getPhoneWork(), phone)) {
                    question = JOptionPane.showConfirmDialog(
                            null,
                            "Номер телефона " + phone + " повторяется. Вы желаете сохранить в этой записи?",
                            "Внимание!!!",
                            YES_NO_OPTION);
                    if (question == JOptionPane.NO_OPTION) result = true;
                    break;
                }
                if (Objects.equals(person.getPhoneFax(), phone)) {
                    question = JOptionPane.showConfirmDialog(
                            null,
                            "Номер телефона " + phone + " повторяется. Вы желаете сохранить в этой записи?",
                            "Внимание!!!",
                            YES_NO_OPTION);
                    if (question == JOptionPane.NO_OPTION) result = true;
                    break;
                }

            }
        }
        return result;
    }
}
