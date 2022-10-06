package ru.vsu.cs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Calendar;


public class TableForm extends JFrame {

    private JPanel mainPanel;
    private JButton buttonFillTable;
    private JTable tableMain;
    private JButton buttonIncreaseColumns;
    private JButton buttonDecreaseColumns;
    private JButton buttonIncreaseRows;
    private JButton buttonDecreaseRows;
    private JButton buttonInitTable;
    private JTextArea ConsoleTable;
    private JTextArea Logs;
    private JButton buttonUpdateTable;
    private JSpinner spinnerNumber;
    private JButton buttonIncreaseCurrentRow;
    private JButton buttonDecreaseCurrentRow;
    private JButton buttonIncreaseCurrentColumn;
    private JButton buttonDecreaseCurrentColumn;
    private JSpinner spinnerMin;
    private JSpinner spinnerMax;
    private JButton buttonSortByRow;
    private JButton buttonSortByColumn;
    private JButton buttonGroupBy;
    private JTextField textFieldGroupBy;

    private DefaultTableModel tableModel;

    private ListTable table;

    private void addToLog(String log) {
        Calendar cal = Calendar.getInstance();
        if (Logs.getText().isEmpty()) {
            Logs.setText("[" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "] " + log);
        } else {
            Logs.setText(Logs.getText() + "\n[" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "] " + log);
        }
    }

    private void updateModel() {
        for (int row = 0; row < tableMain.getRowCount(); row++) {
            for (int column = 0; column < tableMain.getColumnCount(); column++) {
                tableModel.setValueAt(table.getCellValue(row, column), row, column);
            }
        }
        ConsoleTable.setText(table.toString());
    }

    private void updateTable() {
        for (int row = 0; row < tableMain.getRowCount(); row++) {
            for (int column = 0; column < tableMain.getColumnCount(); column++) {
                table.setCellValue(row, column, String.valueOf(tableModel.getValueAt(row, column)));
            }
        }
        ConsoleTable.setText(table.toString());
    }

