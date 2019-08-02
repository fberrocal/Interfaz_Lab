package Model;

/**
 * @author fberrocalm Creado en: 20/02/2015
 */
import java.io.*;
import java.sql.*;

public class Resul_lab implements Cloneable, Serializable {

    private String paciente_cod;
    private Time hora;
    private java.sql.Date fecha;
    private String sede_codigo;
    private String historia;
    private String tipodcto_cod;
    private String nit;
    private String pac_examen;
    private int reg_exa;
    private int posiexa;
    private String analito_cod;
    private String examen_cod;
    private double secuiencia;
    private int codigo;
    private String resultado;
    private String analito;
    private String minimo;
    private String intermedio;
    private String maximo;
    private String unidades;
    private String equipo;
    private String reactivo;
    private String tablav;
    private String tablaa;
    private String tabla1;
    private String tabla2;
    private boolean activo;

    public Resul_lab() {
    }

    public Resul_lab(String paciente_codIn) {
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

    public java.sql.Date getFecha() {
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

    public String getPac_examen() {
        return this.pac_examen;
    }

    public void setPac_examen(String pac_examenIn) {
        this.pac_examen = pac_examenIn;
    }

    public int getReg_exa() {
        return this.reg_exa;
    }

    public void setReg_exa(int reg_exaIn) {
        this.reg_exa = reg_exaIn;
    }

    public int getPosiexa() {
        return this.posiexa;
    }

    public void setPosiexa(int posiexaIn) {
        this.posiexa = posiexaIn;
    }

    public String getAnalito_cod() {
        return this.analito_cod;
    }

    public void setAnalito_cod(String analito_codIn) {
        this.analito_cod = analito_codIn;
    }

    public String getExamen_cod() {
        return this.examen_cod;
    }

    public void setExamen_cod(String examen_codIn) {
        this.examen_cod = examen_codIn;
    }

    public double getSecuiencia() {
        return this.secuiencia;
    }

    public void setSecuiencia(double secuienciaIn) {
        this.secuiencia = secuienciaIn;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigoIn) {
        this.codigo = codigoIn;
    }

    public String getResultado() {
        return this.resultado;
    }

    public void setResultado(String resultadoIn) {
        this.resultado = resultadoIn;
    }

    public String getAnalito() {
        return this.analito;
    }

    public void setAnalito(String analitoIn) {
        this.analito = analitoIn;
    }

    public String getMinimo() {
        return this.minimo;
    }

    public void setMinimo(String minimoIn) {
        this.minimo = minimoIn;
    }

    public String getIntermedio() {
        return this.intermedio;
    }

    public void setIntermedio(String intermedioIn) {
        this.intermedio = intermedioIn;
    }

    public String getMaximo() {
        return this.maximo;
    }

    public void setMaximo(String maximoIn) {
        this.maximo = maximoIn;
    }

    public String getUnidades() {
        return this.unidades;
    }

    public void setUnidades(String unidadesIn) {
        this.unidades = unidadesIn;
    }

    public String getEquipo() {
        return this.equipo;
    }

    public void setEquipo(String equipoIn) {
        this.equipo = equipoIn;
    }

    public String getReactivo() {
        return this.reactivo;
    }

    public void setReactivo(String reactivoIn) {
        this.reactivo = reactivoIn;
    }

    public String getTablav() {
        return this.tablav;
    }

    public void setTablav(String tablavIn) {
        this.tablav = tablavIn;
    }

    public String getTablaa() {
        return this.tablaa;
    }

    public void setTablaa(String tablaaIn) {
        this.tablaa = tablaaIn;
    }

    public String getTabla1() {
        return this.tabla1;
    }

    public void setTabla1(String tabla1In) {
        this.tabla1 = tabla1In;
    }

    public String getTabla2() {
        return this.tabla2;
    }

    public void setTabla2(String tabla2In) {
        this.tabla2 = tabla2In;
    }

    public boolean getActivo() {
        return this.activo;
    }

    public void setActivo(boolean activoIn) {
        this.activo = activoIn;
    }

    public void setAll(String paciente_codIn,
            Time horaIn,
            java.sql.Date fechaIn,
            String sede_codigoIn,
            String historiaIn,
            String tipodcto_codIn,
            String nitIn,
            String pac_examenIn,
            int reg_exaIn,
            int posiexaIn,
            String analito_codIn,
            String examen_codIn,
            double secuienciaIn,
            int codigoIn,
            String resultadoIn,
            String analitoIn,
            String minimoIn,
            String intermedioIn,
            String maximoIn,
            String unidadesIn,
            String equipoIn,
            String reactivoIn,
            String tablavIn,
            String tablaaIn,
            String tabla1In,
            String tabla2In,
            boolean activoIn) {
        this.paciente_cod = paciente_codIn;
        this.hora = horaIn;
        this.fecha = fechaIn;
        this.sede_codigo = sede_codigoIn;
        this.historia = historiaIn;
        this.tipodcto_cod = tipodcto_codIn;
        this.nit = nitIn;
        this.pac_examen = pac_examenIn;
        this.reg_exa = reg_exaIn;
        this.posiexa = posiexaIn;
        this.analito_cod = analito_codIn;
        this.examen_cod = examen_codIn;
        this.secuiencia = secuienciaIn;
        this.codigo = codigoIn;
        this.resultado = resultadoIn;
        this.analito = analitoIn;
        this.minimo = minimoIn;
        this.intermedio = intermedioIn;
        this.maximo = maximoIn;
        this.unidades = unidadesIn;
        this.equipo = equipoIn;
        this.reactivo = reactivoIn;
        this.tablav = tablavIn;
        this.tablaa = tablaaIn;
        this.tabla1 = tabla1In;
        this.tabla2 = tabla2In;
        this.activo = activoIn;
    }

    @Override
    public String toString() {
        return "Resul_lab{" + "paciente_cod=" + paciente_cod + ", hora=" + hora + ", fecha=" + fecha + ", sede_codigo=" + sede_codigo
                + ", historia=" + historia + ", tipodcto_cod=" + tipodcto_cod + ", nit=" + nit + ", pac_examen=" + pac_examen + ", reg_exa="
                + reg_exa + ", posiexa=" + posiexa + ", analito_cod=" + analito_cod + ", examen_cod=" + examen_cod + ", secuiencia=" + secuiencia
                + ", codigo=" + codigo + ", resultado=" + resultado + ", analito=" + analito + ", minimo=" + minimo + ", intermedio=" + intermedio
                + ", maximo=" + maximo + ", unidades=" + unidades + ", equipo=" + equipo + ", reactivo=" + reactivo + ", tablav=" + tablav
                + ", tablaa=" + tablaa + ", tabla1=" + tabla1 + ", tabla2=" + tabla2 + ", activo=" + activo + '}';
    }

}
