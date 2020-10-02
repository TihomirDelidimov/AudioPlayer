package enumeration;

public enum Commands {
    PLAY("play"),
    REPLAY("replay"),
    NEXT("next"),
    PREVIOUS("previous"),
    PAUSE("pause"),
    EXIT("exit"),
    SIZE("SIZE"),
    SEARCH_BY_TITLE("sbt"),
    SEARCH_BY_SINGER("sbs");

    private String commandName;

    Commands(String name) {
        commandName = name;
    }

    /**
     * This method check if the given command in string format is in valid set of commands
     *
     * @param stringCommand - this parameter is the command to check, which is in string format
     * @return this method return true if the command in string format is in the set of valid commands
     */
    public static boolean isValidStringCommand(String stringCommand) {
        if (stringCommand != null && !stringCommand.isEmpty()) {
            for (Commands command : Commands.values()) {
                if (stringCommand.equalsIgnoreCase(command.name())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method return the string format command in Commands constant format
     * @param stringCommand - this parameter is the command in string format, which we evaluate to Commands constant
     * @return - this method return Commands constant
     */
    public static Commands getCommandFromString(String stringCommand) {
        if (stringCommand != null && !stringCommand.isEmpty()) {
            return Commands.valueOf(stringCommand);
        }
        return null;
    }
}
