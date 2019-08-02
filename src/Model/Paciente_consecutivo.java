package Model;

import java.io.*;

/* Clase Paciente_consecutivo */
public class Paciente_consecutivo implements Cloneable, Serializable {

    /* Campos de la tabla paciente_consecutivo. */
    private String paciente_cod;
    private java.util.Date fecha;
    private String sede_codigo;

    /* Constructores. */
    public Paciente_consecutivo() {
    }

    public Paciente_consecutivo(String paciente_codIn) {
        this.paciente_cod = paciente_codIn;
    }

    /*Metodos de encapsulado*/
    public String getPaciente_cod() {
        return this.paciente_cod;
    }

    public void setPaciente_cod(String paciente_codIn) {
        this.paciente_cod = paciente_codIn;
    }

    public java.util.Date getFecha() {
        return this.fecha;
    }

    public void setFecha(java.sql.Date fechaIn) {
        this.fecha = fechaIn;
    }

    public String getSede_codigo() {
        return this.sede_codigo;
    }

    public void setSede_codigo(String sede_codigoIn) {
        this.sede_codigo = sede_codigoIn;
    }

    /* setAll*/
    public void setAll(String paciente_codIn,
            java.util.Date fechaIn,
            String sede_codigoIn) {
        this.paciente_cod = paciente_codIn;
        this.fecha = fechaIn;
        this.sede_codigo = sede_codigoIn;
    }

    @Override
    public String toString() {
        return "Paciente_consecutivo{" + "paciente_cod=" + paciente_cod + ", fecha=" + fecha + ", sede_codigo=" + sede_codigo + '}';
    }

}
