package Model;

/**
 * LABO_RES Value Object.
 *
 * @author fberrocalm Creado en: 19/02/2015
 */
import java.io.*;
import java.sql.*;

public class LABO_RES implements Cloneable, Serializable {

    private int ID;
    private String NOADMISION;
    private String NUM_ORDEN;
    private String TIPO_DOC;
    private String DOCUMENTO;
    private String COD_EXAMEN;
    private int NUM_PETICION;
    private Timestamp FECHA_RESULTADO;
    private String COD_ANALITO;
    private String NOM_ANALITO;
    private String RESULTADO;
    private String VR_MINIMO;
    private String VR_MAXIMO;
    private String UNIDADES;
    private String USU_VALIDA;
    private int ESTADO;

    public LABO_RES() {
    }

    public LABO_RES(int IDIn) {
        this.ID = IDIn;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int IDIn) {
        this.ID = IDIn;
    }

    public String getNOADMISION() {
        return this.NOADMISION;
    }

    public void setNOADMISION(String NOADMISIONIn) {
        this.NOADMISION = NOADMISIONIn;
    }

    public String getNUM_ORDEN() {
        return this.NUM_ORDEN;
    }

    public void setNUM_ORDEN(String NUM_ORDENIn) {
        this.NUM_ORDEN = NUM_ORDENIn;
    }

    public String getTIPO_DOC() {
        return this.TIPO_DOC;
    }

    public void setTIPO_DOC(String TIPO_DOCIn) {
        this.TIPO_DOC = TIPO_DOCIn;
    }

    public String getDOCUMENTO() {
        return this.DOCUMENTO;
    }

    public void setDOCUMENTO(String DOCUMENTOIn) {
        this.DOCUMENTO = DOCUMENTOIn;
    }

    public String getCOD_EXAMEN() {
        return this.COD_EXAMEN;
    }

    public void setCOD_EXAMEN(String COD_EXAMENIn) {
        this.COD_EXAMEN = COD_EXAMENIn;
    }

    public int getNUM_PETICION() {
        return this.NUM_PETICION;
    }

    public void setNUM_PETICION(int NUM_PETICIONIn) {
        this.NUM_PETICION = NUM_PETICIONIn;
    }

    public Timestamp getFECHA_RESULTADO() {
        return this.FECHA_RESULTADO;
    }

    public void setFECHA_RESULTADO(Timestamp FECHA_RESULTADOIn) {
        this.FECHA_RESULTADO = FECHA_RESULTADOIn;
    }

    public String getCOD_ANALITO() {
        return this.COD_ANALITO;
    }

    public void setCOD_ANALITO(String COD_ANALITOIn) {
        this.COD_ANALITO = COD_ANALITOIn;
    }

    public String getNOM_ANALITO() {
        return this.NOM_ANALITO;
    }

    public void setNOM_ANALITO(String NOM_ANALITOIn) {
        this.NOM_ANALITO = NOM_ANALITOIn;
    }

    public String getRESULTADO() {
        return this.RESULTADO;
    }

    public void setRESULTADO(String RESULTADOIn) {
        this.RESULTADO = RESULTADOIn;
    }

    public String getVR_MINIMO() {
        return this.VR_MINIMO;
    }

    public void setVR_MINIMO(String VR_MINIMOIn) {
        this.VR_MINIMO = VR_MINIMOIn;
    }

    public String getVR_MAXIMO() {
        return this.VR_MAXIMO;
    }

    public void setVR_MAXIMO(String VR_MAXIMOIn) {
        this.VR_MAXIMO = VR_MAXIMOIn;
    }

    public String getUNIDADES() {
        return this.UNIDADES;
    }

    public void setUNIDADES(String UNIDADESIn) {
        this.UNIDADES = UNIDADESIn;
    }

    public String getUSU_VALIDA() {
        return this.USU_VALIDA;
    }

    public void setUSU_VALIDA(String USU_VALIDAIn) {
        this.USU_VALIDA = USU_VALIDAIn;
    }

    public int getESTADO() {
        return this.ESTADO;
    }

    public void setESTADO(int ESTADOIn) {
        this.ESTADO = ESTADOIn;
    }

    public void setAll(int IDIn, String NOADMISIONIn, String NUM_ORDENIn, String TIPO_DOCIn, String DOCUMENTOIn, String COD_EXAMENIn, int NUM_PETICIONIn,
            Timestamp FECHA_RESULTADOIn, String COD_ANALITOIn, String NOM_ANALITOIn, String RESULTADOIn, String VR_MINIMOIn, String VR_MAXIMOIn,
            String UNIDADESIn, String USU_VALIDAIn, int ESTADOIn) {
        this.ID = IDIn;
        this.NOADMISION = NOADMISIONIn;
        this.NUM_ORDEN = NUM_ORDENIn;
        this.TIPO_DOC = TIPO_DOCIn;
        this.DOCUMENTO = DOCUMENTOIn;
        this.COD_EXAMEN = COD_EXAMENIn;
        this.NUM_PETICION = NUM_PETICIONIn;
        this.FECHA_RESULTADO = FECHA_RESULTADOIn;
        this.COD_ANALITO = COD_ANALITOIn;
        this.NOM_ANALITO = NOM_ANALITOIn;
        this.RESULTADO = RESULTADOIn;
        this.VR_MINIMO = VR_MINIMOIn;
        this.VR_MAXIMO = VR_MAXIMOIn;
        this.UNIDADES = UNIDADESIn;
        this.USU_VALIDA = USU_VALIDAIn;
        this.ESTADO = ESTADOIn;
    }

    @Override
    public String toString() {
        return "LABO_RES{" + "ID=" + ID + ", NOADMISION=" + NOADMISION + ", NUM_ORDEN=" + NUM_ORDEN + ", TIPO_DOC=" + TIPO_DOC + ", DOCUMENTO="
                + DOCUMENTO + ", COD_EXAMEN=" + COD_EXAMEN + ", NUM_PETICION=" + NUM_PETICION + ", FECHA_RESULTADO=" + FECHA_RESULTADO
                + ", COD_ANALITO=" + COD_ANALITO + ", NOM_ANALITO=" + NOM_ANALITO + ", RESULTADO=" + RESULTADO + ", VR_MINIMO=" + VR_MINIMO
                + ", VR_MAXIMO=" + VR_MAXIMO + ", UNIDADES=" + UNIDADES + ", USU_VALIDA=" + USU_VALIDA + ", ESTADO=" + ESTADO + '}';
    }

}
