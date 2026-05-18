package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;

import java.util.List;

/**
 * Команда за извършване на агрегиращи математически операции (sum, product, maximum, minimum)
 * върху стойностите на целева колона за редове, отговарящи на филтър.
 */
public class AggregateCommand implements Command {

    /**
     * Връща името на командата.
     * @return името на командата като текст
     */
    @Override
    public String getName() {
        return "aggregate";
    }

    /**
     * Описание на командата за помощното меню.
     * @return текст с описанието
     */
    @Override
    public String getDescription()
    {
        return "aggregate <table_name> <search_column_n> <search_value> <target_column_n> <operation> (performs math operations on column cells)";
    }

    /**
     * Обхожда редовете в таблицата, филтрира ги по критерия и акумулира математическия резултат
     * от целевата колона според подадената операция (sum, product, maximum или minimum).
     * @return съобщение с крайния пресметнат резултат от операцията или съобщение за грешка
     */
    @Override
    public String execute(String[] args)
    {
        if (args.length < 5)
        {
            return "Error: Usage aggregate <table_name> <search_column_n> <search_value> <target_column_n> <operation>";
        }

        String tableName = args[0];
        int searchCol = Integer.parseInt(args[1]) - 1;
        String searchValue = args[2];
        int targetCol = Integer.parseInt(args[3]) - 1;
        String operation = args[4].toLowerCase();

        Table table = Database.getInstance().getTable(tableName);
        if (table == null) {
            return "Error: Table '" + tableName + "' not found.";
        }

        double result = 0;
        boolean isFirstMatch = true;
        int count = 0;

        // Извършваме обхождането на всички налични записи в заредената таблица
        for (List<Object> row : table.getRows()) {
            String searchCell = String.valueOf(row.get(searchCol));

            // Проверяваме дали редът отговаря на филтъра за търсене
            if (searchCell.equals(searchValue)) {
                String targetCellStr = String.valueOf(row.get(targetCol));

                // Пропускаме клетки със стойност NULL, за да се избегнат изключения
                if (targetCellStr.equals("NULL")) {
                    continue;
                }

                double value = Double.parseDouble(targetCellStr);
                count++;

                if (operation.equals("sum")) {
                    result = result + value;
                }
                else if (operation.equals("product")) {
                    if (isFirstMatch) {
                        result = value;
                    } else {
                        result = result * value;
                    }
                }
                else if (operation.equals("maximum")) {
                    if (isFirstMatch || value > result) {
                        result = value;
                    }
                }
                else if (operation.equals("minimum")) {
                    if (isFirstMatch || value < result) {
                        result = value;
                    }
                }

                isFirstMatch = false;
            }
        }

        if (count == 0)
        {
            return "Error: No matching records found to calculate.";
        }

        return "Result of " + operation + ": " + result;
    }
}
