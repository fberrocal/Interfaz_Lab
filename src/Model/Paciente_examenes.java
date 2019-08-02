package Model;

/**
 *
 * @author fberrocalm creado en: 16/02/2015
 */
import java.io.*;
import java.sql.*;

public class Paciente_examenes implements Cloneable, Serializable {

    private String paciente_cod;
    private Time hora;
    private java.util.Date fecha;
    private String sede_codigo;
    private String historia;
    private String tipodcto_cod;
    private String nit;
    private String examen;
    private int reg_exa;
    private boolean activo;
    private double valor;
    private String alterno;
    private java.util.Date entrega;
    private String autoriza;
    private String clase;
    private String plan;
    private boolean si_recsuero;
    private boolean si_impcodbar;
    private boolean si_band01;
    private boolean si_band02;
    private boolean si_band03;
    private boolean si_band04;
    private boolean si_band05;
    private boolean si_band06;
    private boolean si_band07;
    private boolean si_band08;
    private int copias;
    private int activo_exa;
    private boolean contestado;
    private boolean validado;
    private boolean modificado;
    private boolean impreso;
    private boolean entregado;
    private java.util.Date fec_res;
    private java.util.Date fec_val;
    private java.util.Date fec_imp;
    private java.util.Date fec_imp1;
    private java.util.Date fec_mod;
    private java.util.Date fec_ent;
    private Time hora_resp;
    private Time hora_val;
    private Time hora_imp;
    private Time hora_imp1;
    private Time hora_mod;
    private Time hora_ent;
    private String parte_anal;
    private String respondido_por;
    private String validado_por;
    private int repitio;
    private String sedepro;
    private String usr_copio;
    private String archivo;
    private String num_cita;
    private boolean exig_sda_firma;
    private String usr_sda_firma;
    private java.util.Date fecha_prometida;
    private int ultimo_previo;
    private boolean val_parcial;
    private java.util.Date fec_val_parcial;
    private Time hora_val_parcial;
    private String usu_val_parcial;
    private String nro_muestra1;
    private String nro_muestra2;
    private String codigo_barras;
    private java.util.Date fecha_recepcion;

    /**
     * Constructors. DaoGen generates two constructors by default.
     */
    public Paciente_examenes() {
    }

    public Paciente_examenes(String paciente_codIn) {
        this.paciente_cod = paciente_codIn;
    }

    /**
     * Get- and Set-methods for persistent variables. The default
     */
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

    public boolean getActivo() {
        return this.activo;
    }

    public void setActivo(boolean activoIn) {
        this.activo = activoIn;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valorIn) {
        this.valor = valorIn;
    }

    public String getAlterno() {
        return this.alterno;
    }

    public void setAlterno(String alternoIn) {
        this.alterno = alternoIn;
    }

    public java.util.Date getEntrega() {
        return this.entrega;
    }

    public void setEntrega(java.util.Date entregaIn) {
        this.entrega = entregaIn;
    }

    public String getAutoriza() {
        return this.autoriza;
    }

    public void setAutoriza(String autorizaIn) {
        this.autoriza = autorizaIn;
    }

    public String getClase() {
        return this.clase;
    }

    public void setClase(String claseIn) {
        this.clase = claseIn;
    }

    public String getPlan() {
        return this.plan;
    }

    public void setPlan(String planIn) {
        this.plan = planIn;
    }

    public boolean getSi_recsuero() {
        return this.si_recsuero;
    }

    public void setSi_recsuero(boolean si_recsueroIn) {
        this.si_recsuero = si_recsueroIn;
    }

    public boolean getSi_impcodbar() {
        return this.si_impcodbar;
    }

    public void setSi_impcodbar(boolean si_impcodbarIn) {
        this.si_impcodbar = si_impcodbarIn;
    }

    public boolean getSi_band01() {
        return this.si_band01;
    }

    public void setSi_band01(boolean si_band01In) {
        this.si_band01 = si_band01In;
    }

    public boolean getSi_band02() {
        return this.si_band02;
    }

    public void setSi_band02(boolean si_band02In) {
        this.si_band02 = si_band02In;
    }

    public boolean getSi_band03() {
        return this.si_band03;
    }

    public void setSi_band03(boolean si_band03In) {
        this.si_band03 = si_band03In;
    }

    public boolean getSi_band04() {
        return this.si_band04;
    }

    public void setSi_band04(boolean si_band04In) {
        this.si_band04 = si_band04In;
    }

    public boolean getSi_band05() {
        return this.si_band05;
    }

    public void setSi_band05(boolean si_band05In) {
        this.si_band05 = si_band05In;
    }

    public boolean getSi_band06() {
        return this.si_band06;
    }

