package Model;

/**
 * Eventos_paciente_exam Value Object.
 *
 * @author fberrocalm Creado en: 17/02/2015
 */
import java.io.*;
import java.sql.*;

public class Eventos_paciente_exam implements Cloneable, Serializable {

    /**
     * Persistent Instance variables. This data is directly mapped to the
     * columns of database table.
     */
    private String paciente_cod;
    private Time hora;
    private java.util.Date fecha;
    private String sede_codigo;
    private String historia;
    private String tipodcto_cod;
    private String nit;
    private String examen;
    private int reg_exa;
    private String tipo_even_cod;
    private java.util.Date fecha_event;
    private Time hora_event;
    private String observ_event;
    private String usr_codigo;
    private boolean activo;
    private int secuencia;

    public Eventos_paciente_exam() {
    }

    public Eventos_paciente_exam(String paciente_codIn) {
        this.paciente_cod = paciente_codIn;
    }

    public String getPaciente_cod() {
        return this.paciente_cod;
    }

    public void setPaciente_cod(String paciente_codIn) {
        this.paciente_cod = paciente_codIn;
    }

    public Time getHora() {
        return this.hora;
    }

    public void setHora(Time horaIn) {
        this.hora = horaIn;
    }

    public java.util.Date getFecha() {
        return this.fecha;
    }

    public void setFecha(java.util.Date fechaIn) {
        this.fecha = fechaIn;
    }

    public String getSede_codigo() {
        return this.sede_codigo;
    }

    public void setSede_codigo(String sede_codigoIn) {
        this.sede_codigo = sede_codigoIn;
    }

    public String getHistoria() {
        return this.historia;
    }

    public void setHistoria(String historiaIn) {
        this.historia = historiaIn;
    }

    public String getTipodcto_cod() {
        return this.tipodcto_cod;
    }

    public void setTipodcto_cod(String tipodcto_codIn) {
        this.tipodcto_cod = tipodcto_codIn;
    }

    public String getNit() {
        return this.nit;
    }

    public void setNit(String nitIn) {
        this.nit = nitIn;
    }

    public String getExamen() {
        return this.examen;
    }

    public void setExamen(String examenIn) {
        this.examen = examenIn;
    }

    public int getReg_exa() {
        return this.reg_exa;
    }

    public void setReg_exa(int reg_exaIn) {
        this.reg_exa = reg_exaIn;
    }

    public String getTipo_even_cod() {
        return this.tipo_even_cod;
    }

    public void setTipo_even_cod(String tipo_even_codIn) {
        this.tipo_even_cod = tipo_even_codIn;
    }

    public java.util.Date getFecha_event() {
        return this.fecha_event;
    }

    public void setFecha_event(java.util.Date fecha_eventIn) {
        this.fecha_event = fecha_eventIn;
    }

    public Time getHora_event() {
        return this.hora_event;
    }

    public void setHora_event(Time hora_eventIn) {
        this.hora_event = hora_eventIn;
    }

    public String getObserv_event() {
        return this.observ_event;
    }

    public void setObserv_event(String observ_eventIn) {
        this.observ_event = observ_eventIn;
    }

    public String getUsr_codigo() {
        return this.usr_codigo;
    }

    public void setUsr_codigo(String usr_codigoIn) {
        this.usr_codigo = usr_codigoIn;
    }

    public boolean getActivo() {
        return this.activo;
    }

    public void setActivo(boolean activoIn) {
        this.activo = activoIn;
    }

    public int getSecuencia() {
        return this.secuencia;
    }

    public void setSecuencia(int secuenciaIn) {
        this.secuencia = secuenciaIn;
    }

    public void setAll(String paciente_codIn,
            Time horaIn,
            java.util.Date fechaIn,
            String sede_codigoIn,
            String historiaIn,
            String tipodcto_codIn,
            String nitIn,
            String examenIn,
            int reg_exaIn,
            String tipo_even_codIn,
            java.util.Date fecha_eventIn,
            Time hora_eventIn,
            String observ_eventIn,
            String usr_codigoIn,
            boolean activoIn,
            int secuenciaIn) {
        this.paciente_cod = paciente_codIn;
        this.hora = horaIn;
        this.fecha = fechaIn;
        this.sede_codigo = sede_codigoIn;
        this.historia = historiaIn;
        this.tipodcto_cod = tipodcto_codIn;
        this.nit = nitIn;
        this.examen = examenIn;
        this.reg_exa = reg_exaIn;
        this.tipo_even_cod = tipo_even_codIn;
        this.fecha_event = fecha_eventIn;
        this.hora_event = hora_eventIn;
        this.observ_event = observ_eventIn;
        this.usr_codigo = usr_codigoIn;
        this.activo = activoIn;
        this.secuencia = secuenciaIn;
    }

    @Override
    public String toString() {
        return "Eventos_paciente_exam{" + "paciente_cod=" + paciente_cod + ", hora=" + hora + ", fecha=" + fecha + ", sede_codigo=" + sede_codigo
                + ", historia=" + historia + ", tipodcto_cod=" + tipodcto_cod + ", nit=" + nit + ", examen=" + examen + ", reg_exa=" + reg_exa
                + ", tipo_even_cod=" + tipo_even_cod + ", fecha_event=" + fecha_event + ", hora_event=" + hora_event + ", observ_event=" + observ_event
                + ", usr_codigo=" + usr_codigo + ", activo=" + activo + ", secuencia=" + secuencia + '}';
    }

}
