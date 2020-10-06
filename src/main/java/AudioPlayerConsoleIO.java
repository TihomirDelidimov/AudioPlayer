import enumeration.Command;
import enumeration.Genre;
import models.Singer;
import models.Song;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static enumeration.Command.*;

/**
 * This class supplies the audio player with console input/output methods to interact with the user
 */
public class AudioPlayerConsoleIO {
    private AudioPlayerState audioPlayerState;
    private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

    public AudioPlayerConsoleIO(AudioPlayerState audioPlayerState) {
        this.audioPlayerState = audioPlayerState;
    }

    /**
     * This method check the console input then check to see if the command given is valid. If the command is valid the state
     * in {@link AudioPlayerState} class is changed and the method returns true, otherwise the state is not changed and the
     * method returns false.
     *
     * @return - this method return true if valid command is given in the console, otherwise return false
     * @throws IOException
     */
    public boolean checkForInput() throws IOException {
        if (inputReader.ready()) {
            String inputCommand = inputReader.readLine().trim().toUpperCase();
            if (isValidCommand(inputCommand)) {
                Command command = findCommand(inputCommand);
                if (command != INVALID_COMMAND) {
                    audioPlayerState.setCurrent(command);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method visualize dialog to allow the user to search for songs that match a singer.
     * The method waits for user input, if the input is valid the searching is starting. The method returns
     * appropriate message to the user if the input of the user is invalid.
     * The method also return the application to previous state, which was before the searching.
     *
     * @return - this method returns new Singer object, which is used by the AudioPlayer for the searching.
     * @throws IOException
     */
    public Singer getSingerFromInput() throws IOException {
        System.out.print("Enter singer: ");
        String userInput = inputReader.readLine();
        if (userInput != null && !userInput.isEmpty()) {
            return new Singer(userInput);
        }
        System.out.println("Invalid input!\n");
        return null;
    }

    /**
     * This method visualize dialog to allow the user to search for a singer and song's position.
     * The method waits for the user input, if the input is valid searching is starting. The method returns
     * appropriate message to the user if the input of the user is invalid.
     * The method also return the application to previous state, which was before the searching.
     *
     * @return - this method returns Song's title, which is used by the AudioPlayer for the searching.
     * @throws IOException
     */
    public String getTitleFromInput() throws IOException {
        System.out.print("Enter title: ");
        String userInput = inputReader.readLine();
        if (userInput != null && !userInput.isEmpty()) {
            return userInput;
        }
        System.out.println("Invalid input! \n");
        return null;
    }


    /**
     * This method is used to show the output of the audio player, which could be a result of search.
     *
     * @param outputResult - this parameter is the output from the audio player.
     */
    public void showAudioPlayerOutput(String outputResult) {
        System.out.println(outputResult);
    }

    /**
     * This method visualize dialog to allow the user to add new song. The method waits for the user to enter the new song attributes.
     */
    public Song getNewSong() throws IOException {
        System.out.print("Enter song's title: ");
        String songTitle = inputReader.readLine();
        System.out.print("Enter song's singer: ");
        String songSinger = inputReader.readLine();
        System.out.print("Enter song's genre: ");
        String songGenre = inputReader.readLine();
        System.out.print("Enter song's timing(in seconds): ");
        int songTiming = Integer.parseInt(inputReader.readLine());
        Singer singer = new Singer(songSinger);
        return new Song(songTitle, singer, Genre.valueOf(songGenre), songTiming);
    }

    /**
     * This method shows the playlist size in the console and returns the application state back to the previous state
     */
    public void showSize(AudioPlayer audioPlayer) {
        System.out.println("Playlist size: " + audioPlayer.size());
    }
}
