package Model;

import java.io.*;

/**
 *
 * @author fberrocalm 15/04/2015
 */
public class Ciudades implements Cloneable, Serializable {

    private String ciudad_cod;
    private String ciudad_descrip;
    private boolean ciudad_activo;

    public Ciudades() {

    }

    public Ciudades(String ciudad_codIn) {
        this.ciudad_cod = ciudad_codIn;
    }

    public String getCiudad_cod() {
        return this.ciudad_cod;
    }

    public void setCiudad_cod(String ciudad_codIn) {
        this.ciudad_cod = ciudad_codIn;
    }

    public String getCiudad_descrip() {
        return this.ciudad_descrip;
    }

    public void setCiudad_descrip(String ciudad_descripIn) {
        this.ciudad_descrip = ciudad_descripIn;
    }

    public boolean getCiudad_activo() {
        return this.ciudad_activo;
    }

    public void setCiudad_activo(boolean ciudad_activoIn) {
        this.ciudad_activo = ciudad_activoIn;
    }

    public void setAll(String ciudad_codIn,
            String ciudad_descripIn,
            boolean ciudad_activoIn) {
        this.ciudad_cod = ciudad_codIn;
        this.ciudad_descrip = ciudad_descripIn;
        this.ciudad_activo = ciudad_activoIn;
    }

    @Override
    public String toString() {
        return "Ciudades{" + "ciudad_cod=" + ciudad_cod + ", ciudad_descrip=" + ciudad_descrip + ", ciudad_activo=" + ciudad_activo + '}';
    }

}
