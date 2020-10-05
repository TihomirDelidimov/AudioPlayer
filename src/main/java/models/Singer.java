package models;

public class Singer {
    private String name;
    private static final String DEFAULT_SINGER_NAME = "Unknown";

    public Singer(String name) {
        this.name = getValidName(name);
    }

    /**
     * This methods check if the singer's name is valid. If it is the name is returned, otherwise DEFAULT_SINGER_NAME is returned
     *
     * @param name
     * @return
     */
    private String getValidName(String name) {
        if (name != null && !name.isEmpty()) {
            return name;
        }
        return DEFAULT_SINGER_NAME;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object singerToCompare) {
        if (!(singerToCompare instanceof Singer)) {
            return false;
        }
        Singer singer = (Singer) singerToCompare;
        return singer.getName().equalsIgnoreCase(name);
    }

    @Override
    public String toString() {
        return "\nSinger's name: " + name;
    }
}
