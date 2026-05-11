package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;
import java.util.List;

public class DescribeCommand implements Command {
    @Override
    public String getName()
    {
        return "describe";
    }

    @Override
    public String getDescription()
    {
        return "describe <name> (shows column types of a table)";
    }

    @Override
    public String execute(String[] args)
    {
        if (args.length < 1)
        {
            return "Error: usage describe <table_name>";
        }

        String tableName = args[0];
        Table table = Database.getInstance().getTable(tableName);

        if (table == null)
        {
            return "Error: Table '" + tableName + "' not found.";
        }

        List<String> types = table.getColumnTypes();
        return "Table '" + tableName + "' column types: " + String.join(", ", types);
    }
}
