package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.models.Database;
import java.util.Set;


/**
 * Команда, която показва списък с имената на всички заредени таблици.
 */
public class ShowTablesCommand implements Command {

    /**
     * Връща името на командата.
     * @return името на командата като текст
     */
    @Override
    public String getName()
    {
        return "showtables";
    }


    /**
     * Описание на командата за помощното меню.
     * @return текст с описанието
     */
    @Override
    public String getDescription()
    {
        return "showtables (lists all loaded tables)";
    }


    /**
     * Взима имената на всички таблици от базата данни и ги принтира списъчно.
     * @return текст със списъка от таблици или съобщение, че няма заредени
     */
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
