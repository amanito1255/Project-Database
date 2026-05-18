package bg.tu_varna.sit.f24621624;

import bg.tu_varna.sit.f24621624.commands.generalCommands.*;
import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.commands.tableCommands.*;
import bg.tu_varna.sit.f24621624.enums.TypeOfCommand;
import bg.tu_varna.sit.f24621624.files.FileService;
import java.util.*;

/**
 * Клас, който управлява жизнения цикъл на програмата, регистрира поддържаните команди
 * и обработва конзолния вход от потребителя.
 */
public class CommandExecutor
{
    //Map, съдържащ съпоставка между текстовото име на командата и нейния изпълним обект
    private final Map<String, Command> commands = new HashMap<>();
    // boolean, контролиращ безкрайния цикъл за четене на команди
    private boolean isRunning = true;

    /**
     * Конструктор, в който се регистрират (зачисляват) всички възможни системни
     * и таблични команди към общия речник.
     */
    public CommandExecutor()
    {
        register(TypeOfCommand.OPEN, new OpenCommand());
        register(TypeOfCommand.CLOSE, new CloseCommand());
        register(TypeOfCommand.SAVE, new SaveCommand());
        register(TypeOfCommand.SAVE_AS, new SaveAsCommand());
        register(TypeOfCommand.HELP, new HelpCommand());
        register(TypeOfCommand.EXIT, new ExitCommand());
        register(TypeOfCommand.IMPORT, new ImportCommand());
        register(TypeOfCommand.SHOWTABLES, new ShowTablesCommand());
        register(TypeOfCommand.DESCRIBE, new DescribeCommand());
        register(TypeOfCommand.PRINT, new PrintCommand());
        register(TypeOfCommand.INSERT, new InsertCommand());
        register(TypeOfCommand.DELETE, new DeleteCommand());
        register(TypeOfCommand.ADDCOLUMN, new AddColumnCommand());
        register(TypeOfCommand.UPDATE, new UpdateCommand());
        register(TypeOfCommand.SELECT, new SelectCommand());
        register(TypeOfCommand.RENAME, new RenameCommand());
        register(TypeOfCommand.COUNT, new CountCommand());
        register(TypeOfCommand.INNERJOIN, new InnerJoinCommand());
        register(TypeOfCommand.EXPORT, new ExportCommand());
        register(TypeOfCommand.AGGREGATE, new AggregateCommand());
    }

    /**
     * Помощен метод за въвеждане на двойка име-команда в речника.
     * @param type типът на командата от изброяването
     * @param cmd конкретната имплементация на интерфейса Command
     */
    private void register(TypeOfCommand type, Command cmd)
    {
        commands.put(type.toString(), cmd);
    }

    /**
     * Стартира основния конзолен цикъл на програмата, разделя входа на име
     * и аргументи и изпълнява съответната логика.
     */
    public void start()
    {
        Scanner scanner = new Scanner(System.in);

        // Основен цикъл на приложението
        while (isRunning) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            // Разделяме входа по интервал, за да намерим името на командата и аргументите ѝ
            String[] tokens = input.split(" ");
            String commandName = tokens[0].toLowerCase();

            // Извличаме аргументите (всичко след първата дума)
            String[] args = new String[tokens.length - 1];
            for (int i = 1; i < tokens.length; i++) {
                args[i - 1] = tokens[i];
            }

            // Търсим дали имаме регистрирана такава команда
            Command cmd = commands.get(commandName);


            if (cmd != null)
            {
                // Защитна проверка: не позволяваме изпълнение на таблични команди без зареден файл
                if (!commandName.equals("open") &&
                        !commandName.equals("exit") &&
                        !commandName.equals("help") &&
                        !commandName.equals("import") &&
                        !FileService.getInstance().hasOpenedFile()) {
                    System.out.println("Error: Please open a file first!");
                }
                else
                {
                    // Изпълняваме самата команда и извеждаме върнатия от нея резултат
                    String result = cmd.execute(args);
                    System.out.println(result);


                    // При команда exit прекратяваме въртенето на конзолния цикъл
                    if (commandName.equals("exit")) {
                        isRunning = false;
                    }
                }
            } else {
                System.out.println("Error: unrecognized command");
            }
        }
        System.out.println("Program closed.");
    }
}