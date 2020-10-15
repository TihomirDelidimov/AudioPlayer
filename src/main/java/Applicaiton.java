import java.io.IOException;
import java.util.Scanner;

import static enumeration.Genre.*;
import static enumeration.Command.*;

import models.Song;
import models.Singer;

/**
 * The Application class is the main class in the application.
 */
public class Applicaiton {

    public static void main(String args[]) throws IOException, InterruptedException {
        AudioPlayerState audioPlayerState = new AudioPlayerState();
        AudioPlayerConsoleIO audioPlayerIO = new AudioPlayerConsoleIO(audioPlayerState);
        AudioPlayer audioPlayer = new AudioPlayer(audioPlayerIO, audioPlayerState);
        AudioPlayerController audioPlayerController = new AudioPlayerController(audioPlayer, audioPlayerIO, audioPlayerState);
        audioPlayer.add(new Song("High way to hell", new Singer("John"), ROCK, 100));
        audioPlayer.add(new Song("Thunderstruck", new Singer("Alex"), ROCK, 100));
        audioPlayer.add(new Song("High voltage", new Singer("Silvia"), ROCK, 100));

        System.out.println("Type <start> to start playing ");
        Scanner userInput = new Scanner(System.in);
        String command = userInput.nextLine().toLowerCase().trim();
        if (command.equals("start")) {
            audioPlayerState.setCurrent(PLAY);
            audioPlayerController.run();
        }
    }
}