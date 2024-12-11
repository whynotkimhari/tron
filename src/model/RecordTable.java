/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * The Record Table
 * @author Nguyen Kim Hai, Bui
 */
public class RecordTable extends AbstractTableModel {
    private final ArrayList<Record> records;
    private final String[] colNames = new String[] { "Name", "Score" };
    
    public RecordTable(ArrayList<Record> records) {
        this.records = records;
    }

    @Override
    public Object getValueAt(int r, int c) {
        Record h = records.get(r);
        if      (c == 0) return h.name;
        return h.score;
    }
    
    @Override
    public int getRowCount() { return records.size(); }

    @Override
    public int getColumnCount() { return 2; }

    @Override
    public String getColumnName(int i) { return colNames[i]; }    
    
}
