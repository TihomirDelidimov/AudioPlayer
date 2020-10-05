package models;

/**
 * This class represent a singer, which is associated with songs
 */
public class Singer {
    private String name;
    private static final String DEFAULT_SINGER_NAME = "Unknown";

    public Singer(String name) {
        this.name = getValidName(name);
    }

    /**
     * This methods check if the singer's name is valid. If it is the name is returned, otherwise DEFAULT_SINGER_NAME is returned
     *
     * @param name - this parameter is the the name to be checked
     * @return - this method return the parameter if it's valid, otherwise return default value
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

    /**
     * This method implement equals between two singer objects. It compares them by name of the singer
     * @param singerToCompare - this is the singer object
     * @return - this method return true if the two objects have same name, otherwise return false
     */
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
