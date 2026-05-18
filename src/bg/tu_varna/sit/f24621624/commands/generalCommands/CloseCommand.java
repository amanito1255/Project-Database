package bg.tu_varna.sit.f24621624.commands.generalCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.files.FileService;
import bg.tu_varna.sit.f24621624.models.Database;

/**
 *Команда за затваряне на отворен фаийл
 * Изчиства заредените таблици в паметта на DB, нулира текущият път към файла
 * Блокира изпълнението на останалите команди
 */
public class CloseCommand implements Command
{
    /**
     * Връща името на командата
     * @return Връща името на командата като string
     */
    @Override
    public String getName()
    {
        return "close";
    }

    /**
     * Връща описанието на командата (използва се при help)
     * @return Връща описанието на командата като string
     */
    @Override
    public String getDescription()
    {
        return "close (closes the current opened file)";
    }

    /**
    *Логика за затварянето на файла
     * Ако има активен файл премахва пътя мъ от FileService
     * и изчиства всички съхранени обекти
    */
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
