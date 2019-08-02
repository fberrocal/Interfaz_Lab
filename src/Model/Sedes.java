package Model;

/**
 * Sedes Value Object.
 *
 * @author fberrocalm creado en 13/02/2015
 */
import java.io.*;

public class Sedes implements Cloneable, Serializable {

    /*VARIABLES DE CLASE (CAMPOS DE TABLA QUE SE USAN)*/
    private String sede_codigo;
    private String prefor;
    private long orden;
    private String preo1;
    private long langel_actual;

    /*METODOS CONSTRUCTORES*/
    public Sedes() {
    }

    public Sedes(String sede_codigoIn) {
        this.sede_codigo = sede_codigoIn;
    }

    /*METODOS DE ENCAPSULAMIENTO*/
    public String getSede_codigo() {
        return this.sede_codigo;
    }

    public void setSede_codigo(String sede_codigoIn) {
        this.sede_codigo = sede_codigoIn;
    }

    public String getPrefor() {
        return this.prefor;
    }

    public void setPrefor(String preforIn) {
        this.prefor = preforIn;
    }

    public long getOrden() {
        return this.orden;
    }

    public void setOrden(long ordenIn) {
        this.orden = ordenIn;
    }

    public String getPreo1() {
        return this.preo1;
    }

    public void setPreo1(String preo1In) {
        this.preo1 = preo1In;
    }

    public long getLangel_actual() {
        return this.langel_actual;
    }

    public void setLangel_actual(long langel_actualIn) {
        this.langel_actual = langel_actualIn;
    }

    /*METODO DE ASIGNACION DE DATOS A TODAS LAS VARIABLES*/
    public void setAll(String sede_codigoIn,
            String preforIn,
            long ordenIn,
            String preo1In,
            long langel_actualIn) {
        this.sede_codigo = sede_codigoIn;
        this.prefor = preforIn;
        this.orden = ordenIn;
        this.preo1 = preo1In;
        this.langel_actual = langel_actualIn;
    }

    @Override
    public String toString() {
        return "Sedes{" + "sede_codigo=" + sede_codigo + ", prefor=" + prefor + ", orden=" + orden + ", preo1=" + preo1 + ", langel_actual=" + langel_actual + '}';
    }

}
