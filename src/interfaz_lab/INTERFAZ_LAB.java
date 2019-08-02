/**
 * Archivo que lanza la Aplicación - Archivo de inicio de la App Se encarga de
 * llamar al formulario Splash que a si vez invoca al formulario de Login
 */
package interfaz_lab;

import com.alee.laf.WebLookAndFeel;
import view.SplashFrame;

/**
 * @author Ing. Francisco Berrocal Machado
 * Made in: MyBrain
 */
public class INTERFAZ_LAB {
    public static void main(String[] args) {
        //Estructura try - Catcht: Establece el Look And Feel de la Aplicación Java
        /*try {
         for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
         if ("Nimbus".equals(info.getName())) {
         UIManager.setLookAndFeel(info.getClassName());
         System.out.println("CHOSEN THIS");
         break;
         } else {
         UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
         }
         }
         } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
         System.out.println(e);
         }*/
        WebLookAndFeel.install();                                                   // <-- Este es el código definitivo para el incio de la Aplicación
        SplashFrame splashFrame = new SplashFrame();
        splashFrame.setVisible(true);
    }
}