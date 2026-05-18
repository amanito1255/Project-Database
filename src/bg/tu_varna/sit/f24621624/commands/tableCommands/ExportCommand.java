package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.files.FileService;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Команда за експортиране на конкретна таблица от базата данни в нов файл.
 */
public class ExportCommand implements Command {
    /**
     * Връща името на командата.
     * @return името на командата като текст
     */
    @Override
    public String getName()
    {
        return "export";
    }

    /**
     * Описание за помощното меню.
     * @return текст с описанието
     */
    @Override
    public String getDescription()
    {
        return "export <table_name> <file_name> (exports a single table to a file)";
    }

    /**
     * Търси таблицата по име, подготвя заглавния ред с типовете
     * и записва редовете с интервали в посочения файл.
     * @return съобщение за успешен експорт или грешка
     */
    @Override
    public String execute(String[] args)
    {
        if (args.length < 2) {
            return "Error: Usage export <table_name> <file_name>";
        }

        String tableName = args[0];
        String fileName = args[1];

        // Търсим таблицата в базата данни
        Table table = Database.getInstance().getTable(tableName);
        if (table == null) {
            return "Error: Table '" + tableName + "' not found.";
        }
        List<String> linesToSave = new ArrayList<>();

        // Добавяме първия ред с типовете на колоните
        linesToSave.add(String.join(" ", table.getColumnTypes()));


        // Обикаляме редовете и ги правим на стрингове, разделени с интервали
        for (List<Object> row : table.getRows()) {
            String rowText = row.toString().replace("[", "").replace("]", "");
            linesToSave.add(rowText);
        }

        // Записваме данните в новия файл чрез FileService
        FileService.getInstance().writeToFile(fileName, linesToSave);

        return "Table '" + tableName + "' successfully exported to '" + fileName + "'.";
    }
}
