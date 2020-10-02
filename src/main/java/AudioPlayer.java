
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import models.Song;
import models.Singer;

/**
 * This class represent
 */
public class AudioPlayer {
    private List<Song> songs = new ArrayList<>();
    //  private ListIterator songIterator;
    private String currentCommand = "play";
    private int currentSongIndex;
    private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

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
     * @return
     * @throws IOException
     */
    private boolean checkForInput() throws IOException {
        if (inputReader.ready()) {
            currentCommand = inputReader.readLine().trim();
            return validateCommand();
        }
        return false;
    }


    /**
     * @return
     */
    private boolean validateCommand() {
        switch (currentCommand) {
            case "play":
            case "next":
            case "prev":
            case "pause":
                return true;
        }
        return currentCommand.contains("add") ||
                currentCommand.contains("remove");
    }

    /**
     * @throws IOException
     * @throws InterruptedException
     */
    public void start() throws IOException, InterruptedException {
        while (!currentCommand.equals("exit")) {
                switch (currentCommand) {
                    case "play":
                        playSong();
                        break;
                    case "next":
                        nextSong();
                        playSong();
                        break;
                    case "prev":
                        prevSong();
                        playSong();
                        break;
                    case "pause":
                        pauseSong();
                        break;
                }
        }
    }

    /**
     * This method is used to play song in a sequence
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
            currentSongIndex = 0;
        }
    }

    /**
     *
     */
    private void pauseSong() throws IOException {
        System.out.println("\nOn pause..");
        while(!currentCommand.equals("play")) {
            checkForInput();
        }
        System.out.println("\nResumed");
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
     * This method search the list of songs by the song's title and return the singer's name and song number in the list.
     *
     * @param title - this parameter is the title of the song to be searched by
     * @return String - this method return the singer of the song and it's number in the list.
     */
    public String searchByTitle(String title) {
        StringBuilder songInfo = new StringBuilder("\nSinger: ");

        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                songInfo.append(song.getSinger())
                        .append("\n Song's number: ")
                        .append(songs.indexOf(song) + 1);
                return songInfo.toString();
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
        songsBySinger.append("\nSongs by: ")
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
