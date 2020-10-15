package enumeration;

/**
 * This enumeration represents a set of commands, which are used by the application.
 */
public enum Command {
    PLAY,
    REPLAY,
    NEXT,
    PREVIOUS,
    PAUSE,
    SHUFFLE,
    STOP,
    EXIT,
    SIZE,
    ADD,
    SEARCH_SINGER_BY_TITLE,
    SEARCH_SONGS_BY_SINGER,
    INVALID_COMMAND;

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