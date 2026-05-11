package bg.tu_varna.sit.f24621624;

import bg.tu_varna.sit.f24621624.commands.interfaces.Command;
import bg.tu_varna.sit.f24621624.commands.fileCommands.OpenCommand;
import bg.tu_varna.sit.f24621624.files.FileService;
import java.util.*;

public class CommandExecutor
{
    private final Map<String, Command> commands = new HashMap<>();
    private boolean isRunning = true;

    public CommandExecutor()
    {
        register(new OpenCommand());
    }

    private void register(Command cmd)
    {
        commands.put(cmd.getName(), cmd);
    }

    public void start()
    {
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            System.out.print(">");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                isRunning = false;
                continue;
            }

            if (input.isEmpty()) continue;

            String[] tokens = input.split(" ");
            String commandName = tokens[0].toLowerCase();
            String[] args = new String[tokens.length - 1];

            for (int i = 1; i < tokens.length; i++)
            {
                args[i - 1] = tokens[i];
            }

            Command cmd = commands.get(commandName);

            if (cmd != null)
            {
                if (!commandName.equals("open") && !FileService.getInstance().hasOpenedFile()) {
                    System.out.println("Error: First open file with 'open' command");
                } else {

                    System.out.println(cmd.execute(args));
                }
            } else {
                System.out.println("Error: unrecognized command");
            }
        }

        System.out.println("Exitting the program.");
    }
}
