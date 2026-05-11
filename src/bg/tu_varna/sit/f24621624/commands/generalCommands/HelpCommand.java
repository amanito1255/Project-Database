package bg.tu_varna.sit.f24621624.commands.generalCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;

public class HelpCommand implements Command {
    @Override
    public String getName()
    {
        return "help";
    }

    @Override
    public String getDescription()
    {
        return "help (shows all supported commands)";
    }

    @Override
    public String execute(String[] args) {
        return "Supported commands:\n" +
                "open <file> (opens a file)\n" +
                "close (closes current file)\n" +
                "exit (exits the program)\n" +
                "help (shows this help)";
    }
}
