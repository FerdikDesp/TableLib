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
        for (int i = 0; i < dataTable.length; i++) {
            for (int j = 0; j < dataTable[i].length; j++) {
                dataTable[i][j] = new Cell(null);
            }
        }
    }

    public void fill() {
        Random random = new Random();
        for (int i = 0; i < dataTable.length; i++) {
            for (int j = 0; j < dataTable[i].length; j++) {
                dataTable[i][j].setValue(Integer.toString(random.nextInt(201) - 100));
            }
        }
    }

    public Cell getCell(int row, int column) {
        return dataTable[row][column];
    }

    public void addRow() {
        Cell[][] tempTable = new Cell[dataTable.length + 1][dataTable[0].length];
        for (int i = 0; i < dataTable.length; i++) {
            for (int j = 0; j < dataTable[i].length; j++) {
                tempTable[i][j] = dataTable[i][j];
            }
        }
        for (int i = 0; i < dataTable[0].length; i++) {
            tempTable[dataTable.length][i] = new Cell(null);
        }
        dataTable = tempTable;
    }

    public void addRow(int rowToAdd) {
        Cell[][] tempTable = new Cell[dataTable.length + 1][dataTable[0].length];
        for (int i = 0; i < dataTable.length + 1; i++) {
            if (i == rowToAdd) {
                for (int j = 0; j < dataTable[0].length; j++) {
                    tempTable[i][j] = new Cell(null);
                    //System.out.println(i + " " + j + " " + tempTable[i][j].getValue());
                }
            } else if (i > rowToAdd) {
                for (int j = 0; j < dataTable[i-1].length; j++) {
                    tempTable[i][j] = dataTable[i-1][j];
                    //System.out.println(i + " " + j + " " + tempTable[i][j].getValue());
                }
            } else {
                for (int j = 0; j < dataTable[i].length; j++) {
                    tempTable[i][j] = dataTable[i][j];
                    //System.out.println(i + " " + j + " " + tempTable[i][j].getValue());
                }
            }
        }
        dataTable = tempTable;
        for (int i = 0; i < dataTable.length; i++) {
            for (int j = 0; j < dataTable[i].length; j++) {
                System.out.print(dataTable[i][j].getValue() + "\t");
            }
            System.out.println();
        }
    }

    public void addColumn() {
        Cell[][] tempTable = new Cell[dataTable.length][dataTable[0].length + 1];
        for (int i = 0; i < dataTable.length; i++) {
            for (int j = 0; j < dataTable[i].length; j++) {
                tempTable[i][j] = dataTable[i][j];
            }
            tempTable[i][dataTable[i].length] = new Cell(null);
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
