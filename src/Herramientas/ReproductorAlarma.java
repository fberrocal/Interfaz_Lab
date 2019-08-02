package Herramientas;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author fberrocalm Creado en: 25/02/2015
 */
public class ReproductorAlarma {

    public ReproductorAlarma() {
    }

    //reproduce alarma...
    public void reproducir() throws URISyntaxException {
        try {
            Clip sonido = AudioSystem.getClip();
            File ruta;
            ruta = new File("etc/alarma.wav");
            sonido.open(AudioSystem.getAudioInputStream(ruta));
            sonido.start();
            Thread.sleep(10000);
            sonido.close();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException e) {
            System.out.println(" " + e);
        }
    }

    public void reproducirAlarma() throws URISyntaxException {
        try {
            Clip sonido = AudioSystem.getClip();
            File ruta;
            ruta = new File("etc/wav.wav");
            sonido.open(AudioSystem.getAudioInputStream(ruta));
            sonido.start();
            Thread.sleep(10000);
            sonido.close();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException e) {
            System.out.println(" " + e);
        }
    }

}
