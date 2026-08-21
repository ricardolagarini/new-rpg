package New_RPG.Tools;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import java.io.File;

public class Audio {

    private static Clip musicaClip;

    public static void playMusica(String filePath) {
        stopMusica();
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            musicaClip = AudioSystem.getClip();
            musicaClip.open(audioStream);
            musicaClip.loop(Clip.LOOP_CONTINUOUSLY);
            musicaClip.start();
        } catch (Exception e) {
            System.out.println("Erro ao reproduzir o áudio: " + e.getMessage());
        }
    }

    public static void stopMusica() {
        if (musicaClip != null) {
            musicaClip.stop();
            musicaClip.close();
            musicaClip = null;
        }
    }

    public static void playAudio(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            Clip efeito = AudioSystem.getClip();
            efeito.open(audioStream);
            efeito.addLineListener(evento -> {
                if (evento.getType() == LineEvent.Type.STOP) {
                    evento.getLine().close();
                }
            });
            efeito.start();
        } catch (Exception e) {
            System.out.println("Erro ao reproduzir o áudio: " + e.getMessage());
        }
    }

    public static void stopAudio(String filePath) {
        stopMusica();
    }

}
