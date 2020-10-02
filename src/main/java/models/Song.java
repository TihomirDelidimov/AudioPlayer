package models;

import enumeration.Genre;

/**
 * This class represent song
 */
public class Song {
    private String title;
    private Genre genre;
    private Author author;
    private Singer singer;
    private int timing;


    /**
     * @param title
     * @param author
     * @param singer
     * @param genre
     * @param timing
     */
    public Song(String title, Author author, Singer singer, Genre genre, int timing) {
        this.title = title;
        this.author = author;
        this.genre = genre != null ? genre : Genre.UNKNOWN;
        this.timing = timing;
        this.singer = singer;
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
     * This methods check if the song's title is valid
     *
     * @param title - this parameter is the title
     * @return boolean - this method return true if title is valid and false if it's not
     */
    private boolean validateTitle(String title) {
        return title != null && title.isEmpty();
    }


    /**
     * This method checks if the song's timing is valid
     *
     * @param timing - this parameter is song's timing which is int and it's measured in seconds
     * @return boolean - this method return true if the timing is valid and false if it's not
     */
    private boolean validateTiming(int timing) {
        return timing > 0;
    }

    /**
     * This method is used to get Song object attributes in string format
     *
     * @return String - this method return String which represent object's fields in string format
     */
    @Override
    public String toString() {
        StringBuilder songInfo = new StringBuilder();
        songInfo.append("Song's title: ")
                .append(title)
                .append("Song's genre: ")
                .append(genre)
                .append("Song's timing: ")
                .append(timing)
                .append("Song's author: ")
                .append(author);

        return songInfo.toString();
    }

    /**
     * This method return song's author and title in formatted output
     *
     * @return String - this method returns formatted string
     */
    public String Author() {
        StringBuilder songInfo = new StringBuilder();
        songInfo.append("Song's title: ")
                .append(title)
                .append("Song's author: ")
                .append(author);

        return songInfo.toString();
    }
}
