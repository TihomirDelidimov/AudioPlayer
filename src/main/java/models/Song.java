package models;

import enumeration.Genre;
import exceptions.IncorrectSingerException;
import exceptions.IncorrectSongTitleException;
import exceptions.InvalidSongTimingException;

/**
 * This class represent song, which will be executed by the audio player
 */
public class Song {
    private String title;
    private Genre genre;
    private Singer singer;
    private int timing;

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
        if (!isTitleValid(title)) {
            throw new IncorrectSongTitleException("Incorrect song title!");
        }
        if (!isSingerValid(singer)) {
            throw new IncorrectSingerException("Singer cannot be null!");
        }
        if (!isGenreValid(genre)) {
            throw new NullPointerException("Genre cannot be null!");
        }
        if (!isTimingValid(timing)) {
            throw new InvalidSongTimingException("Song timing cannot be 0 or less then 0 seconds!");
        }

        this.title = title;
        this.singer = singer;
        this.genre = genre;
        this.timing = timing;
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
     * This method check if the song's singer is valid. If it is the singer is true is returned, otherwise return false
     *
     * @param singer - this parameter is the singer of the song
     * @return - this method return true if the parameter is not null, otherwise return false
     */
    private boolean isSingerValid(Singer singer) {
        return singer != null;
    }

    /**
     * This method check if the song's genre is valid. If it is true is returned, otherwise false value is returned
     *
     * @param genre - this is the genre of the song
     * @return - this method return true if the parameter is valid, otherwise return falise
     */
    private boolean isGenreValid(Genre genre) {
        return genre != null;
    }

    /**
     * This method check if the song's title is valid
     *
     * @param title - this parameter is the song's title
     * @return - this method return true if the title is valid, otherwise return false
     */
    private boolean isTitleValid(String title) {
        return title != null && !title.isEmpty();
    }

    /**
     * This method check if the song's timing is valid
     *
     * @param timing - this parameter is the timing of the song
     * @return - this method return true if the timing is valid, otherwise return false
     */
    private boolean isTimingValid(int timing) {
        return timing > 0;
    }

    /**
     * This method check if a given title is equal as song's title
     * @return - this method return true if the titles are equal, otherwise return false
     */
    public boolean titleEquals(String title) {
        return title.equalsIgnoreCase(this.title);
    }

    /**
     * This method is used to get singer's name
     * @return - this method return singer's name
     */
    public String getSingerName() {
        return singer.getName();
    }

    /**
     * This method check if a given name is equal as song's singer name
     * @param name - this parameter is the name to be checked
     * @return - this method return true if the names are equal, otherwise return false
     */
    public boolean checkSingerName(String name) {
        return name.equalsIgnoreCase(singer.getName());
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
