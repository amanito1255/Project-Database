package bg.tu_varna.sit.f24621624.commands.generalCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.files.FileService;
import bg.tu_varna.sit.f24621624.models.Database;

public class CloseCommand implements Command {
    @Override
    public String getName()
    {
        return "close";
    }

    @Override
    public String getDescription()
    {
        return "close (closes the current opened file)";
    }

    @Override
    public String execute(String[] args) {
        String current = FileService.getInstance().getCurrentFilePath();
        if (current == null) {
            return "Error: No file is currently open.";
        }

        FileService.getInstance().setCurrentFilePath(null);
        Database.getInstance().clear();
        return "Successfully closed " + current;
    }
}
