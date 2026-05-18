package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;
import java.util.ArrayList;
import java.util.List;
/**
 * Команда за вмъкване на нов ред със съответните стойности в избрана таблица.
 */
public class InsertCommand implements Command {
    /**
     * Връща името на командата.
     * @return името на командата като текст
     */
    @Override
    public String getName()
    {
        return "insert";
    }
    /**
     * Описание на командата за помощното меню.
     * @return текст с описанието
     */
    @Override
    public String getDescription()
    {
        return "insert <table_name> <val1> ++ (inserts a row)";
    }
    /**
     * Проверява дали таблицата съществува, дали броят на подадените стойности
     * съвпада с броя на колоните и добавя новия ред с данни.
     * @return съобщение за успешно вмъкване или съобщение за грешка
     */
    @Override
    public String execute(String[] args)
    {
        if (args.length < 2)
        {
            return "Error: Usage insert <table_name> <val1> ++ (inserts a row)";
        }

        String tableName = args[0];
        Table table = Database.getInstance().getTable(tableName);

        if (table == null)
        {
            return "Error: Table " + tableName + "was not found.";
        }
        // Проверяваме дали подадените аргументи съответстват на броя колони в таблицата
        if (args.length - 1 != table.getColumnTypes().size())
        {
            return "Error: Invalid number of values. Expected " + table.getColumnTypes().size() + " values.";
        }

        List<Object> newRow = new ArrayList<>();
        // Обхождаме аргументите от индекс 1 нататък и ги добавяме в новия ред
        for (int i = 1; i < args.length; i++)
        {
            newRow.add(args[i]);
        }

        // Добавяме готовия ред към таблицата в паметта
        table.addRow(newRow);
        return "Successfully inserted 1 row into " + tableName;
    }
}
