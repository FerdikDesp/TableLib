package ru.vsu.cs;

import java.util.ArrayList;
import java.util.Random;

public class ListTable {

    private ArrayList<ArrayList<String>> dataTable;

    public ListTable(int rowCount, int columnCount) {
        dataTable = new ArrayList<>();
        for (int row = 0; row < rowCount; row++) {
            dataTable.add(new ArrayList<>());
            for (int column = 0; column < columnCount; column++) {
                dataTable.get(row).add(row + "" + column);
            }
        }
    }

    public void fillRandom() {
        Random random = new Random();
        for (ArrayList<String> i : dataTable) {
            for (int j = 0; j < i.size(); j++) {
                i.add(j, Integer.toString(random.nextInt(201) - 100));
                i.remove(j+1);
            }
        }
    }

    public void addRow() {
        dataTable.add(new ArrayList<>());
        for (int column = 0; column < dataTable.get(dataTable.size() - 1).size(); column++) {
            dataTable.get(dataTable.size() - 1).add(dataTable.size() - 1 + "" + column);
        }
    }

    public void removeLastRow() {
        dataTable.remove(dataTable.size() - 1);
    }

    public void removeRow(int row) {
        dataTable.remove(row);
    }

    public void addColumn() {
        for (int row = 0; row < dataTable.size(); row++) {
            dataTable.get(row).add(row + "" + dataTable.get(row).size());
        }
    }

    public void removeLastColumn() {
        for (ArrayList<String> row : dataTable) {
            row.remove(row.size() - 1);
        }
    }

    public void removeColumn(int column) {
        for (ArrayList<String> row : dataTable) {
            row.remove(column);
        }
    }

    public void print() {
        for (ArrayList<String> row : dataTable) {
            for (Object column : row) {
                System.out.print(column + "\t");
            }
            System.out.println();
        }
    }
}
