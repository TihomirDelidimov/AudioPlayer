import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import enumeration.Commands;

import static enumeration.Commands.*;

import enumeration.Genre;

import static enumeration.Genre.*;

import models.Song;
import models.Singer;

/**
 * The Application class is the main class in the application.
 */
public class Applicaiton {
    private AudioPlayer audioPlayer = new AudioPlayer(this);
    private Commands currentCommand = PLAY;
    private Commands previousCommand;
    private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws InterruptedException, IOException {

        Applicaiton app = new Applicaiton();
        List<Song> songList = new ArrayList<>();
        songList.add(new Song("High way to hell", new Singer("John"), ROCK, 100));
        songList.add(new Song("Thunderstruck", new Singer("Alex"), ROCK, 100));
        songList.add(new Song("High voltage", new Singer("Silvia"), ROCK, 100));
        app.audioPlayer.addListOfSongs(songList);

        System.out.println("Type <start> to start playing ");
        Scanner userInput = new Scanner(System.in);
        String command = userInput.nextLine().toLowerCase().trim();
        if (command.equals("start")) {
            app.run();
        }
    }

    /**
     * This method is used to check if the application state is changed. The state is changed when a new valid command is given
     *
     * @return
     */
    public boolean isStateChanged() throws IOException {
        return checkForInput();
    }

    /**
     * This method check the console input then check to see if the command given is valid. If the command is valid the state
     * is changed to the new state
     *
     * @return - this method return true if valid command is given in the console, otherwise return false
     * @throws IOException
     */
    private boolean checkForInput() throws IOException {
        String inputCommand;
        if (inputReader.ready()) {
            inputCommand = inputReader.readLine().trim().toUpperCase();
            if (isValidStringCommand(inputCommand)) {
                saveCurrentCommand();
                currentCommand = getCommandFromString(inputCommand);
                return true;
            }
        }
        return false;
    }

    /**
     * This method saves the current state (command)
     */
    private void saveCurrentCommand() {
        previousCommand = currentCommand;
    }

    /**
     * This method changes current state (command) to the previous state (command)
     */
    private void changeCommandToPrev() {
        currentCommand = previousCommand;
    }

    /**
     * This method is the running method of the application. It's purpose is to switch between commands and execute
     * audio player methods
     *
     * @throws IOException
     */
    public void run() throws IOException, InterruptedException {
        while (currentCommand != EXIT) {
            switch (currentCommand) {
                case PLAY:
                    audioPlayer.playSong();
                    break;
                case NEXT:
                    audioPlayer.nextSong();
                    audioPlayer.playSong();
                    break;
                case PREVIOUS:
                    audioPlayer.prevSong();
                    audioPlayer.playSong();
                    break;
                case REPLAY:
                    audioPlayer.replay();
                    audioPlayer.playSong();
                    break;
                case SHUFFLE:
                    audioPlayer.shuffle();
                    break;
                case PAUSE:
                    System.out.println("On pause..");
                    audioPlayer.pauseSong();
                    break;
                case SIZE:
                    showSize();
                    break;
                case ADD:
                    showAddDialog();
                    break;
                case SEARCH_BY_TITLE:
                    showSearchByTitleDialog();
                    break;
                case SEARCH_BY_SINGER:
                    showSearchBySingerDialog();
                    break;
            }
        }
    }

    /**
     * This method visualize dialog to allow the user to search for songs that match a singer.
     * The method waits for user input, if the input is valid the searching is starting. The method returns appropriate message
     * to the user if the there aren't any records or the input of the user is invalid. The method also return the application to
     * * previous state, which was before the searching.
     */
    private void showSearchBySingerDialog() {
        String result, userInput;
        Scanner consoleInput = new Scanner(System.in);

        System.out.print("Enter singer: ");
        userInput = consoleInput.nextLine();
        if (userInput != null && !userInput.isEmpty()) {
            result = audioPlayer.searchBySinger(new Singer(userInput));
            if (result != null) {
                System.out.println(result);
            } else {
                System.out.println("The singer doesn't have any songs in the playlist!");
            }
        } else {
            System.out.println("Invalid input!\n");
        }
        changeCommandToPrev();
    }

    /**
     * This method visualize dialog to allow the user to search for singer and song's position by the title of the song.
     * The method waits for user input, if the input is valid the searching is starting. The method returns appropriate message
     * to the user if the there aren't any records or the input of the user is invalid. The method also return the application to
     * previous state, which was before the searching.
     */
    private void showSearchByTitleDialog() {
        String result, userInput;
        Scanner consoleInput = new Scanner(System.in);

        System.out.print("Enter song's title: ");
        userInput = consoleInput.nextLine();
        if (userInput != null && !userInput.isEmpty()) {
            result = audioPlayer.searchByTitle(userInput);
            if (result != null) {
                System.out.println(result);
            } else {
                System.out.println("The title didn't matched a record!");
            }
        } else {
            System.out.println("Invalid input!\n");
        }
        changeCommandToPrev();
    }

    /**
     * This method shows the playlist size in the console and returns the application state back to the previous state
     */
    private void showSize() {
        System.out.println("Playlist size: " + audioPlayer.size());
        changeCommandToPrev();
    }

    /**
     * This method visualize dialog to allow the user to add new song. The method waits for the user to enter the new song attributes.
     */
    private void showAddDialog() {
        Scanner consoleInput = new Scanner(System.in);
        System.out.print("Enter song's title: ");
        String songTitle = consoleInput.nextLine();
        System.out.print("Enter song's singer: ");
        String songSinger = consoleInput.nextLine();
        System.out.print("Enter song's genre: ");
        String songGenre = consoleInput.nextLine();
        System.out.print("Enter song's timing(in seconds): ");
        int songTiming = Integer.parseInt(consoleInput.nextLine());

        Singer singer = new Singer(songSinger);
        Song song = new Song(songTitle, singer, Genre.valueOf(songGenre), songTiming);
        if (song != null) {
            audioPlayer.add(song);
        }
        changeCommandToPrev();
    }
}