    public void setSi_band06(boolean si_band06In) {
        this.si_band06 = si_band06In;
    }

    public boolean getSi_band07() {
        return this.si_band07;
    }

    public void setSi_band07(boolean si_band07In) {
        this.si_band07 = si_band07In;
    }

    public boolean getSi_band08() {
        return this.si_band08;
    }

    public void setSi_band08(boolean si_band08In) {
        this.si_band08 = si_band08In;
    }

    public int getCopias() {
        return this.copias;
    }

    public void setCopias(int copiasIn) {
        this.copias = copiasIn;
    }

    public int getActivo_exa() {
        return this.activo_exa;
    }

    public void setActivo_exa(int activo_exaIn) {
        this.activo_exa = activo_exaIn;
    }

    public boolean getContestado() {
        return this.contestado;
    }

    public void setContestado(boolean contestadoIn) {
        this.contestado = contestadoIn;
    }

    public boolean getValidado() {
        return this.validado;
    }

    public void setValidado(boolean validadoIn) {
        this.validado = validadoIn;
    }

    public boolean getModificado() {
        return this.modificado;
    }

    public void setModificado(boolean modificadoIn) {
        this.modificado = modificadoIn;
    }

    public boolean getImpreso() {
        return this.impreso;
    }

    public void setImpreso(boolean impresoIn) {
        this.impreso = impresoIn;
    }

    public boolean getEntregado() {
        return this.entregado;
    }

    public void setEntregado(boolean entregadoIn) {
        this.entregado = entregadoIn;
    }

    public java.util.Date getFec_res() {
        return this.fec_res;
    }

    public void setFec_res(java.util.Date fec_resIn) {
        this.fec_res = fec_resIn;
    }

    public java.util.Date getFec_val() {
        return this.fec_val;
    }

    public void setFec_val(java.util.Date fec_valIn) {
        this.fec_val = fec_valIn;
    }

    public java.util.Date getFec_imp() {
        return this.fec_imp;
    }

    public void setFec_imp(java.util.Date fec_impIn) {
        this.fec_imp = fec_impIn;
    }

    public java.util.Date getFec_imp1() {
        return this.fec_imp1;
    }

    public void setFec_imp1(java.util.Date fec_imp1In) {
        this.fec_imp1 = fec_imp1In;
    }

    public java.util.Date getFec_mod() {
        return this.fec_mod;
    }

    public void setFec_mod(java.util.Date fec_modIn) {
        this.fec_mod = fec_modIn;
    }

    public java.util.Date getFec_ent() {
        return this.fec_ent;
    }

    public void setFec_ent(java.util.Date fec_entIn) {
        this.fec_ent = fec_entIn;
    }

    public Time getHora_resp() {
        return this.hora_resp;
    }

    public void setHora_resp(Time hora_respIn) {
        this.hora_resp = hora_respIn;
    }

    public Time getHora_val() {
        return this.hora_val;
    }

    public void setHora_val(Time hora_valIn) {
        this.hora_val = hora_valIn;
    }

    public Time getHora_imp() {
        return this.hora_imp;
    }

    public void setHora_imp(Time hora_impIn) {
        this.hora_imp = hora_impIn;
    }

    public Time getHora_imp1() {
        return this.hora_imp1;
    }

    public void setHora_imp1(Time hora_imp1In) {
        this.hora_imp1 = hora_imp1In;
    }

    public Time getHora_mod() {
        return this.hora_mod;
    }

    public void setHora_mod(Time hora_modIn) {
        this.hora_mod = hora_modIn;
    }

    public Time getHora_ent() {
        return this.hora_ent;
    }

    public void setHora_ent(Time hora_entIn) {
        this.hora_ent = hora_entIn;
    }

    public String getParte_anal() {
        return this.parte_anal;
    }

    public void setParte_anal(String parte_analIn) {
        this.parte_anal = parte_analIn;
    }

    public String getRespondido_por() {
        return this.respondido_por;
    }

    public void setRespondido_por(String respondido_porIn) {
        this.respondido_por = respondido_porIn;
    }

    public String getValidado_por() {
        return this.validado_por;
    }

    public void setValidado_por(String validado_porIn) {
        this.validado_por = validado_porIn;
    }

    public int getRepitio() {
        return this.repitio;
    }

    public void setRepitio(int repitioIn) {
        this.repitio = repitioIn;
    }

    public String getSedepro() {
        return this.sedepro;
    }

    public void setSedepro(String sedeproIn) {
        this.sedepro = sedeproIn;
    }

    public String getUsr_copio() {
        return this.usr_copio;
    }

    public void setUsr_copio(String usr_copioIn) {
        this.usr_copio = usr_copioIn;
    }

    public String getArchivo() {
        return this.archivo;
    }

