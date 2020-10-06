import enumeration.Command;

/**
 * This class represents the state of the audio player. It's purpose is to save the current and the previous state of the audio
 * player
 */
public class AudioPlayerState {
    private static Command previousCommand;
    private static Command currentCommand;

    /**
     * This method is used to set the current state of the application. When new state is set, the current is saved as previous
     *
     * @param command - this parameter is the command to be saved as current
     */
    public void setCurrent(Command command) {
        previousCommand = currentCommand;
        currentCommand = command;
    }

    /**
     * This method is used to change the current state of the audio player to the previous state
     */
    public void changeCurrentToPrevious() {
        currentCommand = previousCommand;
    }

    /**
     * This method changes current state (command) to the previous state (command)
     */
    public Command getCurrent() {
        return currentCommand;
    }
}
