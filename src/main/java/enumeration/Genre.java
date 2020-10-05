package enumeration;

/**
 * This enumeration represents some of the valid song genres
 */
public enum Genre {
    ROCK("RockNRoll"),
    ELECTRONIC("Electronic"),
    DISCO("Disco"),
    HIPHOP("HIP-HOP"),
    JAZZ("Jazz"),
    CLASSIC("Classic"),
    UNKNOWN("Unknown");

    private String genreName;

    Genre(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return genreName;
    }

    /**
     * This method check if the given genre in string format is in valid set of genres
     *
     * @param genre - this parameter is the genre to check, which is in string format
     * @return this method return true if the genre in string format is in the set of valid genres
     */
    public static boolean isValidGenre(String genre) {
        if (genre != null && !genre.isEmpty()) {
            for (Genre command : Genre.values()) {
                if (genre.equalsIgnoreCase(command.name())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method return the string format genre in Genre constant format
     *
     * @param genre - this parameter is the genre in string format, which is evaluated to Genre constant
     * @return - this method return Genre constant
     */
    public static Commands getGenreFromString(String genre) {
        if (genre != null && !genre.isEmpty()) {
            return Commands.valueOf(genre.toUpperCase());
        }
        return null;
    }
}
