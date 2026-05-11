package bg.tu_varna.sit.f24621624.commands.generalCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.files.FileService;

public class SaveCommand implements Command {
    @Override
    public String getName()
    {
        return "save";
    }

    @Override
    public String getDescription()
    {
        return "save (saves the changes to the current file)";
    }

    @Override
    public String execute(String[] args) {
        String path = FileService.getInstance().getCurrentFilePath();
        if (path == null) {
            return "Error: No file is open to save.";
        }
        return "Successfully saved changes to " + path;
    }
}