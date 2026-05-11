package bg.tu_varna.sit.f24621624.commands.generalCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;

public class ExitCommand implements Command {
    @Override
    public String getName() { return "exit"; }

    @Override
    public String getDescription() { return "exit  (closes the program)"; }

    @Override
    public String execute(String[] args) {
        return "Exiting the program...";
    }
}
