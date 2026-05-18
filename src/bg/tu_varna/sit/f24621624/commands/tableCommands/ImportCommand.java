package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.files.FileService;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Команда за импортиране на таблица от текстов файл в базата данни.
 */
public class ImportCommand implements Command {

    /**
     * Връща името на командата.
     * @return името на командата като текст
     */
    @Override
    public String getName() {
        return "import";
    }


    /**
     * Описание за помощното меню.
     * @return текст с описанието
     */
    @Override
    public String getDescription() {
        return "import <file_name> (imports a table from a file)";
    }


    /**
     * Чете данни от файл, прави проверка за повтарящо се име на таблица
     * и я записва в каталога на базата данни.
     * @return съобщение за успешен импорт или грешка
     */
    @Override
    public String execute(String[] args) {
        if (args.length < 1) {
            return "Error: Usage import <file_name>";
        }

        String fileName = args[0];
        List<String> lines = FileService.getInstance().readFromFile(fileName);

        if (lines.isEmpty()) {
            return "Error: Could not read file or file is empty.";
        }

        try {

            // Взимаме името на таблицата от името на файла (махаме разширението .txt)
            String tableName = fileName.split("\\.")[0];

            // Първият ред съдържа типовете на колоните, разделени с интервал
            List<String> types = Arrays.asList(lines.get(0).split(" "));

            Table table = new Table(tableName, types);

            // Обикаляме останалите редове, за да вземем самите данни
            for (int i = 1; i < lines.size(); i++) {
                String[] rowData = lines.get(i).split(" ");
                List<Object> row = new ArrayList<>(Arrays.asList(rowData));
                table.addRow(row);
            }
            // Добавяме готовата таблица в базата данни
            Database.getInstance().addTable(table);
            FileService.getInstance().setCurrentFilePath(fileName);
            return "Table '" + tableName + "' imported successfully with " + table.getRows().size() + " rows.";

        } catch (Exception e) {
            return "Error: Invalid file format.";
        }
    }
}
