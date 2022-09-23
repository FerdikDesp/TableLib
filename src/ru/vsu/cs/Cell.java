package ru.vsu.cs;

public class Cell {

    private String value;
    private int row;
    private int column;

    public Cell(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
