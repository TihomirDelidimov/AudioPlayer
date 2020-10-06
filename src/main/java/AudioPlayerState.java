import enumeration.Command;

public final class AudioPlayerState {
    private static Command previousCommand;
    private static Command currentCommand;

    /**
     * This method is used to set the current state of the application. When new state is set, the current is saved as previous
     * @param command - this parameter is the command to be saved as current
     */
    public static void setCurrent(Command command) {
        previousCommand = currentCommand;
        currentCommand = command;
    }

    /**
     * This method is used to change the current state of the audio player to the previous state
     */
    public static void changeCurrentToPrevious() {
        currentCommand = previousCommand;
    }

    /**
     * This method changes current state (command) to the previous state (command)
     */
    public static Command getCurrent() {
        return currentCommand;
    }

}
