import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.IOException;

import static enumeration.Command.*;

import enumeration.Command;
import models.Song;
import models.Singer;

/**
 * This class represent an audio player and it's functions
 */
public class AudioPlayer {
    private List<Song> songs = new ArrayList<>();
    private Command commandFromInput = INVALID_COMMAND;
    private int currentSongIndex;
    private boolean isSongPaused;
    private int songDurationLeft;
    private static int FIRST_SONG_INDEX = 0;

    /**
     * This methods add a list of songs to the list of songs in the audio player.
     *
     * @param songs - this parameter is the list with songs to add
     */
    public void addListOfSongs(List<Song> songs) {
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
     * This method executes songs starting from current song position, which is tracked by the class internally
     * through currentSongIndex. If the execute() method has changed the commandFromInput instance this means
     * that the user wants to change the state of the applicaiton. In this case the method passes the command to
     * the audio controller. If currentSongIndex points to the last song automatically the execution of the songs
     * will start from the beginning.
     *
     * @return - this method return command, which represent a request to change the state of the application by the user
     * @throws IOException
     * @throws InterruptedException
     */
    public void play() throws IOException, InterruptedException {
        if (validateSongs(songs)) {
            for (; currentSongIndex < songs.size(); next()) {
                System.out.println("Currently playing :" + getSongInfo());
                if (execute()) {
                    return;
                }
            }
            replay();
        }
    }

    /**
     * This method executes song and checks the console input for valid command. If a valid command is supplied on the input,
     * that menas the user wants to change the state of the application. The new command is saved in AudioPlayerState.
     *
     * @return - this method return true if the song is interrupted by new command during the execution, otherwise return false
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean execute() throws IOException, InterruptedException {
        if (!isSongPaused) {
            songDurationLeft = songs.get(currentSongIndex).getTiming();
        } else {
            isSongPaused = false;
        }
        for (; songDurationLeft > 0; songDurationLeft--) {
            Thread.sleep(100);
            if (AudioPlayerConsoleIO.checkForInput()) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method set the index, which points to the current song to the first one and changes the current state to PLAY
     * so the controller will start the execution of play method.
     */
    public void replay() {
        currentSongIndex = FIRST_SONG_INDEX;
        AudioPlayerState.setCurrent(PLAY);
    }

    /**
     * This method pauses the song until the a valid new command is given in the console. The pause() method returns
     * the previous state of the audio player
     */
    public void pause() throws IOException {
        isSongPaused = true;
        while (!AudioPlayerConsoleIO.checkForInput());
    }

    /**
     * This method switch to the previous song of the audio player, if the current song is the first one it switch to the last.
     * The method also changes the audio player state to PLAY, so it will begin to execute the previous song automatically
     */
    public void previous() {
        if (currentSongIndex > 0) {
            currentSongIndex--;
        } else {
            currentSongIndex = songs.size() - 1;
        }
        AudioPlayerState.setCurrent(PLAY);
    }

    /**
     * This method switch to the next song of the audio player, if the current song is the last one it switch to the first.
     * The method also changes the audio player state to PLAY, so it will begin to execute the next song automatically
     */
    public void next() {
        if (currentSongIndex < songs.size() - 1) {
            currentSongIndex++;
        } else {
            currentSongIndex = FIRST_SONG_INDEX;
        }
        AudioPlayerState.setCurrent(PLAY);
    }

    /**
     * This method is used to get specific information about the song which is the song's title, song's number in the list
     * and the singer's name in string format
     *
     * @return String - this method return song's title, song's number and singer's name in a string format
     */
    public String getSongInfo() {
        StringBuilder songInfo = new StringBuilder();
        songInfo.append("\n\tCurrent song: ")
                .append(songs.get(currentSongIndex).getTitle())
                .append("\n\tNumber in the list: ")
                .append(currentSongIndex + 1)
                .append("\n\tSinger: ")
                .append(songs.get(currentSongIndex).getSinger().getName());

        return songInfo.toString();
    }

    /**
     * This method plays the songs in the list, but in random order. If the user has supplied the console with valid command
     * this means that the state needs to be changed, so shuffle breaks and passes the commandFromInput to the audio controller.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public void shuffle() throws IOException, InterruptedException {
        if (validateSongs(songs)) {
            Random randomSongIndex = new Random();
            do {
                currentSongIndex = randomSongIndex.nextInt(songs.size());
                System.out.println("Currently playing :" + getSongInfo());
            } while (!execute());
        }
    }

    /**
     * This method search the list of songs by the song's title and return the singer's name and song number in the list.
     *
     * @param title - this parameter is the title of the song to be searched by
     * @return String - this method return the singer of the song and it's number in the list.
     */
    public String searchSingerByTitle(String title) {
        if (title != null && !title.isEmpty()) {
            for (Song song : songs) {
                if (song.getTitle().equalsIgnoreCase(title)) {
                    StringBuilder songInfo = new StringBuilder("Singer: ");
                    songInfo.append(song.getSingerName())
                            .append("\nSong's number: ")
                            .append(songs.indexOf(song) + 1);
                    return songInfo.toString();
                }
            }
        }
        AudioPlayerState.changeCurrentToPrevious();
        return null;
    }

    /**
     * This method return list of songs in string format searched by the singer of the song.
     *
     * @param singer - this parameter is the singer of the song, whom we are looking for
     * @return String - this method return list of songs in string format
     */
    public String searchSongsBySinger(Singer singer) {
        StringBuilder songsBySinger = new StringBuilder();
        songsBySinger.append("Songs by: ")
                .append(singer.getName());

        for (Song song : songs) {
            if (song.checkSingerName(singer.getName())) {
                songsBySinger.append(song);
            }
        }
        AudioPlayerState.changeCurrentToPrevious();
        return songsBySinger.toString();
    }

    /**
     * This method return number of songs that are present in the list
     *
     * @return int - the returned value is the count of the songs in the list
     */
    public int size() {
        AudioPlayerState.changeCurrentToPrevious();
        return songs.size();
    }

    /**
     * This method adds a new song to the list
     *
     * @param song - this parameter is the actual song
     */
    public void add(Song song) {
        if (song != null) {
            songs.add(song);
        }
        AudioPlayerState.changeCurrentToPrevious();
    }

    /**
     * This method first check if the song is present in the list and then it's index is taken. After deletion current song index
     * needs to be repositioned in case of deletion of the last song or after deletion of song, which was on smaller position in
     * the list then currentSongIndex. In this cases currentSongIndex is repositioned one position backwards, so that currentSongIndex
     * won't lose track of the current song.
     *
     * @param song - this parameter is the song to be deleted
     */
    public void remove(Song song) {
        if (songs.contains(song)) {
            int deleteSongIndex = songs.indexOf(song);
            songs.remove(song);
            if (currentSongIndex == songs.size() || deleteSongIndex < currentSongIndex)
                currentSongIndex--;
        }
    }
}
