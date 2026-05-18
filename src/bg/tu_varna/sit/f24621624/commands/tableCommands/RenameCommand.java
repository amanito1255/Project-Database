package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;
/**
 * Команда за преименуване на съществуваща таблица в базата данни.
 */
public class RenameCommand implements Command {
    /**
     * Връща името на командата.
     * @return името на командата като текст
     */
    @Override
    public String getName() {
        return "rename";
    }

    /**
     * Описание на командата за помощното меню.
     * @return текст с описанието
     */
    @Override
    public String getDescription()
    {
        return "rename <old_name> <new_name> (renames a table)";
    }

    /**
     * Проверява дали старата таблица съществува, дали новото име е уникално
     * и я пререгистрира в списъка на базата данни под новия идентификатор.
     * @return съобщение за успешно преименуване или съобщение за грешка
     */
    @Override
    public String execute(String[] args) {
        if (args.length < 2) {
            return "Error: Usage rename <old_name> <new_name>";
        }

        String oldName = args[0];
        String newName = args[1];

        Database db = Database.getInstance();
        Table table = db.getTable(oldName);

        // Проверяваме за съществуването на таблицата със старото име
        if (table == null)
        {
            return "Error: Table '" + oldName + "' not found.";
        }

        // Проверка дали новото име вече е заето от друга таблица в системата
        if (db.getTable(newName) != null) {
            return "Error: A table with the name '" + newName + "' already exists.";
        }

        // Премахваме стария запис и добавяме таблицата с новия ключ в базата
        db.getTables().remove(oldName);
        db.getTables().put(newName, table);

        return "Table '" + oldName + "' successfully renamed to '" + newName + "'.";
    }
}
