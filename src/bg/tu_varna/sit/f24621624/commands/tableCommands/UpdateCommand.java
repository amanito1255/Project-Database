package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;

import java.util.List;

/**
 * Команда за обновяване на стойности в клетките на таблица по определено условие.
 */
public class UpdateCommand implements Command {
    /**
     * Връща името на командата.
     * @return името на командата като текст
     */
    @Override
    public String getName() {
        return "update";
    }

    /**
     * Описание на командата за помощното меню.
     * @return текст с описанието
     */
    @Override
    public String getDescription()
    {
        return "update <table_name> <search_column_n> <search_value> <target_column_n> <target_value> (updates cells)";
    }
    /**
     * Обхожда редовете в таблицата, търси съвпадение в колоната за търсене
     * и при откриване променя стойността в целевата колона с новата стойност.
     * @return текст с броя на обновените редове или съобщение за грешка
     */
    @Override
    public String execute(String[] args) {
        if (args.length < 5) {
            return "Error: Usage update <table_name> <search_column_n> <search_value> <target_column_n> <target_value>";
        }

        String tableName = args[0];
        int searchCol = Integer.parseInt(args[1]) - 1;
        String searchVal = args[2];
        int targetCol = Integer.parseInt(args[3]) - 1;
        String targetVal = args[4];

        Table table = Database.getInstance().getTable(tableName);
        if (table == null) {
            return "Error: Table '" + tableName + "' not found.";
        }

        int updatedCount = 0;
        // Извършваме обхождането на всички записи в заредената таблица
        for (List<Object> row : table.getRows()) {
            String currentCell = String.valueOf(row.get(searchCol));
            // Ако стойността в клетката съвпада с филтъра, променяме целевата колона
            if (currentCell.equals(searchVal)) {
                row.set(targetCol, targetVal);
                updatedCount++;
            }
        }

        return "Successfully updated " + updatedCount + " rows in '" + tableName + "'.";
    }
}