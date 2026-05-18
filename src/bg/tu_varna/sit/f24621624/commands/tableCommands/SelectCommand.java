package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;

import java.util.List;

/**
 * Команда за намиране на редове по определена стойност в дадена колона.
 */
public class SelectCommand implements Command {
    /**
     * Връща името на командата.
     * @return името на командата като текст
     */
    @Override
    public String getName() {
        return "select";
    }

    /**
     * Описание на командата за помощното меню.
     * @return текст с описанието
     */
    @Override
    public String getDescription()
    {
        return "select <column-n> <value> <table_name> (prints rows containing value of the specified column)";
    }

    /**
     * Обхожда редовете на таблицата, намира съвпаденията по подадения индекс
     * на колона и ги събира в общ текст.
     * @return намерените редове като текст или съобщение за грешка/липса на резултати
     */
    @Override
    public String execute(String[] args) {
        if (args.length < 3) {
            return "Error: Usage select <column-n> <value> <table_name>";
        }

        int columnIndex = Integer.parseInt(args[0]) - 1;
        String searchValue = args[1];
        String tableName = args[2];

        Table table = Database.getInstance().getTable(tableName);
        if (table == null) {
            return "Error: Table '" + tableName + "' not found.";
        }

        StringBuilder sb = new StringBuilder();
        // Обикаляме редовете в оригиналния им вид
        for (List<Object> row : table.getRows())
        {
            String cellValue = String.valueOf(row.get(columnIndex));

            // Проверяваме дали стойността в клетката съвпада с търсената
            if (cellValue.equals(searchValue))
            {
                sb.append(row.toString()).append("\n");
            }
        }

        if (sb.length() == 0)
        {
            return "No matching rows found.";
        }

        return sb.toString().trim();
    }
}