    public TableForm() {
        this.setTitle("Таблица");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setContentPane(mainPanel);

        tableModel = new DefaultTableModel();
        tableModel.setColumnCount(5);
        tableModel.setRowCount(5);
        table = new ListTable(tableModel.getRowCount(), tableModel.getColumnCount());
        tableMain.setModel(tableModel);
        tableMain.getTableHeader().setResizingAllowed(false);
        tableMain.getTableHeader().setReorderingAllowed(false);

        Logs.setEnabled(false);
        Logs.setBackground(Color.black);

        ConsoleTable.setEnabled(false);
        ConsoleTable.setBackground(Color.black);

        updateTable();
        addToLog("Программа успешно запущена!");

        buttonIncreaseColumns.addActionListener(e -> {
            tableModel.setColumnCount(tableModel.getColumnCount() + 1);
            table.addColumn();
            updateTable();
            addToLog("Количество столбцов увеличено до " + tableModel.getColumnCount());
        });

        buttonDecreaseColumns.addActionListener(e -> {
            if (tableModel.getColumnCount() > 1) {
                tableModel.setColumnCount(tableModel.getColumnCount() - 1);
                table.removeLastColumn();
                updateTable();
                addToLog("Количество столбцов уменьшено до " + tableModel.getColumnCount());
            } else {
                JOptionPane.showMessageDialog(TableForm.this, "Вы не можете удалить последний столбец!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                addToLog("Не удалось уменьшить количество столбцов");
            }
        });

        buttonIncreaseRows.addActionListener(e -> {
            tableModel.setRowCount(tableModel.getRowCount() + 1);
            table.addRow();
            updateTable();
            addToLog("Количество строк увеличено до " + tableModel.getRowCount());
        });

        buttonDecreaseRows.addActionListener(e -> {
            if (tableModel.getRowCount() > 1) {
                tableModel.setRowCount(tableModel.getRowCount() - 1);
                table.removeLastRow();
                updateTable();
                addToLog("Количество строк уменьшено до " + tableModel.getRowCount());
            } else {
                JOptionPane.showMessageDialog(TableForm.this, "Вы не можете удалить последнюю строку!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                addToLog("Не удалось уменьшить количество строк");
            }
        });

        buttonInitTable.addActionListener(e -> {
            if ((Integer) spinnerMin.getValue() < 1 || (Integer) spinnerMax.getValue() < 1) {
                JOptionPane.showMessageDialog(TableForm.this, "Вы не можете инициализировать таблицу с таким количеством строк и/или столбцов!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                addToLog("Не удалось инициализировать таблицу");
                return;
            }
            tableModel = new DefaultTableModel();
            tableModel.setColumnCount((Integer) spinnerMax.getValue());
            tableModel.setRowCount((Integer) spinnerMin.getValue());
            table = new ListTable(tableModel.getRowCount(), tableModel.getColumnCount());
            tableMain.setModel(tableModel);
            tableMain.getTableHeader().setResizingAllowed(false);
            tableMain.getTableHeader().setReorderingAllowed(false);
            updateTable();
            addToLog("Инициализирована новая таблица " + tableModel.getRowCount() + "x" + tableModel.getColumnCount());
        });

        buttonFillTable.addActionListener(e -> {
            if ((Integer) spinnerMax.getValue() - (Integer) spinnerMin.getValue() <= 0) {
                JOptionPane.showMessageDialog(TableForm.this, "Вы не можете заполнить таблицу отрицательными числами!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                addToLog("Не удалось заполнить таблицу");
                return;
            }
            table.fillRandom((Integer) spinnerMin.getValue(), (Integer) spinnerMax.getValue());
            ConsoleTable.setText("");
            updateModel();
            addToLog("Таблица была заполнена числами");
        });

        buttonUpdateTable.addActionListener(e -> {
            updateTable();
            addToLog("Значения ячеек на окне обновлены в классе таблицы");
        });

        buttonIncreaseCurrentRow.addActionListener(e -> {
            if ((Integer) spinnerNumber.getValue() < 0 || (Integer) spinnerNumber.getValue() > tableModel.getRowCount()) {
                JOptionPane.showMessageDialog(TableForm.this, "Вы не можете добавить строку в несуществующую позицию!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                addToLog("Не удалось добавить строку");
                return;
            }
            tableModel.setRowCount(tableModel.getRowCount() + 1);
            table.addCurRow((Integer) spinnerNumber.getValue());
            updateModel();
            addToLog("Количество строк увеличено до " + tableModel.getRowCount() + ", добавлена строка " + spinnerNumber.getValue());
        });

        buttonDecreaseCurrentRow.addActionListener(e -> {
            if ((Integer) spinnerNumber.getValue() < 0 || (Integer) spinnerNumber.getValue() >= tableModel.getRowCount()) {
                JOptionPane.showMessageDialog(TableForm.this, "Вы не можете удалить несуществующую строку!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                addToLog("Не удалось убрать строку");
            } else {
                tableModel.setRowCount(tableModel.getRowCount() - 1);
                table.removeRow((Integer) spinnerNumber.getValue());
                updateModel();
                addToLog("Количество строк уменьшено до " + tableModel.getRowCount() + ", убрана строка " + spinnerNumber.getValue());
            }
        });

        buttonIncreaseCurrentColumn.addActionListener(e -> {
            if ((Integer) spinnerNumber.getValue() < 0 || (Integer) spinnerNumber.getValue() > tableModel.getColumnCount()) {
                JOptionPane.showMessageDialog(TableForm.this, "Вы не можете добавить столбец в несуществующую позицию!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                addToLog("Не удалось добавить столбец");
                return;
            }
            tableModel.setColumnCount(tableModel.getColumnCount() + 1);
            table.addCurColumn((Integer) spinnerNumber.getValue());
            updateModel();
            addToLog("Количество столбцов увеличено до " + tableModel.getRowCount() + ", добавлен столбец " + spinnerNumber.getValue());
        });

        buttonDecreaseCurrentColumn.addActionListener(e -> {
            if ((Integer) spinnerNumber.getValue() < 0 || (Integer) spinnerNumber.getValue() >= tableModel.getColumnCount()) {
                JOptionPane.showMessageDialog(TableForm.this, "Вы не можете удалить несуществующий столбец!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                addToLog("Не удалось убрать столбец");
            } else {
                tableModel.setColumnCount(tableModel.getColumnCount() - 1);
                table.removeColumn((Integer) spinnerNumber.getValue());
                updateModel();
                addToLog("Количество столбцов уменьшено до " + tableModel.getRowCount() + ", убран столбец " + spinnerNumber.getValue());
            }
        });

        buttonSortByRow.addActionListener(e -> {
            if ((Integer) spinnerNumber.getValue() < 0 || (Integer) spinnerNumber.getValue() >= tableModel.getRowCount()) {
                JOptionPane.showMessageDialog(TableForm.this, "Вы не можете сортировать таблицу по несуществующей строке!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                addToLog("Не удалось отсортировать таблицу по строке");
                return;
            }
            table.sortByRow((Integer) spinnerNumber.getValue());
            updateModel();
            addToLog("Таблица сгруппирована по строке " + spinnerNumber.getValue());
        });

        buttonSortByColumn.addActionListener(e -> {
            if ((Integer) spinnerNumber.getValue() < 0 || (Integer) spinnerNumber.getValue() >= tableModel.getColumnCount()) {
                JOptionPane.showMessageDialog(TableForm.this, "Вы не можете сортировать таблицу по несуществующему столбцу!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                addToLog("Не удалось отсортировать таблицу по столбцу");
                return;
            }
            table.sortByColumn((Integer) spinnerNumber.getValue());
            updateModel();
            addToLog("Таблица сгруппирована по столбцу " + spinnerNumber.getValue());
        });
        buttonGroupBy.addActionListener(e -> {
            table = table.groupDataTableBy(textFieldGroupBy.getText());
            tableModel = new DefaultTableModel();
            tableModel.setColumnCount(table.getColumnCount());
            tableModel.setRowCount(table.getRowCount());
            tableMain.setModel(tableModel);
            tableMain.getTableHeader().setResizingAllowed(false);
            tableMain.getTableHeader().setReorderingAllowed(false);
            updateModel();
            addToLog("Таблица сгруппирована!");
        });
    }
}
