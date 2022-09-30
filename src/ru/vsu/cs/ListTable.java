package ru.vsu.cs;

import java.util.ArrayList;
import java.util.Random;

public class ListTable {

    private final ArrayList<ArrayList<String>> dataTable;
    private int columnCount;

    ListTable(int rowCount, int columnCount) {
        this.columnCount = columnCount;
        dataTable = new ArrayList<>();
        for (int row = 0; row < rowCount; row++) {
            dataTable.add(new ArrayList<>());
            for (int column = 0; column < columnCount; column++) {
                dataTable.get(row).add(null);
            }
        }
    }

    public void fillRandom(int minValue, int maxValue) {
        Random random = new Random();
        for (ArrayList<String> i : dataTable) {
            for (int j = 0; j < i.size(); j++) {
                i.set(j, Integer.toString(random.nextInt(maxValue - minValue) + minValue));
            }
        }
    }

    public void setCellValue(int row, int column, String value) {
        dataTable.get(row).set(column, value);
    }

    public String getCellValue(int row, int column) {
        return dataTable.get(row).get(column);
    }

    public void addRow() {
        dataTable.add(new ArrayList<>());
        for (int column = 0; column < columnCount; column++) {
            dataTable.get(dataTable.size() - 1).add(null);
        }
    }

    public void addCurRow(int row) {
        dataTable.add(row, new ArrayList<>());
        for (int column = 0; column < columnCount; column++) {
            dataTable.get(row).add(null);
        }
    }

    public void removeLastRow() {
        dataTable.remove(dataTable.size() - 1);
    }

    public void removeRow(int row) {
        dataTable.remove(row);
    }

    public void addColumn() {
        columnCount++;
        for (ArrayList<String> row : dataTable) {
            row.add(null);
        }
    }

    public void addCurColumn(int column) {
        columnCount++;
        for (ArrayList<String> row : dataTable) {
            row.add(column, null);
        }
    }

    public void removeLastColumn() {
        columnCount--;
        for (ArrayList<String> row : dataTable) {
            row.remove(row.size() - 1);
        }
    }

    public void removeColumn(int column) {
        columnCount--;
        for (ArrayList<String> row : dataTable) {
            row.remove(column);
        }
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (ArrayList<String> row : dataTable) {
            for (Object column : row) {
                sb.append(column).append("\t");
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
