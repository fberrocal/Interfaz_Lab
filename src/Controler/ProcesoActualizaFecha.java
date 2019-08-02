/*
 * Este hilo de proceso se ejecuta cada 24 horas y actualiza la fecha de la interfaz, cada dia 1 del mes actualiza la fecha del combo de fechas de la interfaz
 * con el objetivo de trabajar solo con laboratorios de un mes de solicitud
 */
package Controler;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import view.Prueba1;

/**
 * @author Ing. Francisco Berrocal Machado
 * Made in: MyBrain
 */
public class ProcesoActualizaFecha implements Runnable {

    public boolean centinela = true;
    Calendar fechaSys;

    @Override
    public void run() {
        while (centinela) {
            try {
                fechaSys = new GregorianCalendar();
                actualizarFechaInterfaz();
                Thread.sleep(86400000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProcesoActualizaFecha.class.getName()).log(Level.SEVERE, "Error Actualizando La Fecha de la interfaz", ex);
            }
        }
    }

    public void actualizarFechaInterfaz() {
        SwingUtilities.invokeLater(() -> {
            if (fechaSys.get(Calendar.DAY_OF_MONTH) == 1) {
                Prueba1.fechaLab.setDate(new Date());
            }
        });
    }
}