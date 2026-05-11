package bg.tu_varna.sit.f24621624.models;

import java.util.ArrayList;
import java.util.List;

public class Table
{
    private String name;
    private List<String> columnTypes;
    private List<List<Object>> rows;

    public Table(String name, List<String> columnTypes)
    {
        this.name = name;
        this.columnTypes = columnTypes;
        this.rows = new ArrayList<>();
    }

    public String getName() { return name; }
    public List<List<Object>> getRows() { return rows; }
    public List<String> getColumnTypes() { return columnTypes; }

    public void addRow(List<Object> row) {
        this.rows.add(row);
    }
}