    public void setArchivo(String archivoIn) {
        this.archivo = archivoIn;
    }

    public String getNum_cita() {
        return this.num_cita;
    }

    public void setNum_cita(String num_citaIn) {
        this.num_cita = num_citaIn;
    }

    public boolean getExig_sda_firma() {
        return this.exig_sda_firma;
    }

    public void setExig_sda_firma(boolean exig_sda_firmaIn) {
        this.exig_sda_firma = exig_sda_firmaIn;
    }

    public String getUsr_sda_firma() {
        return this.usr_sda_firma;
    }

    public void setUsr_sda_firma(String usr_sda_firmaIn) {
        this.usr_sda_firma = usr_sda_firmaIn;
    }

    public java.util.Date getFecha_prometida() {
        return this.fecha_prometida;
    }

    public void setFecha_prometida(java.util.Date fecha_prometidaIn) {
        this.fecha_prometida = fecha_prometidaIn;
    }

    public int getUltimo_previo() {
        return this.ultimo_previo;
    }

    public void setUltimo_previo(int ultimo_previoIn) {
        this.ultimo_previo = ultimo_previoIn;
    }

    public boolean getVal_parcial() {
        return this.val_parcial;
    }

    public void setVal_parcial(boolean val_parcialIn) {
        this.val_parcial = val_parcialIn;
    }

    public java.util.Date getFec_val_parcial() {
        return this.fec_val_parcial;
    }

    public void setFec_val_parcial(java.util.Date fec_val_parcialIn) {
        this.fec_val_parcial = fec_val_parcialIn;
    }

    public Time getHora_val_parcial() {
        return this.hora_val_parcial;
    }

    public void setHora_val_parcial(Time hora_val_parcialIn) {
        this.hora_val_parcial = hora_val_parcialIn;
    }

    public String getUsu_val_parcial() {
        return this.usu_val_parcial;
    }

    public void setUsu_val_parcial(String usu_val_parcialIn) {
        this.usu_val_parcial = usu_val_parcialIn;
    }

    public String getNro_muestra1() {
        return this.nro_muestra1;
    }

    public void setNro_muestra1(String nro_muestra1In) {
        this.nro_muestra1 = nro_muestra1In;
    }

    public String getNro_muestra2() {
        return this.nro_muestra2;
    }

    public void setNro_muestra2(String nro_muestra2In) {
        this.nro_muestra2 = nro_muestra2In;
    }

    public String getCodigo_barras() {
        return this.codigo_barras;
    }

    public void setCodigo_barras(String codigo_barrasIn) {
        this.codigo_barras = codigo_barrasIn;
    }

    public java.util.Date getFecha_recepcion() {
        return this.fecha_recepcion;
    }

    public void setFecha_recepcion(java.util.Date fecha_recepcionIn) {
        this.fecha_recepcion = fecha_recepcionIn;
    }

