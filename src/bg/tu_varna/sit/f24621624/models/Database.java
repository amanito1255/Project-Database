package bg.tu_varna.sit.f24621624.models;

import java.util.HashMap;
import java.util.Map;

public class Database
{
    private static Database instance;
    private final Map<String, Table> tables = new HashMap<>();

    private Database() {}

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void addTable(Table table) {
        tables.put(table.getName(), table);
    }

    public Table getTable(String name) {
        return tables.get(name);
    }

    public Map<String, Table> getTables() {
        return tables;
    }

    public void clear() {
        tables.clear();
    }
}
