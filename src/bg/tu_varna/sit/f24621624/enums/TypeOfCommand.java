package bg.tu_varna.sit.f24621624.enums;

public enum TypeOfCommand {
    OPEN("open"),
    CLOSE("close"),
    SAVE("save"),
    SAVE_AS("saveas"),
    HELP("help"),
    EXIT("exit"),
    IMPORT("import"),
    SHOWTABLES("showtables"),
    DESCRIBE("describe"),
    PRINT("print"),
    INSERT("insert"),
    DELETE("delete"),
    UPDATE("update"),
    SELECT("select"),
    ADDCOLUMN("addcolumn"),
    RENAME("rename"),
    COUNT("count"),
    INNERJOIN("innerjoin"),
    EXPORT("export"),
    AGGREGATE("aggregate");

    private final String commandName;
    TypeOfCommand(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public String toString() {
        return this.commandName;
    }
}
