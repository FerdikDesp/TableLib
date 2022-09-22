package ru.vsu.cs;

import java.util.Random;

public class Table {

    private Cell[][] dataTable;
    private int columnCount;
    private int rowCount;

    public Table(int rowCount, int columnCount) {
        dataTable = new Cell[rowCount][columnCount];
        this.columnCount = columnCount;
        this.rowCount = rowCount;
    }

    public void fill() {
        Random random = new Random();
        for (int i = 0; i < dataTable.length; i++) {
            for (int j = 0; j < dataTable[i].length; j++) {
                dataTable[i][j].setValue(Integer.toString(random.nextInt(201) - 100));
            }
        }
    }

    public String getCellValue(int row, int column) {
        return dataTable[row][column].getValue();
    }

    public void setCellValue(int row, int column, String value) {
        dataTable[row][column].setValue(value);
    }

    public void addRow() {
        Cell[][] tempTable = new Cell[dataTable.length + 1][dataTable[0].length];
        for (int i = 0; i < dataTable.length; i++) {
            for (int j = 0; j < dataTable[i].length; j++) {
                tempTable[i][j] = dataTable[i][j];
            }
        }
        dataTable = tempTable;
    }

    public void addColumn() {
        Cell[][] tempTable = new Cell[dataTable.length][dataTable[0].length + 1];
        for (int i = 0; i < dataTable.length; i++) {
            for (int j = 0; j < dataTable[i].length; j++) {
                tempTable[i][j] = dataTable[i][j];
            }
        }
        dataTable = tempTable;
    }

    public void removeRow() {
        if (dataTable.length >= 1) {
            Cell[][] tempTable = new Cell[dataTable.length - 1][dataTable[0].length];
            for (int i = 0; i < dataTable.length - 1; i++) {
                for (int j = 0; j < dataTable[i].length; j++) {
                    tempTable[i][j] = dataTable[i][j];
                }
            }
            dataTable = tempTable;
        }
    }

    public void removeColumn() {
        if (dataTable[0].length >= 1) {
            Cell[][] tempTable = new Cell[dataTable.length][dataTable[0].length - 1];
            for (int i = 0; i < dataTable.length; i++) {
                for (int j = 0; j < dataTable[i].length - 1; j++) {
                    tempTable[i][j] = dataTable[i][j];
                }
            }
            dataTable = tempTable;
        }
    }

}
