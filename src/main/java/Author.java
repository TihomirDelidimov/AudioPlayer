public class Author {
    private String name;

    Author(String name) {
        if(name!=null && !name.isEmpty()) {
            this.name = name;
        }
    }

    @Override
    public String toString() {
        return "\nAuthor's name: " + name;
    }

}
