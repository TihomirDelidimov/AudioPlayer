import enumeration.Command;
import enumeration.Genre;
import models.Singer;
import models.Song;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static enumeration.Command.*;

public class AudioPlayerConsoleIO {
    private static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * This method check the console input then check to see if the command given is valid. If the command is valid the state
     * is changed to the new state
     *
     * @return - this method return true if valid command is given in the console, otherwise return false
     * @throws IOException
     */
    public static boolean checkForInput() throws IOException {
        if (inputReader.ready()) {
            String inputCommand = inputReader.readLine().trim().toUpperCase();
            if (isValidCommand(inputCommand)) {
                Command command = findCommand(inputCommand);
                if (command != INVALID_COMMAND) {
                    AudioPlayerState.setCurrent(command);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method visualize dialog to allow the user to search for songs that match a singer.
     * The method waits for user input, if the input is valid the searching is starting. The method returns appropriate message
     * to the user if the there aren't any records or the input of the user is invalid. The method also return the application to
     * * previous state, which was before the searching.
     */
    public static Singer getSingerFromInput(AudioPlayer audioPlayer) throws IOException {
        if (audioPlayer != null) {
            System.out.print("Enter singer: ");
            String userInput = inputReader.readLine();
            if (userInput != null && !userInput.isEmpty()) {
                return new Singer(userInput);
            }
            System.out.println("Invalid input!\n");
        }
        return null;
    }

    /**
     * This method visualize dialog to allow the user to add new song. The method waits for the user to enter the new song attributes.
     */
    public static Song getNewSong(AudioPlayer audioPlayer) throws IOException {
        if (audioPlayer != null) {
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
        return null;
    }

    /**
     * This method shows the playlist size in the console and returns the application state back to the previous state
     */
    public static void showSize(AudioPlayer audioPlayer) {
        System.out.println("Playlist size: " + audioPlayer.size());
    }
}
