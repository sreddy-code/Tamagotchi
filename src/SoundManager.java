import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class SoundManager {

    private MediaPlayer mediaPlayer;

    public void playMusic(String filePath) {
        try {
            Media sound = new Media(new File(filePath).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.4);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Could not load music: " + e.getMessage());
        }
    }

    public void pause() {
        if (mediaPlayer != null) mediaPlayer.pause();
    }

    public void resume() {
        if (mediaPlayer != null) mediaPlayer.play();
    }

    public void mute() {
        if (mediaPlayer != null) mediaPlayer.setMute(true);
    }

    public void unmute() {
        if (mediaPlayer != null) mediaPlayer.setMute(false);
    }

    public boolean isMuted() {
        return mediaPlayer != null && mediaPlayer.isMute();
    }

    public void setVolume(double volume) {
        if (mediaPlayer != null) mediaPlayer.setVolume(volume);
    }
}