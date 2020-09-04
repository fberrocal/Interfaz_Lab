package Model;

/**
 * 03.09.2020 Dev. fberrocalm
 */

import java.io.Serializable;
import java.sql.Time;

public class Paciente_Adicionales implements Cloneable, Serializable {

    private String paciente_cod;
    private Time hora;
    private java.util.Date fecha;
    private String sede_codigo;
    private String historia;
    private String tipodcto_cod;
    private String nit;
    private String concap_cod;
    private String contenido;
    private boolean activo;

    public Paciente_Adicionales () {

    }

    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     * @return 
     */
    public boolean getActivo() {
          return this.activo;
    }
    public void setActivo(boolean activo) {
          this.activo = activo;
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

    public String getConcap_cod() {
          return this.concap_cod;
    }
    public void setConcap_cod(String concap_codIn) {
          this.concap_cod = concap_codIn;
    }

    public String getContenido() {
          return this.contenido;
    }
    public void setContenido(String contenidoIn) {
          this.contenido = contenidoIn;
    }

    public void setAll(String paciente_codIn, Time horaIn, java.util.Date fechaIn, String sede_codigoIn,
          String historiaIn, String tipodcto_codIn, String nitIn, String concap_codIn, String contenidoIn, boolean activo) {
          this.activo       = activo;
          this.paciente_cod = paciente_codIn;
          this.hora         = horaIn;
          this.fecha        = fechaIn;
          this.sede_codigo  = sede_codigoIn;
          this.historia     = historiaIn;
          this.tipodcto_cod = tipodcto_codIn;
          this.nit          = nitIn;
          this.concap_cod   = concap_codIn;
          this.contenido    = contenidoIn;
    }

    public boolean hasEqualMapping(Paciente_Adicionales valueObject) {

          if (valueObject.getActivo()!= this.activo) {
                    return(false);
          }
          if (this.paciente_cod == null) {
                    if (valueObject.getPaciente_cod() != null)
                           return(false);
          } else if (!this.paciente_cod.equals(valueObject.getPaciente_cod())) {
                    return(false);
          }
          if (this.hora == null) {
                    if (valueObject.getHora() != null)
                           return(false);
          } else if (!this.hora.equals(valueObject.getHora())) {
                    return(false);
          }
          if (this.fecha == null) {
                    if (valueObject.getFecha() != null)
                           return(false);
          } else if (!this.fecha.equals(valueObject.getFecha())) {
                    return(false);
          }
          if (this.sede_codigo == null) {
                    if (valueObject.getSede_codigo() != null)
                           return(false);
          } else if (!this.sede_codigo.equals(valueObject.getSede_codigo())) {
                    return(false);
          }
          if (this.historia == null) {
                    if (valueObject.getHistoria() != null)
                           return(false);
          } else if (!this.historia.equals(valueObject.getHistoria())) {
                    return(false);
          }
          if (this.tipodcto_cod == null) {
                    if (valueObject.getTipodcto_cod() != null)
                           return(false);
          } else if (!this.tipodcto_cod.equals(valueObject.getTipodcto_cod())) {
                    return(false);
          }
          if (this.nit == null) {
                    if (valueObject.getNit() != null)
                           return(false);
          } else if (!this.nit.equals(valueObject.getNit())) {
                    return(false);
          }
          if (this.concap_cod == null) {
                    if (valueObject.getConcap_cod() != null)
                           return(false);
          } else if (!this.concap_cod.equals(valueObject.getConcap_cod())) {
                    return(false);
          }
          if (this.contenido == null) {
                    if (valueObject.getContenido() != null)
                           return(false);
          } else if (!this.contenido.equals(valueObject.getContenido())) {
                    return(false);
          }

          return true;
    }

    @Override
    public String toString() {
        return "[" +
        "\"Activo\":\"" + this.activo + "\"," + 
        "\"paciente_cod\":\"" + this.paciente_cod + "\"," + 
        "\"hora\":\"" + this.hora + "\"," + 
        "\"fecha\":\"" + this.fecha + "\"," + 
        "\"sede_codigo\":\"" + this.sede_codigo + "\"," + 
        "\"historia\":\"" + this.historia + "\"," + 
        "\"tipodcto_cod\":\"" + this.tipodcto_cod + "\"," +
        "\"nit\":\"" + this.nit + "\"," +
        "\"concap_cod\":\"" + this.concap_cod + "\"," +
        "\"contenido\":\"" + this.contenido + "\"]"; 
    }

    @Override
    public Object clone() {
        Paciente_Adicionales cloned = new Paciente_Adicionales();

        cloned.setActivo(this.activo); 
        if (this.paciente_cod != null)
             cloned.setPaciente_cod(this.paciente_cod); 
        if (this.hora != null)
             cloned.setHora((Time)this.hora.clone()); 
        if (this.fecha != null)
             cloned.setFecha((java.sql.Date)this.fecha.clone()); 
        if (this.sede_codigo != null)
             cloned.setSede_codigo(this.sede_codigo); 
        if (this.historia != null)
             cloned.setHistoria(this.historia); 
        if (this.tipodcto_cod != null)
             cloned.setTipodcto_cod(this.tipodcto_cod); 
        if (this.nit != null)
             cloned.setNit(this.nit); 
        if (this.concap_cod != null)
             cloned.setConcap_cod(this.concap_cod); 
        if (this.contenido != null)
             cloned.setContenido(this.contenido); 
        return cloned;
    }
    
}
