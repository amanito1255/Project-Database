package bg.tu_varna.sit.f24621624.commands.generalCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;


/**
 * Команда за спиране и излизане от програмата.
 */
public class ExitCommand implements Command {

    /**
     * Връща името на командата.
     * @return името на командата като текст
     */
    @Override
    public String getName()
    {
        return "exit";
    }


    /**
     * Описание за помощното меню при help.
     * @return текст с описанието
     */
    @Override
    public String getDescription()
    {
        return "exit  (closes the program)";
    }


    /**
     * Спира изпълнението на конзолното приложение.
     * @return съобщение, че програмата се затваря
     */
    @Override
    public String execute(String[] args)
    {
        System.exit(0);
        return "Exiting the program...";
    }
}
