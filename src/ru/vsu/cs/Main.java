package ru.vsu.cs;

public class Main {

    public static void main(String[] args) {

        new tableForm();

        ListTable table = new ListTable(5, 5);

        table.print();

        System.out.println();

        table.removeColumn(1);

        table.print();

    }
}
