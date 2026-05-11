package commands.interfaces;

public interface Command
{
    String getName();
    String getDescription();
    String execute(String[] args);
}
