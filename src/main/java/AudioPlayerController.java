
import exceptions.MissingAudioPlayerReferenceException;
import exceptions.MissingAudioPlayerIOReferenceException;
import exceptions.MissingAudioPlayerStateReferenceException;
import models.Song;

import java.io.IOException;
import java.util.List;

import static enumeration.Command.*;

/**
 * This class represents the audio player controller, which executes the calling of the methods of {@link AudioPlayer} .
 * It has run() method, which implements the calling of the {@link AudioPlayer} methods. This calling depends on the
 * state of the {@link AudioPlayerState} class, which is responsible to contain the state of the audio player.
 */
public class AudioPlayerController {
    private AudioPlayerState audioPlayerState;
    private AudioPlayer audioPlayer;
    private AudioPlayerConsoleIO audioPlayerIO;

    /**
     * This constructor initializes the {@link AudioPlayerController} object with two references, one of which is
     * to the {@link AudioPlayer}. The other reference is to the {@link AudioPlayerConsoleIO} object. This constructor
     * uses validator method, which throws exceptions for missing references.
     *
     * @param audioPlayer      - this parameter is reference to the AudioPlayer object
     * @param audioPlayerIO    - this parameter is reference to the AudioPlayerConsoleIO object
     * @param audioPlayerState - this parameter is reference to the AudioPlayerState object
     */
    public AudioPlayerController(AudioPlayer audioPlayer, AudioPlayerConsoleIO audioPlayerIO,
                                 AudioPlayerState audioPlayerState) {
        validator(audioPlayer, audioPlayerIO, audioPlayerState);
        this.audioPlayerIO = audioPlayerIO;
        this.audioPlayer = audioPlayer;
        this.audioPlayerState = audioPlayerState;
    }

    /**
     * This method is used to validate all references passed on to the constructor
     *
     * @throws MissingAudioPlayerReferenceException      - this exception is thrown when reference to {@link AudioPlayer} is missing
     * @throws MissingAudioPlayerIOReferenceException    - this exception is thrown when reference to {@link AudioPlayerConsoleIO} is missing
     * @throws MissingAudioPlayerStateReferenceException - this exception is thrown when reference to {@link AudioPlayerState} is missing
     */
    private void validator(AudioPlayer audioPlayer, AudioPlayerConsoleIO audioPlayerIO, AudioPlayerState audioPlayerState) {
        if (audioPlayer == null) {
            throw new MissingAudioPlayerReferenceException("Missing AudioPlayer reference!");
        }
        if (audioPlayerIO == null) {
            throw new MissingAudioPlayerIOReferenceException("Missing AudioPlayerConsoleIO reference!");
        }
        if (audioPlayerState == null) {
            throw new MissingAudioPlayerStateReferenceException("Missing AudioPlayerState reference!");
        }
    }

    /**
     * This method is the running method of the application. Its purpose is to switch between commands and execute
     * audio player methods
     * This method is the running method of the application. Its purpose is to execute {@link AudioPlayer} methods
     * and interact with the user through {@link AudioPlayerConsoleIO} methods.
     *
     * @throws IOException - this exception is thrown if I/O operations failed
     */
    public void run() throws IOException, InterruptedException {
        while (audioPlayerState.getCurrent() != EXIT) {
            switch (audioPlayerState.getCurrent()) {
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
                case STOP:
                    audioPlayer.stop();
                    break;
                case SIZE:
                    audioPlayerIO.showPlaylistSize(audioPlayer);
                    break;
                case ADD:
                    audioPlayer.add(audioPlayerIO.getNewSong());
                    break;
                case SEARCH_SINGER_BY_TITLE:
                    String searchResult = audioPlayer.searchSingerByTitle(audioPlayerIO.getTitleFromInput());
                    audioPlayerIO.showAudioPlayerOutput(searchResult);
                    break;
                case SEARCH_SONGS_BY_SINGER:
                    List<Song> searchResultList = audioPlayer.searchSongsBySinger(audioPlayerIO.getSingerFromInput());
                    audioPlayerIO.showAudioPlayerOutput(searchResultList.toString());
                    break;
            }
        }
    }
}