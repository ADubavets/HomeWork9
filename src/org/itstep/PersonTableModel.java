package org.itstep;

import javax.swing.table.AbstractTableModel;

import static org.itstep.TelephoneBook.writeXML;

public class PersonTableModel extends AbstractTableModel {
    private int columnCount = 8;
    private Persons dataArrayList;

    PersonTableModel(){
        dataArrayList = new Persons();
        for (int i = 0; i < dataArrayList.size(); i++){
            dataArrayList.add(new String[getColumnCount()]);
        }
    }
    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0: return "№";
            case 1: return "Фамилия";
            case 2: return "Имя";
            case 3: return "Отчество";
            case 4: return "Моб. телефон";
            case 5: return "Дом. телефон";
            case 6: return "Раб. телефон";
            case 7: return "E-mail";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = (String[]) dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }
    public void addData(String[] row){
        String[]rowTable = new String[getColumnCount()];
        rowTable = row;
        dataArrayList.add(rowTable);
        fireTableDataChanged();
        writeXML();
    }

    public void clearData(){
        while (getRowCount()>0) removeRow(getRowCount()-1);
    }

    public void removeRow(int rowIndex) {
        dataArrayList.remove(rowIndex);
        fireTableDataChanged();
        writeXML();
    }
}
