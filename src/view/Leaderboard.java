/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.WindowConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.RecordTable;
import model.Record;

/**
 * The leaderboard
 * @author Nguyen Kim Hai, Bui
 */
public class Leaderboard extends JDialog {
    private final JTable table;
    
    public Leaderboard(ArrayList<Record> records, JFrame parent) {
        super(parent, true);
        table = new JTable(new RecordTable(records));
        table.setFillsViewportHeight(true);
        
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        
        sorter.setComparator(1, (o1, o2) -> {
            Integer num1 = Integer.valueOf(o1.toString());
            Integer num2 = Integer.valueOf(o2.toString());
            return num1.compareTo(num2);
        });
        
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        
        sorter.setSortKeys(sortKeys);
        table.setRowSorter(sorter);
        
        add(new JScrollPane(table));
        setSize(400,400);
        setTitle("Leaderboard");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
