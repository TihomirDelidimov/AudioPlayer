
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

import enumeration.Commands;

import static enumeration.Commands.*;

import models.Song;
import models.Singer;

/**
 * This class represent
 */
public class AudioPlayer {
    private List<Song> songs = new ArrayList<>();
    private Commands currentCommand = PLAY;
    private Commands previousCommand;
    private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    private int currentSongIndex;
    private static int FIRST_SONG_INDEX = 0;

    /**
     * This constructor initiates the AudioPlayer object with list of songs. It sets the iterator to the first element.
     *
     * @param songs - this parameter is the list with songs
     */
    public AudioPlayer(List<Song> songs) {
        if (validateSongs(songs)) {
            this.songs.addAll(songs);
        }
    }

    /**
     * This method check if there are any songs in the list
     *
     * @return boolean - the method return true if there is at least one song, if there isn't return false
     */
    private boolean validateSongs(List<Song> songs) {
        return songs != null && songs.size() > 0;
    }

    /**
     * This method is used to get AudioPlayer object's characteristics in string format
     *
     * @return String - this method return AudioPlayer object's characteristics in string format
     */
    @Override
    public String toString() {
        StringBuilder audioPlayerInfo = new StringBuilder();
        audioPlayerInfo.append("\nSongs: ")
                .append(songs);
        return audioPlayerInfo.toString();
    }

    /**
     * This method checks the console input for commands.
     *
     * @return this method check
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
     * @throws IOException
     * @throws InterruptedException
     */
    public void start() throws IOException, InterruptedException {
        while (currentCommand != EXIT) {
            switch (currentCommand) {
                case PLAY:
                    playSong();
                    break;
                case NEXT:
                    nextSong();
                    playSong();
                    break;
                case PREVIOUS:
                    prevSong();
                    playSong();
                    break;
                case REPLAY:
                    replay();
                    playSong();
                    break;
                case PAUSE:
                    pauseSong();
                    break;
                case SIZE:
                    size();
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
     * This method is used to play song in a sequence, if the song's execution is not interrupted after tha last song, the playlist
     * will start at the beginning automatically.
     */
    public void playSong() throws InterruptedException, IOException {
        if (validateSongs(songs)) {
            for (int songIndex = currentSongIndex; songIndex < songs.size(); songIndex++, nextSong()) {
                System.out.println("\nCurrently playing :" + getSongInfo());
                for (int timeLeft = songs.get(songIndex).getTiming(); timeLeft > 0; timeLeft--) {
                    Thread.sleep(100);
                    if (checkForInput()) {
                        return;
                    }
                }
            }
            replay();
        }
    }

    /**
     * This method set the index, which points to the current song to the first one and changes the current state to PLAY
     */
    private void replay() {
        currentSongIndex = FIRST_SONG_INDEX;
        currentCommand = PLAY;
    }

    /**
     * This method pauses the song until the "play" command is given in the console
     */
    private void pauseSong() throws IOException {
        System.out.println("On pause..");
        while (currentCommand == PAUSE) {
            checkForInput();
        }
        System.out.println("Resumed");
    }

    /**
     * This method switch to the next song of the audio player if possible
     */
    public void prevSong() {
        if ((currentSongIndex - 1) >= 0) {
            currentSongIndex--;
        }
    }

    /**
     * This method switch to the previous song of the audio player if possible
     */
    public void nextSong() {
        if ((currentSongIndex + 1) < songs.size()) {
            currentSongIndex++;
        }
    }

    /**
     * This method is used to get specific information about the song which is the song's title, song's number in the list
     * and the singer's name in string format
     *
     * @return String - this method return song's title, song's number and singer's name in a string format
     */
    public String getSongInfo() {
        StringBuilder songInfo = new StringBuilder();
        songInfo.append("\nCurrent song: ")
                .append(songs.get(currentSongIndex).getTitle())
                .append("\n\tNumber in the list: ")
                .append(currentSongIndex + 1)
                .append("\n\tSinger: ")
                .append(songs.get(currentSongIndex).getSinger().getName());

        return songInfo.toString();
    }

    /**
     * This method visualize dialog to allow the user to search for singer and song's position by the title of the song.
     * The method waits for user input, if the input is valid the searching is starting. The method returns appropriate message
     * to the user if the there aren't any records or the input of the user is invalid. The method also saves the current state
     * of the player and then returns back to it.
     */
    private void showSearchByTitleDialog() {
        String result, userInput;
        Scanner consoleInput = new Scanner(System.in);

        System.out.print("Enter song's title: ");
        userInput = consoleInput.nextLine();
        if (userInput != null && !userInput.isEmpty()) {
            result = searchByTitle(userInput);
            if (result != null) {
                System.out.print(result);
            } else {
                System.out.println("The title didn't matched a record!");
            }
        } else {
            System.out.println("Invalid input!\n");
        }
        changeCommandToPrev();
    }

    /**
     * This method search the list of songs by the song's title and return the singer's name and song number in the list.
     *
     * @param title - this parameter is the title of the song to be searched by
     * @return String - this method return the singer of the song and it's number in the list.
     */
    public String searchByTitle(String title) {
        if (title != null && !title.isEmpty()) {
            for (Song song : songs) {
                if (song.getTitle().equalsIgnoreCase(title)) {
                    StringBuilder songInfo = new StringBuilder("\nSinger: ");
                    songInfo.append(song.getSinger().getName())
                            .append("\nSong's number: ")
                            .append(songs.indexOf(song) + 1);
                    return songInfo.toString();
                }
            }
        }
        return null;
    }

    /**
     * This method visualize dialog to allow the user to search for songs that match a singer.
     * The method waits for user input, if the input is valid the searching is starting. The method returns appropriate message
     * to the user if the there aren't any records or the input of the user is invalid. he method also saves the current state
     * of the player and then returns back to it.
     */
    private void showSearchBySingerDialog(){
        String result, userInput;
        Scanner consoleInput = new Scanner(System.in);

        System.out.print("Enter singer: ");
        userInput = consoleInput.nextLine();
        if (userInput != null && !userInput.isEmpty()) {
            result = searchBySinger(new Singer(userInput));
            if (result != null) {
                System.out.print(result);
            } else {
                System.out.println("The singer doesn't have any songs in the playlist!");
            }
        } else {
            System.out.println("Invalid input!\n");
        }
        changeCommandToPrev();
    }

    /**
     * This method return list of songs in string format searched by the singer of the song.
     *
     * @param singer - this parameter is the singer of the song, whom we are looking for
     * @return String - this method return list of songs in string format
     */
    public String searchBySinger(Singer singer) {
        StringBuilder songsBySinger = new StringBuilder();
        songsBySinger.append("Songs by: ")
                .append(singer.getName());

        for (Song song : songs) {
            if (song.getSinger().equals(singer)) {
                songsBySinger.append(song);
            }
        }
        return songsBySinger.toString();
    }

    /**
     * This method shows the playlist size in the console and returns the audio player's state back
     */
    private void showSize() {
        System.out.println("Playlist size: " + size());
        changeCommandToPrev();
    }

    /**
     * This method return number of songs that are present in the list
     *
     * @return int - the returned value is the count of the songs in the list
     */
    public int size() {
        return songs.size();
    }

    /**
     * This method adds a new song to the list
     *
     * @param song - this parameter is the actual song
     */
    public void add(Song song) {
        songs.add(song);
    }

    /**
     * This method first check if the song is present in the list and then deletes it from the list
     *
     * @param song - this parameter is the song to be deleted
     */
    public void remove(Song song) {
        if (songs.contains(song)) {
            songs.remove(song);
        }
    }
}
