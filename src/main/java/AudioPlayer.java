import java.util.ArrayList;
import java.util.ListIterator;
import java.util.List;

/**
 * This class represent
 */
public class AudioPlayer {
    private List<Song> songs = new ArrayList<>();
    //  private ListIterator songIterator;
    private int currentSongIndex;

    /**
     * This constructor initiates the AudioPlayer object with list of songs. It sets the iterator to the first element.
     *
     * @param songs - this parameter is the list with songs
     */
    public AudioPlayer(ArrayList<Song> songs) {
        if (validateSongs(songs)) {
            this.songs.addAll(songs);
            currentSongIndex = 0;
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
     * @return
     */
    @Override
    public String toString() {
        //  songs.listIterator().hasNext()
        return "";
    }

    /**
     * This method is used to start songs in a sequence
     */
    public void playSong() {
        if (validateSongs(songs)) {

        }
    }

    /**
     * This method switch to the next song of the audio player
     */
    public void prevSong() {
        if ((currentSongIndex - 1) >= 0) {
            currentSongIndex--;
        }
    }

    /**
     * This method switch to the previous song of the audio player
     */
    public void nextSong() {
        if ((currentSongIndex + 1) < songs.size()) {
            currentSongIndex++;
        }
    }

    /**
     * @return
     */
    public String getSongInfo() {
        StringBuilder songInfo = new StringBuilder();
        songInfo.append("\nCurrent song: ")
                .append(songs.get(currentSongIndex).getTitle())
                .append("\n\tNumber: ")
                .append(currentSongIndex+1)
                .append("\n\tAuthor: ")
                .append(songs.get(currentSongIndex).getAuthor());

        return songInfo.toString();
    }

    /**
     * This method return list of songs in string format searched by the singer of the song.
     * @param singer - this is the singer of the song, whom we are looking for
     * @return String - this is the list of songs in string format
     */
    public String searchBySinger(Singer singer) {
        StringBuilder songsBySinger = new StringBuilder();
        songsBySinger.append("\nSongs by: ")
                .append(singer.getName());

        for(Song song : songs) {
            if(song.equals(singer)) {
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
    public int Size() {
        return songs.size();
    }

    /**
     * @param song
     */
    public void add(Song song) {
        songs.add(song);
    }

    /**
     * This method first check if the song is present in the list and then deletes it from the list
     *
     * @param song
     */
    public void remove(Song song) {
        if (songs.contains(song)) {
            if (songs.indexOf(song) == currentSongIndex) {
                if ((currentSongIndex+1) < songs.size()) {
                    currentSongIndex++;
                } else if ((currentSongIndex-1) >= 0) {
                    currentSongIndex--;
                } else {
                    currentSongIndex = -1;
                }
            }
            songs.remove(song);
        }
    }
}
