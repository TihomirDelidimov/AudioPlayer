import enumeration.Genre;
import models.Song;
import models.Author;
import models.Singer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Applicaiton {
    public static void main(String args[]) throws InterruptedException, IOException {

        List<Song> songList = new ArrayList<>();
        songList.add(new Song("High way to hell", new Author("George"), new Singer("John"), Genre.ROCK, 100));
        songList.add(new Song("Thunderstruck", new Author("Peter"), new Singer("Alex"), Genre.ROCK, 100));
        songList.add(new Song("High voltage", new Author("Edward"), new Singer("Silvia"), Genre.ROCK, 100));

        AudioPlayer audioPlayer = new AudioPlayer(songList);
        System.out.println("Type <play> to start the music: ");
        Scanner userInput = new Scanner(System.in);  // Create a Scanner object
        String command = userInput.nextLine().toLowerCase().trim();
        if(command.equals("play")) {
            audioPlayer.start();
        }
    }
}