    public void setAll(String paciente_cod, Time hora, java.util.Date fecha, String sede_codigo, String historia, String tipodcto_cod, String nit, String examen, int reg_exa, boolean activo, double valor, String alterno, java.util.Date entrega, String autoriza, String clase, String plan, boolean si_recsuero, boolean si_impcodbar, boolean si_band01, boolean si_band02, boolean si_band03, boolean si_band04, boolean si_band05, boolean si_band06, boolean si_band07, boolean si_band08, int copias, int activo_exa, boolean contestado, boolean validado, boolean modificado, boolean impreso, boolean entregado, java.util.Date fec_res, java.util.Date fec_val, java.util.Date fec_imp, java.util.Date fec_imp1, java.util.Date fec_mod, java.util.Date fec_ent, Time hora_resp, Time hora_val, Time hora_imp, Time hora_imp1, Time hora_mod, Time hora_ent, String parte_anal, String respondido_por, String validado_por, int repitio, String sedepro, String usr_copio, String archivo, String num_cita, boolean exig_sda_firma, String usr_sda_firma, java.util.Date fecha_prometida, int ultimo_previo, boolean val_parcial, java.util.Date fec_val_parcial, Time hora_val_parcial, String usu_val_parcial, String nro_muestra1, String nro_muestra2, String codigo_barras, java.util.Date fecha_recepcion) {
        this.paciente_cod = paciente_cod;
        this.hora = hora;
        this.fecha = fecha;
        this.sede_codigo = sede_codigo;
        this.historia = historia;
        this.tipodcto_cod = tipodcto_cod;
        this.nit = nit;
        this.examen = examen;
        this.reg_exa = reg_exa;
        this.activo = activo;
        this.valor = valor;
        this.alterno = alterno;
        this.entrega = entrega;
        this.autoriza = autoriza;
        this.clase = clase;
        this.plan = plan;
        this.si_recsuero = si_recsuero;
        this.si_impcodbar = si_impcodbar;
        this.si_band01 = si_band01;
        this.si_band02 = si_band02;
        this.si_band03 = si_band03;
        this.si_band04 = si_band04;
        this.si_band05 = si_band05;
        this.si_band06 = si_band06;
        this.si_band07 = si_band07;
        this.si_band08 = si_band08;
        this.copias = copias;
        this.activo_exa = activo_exa;
        this.contestado = contestado;
        this.validado = validado;
        this.modificado = modificado;
        this.impreso = impreso;
        this.entregado = entregado;
        this.fec_res = fec_res;
        this.fec_val = fec_val;
        this.fec_imp = fec_imp;
        this.fec_imp1 = fec_imp1;
        this.fec_mod = fec_mod;
        this.fec_ent = fec_ent;
        this.hora_resp = hora_resp;
        this.hora_val = hora_val;
        this.hora_imp = hora_imp;
        this.hora_imp1 = hora_imp1;
        this.hora_mod = hora_mod;
        this.hora_ent = hora_ent;
        this.parte_anal = parte_anal;
        this.respondido_por = respondido_por;
        this.validado_por = validado_por;
        this.repitio = repitio;
        this.sedepro = sedepro;
        this.usr_copio = usr_copio;
        this.archivo = archivo;
        this.num_cita = num_cita;
        this.exig_sda_firma = exig_sda_firma;
        this.usr_sda_firma = usr_sda_firma;
        this.fecha_prometida = fecha_prometida;
        this.ultimo_previo = ultimo_previo;
        this.val_parcial = val_parcial;
        this.fec_val_parcial = fec_val_parcial;
        this.hora_val_parcial = hora_val_parcial;
        this.usu_val_parcial = usu_val_parcial;
        this.nro_muestra1 = nro_muestra1;
        this.nro_muestra2 = nro_muestra2;
        this.codigo_barras = codigo_barras;
        this.fecha_recepcion = fecha_recepcion;
    }

    /**
     * setAll allows to set all persistent variables in one method call.
     */
    @Override
    public String toString() {
        return "Paciente_examenes{" + "paciente_cod=" + paciente_cod + ", hora=" + hora + ", fecha=" + fecha + ", sede_codigo=" + sede_codigo
                + ", historia=" + historia + ", tipodcto_cod=" + tipodcto_cod + ", nit=" + nit + ", examen=" + examen + ", reg_exa=" + reg_exa
                + ", activo=" + activo + ", valor=" + valor + ", alterno=" + alterno + ", entrega=" + entrega + ", autoriza=" + autoriza
                + ", clase=" + clase + ", plan=" + plan + ", si_recsuero=" + si_recsuero + ", si_impcodbar=" + si_impcodbar + ", si_band01="
                + si_band01 + ", si_band02=" + si_band02 + ", si_band03=" + si_band03 + ", si_band04=" + si_band04 + ", si_band05=" + si_band05
                + ", si_band06=" + si_band06 + ", si_band07=" + si_band07 + ", si_band08=" + si_band08 + ", copias=" + copias + ", activo_exa="
                + activo_exa + ", contestado=" + contestado + ", validado=" + validado + ", modificado=" + modificado + ", impreso=" + impreso
                + ", entregado=" + entregado + ", fec_res=" + fec_res + ", fec_val=" + fec_val + ", fec_imp=" + fec_imp + ", fec_imp1=" + fec_imp1
                + ", fec_mod=" + fec_mod + ", fec_ent=" + fec_ent + ", hora_resp=" + hora_resp + ", hora_val=" + hora_val + ", hora_imp="
                + hora_imp + ", hora_imp1=" + hora_imp1 + ", hora_mod=" + hora_mod + ", hora_ent=" + hora_ent + ", parte_anal=" + parte_anal
                + ", respondido_por=" + respondido_por + ", validado_por=" + validado_por + ", repitio=" + repitio + ", sedepro=" + sedepro
                + ", usr_copio=" + usr_copio + ", archivo=" + archivo + ", num_cita=" + num_cita + ", exig_sda_firma=" + exig_sda_firma
                + ", usr_sda_firma=" + usr_sda_firma
                + ", fecha_prometida=" + fecha_prometida + ", ultimo_previo=" + ultimo_previo + ", val_parcial=" + val_parcial
                + ", fec_val_parcial=" + fec_val_parcial + ", hora_val_parcial=" + hora_val_parcial + ", usu_val_parcial=" + usu_val_parcial
                + ", nro_muestra1=" + nro_muestra1 + ", nro_muestra2=" + nro_muestra2 + ", codigo_barras=" + codigo_barras + ", fecha_recepcion="
                + fecha_recepcion + '}';
    }
}
