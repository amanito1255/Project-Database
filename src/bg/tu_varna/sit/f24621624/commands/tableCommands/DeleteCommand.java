package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;

import java.util.List;

/**
 * Команда за изтриване на редове от таблица по определено условие.
 */
public class DeleteCommand implements Command {
    /**
     * Връща името на командата.
     * @return името на командата като текст
     */
    @Override
    public String getName()
    {
        return "delete";
    }

    /**
     * Описание на командата за помощното меню.
     * @return текст с описанието
     */
    @Override
    public String getDescription()
    {
        return "delete <table_name> <search_column_n> <search_value> (deletes rows)";
    }

    /**
     * Обхожда редовете на таблицата и изтрива всеки ред, чиято клетка на
     * посочения индекс съвпада с търсената стойност.
     * @return съобщение с броя на изтритите редове или съобщение за грешка
     */
    @Override
    public String execute(String[] args)
    {
        if (args.length < 3)
        {
            return "Error: Usage delete <table_name> <column> <value>";
        }

        String tableName = args[0];
        int columnIndex = Integer.parseInt(args[1]) - 1;
        String searchValue = args[2];

        Table table = Database.getInstance().getTable(tableName);
        if (table == null)
        {
            return "Error: Table not found.";
        }

        List<List<Object>> rows = table.getRows();
        int deletedCount = 0;
        // Въртим цикъл за обхождане и изтриване на съвпадащите редове
        for (int i = 0; i < rows.size(); i++) {
            List<Object> row = rows.get(i);
            String cellValue = String.valueOf(row.get(columnIndex));

            // Ако стойността съвпада, премахваме реда от таблицата
            if (cellValue.equals(searchValue)) {
                table.removeRow(i);
                i--; // Намаляваме индекса, за да не прескочим следващия ред след изтриването
                deletedCount++;
            }
        }

        return "Deleted " + deletedCount + " rows.";
    }
}