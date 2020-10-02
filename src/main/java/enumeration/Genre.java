package enumeration;

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
}
