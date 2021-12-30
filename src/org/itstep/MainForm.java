package org.itstep;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Objects;

import static javax.swing.JOptionPane.YES_NO_OPTION;
import static org.itstep.TelephoneBook.persons;
import static org.itstep.TelephoneBook.writeXML;

public class MainForm {
    public static JFrame mainFrame;
    // окна для отображения
    public static JTextField nameEdit;
    public static JTextField familyEdit;
    public static JTextField patronymicEdit;
    public static JTextField phoneNumberMobilEdit;
    public static JTextField phoneNumberWorkEdit;
    public static JTextField phoneNumberHomeEdit;
    public static JTextField faxEdit;
    public static JTextField eMailEdit;
    public static JTable phoneTable = new JTable();
    public static PersonTableModel tableModel;

    // Конструктор класса:
    MainForm() {
        // Создание окна - объекта класса JFrame.
        // Текстовый аргумент конструктора задает имя окна:
        mainFrame = new JFrame("Телефонный справочник");
        Image icon = mainFrame.getToolkit().getImage("src/org/itstep/Icon/book.png");
        mainFrame.setIconImage(icon);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // размещение элементов окна

        // фамилия
        JLabel familyLabel = new JLabel("Фамилия:");
        familyLabel.setBounds(40, 40, 100, 20);
        mainFrame.add(familyLabel);
        familyEdit = new JTextField();
        familyEdit.setBounds(105, 40, 140, 20);
        mainFrame.add(familyEdit);
        // имя
        // надписи в приложении
        JLabel nameLabel = new JLabel("Имя:");
        nameLabel.setBounds(40, 70, 100, 20);
        mainFrame.add(nameLabel);
        nameEdit = new JTextField();
        nameEdit.setBounds(105, 70, 140, 20);
        mainFrame.add(nameEdit);
        // отчество
        JLabel patronymicLabel = new JLabel("Отчество:");
        patronymicLabel.setBounds(40, 100, 100, 20);
        mainFrame.add(patronymicLabel);
        patronymicEdit = new JTextField();
        patronymicEdit.setBounds(105, 100, 140, 20);
        mainFrame.add(patronymicEdit);
        // мобильный телефон
        JLabel phoneNumberHomeLabel = new JLabel("Моб. телефон:");
        phoneNumberHomeLabel.setBounds(260, 40, 255, 20);
        mainFrame.add(phoneNumberHomeLabel);
        phoneNumberHomeEdit = new JTextField();
        phoneNumberHomeEdit.setBounds(360, 70, 140, 20);
        mainFrame.add(phoneNumberHomeEdit);
        // домашний телефон
        JLabel phoneNumberMobilLabel = new JLabel("Дом. телефон:");
        phoneNumberMobilLabel.setBounds(260, 70, 255, 20);
        mainFrame.add(phoneNumberMobilLabel);
        phoneNumberMobilEdit = new JTextField();
        phoneNumberMobilEdit.setBounds(360, 40, 140, 20);
        mainFrame.add(phoneNumberMobilEdit);
        // рабочий телефон
        JLabel phoneNumberWorkLabel = new JLabel("Раб. телефон:");
        phoneNumberWorkLabel.setBounds(260, 100, 255, 20);
        mainFrame.add(phoneNumberWorkLabel);
        phoneNumberWorkEdit = new JTextField();
        phoneNumberWorkEdit.setBounds(360, 100, 140, 20);
        mainFrame.add(phoneNumberWorkEdit);
        // fax
        JLabel faxLabel = new JLabel("Факс:");
        faxLabel.setBounds(260, 130, 255, 20);
        mainFrame.add(faxLabel);
        faxEdit = new JTextField();
        faxEdit.setBounds(360, 130, 140, 20);
        mainFrame.add(faxEdit);
        // E-mail
        JLabel eMailLabel = new JLabel("E-mail:");
        eMailLabel.setBounds(260, 160, 255, 20);
        mainFrame.add(eMailLabel);
        eMailEdit = new JTextField();
        eMailEdit.setBounds(360, 160, 140, 20);
        mainFrame.add(eMailEdit);
        // список телефонов
        JLabel listLabel = new JLabel("Список телефонов:");
        listLabel.setBounds(40, 180, 255, 20);
        mainFrame.add(listLabel);

        //формарование данных для таблицы
        // Names of the column
        tableModel = new PersonTableModel();
        phoneTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(phoneTable);
        setColumnWidths(phoneTable, 20, 122, 122, 122, 122, 122, 122, 122, 122, 122);
        scrollPane.setBounds(20, 200, 1145, 420);
        mainFrame.add(scrollPane);
        enterDataTable(tableModel);
        // реакция на событие в таблице
        ListSelectionModel selModel = phoneTable.getSelectionModel();
        selModel.addListSelectionListener(e -> {
            String sFamily = "";
            String sName = "";
            String sPatronymic = "";
            String sPhoneNumberHome = "";
            String sPhoneNumberWork = "";
            String sPhoneNumberMobil = "";
            String sFax = "";
            String sMail = "";
            int[] selectedRows = phoneTable.getSelectedRows();
            for (int selIndex : selectedRows) {
                TableModel model = phoneTable.getModel();
                sFamily = (String) model.getValueAt(selIndex, 1);
                sName = (String) model.getValueAt(selIndex, 2);
                sPatronymic = (String) model.getValueAt(selIndex, 3);
                sPhoneNumberMobil = (String) model.getValueAt(selIndex, 5);
                sPhoneNumberHome = (String) model.getValueAt(selIndex, 6);
                sPhoneNumberWork = (String) model.getValueAt(selIndex, 7);
                sMail = (String) model.getValueAt(selIndex, 8);
                sFax = (String) model.getValueAt(selIndex, 9);
            }
            familyEdit.setText(sFamily);
            nameEdit.setText(sName);
            patronymicEdit.setText(sPatronymic);
            phoneNumberMobilEdit.setText(sPhoneNumberMobil);
            phoneNumberHomeEdit.setText(sPhoneNumberHome);
            phoneNumberWorkEdit.setText(sPhoneNumberWork);
            eMailEdit.setText(sMail);
            faxEdit.setText(sFax);
        });

        // Установка размеров окна
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        mainFrame.setSize(1200, 650);
        mainFrame.setLocationRelativeTo(null);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Реакция окна на щелчок на системной пиктограмме закрытия окна:
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // кнопки приложения
        // кнопка "Добавить"
        JButton addPerson = new JButton("Добавить запись");
        addPerson.setBounds(800, 40, 200, 25);
        addPerson.setIcon(new ImageIcon("src/org/itstep/Icon/add user.png"));
        addPerson.addActionListener(e -> FrameAddPerson.showAddPerson());
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        mainFrame.add(addPerson);
        // кнопка "Изменить"
        JButton editPerson = new JButton("Изменить запись");
        editPerson.setBounds(800, 70, 200, 25);
        editPerson.setIcon(new ImageIcon("src/org/itstep/Icon/insert.png"));
        editPerson.addActionListener(e -> {
            if (familyEdit.getText().length() == 0) {
                JOptionPane.showMessageDialog(
                        null,
                        "Выберите из списка пользователя",
                        "Внимание!!!",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            FrameEditPerson.showEditPerson(
                    familyEdit.getText(),
                    nameEdit.getText(),
                    patronymicEdit.getText(),
                    phoneNumberMobilEdit.getText(),
                    phoneNumberHomeEdit.getText(),
                    phoneNumberWorkEdit.getText(),
                    faxEdit.getText(),
                    eMailEdit.getText());
        });
        mainFrame.add(editPerson);

        // кнопка "Удалить"
        JButton deletePerson = new JButton("Удалить запись");
        deletePerson.setBounds(800, 100, 200, 25);
        deletePerson.setIcon(new ImageIcon("src/org/itstep/Icon/remove user.png"));

        // обработчик кнопки "удалить"
        deletePerson.addActionListener(e -> deletePerson());

        mainFrame.add(deletePerson);
        // кнопка "Обновить"
        JButton updatePerson = new JButton("Обновить данные");
        updatePerson.setBounds(800, 130, 200, 25);
        updatePerson.setIcon(new ImageIcon("src/org/itstep/Icon/update.png"));
        // обработчик кнопки "Обновить"/////////////////////////////////////////////////////////////////////////////////
        updatePerson.addActionListener(e -> enterDataTable(tableModel));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        mainFrame.add(updatePerson);
        // кнопка "Поиск"
        JButton searchPerson = new JButton("Поиск");
        searchPerson.setBounds(800, 160, 200, 25);
        searchPerson.setIcon(new ImageIcon("src/org/itstep/Icon/search.png"));
        searchPerson.addActionListener(e -> {
            String result = (String) JOptionPane.showInputDialog(
                    null,
                    "Введите Имя и/или номер телефона персоны",
                    "ВНИМАНИЕ",
                    JOptionPane.QUESTION_MESSAGE,
                    null, null, null);
            if (!result.equals("")) {
                tableModel.clearData();
                int i = 0;
                for (Person person : persons.getPersons()) {
                    String s = person.toString();
                    if (s.contains(result)) {
                        String[] str = new String[10];
                        str[0] = String.valueOf(i + 1);
                        str[1] = person.getFamily();
                        str[2] = person.getName();
                        str[3] = person.getPatronymic();
                        String date = new SimpleDateFormat("dd-MM-yyyy").format(person.getBirthday());
                        str[4] = date;
                        str[5] = person.getPhoneMobil();
                        str[6] = person.getPhoneHome();
                        str[7] = person.getPhoneWork();
                        str[8] = person.getEmail();
                        str[9] = person.getPhoneFax();
                        tableModel.addData(str);
                        i++;
                    }
                }
            }
        });
        mainFrame.add(searchPerson);
        // Отображение окна:
        mainFrame.setLayout(null);
        JPanel panel = new JPanel();
        panel.setBounds(20, 180, 1200, 600);
        panel.setBounds(5, 5, 1175, 600);
        panel.setBackground(Color.gray);
        mainFrame.add(panel);
        mainFrame.setResizable(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public static void setColumnWidths(JTable table, int... widths) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < widths.length; i++) {
            if (i < columnModel.getColumnCount()) {
                columnModel.getColumn(i).setMaxWidth(widths[i]);
            } else break;
        }
    }

    public static void enterDataTable(PersonTableModel tableModel) {

        tableModel.clearData();

        int i = 0;
        for (Person person : persons.getPersons()) {
            String[] str = new String[10];
            str[0] = String.valueOf(i + 1);
            str[1] = person.getFamily();
            str[2] = person.getName();
            str[3] = person.getPatronymic();
            String date = new SimpleDateFormat("dd-MM-yyyy").format(person.getBirthday());
            str[4] = date;
            str[5] = person.getPhoneMobil();
            str[6] = person.getPhoneHome();
            str[7] = person.getPhoneWork();
            str[8] = person.getEmail();
            str[9] = person.getPhoneFax();

            tableModel.addData(str);
            i++;
        }
    }

    public void deletePerson() {
        if (familyEdit.getText().length() == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Выберите из списка пользователя",
                    "Внимание!!!",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int result = JOptionPane.showConfirmDialog(
                null,
                "Вы желаете удалить запись о пользователе: " +
                        familyEdit.getText() + " " +
                        nameEdit.getText() + " " +
                        patronymicEdit.getText() + "?",
                "СООБЩЕНИЕ",
                YES_NO_OPTION);
        try {
            if (result == JOptionPane.YES_OPTION) {
                for (Person person : persons.getPersons()) {
                    if ((Objects.equals(familyEdit.getText(), person.getFamily())) &&
                            (Objects.equals(nameEdit.getText(), person.getName())) &&
                            (Objects.equals(patronymicEdit.getText(), person.getPatronymic()))) {
                        persons.removePerson(person);
                        break;
                    }
                }
                enterDataTable(tableModel);
            }
        } catch (IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Выберите в списке пользователя",
                    "Внимание!!!",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
