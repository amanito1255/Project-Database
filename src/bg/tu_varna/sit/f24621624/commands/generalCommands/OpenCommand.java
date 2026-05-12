package bg.tu_varna.sit.f24621624.commands.generalCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.files.FileService;
import java.io.File;

public class OpenCommand implements Command
{
    @Override
    public String getName()

    {
        return "open";
    }

    @Override
    public String getDescription()

    {
        return "open <file_path>  (open file with data)";
    }

    @Override
    public String execute(String[] args)
    {
        if (args.length < 1) {
            return "Error: Make sure you entered the file path";
        }

        String path = args[0];
        File file = new File(path);

        FileService.getInstance().setCurrentFilePath(path);

        if (!file.exists()) {
            return "File: " + path + " not exist, but it it's selected to be used";
        }
        return "Successfully opened file: " + path;
    }
}