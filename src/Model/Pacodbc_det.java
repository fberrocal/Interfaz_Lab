package Model;

import java.io.*;

public class Pacodbc_det implements Cloneable, Serializable {

    private String cod_odbc;
    private String tipodcto_cod;
    private String nit;
    private String cod_enla1;
    private String examen_cod;
    private String secuencia;
    private String detalle;
    private int cantidad;
    private double precio;
    private double copago;
    private String alterno_exa1;
    private String alterno_exa2;
    private String alterno_exa3;
    private boolean procesado;

    /**
     * Constructors. DaoGen generates two constructors by default.
     */
    public Pacodbc_det() {
    }

    public Pacodbc_det(String cod_odbcIn) {
        this.cod_odbc = cod_odbcIn;
    }

    /**
     * Métodos de persistencia
     */
    public String getCod_odbc() {
        return this.cod_odbc;
    }

    public void setCod_odbc(String cod_odbcIn) {
        this.cod_odbc = cod_odbcIn;
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

    public String getCod_enla1() {
        return this.cod_enla1;
    }

    public void setCod_enla1(String cod_enla1In) {
        this.cod_enla1 = cod_enla1In;
    }

    public String getExamen_cod() {
        return this.examen_cod;
    }

    public void setExamen_cod(String examen_codIn) {
        this.examen_cod = examen_codIn;
    }

    public String getSecuencia() {
        return this.secuencia;
    }

    public void setSecuencia(String secuenciaIn) {
        this.secuencia = secuenciaIn;
    }

    public String getDetalle() {
        return this.detalle;
    }

    public void setDetalle(String detalleIn) {
        this.detalle = detalleIn;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidadIn) {
        this.cantidad = cantidadIn;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precioIn) {
        this.precio = precioIn;
    }

    public double getCopago() {
        return this.copago;
    }

    public void setCopago(double copagoIn) {
        this.copago = copagoIn;
    }

    public String getAlterno_exa1() {
        return this.alterno_exa1;
    }

    public void setAlterno_exa1(String alterno_exa1In) {
        this.alterno_exa1 = alterno_exa1In;
    }

    public String getAlterno_exa2() {
        return this.alterno_exa2;
    }

    public void setAlterno_exa2(String alterno_exa2In) {
        this.alterno_exa2 = alterno_exa2In;
    }

    public String getAlterno_exa3() {
        return this.alterno_exa3;
    }

    public void setAlterno_exa3(String alterno_exa3In) {
        this.alterno_exa3 = alterno_exa3In;
    }

    public boolean getProcesado() {
        return this.procesado;
    }

    public void setProcesado(boolean procesadoIn) {
        this.procesado = procesadoIn;
    }

    /**
     * todos allows to set all persistent variables in one method call.
     */
    public void setAll(String cod_odbcIn,
            String tipodcto_codIn,
            String nitIn,
            String cod_enla1In,
            String examen_codIn,
            String secuenciaIn,
            String detalleIn,
            int cantidadIn,
            double precioIn,
            double copagoIn,
            String alterno_exa1In,
            String alterno_exa2In,
            String alterno_exa3In,
            boolean procesadoIn) {
        this.cod_odbc = cod_odbcIn;
        this.tipodcto_cod = tipodcto_codIn;
        this.nit = nitIn;
        this.cod_enla1 = cod_enla1In;
        this.examen_cod = examen_codIn;
        this.secuencia = secuenciaIn;
        this.detalle = detalleIn;
        this.cantidad = cantidadIn;
        this.precio = precioIn;
        this.copago = copagoIn;
        this.alterno_exa1 = alterno_exa1In;
        this.alterno_exa2 = alterno_exa2In;
        this.alterno_exa3 = alterno_exa3In;
        this.procesado = procesadoIn;
    }

    @Override
    public String toString() {
        return "Pacodbc_det{" + "cod_odbc=" + cod_odbc + ", tipodcto_cod=" + tipodcto_cod + ", nit=" + nit + ", cod_enla1=" + cod_enla1
                + ", examen_cod=" + examen_cod + ", secuencia=" + secuencia + ", detalle=" + detalle + ", cantidad=" + cantidad + ", precio="
                + precio + ", copago=" + copago + ", alterno_exa1=" + alterno_exa1 + ", alterno_exa2=" + alterno_exa2 + ", alterno_exa3="
                + alterno_exa3 + ", procesado=" + procesado + '}';
    }

}
