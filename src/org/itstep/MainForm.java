package org.itstep;

import javax.swing.*;

import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;

import static javax.swing.JOptionPane.YES_NO_OPTION;
import static org.itstep.TelephoneBook.persons;

public class MainForm {
    public static JFrame mainFrame;
    // окна для отображения
    public static JTextField nameEdit;
    public static JTextField familyEdit;
    public static JTextField patronymicEdit;
    public static JTextField phoneNumberHomeEdit;
    public static JTextField phoneNumberWorkEdit;
    public static JTextField phoneNumberMobilEdit;
    public static JTextField eMailEdit;
    public static JTable phoneTable = new JTable();
    public static PersonTableModel tableModel;

    // Конструктор класса:
    MainForm() {
        // Создание окна - объекта класса JFrame.
        // Текстовый аргумент конструктора задает имя окна:
        mainFrame = new JFrame("Телефонный справочник");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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
        // домашний телефон
        JLabel phoneNumberHomeLabel = new JLabel("Дом. телефон:");
        phoneNumberHomeLabel.setBounds(260, 40, 255, 20);
        mainFrame.add(phoneNumberHomeLabel);
        phoneNumberHomeEdit = new JTextField();
        phoneNumberHomeEdit.setBounds(360, 40, 140, 20);
        mainFrame.add(phoneNumberHomeEdit);
        // рабочий телефон
        JLabel phoneNumberWorkLabel = new JLabel("Раб. телефон:");
        phoneNumberWorkLabel.setBounds(260, 70, 255, 20);
        mainFrame.add(phoneNumberWorkLabel);
        phoneNumberWorkEdit = new JTextField();
        phoneNumberWorkEdit.setBounds(360, 70, 140, 20);
        mainFrame.add(phoneNumberWorkEdit);
        // мобильный телефон
        JLabel phoneNumberMobilLabel = new JLabel("Моб. телефон:");
        phoneNumberMobilLabel.setBounds(260, 100, 255, 20);
        mainFrame.add(phoneNumberMobilLabel);
        phoneNumberMobilEdit = new JTextField();
        phoneNumberMobilEdit.setBounds(360, 100, 140, 20);
        mainFrame.add(phoneNumberMobilEdit);
        // E-mail
        JLabel faxLabel = new JLabel("E-mail:");
        faxLabel.setBounds(260, 130, 255, 20);
        mainFrame.add(faxLabel);
        eMailEdit = new JTextField();
        eMailEdit.setBounds(360, 130, 140, 20);
        mainFrame.add(eMailEdit);
        // список телефонов
        JLabel listLabel = new JLabel("Список телефонов:");
        listLabel.setBounds(40, 150, 255, 20);
        mainFrame.add(listLabel);

        //формарование данных для таблицы
        // Names of the column
        tableModel = new PersonTableModel();
        phoneTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(phoneTable);
        setColumnWidths(phoneTable, 20, 115, 115, 115, 115, 115, 115, 115);
        scrollPane.setBounds(20, 170, 845, 420);
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
            int[] selectedRows = phoneTable.getSelectedRows();
            for (int selIndex : selectedRows) {
                TableModel model = phoneTable.getModel();
                sFamily = (String) model.getValueAt(selIndex, 1);
                sName = (String) model.getValueAt(selIndex, 2);
                sPatronymic = (String) model.getValueAt(selIndex, 3);
                sPhoneNumberHome = (String) model.getValueAt(selIndex, 4);
                sPhoneNumberWork = (String) model.getValueAt(selIndex, 5);
                sPhoneNumberMobil = (String) model.getValueAt(selIndex, 6);
                sFax = (String) model.getValueAt(selIndex, 7);
            }
            familyEdit.setText(sFamily);
            nameEdit.setText(sName);
            patronymicEdit.setText(sPatronymic);
            phoneNumberHomeEdit.setText(sPhoneNumberHome);
            phoneNumberWorkEdit.setText(sPhoneNumberWork);
            phoneNumberMobilEdit.setText(sPhoneNumberMobil);
            eMailEdit.setText(sFax);
        });

        // Установка размеров окна
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        mainFrame.setSize(900, 650);
        int x = (int) ((screenSize.getWidth() - mainFrame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - mainFrame.getHeight()) / 2);
        mainFrame.setLocation(x, y);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Реакция окна на щелчок на системной пиктограмме закрытия окна:
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // кнопки приложения
        // кнопка "Добавить"
        JButton addPerson = new JButton("Добавить запись");
        addPerson.setBounds(600, 40, 200, 25);
        addPerson.setIcon(new ImageIcon("src/org/itstep/Icon/add user.png"));
        addPerson.setActionCommand("Нажата кнопка \"Добавить\"");

        // обработчик кнопки "Добавить"/////////////////////////////////////////////////////////////////////////////////
        addPerson.addActionListener(e -> FrameAddPerson.show());
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        mainFrame.add(addPerson);
        // кнопка "Удалить"
        JButton deletePerson = new JButton("Удалить запись");
        deletePerson.setBounds(600, 70, 200, 25);
        deletePerson.setIcon(new ImageIcon("src/org/itstep/Icon/remove user.png"));

        // обработчик кнопки "удалить"
        deletePerson.addActionListener(e -> deletePerson());

        mainFrame.add(deletePerson);
        // кнопка "Обновить"
        JButton updatePerson = new JButton("Обновить данные");
        updatePerson.setBounds(600, 100, 200, 25);
        updatePerson.setIcon(new ImageIcon("src/org/itstep/Icon/update.png"));
        // обработчик кнопки "Обновить"/////////////////////////////////////////////////////////////////////////////////
        updatePerson.addActionListener(e -> enterDataTable(tableModel));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        mainFrame.add(updatePerson);
        // кнопка "Поиск"
        JButton searchPerson = new JButton("Поиск");
        searchPerson.setBounds(600, 130, 200, 25);
        searchPerson.setIcon(new ImageIcon("src/org/itstep/Icon/search.png"));
        searchPerson.addActionListener(e -> {
            String result = (String) JOptionPane.showInputDialog(
                    null,
                    "Введите фамилию персоны",
                    "ВНИМАНИЕ",
                    JOptionPane.QUESTION_MESSAGE,
                    null, null, null);
            if (!result.equals("")){
                tableModel.clearData();
                int i = 0;
                for (Person person : persons.getPersons()){
                    String s = person.toString();
                    if (s.contains(result)) {
                        String[] str = new String[8];
                        str[0] = String.valueOf(i + 1);
                        str[1] = person.getFamily();
                        str[2] = person.getName();
                        str[3] = person.getPatronymic();
                        str[4] = person.getPhoneMobil();
                        str[5] = person.getPhoneHome();
                        str[6] = person.getPhoneWork();
                        str[7] = person.geteMail();
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
        panel.setBounds(20, 180, 900, 600);
        panel.setBounds(5, 5, 875, 600);
        panel.setBackground(Color.gray);
        mainFrame.add(panel);
        mainFrame.setResizable(false);
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
            String[] str = new String[8];
            str[0] = String.valueOf(i + 1);
            str[1] = person.getFamily();
            str[2] = person.getName();
            str[3] = person.getPatronymic();
            str[4] = person.getPhoneMobil();
            str[5] = person.getPhoneHome();
            str[6] = person.getPhoneWork();
            str[7] = person.geteMail();

            tableModel.addData(str);
            i++;
        }
    }
    public void deletePerson(){
        if (familyEdit.getText().length() == 0){
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
                for (Person person : persons.getPersons()){
                    String s = person.toString();
                    String str = person.getFamily();
                    if (!person.getName().equals("")) str = str +" " + person.getName();
                    if (!person.getPatronymic().equals("")) str = str +" " + person.getPatronymic();
                    if (s.contains(str)) {
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
