package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.models.Database;
import java.util.Set;

public class ShowTablesCommand implements Command {
    @Override
    public String getName()
    {
        return "showtables";
    }

    @Override
    public String getDescription()
    {
        return "showtables (lists all loaded tables)";
    }

    @Override
    public String execute(String[] args) {
        Set<String> tableNames = Database.getInstance().getTables().keySet();

        if (tableNames.isEmpty()) {
            return "No tables currently loaded in the database.";
        }

        StringBuilder sb = new StringBuilder("Loaded tables:\n");
        for (String name : tableNames) {
            sb.append("-> ").append(name).append("\n");
        }
        return sb.toString().trim();
    }
}
