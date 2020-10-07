package enumeration;

/**
 * This enumeration represents a set of commands, which are used by the application.
 */
public enum Command {
    PLAY("play"),
    REPLAY("replay"),
    NEXT("next"),
    PREVIOUS("previous"),
    PAUSE("pause"),
    SHUFFLE("shuffle"),
    STOP("stop"),
    EXIT("exit"),
    SIZE("size"),
    ADD("add"),
    SEARCH_SINGER_BY_TITLE("sbt"),
    SEARCH_SONGS_BY_SINGER("sbs"),
    INVALID_COMMAND("unknown");

    private String commandName;

    Command(String name) {
        commandName = name;
    }

    /**
     * This method check if the given command in string format is in valid set of commands
     *
     * @param stringCommand - this parameter is the command to check, which is in string format
     * @return this method return true if the command in string format is in the set of valid commands
     */
    public static boolean isValidCommand(String stringCommand) {
        if (stringCommand != null && !stringCommand.isEmpty()) {
            for (Command command : Command.values()) {
                if (stringCommand.equalsIgnoreCase(command.name())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method return the string format command in Commands constant format
     *
     * @param stringCommand - this parameter is the command in string format, which is evaluated to Commands constant
     * @return - this method return Commands constant
     */
    public static Command findCommand(String stringCommand) {
        if (stringCommand != null && !stringCommand.isEmpty()) {
            return Command.valueOf(stringCommand);
        }
        return null;
    }
}