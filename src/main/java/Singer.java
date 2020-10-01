public class Singer {
    private String name;

    public Singer(String name) {
        if(name!=null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object singerToCompare) {
        if(!(singerToCompare instanceof Singer)) {
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
