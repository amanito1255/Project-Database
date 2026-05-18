package bg.tu_varna.sit.f24621624.commands.generalCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.files.FileService;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Команда за записване на промените в текущия файл.
 */
public class SaveCommand implements Command
{
    /**
     * Връща името на командата.
     */
    @Override
    public String getName()
    {
        return "save";
    }

    /**
     *@return връша оописание за командата save.
     */
    @Override
    public String getDescription()
    {
        return "save (saves the changes to the current file)";
    }

    /**
     * Взима текущата таблица от паметта, прави редовете на текст
     * с интервали и я записва във файла чрез FileService.
     */
    @Override
    public String execute(String[] args)
    {
        String path = FileService.getInstance().getCurrentFilePath();
        if (path == null) {
            return "Error: No file is open to save.";
        }

        Map<String, Table> tables = Database.getInstance().getTables();

        if (tables.isEmpty())
        {
            return "Error: No tables loaded to save.";
        }

        // Взимаме първата таблица от базата данни
        Table table = tables.values().iterator().next();
        List<String> linesToSave = new ArrayList<>();

        // Първо записваме реда с типовете на колоните
        linesToSave.add(String.join(" ", table.getColumnTypes()));

        // Обикаляме редовете и ги правим на стрингове с интервал между клетките
        for (List<Object> row : table.getRows())
        {
            StringBuilder rowString = new StringBuilder();
            for (int i = 0; i < row.size(); i++)
            {
                rowString.append(row.get(i)).append(" ");
            }
            linesToSave.add(rowString.toString().trim());
        }

        // Извикваме писането във файл
        FileService.getInstance().writeToFile(path, linesToSave);
        return "Successfully saved changes to " + path;
    }
}