package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;

import java.util.List;

/**
 * Команда за добавяне на нова колона в края на съществуваща таблица.
 */
public class AddColumnCommand implements Command {
    /**
     * Връща името на командата.
     * @return името на командата като текст
     */
    @Override
    public String getName() {
        return "addcolumn";
    }

    /**
     * Описание на командата за помощното меню.
     * @return текст с описанието
     */
    @Override
    public String getDescription()
    {
        return "addcolumn <table_name> <column_name> <column_type> (adds a new column to the table)";
    }

    /**
     * Проверява типа на новата колона, добавя го към структурата на таблицата
     * и допълва всички налични редове с празна стойност (NULL).
     * @return съобщение за успешно добавяне или грешка при невалидни данни
     */
    @Override
    public String execute(String[] args)
    {
        if (args.length < 3) {
            return "Error: Usage addcolumn <table_name> <column_name> <column_type>";
        }

        String tableName = args[0];
        String columnType = args[2];

        Table table = Database.getInstance().getTable(tableName);
        if (table == null)
        {
            return "Error: Table '" + tableName + "' not found.";
        }

        // Проверяваме дали подаденият тип е сред поддържаните от системата
        if (!columnType.equals("int") && !columnType.equals("double") && !columnType.equals("string"))
        {
            return "Error: Invalid column type. Supported types: int, double, string.";
        }

        // Добавяме новия тип към списъка с типове на таблицата
        table.getColumnTypes().add(columnType);

        // Обхождаме съществуващите редове и добавяме NULL на новата позиция
        for (List<Object> row : table.getRows())
        {
            row.add("NULL");
        }

        return "Column added successfully to table '" + tableName + "'. All existing rows populated with NULL.";
    }
}
