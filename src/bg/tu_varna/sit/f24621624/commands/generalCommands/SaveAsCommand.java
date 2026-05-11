package bg.tu_varna.sit.f24621624.commands.generalCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;

public class SaveAsCommand implements Command {
    @Override
    public String getName() { return "saveas"; }

    @Override
    public String getDescription() { return "saveas <file_path> (saves the data to a new file)"; }

    @Override
    public String execute(String[] args) {
        if (args.length < 1) {
            return "Error: Please specify a new file path.";
        }

        String newPath = args[0];
        // Тук по-късно ще реализираме реалния запис
        return "Successfully saved data as " + newPath;
    }
}
