
import exceptions.MissingAudioPlayerReferenceException;

import java.io.IOException;

import static enumeration.Command.*;

/**
 * This class represents the audio player controller, which executes the calling of the methods of {@link AudioPlayer} .
 * It has run() method, which implements the calling of the {@link AudioPlayer} methods. This calling depends on the
 * state
 *
 *
 */
public class AudioPlayerController {
    private AudioPlayer audioPlayer;

    public AudioPlayerController(AudioPlayer audioPlayer) {
        if (audioPlayer == null) {
            throw new MissingAudioPlayerReferenceException("Missing AudioPlayer reference!");
        }
        this.audioPlayer = audioPlayer;
    }

    /**
     * This method is the running method of the application. It's purpose is to switch between commands and execute
     * audio player methods
     *
     * @throws IOException
     */
    public void run() throws IOException, InterruptedException {
        while (AudioPlayerState.getCurrent() != EXIT) {
            switch (AudioPlayerState.getCurrent()) {
                case PLAY:
                    audioPlayer.play();
                    break;
                case NEXT:
                    audioPlayer.next();
                    break;
                case PREVIOUS:
                    audioPlayer.previous();
                    break;
                case REPLAY:
                    audioPlayer.replay();
                    break;
                case SHUFFLE:
                    audioPlayer.shuffle();
                    break;
                case PAUSE:
                    System.out.println("On pause..");
                    audioPlayer.pause();
                    break;
                case SIZE:
                    AudioPlayerConsoleIO.showSize(audioPlayer);
                    break;
                case ADD:
                    audioPlayer.add(AudioPlayerConsoleIO.getNewSong(audioPlayer));
                    break;
                case SEARCH_BY_TITLE:
                    //showSearchByTitleDialog();
                    break;
                case SEARCH_BY_SINGER:
                    // showSearchBySingerDialog();
                    break;
            }
        }
    }
}
