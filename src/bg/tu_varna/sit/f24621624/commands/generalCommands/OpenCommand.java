package bg.tu_varna.sit.f24621624.commands.generalCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.files.FileService;
import java.io.File;

/**
 *Команда за отваряне на файл
 */
public class OpenCommand implements Command
{

    /**
     * Връща името на командата
     * @return Връща името на командата като string
     */
    @Override
    public String getName()

    {
        return "open";
    }

    /**
     * Връща описанието на командата (използва се при help)
     * @return Връща описанието на командата като string
     */
    @Override
    public String getDescription()

    {
        return "open <file_path>  (open file with data)";
    }

    /**
     * Логикатра на командата при отваряне на файл
     * Проверява за път към фала, задава пътя във FileService
     * @return връща информация за резултата на процеса
     */
    @Override
    public String execute(String[] args)
    {
        if (args.length < 1) {
            return "Error: Make sure you entered the file path";
        }

        String path = args[0];
        File file = new File(path);

        //Задава текущият път към файла във FileService, след което функционалността
        //на останалите команди е отключена
        FileService.getInstance().setCurrentFilePath(path);

        //Проверка за създаването на файла. Понеже датабаза реална няма, а работим само в паммета
        //след команда open винаги ще функционират останалите команди
        if (!file.exists()) {
            return "Successfully opened " + path;
        }
        return "Successfully opened " + path;
    }
}