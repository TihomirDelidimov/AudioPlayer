import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Applicaiton {
    public static void main(String args[]) throws IOException, InterruptedException {

        List<Song> songList = new ArrayList<>();
        songList.add(new Song("High way to hell", new Author("George"), new Singer("John"), Genre.ROCK, 100));
        songList.add(new Song("Thunderstruck", new Author("Peter"), new Singer("Alex"), Genre.ROCK, 100));
        songList.add(new Song("High voltage", new Author("Edward"), new Singer("Silvia"), Genre.ROCK, 100));

        AudioPlayer audioPlayer = new AudioPlayer(songList);
        audioPlayer.start();


    }
}
