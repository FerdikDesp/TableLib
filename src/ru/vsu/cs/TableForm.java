package ru.vsu.cs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Random;


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

    private ListTable<String> table;

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
        this.setTitle("??????????????");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setContentPane(mainPanel);

        tableModel = new DefaultTableModel();
        tableModel.setColumnCount(5);
        tableModel.setRowCount(5);
        table = new ListTable<>(tableModel.getRowCount(), tableModel.getColumnCount());
        tableMain.setModel(tableModel);
        tableMain.getTableHeader().setResizingAllowed(false);
        tableMain.getTableHeader().setReorderingAllowed(false);

        Logs.setEnabled(false);
        Logs.setBackground(Color.black);

        ConsoleTable.setEnabled(false);
        ConsoleTable.setBackground(Color.black);

        updateTable();
        addToLog("?????????????????? ?????????????? ????????????????!");

        buttonIncreaseColumns.addActionListener(e -> {
            tableModel.setColumnCount(tableModel.getColumnCount() + 1);
            table.addColumn();
            updateTable();
            addToLog("???????????????????? ???????????????? ?????????????????? ???? " + tableModel.getColumnCount());
        });

        buttonDecreaseColumns.addActionListener(e -> {
            if (tableModel.getColumnCount() > 1) {
                tableModel.setColumnCount(tableModel.getColumnCount() - 1);
                table.removeLastColumn();
                updateTable();
                addToLog("???????????????????? ???????????????? ?????????????????? ???? " + tableModel.getColumnCount());
            } else {
                JOptionPane.showMessageDialog(TableForm.this, "???? ???? ???????????? ?????????????? ?????????????????? ??????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
                addToLog("???? ?????????????? ?????????????????? ???????????????????? ????????????????");
            }
        });

        buttonIncreaseRows.addActionListener(e -> {
            tableModel.setRowCount(tableModel.getRowCount() + 1);
            table.addRow();
            updateTable();
            addToLog("???????????????????? ?????????? ?????????????????? ???? " + tableModel.getRowCount());
        });

        buttonDecreaseRows.addActionListener(e -> {
            if (tableModel.getRowCount() > 1) {
                tableModel.setRowCount(tableModel.getRowCount() - 1);
                table.removeLastRow();
                updateTable();
                addToLog("???????????????????? ?????????? ?????????????????? ???? " + tableModel.getRowCount());
            } else {
                JOptionPane.showMessageDialog(TableForm.this, "???? ???? ???????????? ?????????????? ?????????????????? ????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
                addToLog("???? ?????????????? ?????????????????? ???????????????????? ??????????");
            }
        });

        buttonInitTable.addActionListener(e -> {
            if ((Integer) spinnerMin.getValue() < 1 || (Integer) spinnerMax.getValue() < 1) {
                JOptionPane.showMessageDialog(TableForm.this, "???? ???? ???????????? ???????????????????????????????? ?????????????? ?? ?????????? ?????????????????????? ?????????? ??/?????? ????????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
                addToLog("???? ?????????????? ???????????????????????????????? ??????????????");
                return;
            }
            tableModel = new DefaultTableModel();
            tableModel.setColumnCount((Integer) spinnerMax.getValue());
            tableModel.setRowCount((Integer) spinnerMin.getValue());
            table = new ListTable<>(tableModel.getRowCount(), tableModel.getColumnCount());
            tableMain.setModel(tableModel);
            tableMain.getTableHeader().setResizingAllowed(false);
            tableMain.getTableHeader().setReorderingAllowed(false);
            updateTable();
            addToLog("???????????????????????????????? ?????????? ?????????????? " + tableModel.getRowCount() + "x" + tableModel.getColumnCount());
        });

        buttonFillTable.addActionListener(e -> {
            if ((Integer) spinnerMax.getValue() - (Integer) spinnerMin.getValue() <= 0) {
                JOptionPane.showMessageDialog(TableForm.this, "???? ???? ???????????? ?????????????????? ?????????????? ???????????????????????????? ??????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
                addToLog("???? ?????????????? ?????????????????? ??????????????");
                return;
            }


            int maxValue = (Integer) spinnerMax.getValue();
            int minValue = (Integer) spinnerMin.getValue();

            Random random = new Random();

            for (int row = 0; row < tableMain.getRowCount(); row++) {
                for (int column = 0; column < tableMain.getColumnCount(); column++) {
                    tableMain.getModel().setValueAt((random.nextInt(maxValue - minValue) + minValue), row, column);
                }
            }
            updateTable();
            ConsoleTable.setText("");
            updateModel();
            addToLog("?????????????? ???????? ?????????????????? ??????????????");
        });

        buttonUpdateTable.addActionListener(e -> {
            updateTable();
            addToLog("???????????????? ?????????? ???? ???????? ?????????????????? ?? ???????????? ??????????????");
        });

        buttonIncreaseCurrentRow.addActionListener(e -> {
            if ((Integer) spinnerNumber.getValue() < 0 || (Integer) spinnerNumber.getValue() > tableModel.getRowCount()) {
                JOptionPane.showMessageDialog(TableForm.this, "???? ???? ???????????? ???????????????? ???????????? ?? ???????????????????????????? ??????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
                addToLog("???? ?????????????? ???????????????? ????????????");
                return;
            }
            tableModel.setRowCount(tableModel.getRowCount() + 1);
            table.addCurRow((Integer) spinnerNumber.getValue());
            updateModel();
            addToLog("???????????????????? ?????????? ?????????????????? ???? " + tableModel.getRowCount() + ", ?????????????????? ???????????? " + spinnerNumber.getValue());
        });

        buttonDecreaseCurrentRow.addActionListener(e -> {
            if ((Integer) spinnerNumber.getValue() < 0 || (Integer) spinnerNumber.getValue() >= tableModel.getRowCount()) {
                JOptionPane.showMessageDialog(TableForm.this, "???? ???? ???????????? ?????????????? ???????????????????????????? ????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
                addToLog("???? ?????????????? ???????????? ????????????");
            } else {
                tableModel.setRowCount(tableModel.getRowCount() - 1);
                table.removeRow((Integer) spinnerNumber.getValue());
                updateModel();
                addToLog("???????????????????? ?????????? ?????????????????? ???? " + tableModel.getRowCount() + ", ???????????? ???????????? " + spinnerNumber.getValue());
            }
        });

        buttonIncreaseCurrentColumn.addActionListener(e -> {
            if ((Integer) spinnerNumber.getValue() < 0 || (Integer) spinnerNumber.getValue() > tableModel.getColumnCount()) {
                JOptionPane.showMessageDialog(TableForm.this, "???? ???? ???????????? ???????????????? ?????????????? ?? ???????????????????????????? ??????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
                addToLog("???? ?????????????? ???????????????? ??????????????");
                return;
            }
            tableModel.setColumnCount(tableModel.getColumnCount() + 1);
            table.addCurColumn((Integer) spinnerNumber.getValue());
            updateModel();
            addToLog("???????????????????? ???????????????? ?????????????????? ???? " + tableModel.getRowCount() + ", ???????????????? ?????????????? " + spinnerNumber.getValue());
        });

        buttonDecreaseCurrentColumn.addActionListener(e -> {
            if ((Integer) spinnerNumber.getValue() < 0 || (Integer) spinnerNumber.getValue() >= tableModel.getColumnCount()) {
                JOptionPane.showMessageDialog(TableForm.this, "???? ???? ???????????? ?????????????? ???????????????????????????? ??????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
                addToLog("???? ?????????????? ???????????? ??????????????");
            } else {
                tableModel.setColumnCount(tableModel.getColumnCount() - 1);
                table.removeColumn((Integer) spinnerNumber.getValue());
                updateModel();
                addToLog("???????????????????? ???????????????? ?????????????????? ???? " + tableModel.getRowCount() + ", ?????????? ?????????????? " + spinnerNumber.getValue());
            }
        });

        buttonSortByRow.addActionListener(e -> {
            if ((Integer) spinnerNumber.getValue() < 0 || (Integer) spinnerNumber.getValue() >= tableModel.getRowCount()) {
                JOptionPane.showMessageDialog(TableForm.this, "???? ???? ???????????? ?????????????????????? ?????????????? ???? ???????????????????????????? ????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
                addToLog("???? ?????????????? ?????????????????????????? ?????????????? ???? ????????????");
                return;
            }
            table.sortByRow((Integer) spinnerNumber.getValue());
            updateModel();
            addToLog("?????????????? ?????????????????????????? ???? ???????????? " + spinnerNumber.getValue());
        });

        buttonSortByColumn.addActionListener(e -> {
            if ((Integer) spinnerNumber.getValue() < 0 || (Integer) spinnerNumber.getValue() >= tableModel.getColumnCount()) {
                JOptionPane.showMessageDialog(TableForm.this, "???? ???? ???????????? ?????????????????????? ?????????????? ???? ?????????????????????????????? ??????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
                addToLog("???? ?????????????? ?????????????????????????? ?????????????? ???? ??????????????");
                return;
            }
            table.sortByColumn((Integer) spinnerNumber.getValue());
            updateModel();
            addToLog("?????????????? ?????????????????????????? ???? ?????????????? " + spinnerNumber.getValue());
        });
        buttonGroupBy.addActionListener(e -> {
            table.groupDataTableBy(textFieldGroupBy.getText());
            tableModel = new DefaultTableModel();
            tableModel.setColumnCount(table.getColumnCount());
            tableModel.setRowCount(table.getRowCount());
            tableMain.setModel(tableModel);
            tableMain.getTableHeader().setResizingAllowed(false);
            tableMain.getTableHeader().setReorderingAllowed(false);
            updateModel();
            addToLog("?????????????? ??????????????????????????!");
        });
        tableMain.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == 10) {
                    updateTable();
                }
            }
        });
    }
}
