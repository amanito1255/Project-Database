package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;
import java.util.List;


/**
 * Команда, която описва структурата на таблица (показва типовете на колоните).
 */
public class DescribeCommand implements Command {

    /**
     * Връща името на командата.
     * @return името на командата като текст
     */
    @Override
    public String getName()
    {
        return "describe";
    }

    /**
     * Описание за помощното меню.
     * @return текст с описанието
     */
    @Override
    public String getDescription()
    {
        return "describe <name> (shows column types of a table)";
    }


    /**
     * Проверява дали таблицата съществува в базата данни и връща
     * списък с типовете на колоните ѝ.
     * @return текст с типовете данни или съобщение за грешка
     */
    @Override
    public String execute(String[] args)
    {
        if (args.length < 1)
        {
            return "Error: usage describe <table_name>";
        }

        String tableName = args[0];
        // Търсим таблицата по име
        Table table = Database.getInstance().getTable(tableName);

        // Ако не я намерим, връщаме съобщение за грешка
        if (table == null)
        {
            return "Error: Table '" + tableName + "' not found.";
        }

        // Взимаме списъка с типове и го съединяваме за принтиране
        List<String> types = table.getColumnTypes();
        return "Table '" + tableName + "' column types: " + String.join(", ", types);
    }
}
