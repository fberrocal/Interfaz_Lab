package Model;

import java.io.*;

/**
 *
 * @author fberrocalm 17/04/2015 14:03 Agilis S.A.S.
 */
public class Centro_costo implements Cloneable, Serializable {

    /**
     * Persistent Instance variables. This data is directly mapped to the
     * columns of database table.
     */
    private String ccosto_cod;
    private String ccosto_desc;
    private boolean ccosto_estado;
    private String cod_ambito;

    /**
     * Constructors. DaoGen generates two constructors by default. The first one
     * takes no arguments and provides the most simple way to create object
     * instance. The another one takes one argument, which is the primary key of
     * the corresponding table.
     */
    public Centro_costo() {
    }

    public Centro_costo(String ccosto_codIn) {
        this.ccosto_cod = ccosto_codIn;
    }

    /**
     * Get- and Set-methods for persistent variables. The default behaviour does
     * not make any checks against malformed data, so these might require some
     * manual additions.
     *
     * @return
     */
    public String getCcosto_cod() {
        return this.ccosto_cod;
    }

    public void setCcosto_cod(String ccosto_codIn) {
        this.ccosto_cod = ccosto_codIn;
    }

    public String getCcosto_desc() {
        return this.ccosto_desc;
    }

    public void setCcosto_desc(String ccosto_descIn) {
        this.ccosto_desc = ccosto_descIn;
    }

    public boolean getCcosto_estado() {
        return this.ccosto_estado;
    }

    public void setCcosto_estado(boolean ccosto_estadoIn) {
        this.ccosto_estado = ccosto_estadoIn;
    }

    public String getCod_ambito() {
        return this.cod_ambito;
    }

    public void setCod_ambito(String cod_ambitoIn) {
        this.cod_ambito = cod_ambitoIn;
    }

    /**
     * setAll allows to set all persistent variables in one method call. This is
     * useful, when all data is available and it is needed to set the initial
     * state of this object. Note that this method will directly modify instance
     * variales, without going trough the individual set-methods.
     *
     * @param ccosto_codIn
     * @param ccosto_descIn
     * @param ccosto_estadoIn
     * @param cod_ambitoIn
     */
    public void setAll(String ccosto_codIn, String ccosto_descIn, boolean ccosto_estadoIn, String cod_ambitoIn) {
        this.ccosto_cod = ccosto_codIn;
        this.ccosto_desc = ccosto_descIn;
        this.ccosto_estado = ccosto_estadoIn;
        this.cod_ambito = cod_ambitoIn;
    }

    @Override
    public String toString() {
        return "Centro_costo{" + "ccosto_cod=" + ccosto_cod + ", ccosto_desc=" + ccosto_desc + ", ccosto_estado=" + ccosto_estado + ", cod_ambito=" + cod_ambito + '}';
    }

}
