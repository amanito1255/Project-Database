package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.files.FileService;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImportCommand implements Command
{
    @Override
    public String getName()
    {
        return "import";
    }

    @Override
    public String getDescription()
    {
        return "import <file_name> (imports a table from a file)";
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 1)
        {
            return "Error: Usage import <file_name>";
        }

        String fileName = args[0];
        List<String> lines = FileService.getInstance().readFromFile(fileName);

        if (lines.isEmpty())
        {

            return "Error: Could not read file or file is empty.";
        }

        try
        {
            String tableName = fileName.split("\\.")[0];


            List<String> types = Arrays.asList(lines.get(1).split(" "));

            Table table = new Table(tableName, types);


            for (int i = 2; i < lines.size(); i++) {
                String[] rowData = lines.get(i).split(" ");
                List<Object> row = new ArrayList<>(Arrays.asList(rowData));
                table.addRow(row);
            }

            Database.getInstance().addTable(table);
            return "Table '" + tableName + "' imported successfully with " + table.getRows().size() + " rows.";

        }
        catch (Exception e)
        {
            return "Error: Invalid file format.";
        }
    }
}
