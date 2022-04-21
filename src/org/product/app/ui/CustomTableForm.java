package org.product.app.ui;

import lombok.AllArgsConstructor;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.List;

@AllArgsConstructor
public class CustomTableForm<T> extends AbstractTableModel {
    private Class<T> cls;
    private String[] columNames;
    private List<T> rows;

    @Override
    public String getColumnName(int column) {
        return columNames[column];
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return cls.getDeclaredFields().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            T t = rows.get(rowIndex);
            Field field = cls.getDeclaredFields()[columnIndex];
            field.setAccessible(true);
            return field.get(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    public Class<T> getCls() {
        return cls;
    }

    public void setCls(Class<T> cls) {
        this.cls = cls;
    }

    public String[] getColumNames() {
        return columNames;
    }

    public void setColumNames(String[] columNames) {
        this.columNames = columNames;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
