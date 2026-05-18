package bg.tu_varna.sit.f24621624.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Клас, представящ базата данни в паметта, реализиран чрез Singleton pattern.
 * Съхранява каталога от всички заредени таблици.
 */
public class Database
{
    // Единствената инстанция на базата данни за цялото приложение
    private static Database instance;
    // Map, пазещ таблиците, където ключ е името на таблицата, а стойност е самият обект Table
    private final Map<String, Table> tables = new HashMap<>();

    /**
     * Частен (private) конструктор, за да се ограничи създаването на обекти извън класа.
     */
    private Database() {}

    /**
     * Глобална точка за достъп до инстанцията на базата данни.
     * @return обекта на базата данни
     */
    public static Database getInstance() {
        if (instance == null)
        {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Добавя нова таблица в базата данни или презаписва съществуваща със същото име.
     * @param table обектът на таблицата, която се добавя
     */
    public void addTable(Table table)
    {
        tables.put(table.getName(), table);
    }

    /**
     * Търси и връща таблица по нейното текстово име.
     * @param name името на търсената таблица
     * @return обектът Table или null, ако таблицата не съществува
     */
    public Table getTable(String name) {
        return tables.get(name);
    }

    /**
     * Връща целия речник с всички заредени таблици в базата данни.
     * @return структурата Map, съдържаща имената и обектите на таблиците
     */
    public Map<String, Table> getTables() {
        return tables;
    }

    /**
     * Изтрива всички заредени таблици от паметта на базата данни (изчиства каталога).
     */
    public void clear() {
        tables.clear();
    }
}
