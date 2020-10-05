package models;

import static enumeration.Genre.*;

import enumeration.Genre;

/**
 * This class represent song, which will be executed by the audio player
 */
public class Song {
    private String title;
    private Genre genre;
    private Singer singer;
    private int timing;
    private static final String DEFAULT_TITLE = "Unknown";
    private static final int DEFAULT_TIMING = 1;

    /**
     * The constructor initialize the class fields with the values from the parameters if they are valid, otherwise it
     * initialize the fields with default values
     *
     * @param title  - this parameter is the title of the song
     * @param singer - this parameter is the singer of the song
     * @param genre  - this parameter is the genre of the song
     * @param timing - this parameter is timing of the song
     */
    public Song(String title, Singer singer, Genre genre, int timing) {
        this.title = getValidTitle(title);
        this.genre = getValidGenre(genre);
        this.timing = getValidTiming(timing);
        this.singer = getValidSinger(singer);
    }

    public String getTitle() {
        return title;
    }

    public Singer getSinger() {
        return singer;
    }

    public int getTiming() {
        return timing;
    }

    /**
     * This method check if the song's singer is valid. If it is the singer is returned, otherwise the default value is returned
     * from Singer class
     *
     * @param singer - this parameter is the singer of the song
     * @return - this method return the singer if the parameter is valid, otherwise return new singer, which will be populated with default values
     */
    private Singer getValidSinger(Singer singer) {
        return singer != null ? singer : new Singer(null);
    }

    /**
     * This method check if the song's genre is valid. If it is the genre is returned, otherwise UNKNOWN genre is returned
     *
     * @param genre - this parameter is the genre of the song
     * @return - this method return the genre if the parameter is valid, otherwise return default value for the genre
     */
    private Genre getValidGenre(Genre genre) {
        return genre != null ? genre : UNKNOWN;
    }

    /**
     * This methods check if the song's title is valid. If it is the title is returned, otherwise DEFAULT_TITLE is returned
     *
     * @param title - this parameter is the title of the song
     * @return boolean - this method return the title if the parameter is valid, otherwise return default value for the title
     */
    private String getValidTitle(String title) {
        if (title != null && !title.isEmpty()) {
            return title;
        }
        return DEFAULT_TITLE;
    }

    /**
     * This method checks if the song's timing is valid. If it is the timing is returned, otherwise DEFAULT_TIMING is returned
     *
     * @param timing - this parameter is song's timing which is measured in seconds
     * @return boolean - this method return the timing if its valid, otherwise DEFAULT_TIMING
     */
    private int getValidTiming(int timing) {
        return timing > 0 ? timing : DEFAULT_TIMING;
    }

    /**
     * This method is used to get Song object attributes in string format
     *
     * @return String - this method return String which represent object's fields in string format
     */
    @Override
    public String toString() {
        StringBuilder songInfo = new StringBuilder();
        songInfo.append("\nSong's title: ")
                .append(title)
                .append("\nSong's genre: ")
                .append(genre)
                .append("\nSong's timing: ")
                .append(timing)
                .append("s")
                .append("\nSong's singer: ")
                .append(singer.getName());

        return songInfo.toString();
    }
}
