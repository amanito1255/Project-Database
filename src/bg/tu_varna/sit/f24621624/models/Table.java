package bg.tu_varna.sit.f24621624.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, представящ структурата и съдържанието на една таблица в базата данни.
 */
public class Table
{
    // Име на таблицата
    private String name;
    // Списък от редове, където всеки ред е списък от обекти (клекти с данни)
    private List<List<Object>> rows;
    // Списък, съхраняващ типовете на колоните (напр. "int", "string", "double")
    private List<String> columnTypes;

    /**
     * Конструктор за създаване на нова таблица с име и дефинирани типове на колоните.
     * @param name името на таблицата
     * @param columnTypes списък с типовете на колоните
     */
    public Table(String name, List<String> columnTypes)
    {
        this.name = name;
        this.rows = new ArrayList<>();
        this.columnTypes = columnTypes;
    }

    /**
     * Връща името на таблицата.
     * @return името като текст
     */
    public String getName()
    {
        return name;
    }
    /**
     * Връща всички редове с данни от таблицата.
     * @return списък от редовете
     */
    public List<List<Object>> getRows()
    {
        return rows;
    }
    /**
     * Връща списъка с типовете на колоните на таблицата.
     * @return списък от текстови типове
     */
    public List<String> getColumnTypes()
    {
        return columnTypes;
    }

    /**
     * Добавя нов ред с данни в таблицата.
     * @param row списък от обекти, представляващ новия ред
     */
    public void addRow(List<Object> row)
    {
        this.rows.add(row);
    }
    /**
     * Премахва ред от таблицата по неговия пореден индекс.
     * @param index поредният номер на реда за изтриване
     */
    public void removeRow(int index) {
        this.rows.remove(index);
    }

}