package ru.vsu.cs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.Date;


public class tableForm extends JFrame {

    private JPanel mainPanel;
    private JButton buttonFillTable;
    private JTable tableMain;
    private JButton buttonIncreaseColumns;
    private JButton buttonDecreaseColumns;
    private JButton buttonIncreaseRows;
    private JButton buttonDecreaseRows;
    private JButton buttonInitTable;
    private JTextArea ConcoleTable;
    private JTextArea Logs;
    private JButton buttonUpdateTable;

    private DefaultTableModel tableModel;

    private DefaultTableModel tempTableModel;

    private Table table;
    private boolean wasTableGenerated = false;
    Date date;

    private void addToLog(String log) {
        date = new Date();
        if (Logs.getText().isEmpty()) {
            Logs.setText("[" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "] " + log);
        } else {
            Logs.setText(Logs.getText() + "\n[" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "] " + log);
        }
    }

    public tableForm() {
        this.setTitle("Таблица");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setContentPane(mainPanel);

        tableModel = new DefaultTableModel();
        tableModel.setColumnCount(5);
        tableModel.setRowCount(5);
        table = new Table(tableModel.getRowCount(), tableModel.getColumnCount());
        tableMain.setModel(tableModel);
        tableMain.getTableHeader().setResizingAllowed(false);
        tableMain.getTableHeader().setReorderingAllowed(false);

        Logs.setEnabled(false);
        Logs.setBackground(Color.black);

        ConcoleTable.setEnabled(false);
        ConcoleTable.setBackground(Color.black);

        addToLog("Программа успешно запущена!");

        buttonIncreaseColumns.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setColumnCount(tableModel.getColumnCount() + 1);
                table.addColumn();
                addToLog("Количество столбцов увеличено до " + tableModel.getColumnCount());
            }
        });

        buttonDecreaseColumns.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableModel.getColumnCount() > 0) {
                    tableModel.setColumnCount(tableModel.getColumnCount() - 1);
                    table.removeColumn();
                    addToLog("Количество столбцов уменьшено до " + tableModel.getColumnCount());
                } else {
                    JOptionPane.showMessageDialog(tableForm.this, "Вы не можете удалить несуществующий столбец!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    addToLog("Не удалось уменьшить количество столбцов");
                }
            }
        });

        buttonIncreaseRows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(tableModel.getRowCount() + 1);
                table.addRow();
                addToLog("Количество строк увеличено до " + tableModel.getRowCount());
            }
        });

        buttonDecreaseRows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableModel.getRowCount() > 0) {
                    tableModel.setRowCount(tableModel.getRowCount() - 1);
                    table.removeRow();
                    addToLog("Количество строк уменьшено до " + tableModel.getRowCount());
                } else {
                    JOptionPane.showMessageDialog(tableForm.this, "Вы не можете удалить несуществующую строку!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    addToLog("Не удалось уменьшить количество строк");
                }
            }
        });

        buttonInitTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel = new DefaultTableModel();
                tableModel.setColumnCount(5);
                tableModel.setRowCount(5);
                table = new Table(tableModel.getRowCount(), tableModel.getColumnCount());
                tableMain.setModel(tableModel);
                tableMain.getTableHeader().setResizingAllowed(false);
                tableMain.getTableHeader().setReorderingAllowed(false);
                addToLog("Инициализирована новая таблица");
            }
        });

        buttonFillTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.fill();
                ConcoleTable.setText("");
                for (int i = 0; i < tableMain.getRowCount(); i++) {
                    for (int j = 0; j < tableMain.getColumnCount(); j++) {
                        tableModel.setValueAt(table.getCellValue(i, j), i, j);
                        ConcoleTable.setText(ConcoleTable.getText() + table.getCellValue(i, j) + "\t");
                    }
                    ConcoleTable.setText(ConcoleTable.getText() + "\n");
                }
                addToLog("Таблица была заполнена числами");
            }
        });

        buttonUpdateTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConcoleTable.setText("");
                for (int i = 0; i < tableMain.getRowCount(); i++) {
                    for (int j = 0; j < tableMain.getColumnCount(); j++) {
                        table.setCellValue(i, j, String.valueOf(tableModel.getValueAt(i, j)));
                        ConcoleTable.setText(ConcoleTable.getText() + table.getCellValue(i, j) + "\t");
                    }
                    ConcoleTable.setText(ConcoleTable.getText() + "\n");
                }
                addToLog("Значения ячеек на окне обновлены в классе таблицы");
            }
        });
    }
}
