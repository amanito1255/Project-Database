package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;

import java.util.List;

/**
 * Команда за преброяване на редовете в таблица, които съдържат определена стойност.
 */
public class CountCommand implements Command
{
    /**
     * Връща името на командата.
     * @return името на командата като текст
     */
    @Override
    public String getName() {
        return "count";
    }

    /**
     * Описание на командата за помощното меню.
     * @return текст с описанието
     */
    @Override
    public String getDescription() {
        return "count <table_name> <search_column_n> <search_value> (counts rows with specific value)";
    }

    /**
     * Обхожда редовете на таблицата, проверява стойността на посочената колона
     * и инкрементира брояч при всяко намерено съвпадение.
     * @return текст с общия брой намерени съвпадения или съобщение за грешка
     */
    @Override
    public String execute(String[] args) {
        if (args.length < 3) {
            return "Error: Usage count <table_name> <search_column_n> <search_value>";
        }

        String tableName = args[0];
        int columnIndex = Integer.parseInt(args[1]) - 1;
        String searchValue = args[2];

        Table table = Database.getInstance().getTable(tableName);
        if (table == null) {
            return "Error: Table '" + tableName + "' not found.";
        }

        int count = 0;
        // Обхождаме редовете в заредената таблица
        for (List<Object> row : table.getRows()) {
            String cellValue = String.valueOf(row.get(columnIndex));
            // Проверяваме дали стойността в клетката съвпада с филтъра
            if (cellValue.equals(searchValue)) {
                count++;
            }
        }

        return "Found " + count + " rows matching in '" + tableName;
    }
}
