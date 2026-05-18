package bg.tu_varna.sit.f24621624.commands.generalCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.files.FileService;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Команда за записване на промените в нов файл (Save As).
 */
public class SaveAsCommand implements Command {

    /**
     * Връща името на командата.
     * * @return името на командата като текст
     */
    @Override
    public String getName() {
        return "saveas";
    }

    /**
     * Описание за помощното меню.
     * * @return текст с описанието
     */
    @Override
    public String getDescription() {
        return "saveas <file_path> (saves the data to a new file)";
    }

    /**
     * Взима новия път от потребителя, обръща текущата таблица
     * в текст с интервали и я записва на новото място.
     * * @return съобщение за успешен запис или грешка
     */
    @Override
    public String execute(String[] args) {
        if (args.length < 1) {
            return "Error: Please specify new file path. Usage: saveas <file_path> (saves the data to a new file)";
        }

        String newPath = args[0];
        Map<String, Table> tables = Database.getInstance().getTables();

        if (tables.isEmpty())
        {
            return "Error: No tables loaded to save.";
        }

        // Взимаме таблицата от паметта
        Table table = tables.values().iterator().next();
        List<String> linesToSave = new ArrayList<>();
        // Слагаме първия ред с типовете
        linesToSave.add(String.join(" ", table.getColumnTypes()));


        // Правим редовете на стрингове, разделени с интервал
        for (List<Object> row : table.getRows())
        {
            StringBuilder rowStr = new StringBuilder();
            for (int i = 0; i < row.size(); i++) {
                rowStr.append(row.get(i)).append(" ");
            }
            linesToSave.add(rowStr.toString().trim());
        }

        // Записваме в новия файл
        FileService.getInstance().writeToFile(newPath, linesToSave);
        return "Successfully saved data as " + newPath;
    }
}
