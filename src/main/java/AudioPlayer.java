import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.IOException;

import models.Song;
import models.Singer;

/**
 * This class represent an audio player and it's functions
 */
public class AudioPlayer {
    private List<Song> songs = new ArrayList<>();
    private Applicaiton applicaiton;
    private int currentSongIndex;
    private boolean wasSongPaused;
    private int songDurationLeft;
    private static int FIRST_SONG_INDEX = 0;

    public AudioPlayer(Applicaiton app) {
        applicaiton = app;
    }

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
     * This method executes songs starting from current song position, which is tracked by the class internally. If the songs list
     * end is reached the playing start from the beginning.
     */
    public void playSong() throws IOException, InterruptedException {
        if (validateSongs(songs)) {
            for (; currentSongIndex < songs.size(); nextSong()) {
                System.out.println("Currently playing :" + getSongInfo());
                if (executeSong()) {
                    return;
                }
            }
            replay();
        }
    }

    /**
     * This method execute song and checks the application if the state is changed. If the state is changed the execution of the song
     * is interrupted. If the application's state is not changed when the time left reach 0, false value is return. If the song is paused
     * during the playing, then the playing is restored from the same song, the execution of the song continues from the same timing.
     * Otherwise if a new song is playing new song duration is set.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean executeSong() throws IOException, InterruptedException {
        int timeLeft;
        if (!wasSongPaused) {
            songDurationLeft = songs.get(currentSongIndex).getTiming();
        } else {
            wasSongPaused = false;
        }
        for (; songDurationLeft > 0; songDurationLeft--) {
            Thread.sleep(100);
            if (applicaiton.isStateChanged()) {
                return true;
            }
        }
        return false;
    }


    /**
     * This method set the index, which points to the current song to the first one and changes the current state to PLAY
     */
    public void replay() {
        currentSongIndex = FIRST_SONG_INDEX;
    }

    /**
     * This method pauses the song until the "play" command is given in the console
     */
    public void pauseSong() throws IOException {
        wasSongPaused = true;
        while (!applicaiton.isStateChanged()) ;
    }

    /**
     * This method switch to the previous song of the audio player, if the current song is the first one it switch to the last
     */
    public void prevSong() {
        if ((currentSongIndex - 1) >= 0) {
            currentSongIndex--;
        } else {
            currentSongIndex = songs.size() - 1;
        }
    }

    /**
     * This method switch to the next song of the audio player, if the current song is the last one it switch to the first
     */
    public void nextSong() {
        if ((currentSongIndex + 1) < songs.size()) {
            currentSongIndex++;
        } else {
            currentSongIndex = FIRST_SONG_INDEX;
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
        songInfo.append("\n\tCurrent song: ")
                .append(songs.get(currentSongIndex).getTitle())
                .append("\n\tNumber in the list: ")
                .append(currentSongIndex + 1)
                .append("\n\tSinger: ")
                .append(songs.get(currentSongIndex).getSinger().getName());

        return songInfo.toString();
    }

    /**
     * This method plays the songs in the list, but in random order. If the application state is changed the shuffle is interrupted
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
            } while (!executeSong());
        }
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
                    StringBuilder songInfo = new StringBuilder("Singer: ");
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
        if (song != null) {
            songs.add(song);
        }
    }

    /**
     * This method first check if the song is present in the list and then deletes it from the list
     *
     * @param song - this parameter is the song to be deleted
     */
    public void remove(Song song) {
        if (songs.contains(song)) {
            songs.remove(song);
            currentSongIndex--;
        }
    }
}
