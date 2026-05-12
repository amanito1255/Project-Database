package bg.tu_varna.sit.f24621624.models;

import java.util.ArrayList;
import java.util.List;

public class Table
{
    private String name;
    private List<List<Object>> rows;
    private List<String> columnTypes;

    public Table(String name, List<String> columnTypes)
    {
        this.name = name;
        this.rows = new ArrayList<>();
        this.columnTypes = columnTypes;
    }

    public String getName()
    { return name;
    }
    public List<List<Object>> getRows()
    { return rows;
    }
    public List<String> getColumnTypes()
    {
        return columnTypes;
    }

    public void addRow(List<Object> row)
    {
        this.rows.add(row);
    }
}