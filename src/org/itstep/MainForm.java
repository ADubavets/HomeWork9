package org.itstep;

import javax.swing.*;

import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;

import static org.itstep.TelephoneBook.persons;

public class MainForm {
    public JFrame mainFrame;
    // окна для отображения
    private final JTextField nameEdit;
    private final JTextField familyEdit;
    private final JTextField patronymicEdit;
    private final JTextField phoneNumberHomeEdit;
    private final JTextField phoneNumberWorkEdit;
    private final JTextField phoneNumberMobilEdit;
    private final JTextField faxEdit;
    private final JTable phoneTable;
    private static String[][] data;

    // Конструктор класса:
    MainForm(){
        // Создание окна - объекта класса JFrame.
        // Текстовый аргумент конструктора задает имя окна:
        mainFrame = new JFrame("Телефонный справочник");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // размещение элементов окна

        // фамилия
        JLabel familyLabel = new JLabel("Фамилия:");
        familyLabel.setBounds(40,40,100,20);
        mainFrame.add(familyLabel);
        familyEdit = new JTextField();
        familyEdit.setBounds(105,40,140,20);
        mainFrame.add(familyEdit);
        // имя
        // надписи в приложении
        JLabel nameLabel = new JLabel("Имя:");
        nameLabel.setBounds(40,70,100,20);
        mainFrame.add(nameLabel);
        nameEdit = new JTextField();
        nameEdit.setBounds(105,70,140,20);
        mainFrame.add(nameEdit);
        // отчество
        JLabel patronymicLabel = new JLabel("Отчество:");
        patronymicLabel.setBounds(40,100,100,20);
        mainFrame.add(patronymicLabel);
        patronymicEdit = new JTextField();
        patronymicEdit.setBounds(105,100,140,20);
        mainFrame.add(patronymicEdit);
        // домашний телефон
        JLabel phoneNumberHomeLabel = new JLabel("Дом. телефон:");
        phoneNumberHomeLabel.setBounds(260,40,255,20);
        mainFrame.add(phoneNumberHomeLabel);
        phoneNumberHomeEdit = new JTextField();
        phoneNumberHomeEdit.setBounds(360,40,140,20);
        mainFrame.add(phoneNumberHomeEdit);
        // рабочий телефон
        JLabel phoneNumberWorkLabel = new JLabel("Раб. телефон:");
        phoneNumberWorkLabel.setBounds(260,70,255,20);
        mainFrame.add(phoneNumberWorkLabel);
        phoneNumberWorkEdit = new JTextField();
        phoneNumberWorkEdit.setBounds(360,70,140,20);
        mainFrame.add(phoneNumberWorkEdit);
        // мобильный телефон
        JLabel phoneNumberMobilLabel = new JLabel("Моб. телефон:");
        phoneNumberMobilLabel.setBounds(260,100,255,20);
        mainFrame.add(phoneNumberMobilLabel);
        phoneNumberMobilEdit = new JTextField();
        phoneNumberMobilEdit.setBounds(360,100,140,20);
        mainFrame.add(phoneNumberMobilEdit);
        // факс
        JLabel faxLabel = new JLabel("Факс:");
        faxLabel.setBounds(260,130,255,20);
        mainFrame.add(faxLabel);
        faxEdit = new JTextField();
        faxEdit.setBounds(360,130,140,20);
        mainFrame.add(faxEdit);
        // список телефонов
        JLabel listLabel = new JLabel("Список телефонов:");
        listLabel.setBounds(40,150,255,20);
        mainFrame.add(listLabel);
        // Names of the column
        String[] columnsName = {
                "№",
                "Фамилия",
                "Имя",
                "Отчество",
                "Дом. телефон",
                "Раб. телефон",
                "Моб. телефон",
                "Факс"};
        enterDataTable(persons);
        phoneTable = new JTable(data,columnsName);
        setColumnWidths(phoneTable,20,115,115,115,115,115,115,115);
        JScrollPane scrollPane = new JScrollPane(phoneTable);
        scrollPane.setBounds(20,170,845,420);
        mainFrame.add(scrollPane);
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
            faxEdit.setText(sFax);
        });
        // Установка размеров окна
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        mainFrame.setSize(900,650);
        int x = (int) ((screenSize.getWidth() - mainFrame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - mainFrame.getHeight()) / 2);
        mainFrame.setLocation(x, y);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Реакция окна на щелчок на системной пиктограмме закрытия окна:
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // кнопка "Добавить"
        // кнопки приложения
        JButton addPerson = new JButton("Добавить запись");
        addPerson.setBounds(600,40,200,25);
        addPerson.setIcon(new ImageIcon("src/org/itstep/Icon/add user.png"));
        addPerson.setActionCommand("Нажата кнопка \"Добавить\"");
        addPerson.addActionListener(e -> FrameAddPerson.show());
        mainFrame.add(addPerson);
        // кнопка "Удалить"
        JButton deletePerson = new JButton("Удалить запись");
        deletePerson.setBounds(600,70,200,25);
        deletePerson.setIcon(new ImageIcon("src/org/itstep/Icon/remove user.png"));
        mainFrame.add(deletePerson);
        // кнопка "Обновить"
        JButton updatePerson = new JButton("Изменить данные");
        updatePerson.setBounds(600,100,200,25);
        updatePerson.setIcon(new ImageIcon("src/org/itstep/Icon/update.png"));
        mainFrame.add(updatePerson);
        // кнопка "Поиск"
        JButton searchPerson = new JButton("Поиск");
        searchPerson.setBounds(600,130,200,25);
        searchPerson.setIcon(new ImageIcon("src/org/itstep/Icon/search.png"));
        mainFrame.add(searchPerson);
        // Отображение окна:
        mainFrame.setLayout(null);
        JPanel panel = new JPanel();
        panel.setBounds(20, 180, 900, 600);
        panel.setBounds(5,5,875,600);
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
            }
            else break;
        }
    }
    public static void enterDataTable(Persons persons){

        data = new String[][]{
                {"1", "Иванов", "Иван", "Иванович", "25-36-14", "74-85-96", "456-78-89", "74-85-95"},
                {"2", "Петров", "Петр", "Петрович", "55-36-84", "74-85-96", "444-78-89", "74-85-95"},
                {"3", "Васильев", "Василий", "Васильевич", "45-69-78", "", "555-78-89", ""},

        };
    }
}
