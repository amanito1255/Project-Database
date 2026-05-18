package bg.tu_varna.sit.f24621624.commands.tableCommands;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.models.Database;
import bg.tu_varna.sit.f24621624.models.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Команда за извършване на Inner Join операция между две таблици по съвпадащи стойности в колоните.
 */
public class InnerJoinCommand implements Command {
    /**
     * Връща името на командата.
     * @return името на командата като текст
     */
    @Override
    public String getName() {
        return "innerjoin";
    }

    /**
     * Описание на командата за помощното меню.
     * @return текст с описанието
     */
    @Override
    public String getDescription()
    {
        return "innerjoin <table1> <column_n1> <table2> <column_n2> (joins two tables based on matching column values)";
    }

    /**
     * Извършва Inner Join над две таблици спрямо подадените номера на колони,
     * съчетава техните редове при съвпадение и регистрира нова таблица в базата.
     * @return съобщение с името на новосъздадената таблица или съобщение за грешка
     */
    @Override
    public String execute(String[] args) {
        if (args.length < 4) {
            return "Error: Usage innerjoin <table1> <column_n1> <table2> <column_n2>";
        }

        String name1 = args[0];
        int col1 = Integer.parseInt(args[1]) - 1;
        String name2 = args[2];
        int col2 = Integer.parseInt(args[3]) - 1;

        Database db = Database.getInstance();
        Table t1 = db.getTable(name1);
        Table t2 = db.getTable(name2);

        if (t1 == null || t2 == null) {
            return "Error: One or both tables not found.";
        }

        // Обединяваме типовете на колоните от двете таблици за новата структура
        List<String> joinedTypes = new ArrayList<>();
        joinedTypes.addAll(t1.getColumnTypes());
        joinedTypes.addAll(t2.getColumnTypes());

        String joinedTableName = "join_" + name1 + "_" + name2;
        Table joinedTable = new Table(joinedTableName, joinedTypes);

        // Вложени цикли за сравняване на стойностите ред по ред от двете таблици
        for (List<Object> row1 : t1.getRows()) {
            String val1 = String.valueOf(row1.get(col1));

            for (List<Object> row2 : t2.getRows()) {
                String val2 = String.valueOf(row2.get(col2));

                // При съвпадение на стойностите, създаваме и пълним нов обединен ред
                if (val1.equals(val2)) {

                    List<Object> joinedRow = new ArrayList<>();
                    joinedRow.addAll(row1);
                    joinedRow.addAll(row2);

                    joinedTable.addRow(joinedRow);
                }
            }
        }

        // Записваме резултатната таблица обратно в каталога на базата от данни
        db.getTables().put(joinedTableName, joinedTable);

        return "Inner join completed successfully! Created new table: '" + joinedTableName + "'";
    }
}
