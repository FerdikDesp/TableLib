package ru.vsu.cs;

import java.util.ArrayList;
import java.util.List;

public class ListTable<T> {

    private List<List<T>> dataTable;
    private int rowCount;
    private int columnCount;

    public ListTable(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        dataTable = new ArrayList<>();
        for (int row = 0; row < rowCount; row++) {
            dataTable.add(new ArrayList<>());
            for (int column = 0; column < columnCount; column++) {
                dataTable.get(row).add(null);
            }
        }
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setCellValue(int row, int column, T value) {
        dataTable.get(row).set(column, value);
    }

    public T getCellValue(int row, int column) {
        return dataTable.get(row).get(column);
    }

    public void addRow() {
        rowCount++;
        dataTable.add(new ArrayList<>());
        for (int column = 0; column < columnCount; column++) {
            dataTable.get(dataTable.size() - 1).add(null);
        }
    }

    public void addCurRow(int row) {
        rowCount++;
        dataTable.add(row, new ArrayList<>());
        for (int column = 0; column < columnCount; column++) {
            dataTable.get(row).add(null);
        }
    }

    public void removeLastRow() {
        rowCount--;
        dataTable.remove(dataTable.size() - 1);
    }

    public void removeRow(int row) {
        rowCount--;
        dataTable.remove(row);
    }

    public void addColumn() {
        columnCount++;
        for (List<T> row : dataTable) {
            row.add(null);
        }
    }

    public void addCurColumn(int column) {
        columnCount++;
        for (List<T> row : dataTable) {
            row.add(column, null);
        }
    }

    public void removeLastColumn() {
        columnCount--;
        for (List<T> row : dataTable) {
            row.remove(row.size() - 1);
        }
    }

    public void removeColumn(int column) {
        columnCount--;
        for (List<T> row : dataTable) {
            row.remove(column);
        }
    }

    public void sortByRow(int row) {
        for (int i = 0; i < dataTable.size(); i++) {
            for (int j = i; j < dataTable.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (Integer.parseInt((String) dataTable.get(row).get(j)) > Integer.parseInt((String) dataTable.get(row).get(i))) {
                    for (List<T> k : dataTable) {
                        T temp = k.get(j);
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
                if (Integer.parseInt((String) dataTable.get(j).get(column)) > Integer.parseInt((String) dataTable.get(i).get(column))) {
                    for (int k = 0; k < dataTable.size(); k++) {
                        T temp = dataTable.get(j).get(k);
                        dataTable.get(j).set(k, dataTable.get(i).get(k));
                        dataTable.get(i).set(k, temp);
                    }
                }
            }
        }
    }

    public void groupDataTableBy(String textToSearch) {
        List<List<T>> newDataTable = new ArrayList<>();
        for (int row = 0; row < rowCount; row++) {
           newDataTable.add(new ArrayList<>());
        }
        for (int column = 0; column < dataTable.get(0).size(); column++) {
            System.out.println(String.valueOf(dataTable.get(0).get(column)).contains(textToSearch));
            if (String.valueOf(dataTable.get(0).get(column)).contains(textToSearch)) {
                for (int i = 0; i < rowCount; i++) {
                    newDataTable.get(i).add(dataTable.get(i).get(column));
                }
            }
        }
        dataTable = newDataTable;
        rowCount = dataTable.size();
        columnCount = dataTable.get(0).size();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<T> row : dataTable) {
            for (T column : row) {
                sb.append(column).append("\t");
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
