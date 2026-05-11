package bg.tu_varna.sit.f24621624.commands.interfaces;

public interface Command
{
    String getName();
    String getDescription();
    String execute(String[] args);
}
