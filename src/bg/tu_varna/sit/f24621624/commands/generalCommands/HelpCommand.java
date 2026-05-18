package bg.tu_varna.sit.f24621624.commands.generalCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.commands.tableCommands.*;

/**
 * Команда, която показва списък с всички команди в програмата.
 */
public class HelpCommand implements Command {

    /**
     * Връща името на командата.
     * @return името на командата като текст
     */
    @Override
    public String getName() {
        return "help";
    }


    /**
     * Описание за самото помощно меню.
     * @return текст с описанието
     */
    @Override
    public String getDescription() {
        return "help (shows all commands)";
    }


    /**
     * Създава списък от всички налични команди, взима описанието
     * на всяка една и ги събира в общ текст.
     * @return списък с описанията на всички команди
     */
    @Override
    public String execute(String[] args) {

        Command[] allCommands = {
                new OpenCommand(),
                new CloseCommand(),
                new SaveCommand(),
                new SaveAsCommand(),
                new HelpCommand(),
                new ExitCommand(),
                new ImportCommand(),
                new ShowTablesCommand(),
                new DescribeCommand(),
                new PrintCommand(),
                new InsertCommand(),
                new DescribeCommand(),
                new UpdateCommand(),
                new SelectCommand(),
                new AddColumnCommand(),
                new RenameCommand(),
                new CountCommand(),
                new InnerJoinCommand(),
                new ExportCommand()
        };

        StringBuilder sb = new StringBuilder();
        sb.append("Supported commands:\n");

        // Обикаляме командите и им взимаме описанията
        for (Command cmd : allCommands) {
            sb.append("-> ").append(cmd.getDescription()).append("\n");
        }

        return sb.toString().trim();
    }
}
