package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;

import java.util.List;


/**
 * Команда за разглеждане на съдържанието на таблица по страници.
 */
public class PrintCommand implements Command {
    /**
     * Връща името на командата.
     * @return името на командата като текст
     */
    @Override
    public String getName()
    {
        return "print";
    }
    /**
     * Описание за помощното меню.
     * @return текст с описанието
     */
    @Override
    public String getDescription()
    {
        return "print <table_name> (prints the content of a table)";
    }

    /**
     * @return съобщение за край на прегледа или грешка
     */
    @Override
    public String execute(String[] args) {
        if (args.length < 1)
        {
            return "Error: Usage print <table_name>";
        }

        String tableName = args[0];
        Table table = Database.getInstance().getTable(tableName);

        if (table == null)
        {
            return "Error: table " + tableName + "was not found.";
        }
        if (table.getRows().isEmpty())
        {
            return "Table " + tableName + " is empty.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Data for table - ").append(tableName).append("\n");
        for (List<Object> row : table.getRows())
        {
            sb.append("- - - - - - - - - - - -").append("\n");
            for (int i = 0; i < row.size(); i++)
            {
                sb.append(row.get(i));
                if (i < row.size() - 1)
                {
                    sb.append(" || ");
                }
            }
            sb.append("\n");
        }

        return sb.toString().trim();
    }
}