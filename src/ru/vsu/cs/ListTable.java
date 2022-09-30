package ru.vsu.cs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListTable {

    private final List<List<String>> dataTable;
    private int columnCount;

    public ListTable(int rowCount, int columnCount) {
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
        for (List<String> i : dataTable) {
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
        for (List<String> row : dataTable) {
            row.add(null);
        }
    }

    public void addCurColumn(int column) {
        columnCount++;
        for (List<String> row : dataTable) {
            row.add(column, null);
        }
    }

    public void removeLastColumn() {
        columnCount--;
        for (List<String> row : dataTable) {
            row.remove(row.size() - 1);
        }
    }

    public void removeColumn(int column) {
        columnCount--;
        for (List<String> row : dataTable) {
            row.remove(column);
        }
    }

    public void sortByRow(int row) {
        for (int i = 0; i < dataTable.size(); i++) {
            for (int j = i; j < dataTable.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (Integer.parseInt(dataTable.get(row).get(j)) > Integer.parseInt(dataTable.get(row).get(i))) {
                    for (List<String> k : dataTable) {
                        String temp = k.get(j);
                        k.set(j, k.get(i));
                        k.set(i, temp);
                    }
                }
            }
        }
    }

    public void sortByColumn(int column) {
        for (int i = 0; i < dataTable.size(); i++) {
            for (int j = i; j < dataTable.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (Integer.parseInt(dataTable.get(j).get(column)) > Integer.parseInt(dataTable.get(i).get(column))) {
                    for (int k = 0; k < dataTable.size(); k++) {
                        String temp = dataTable.get(j).get(k);
                        dataTable.get(j).set(k, dataTable.get(i).get(k));
                        dataTable.get(i).set(k, temp);
                    }
                }
            }
        }
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (List<String> row : dataTable) {
            for (Object column : row) {
                sb.append(column).append("\t");
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
