/*
 * Modelo de tabla LABO_ORD: Representa la tabla LABO_ORD de la base de datos AGILIS, 
 * utilizada para manipular registros almacenados en la tabla o los registros que serán
 * almacenados en ésta
 */
package Model;

import java.io.Serializable;
import java.sql.*;

/**
 *
 * @author ADMINIMAT Creado en: 03/02/2015
 */
public class LABO_ORD implements Cloneable, Serializable {

    /**
     * variables de persistencia. represetnas las columnas de la tabla LABO_ORD.
     */
    private int ID;
    private String NOADMISION;          
    private String NUM_ORDEN;           
    private Timestamp FECHA_RESULTADO;  
    private String TIPO_DOC;            
    private String DOCUMENTO;           // <-- This value needs to be validated
    private String APELLIDO1;           // <-- This value needs to be validated
    private String APELLIDO2;           // <-- This value needs to be validated
    private String NOMBRE1;             // <-- This value needs to be validated
    private String NOMBRE2;             // <-- This value needs to be validated
    private String SEXO;                // <-- Check this field
    private Timestamp FECHANAC;
    private String DIRECCION;           // <-- This value needs to be validated
    private String TELEFONO;            // <-- This value needs to be validated
    private String COD_CIUDAD;          
    private String COD_ZONA;    
    private String CELULAR;             // <-- This value needs to be validated
    private String EMAIL;               
    private String COD_EXAMEN;          
    private String NOM_EXAMEN;
    private int CANTIDAD;               // <-- This value needs to be validated
    private int NUM_PETICION;           
    private String PISO;                
    private int EN_EMBARAZO;
    private String TIPO_USUARIO;
    private String TIPOSER;
    private String COD_MEDICO;
    private String NOM_MEDICO;          // <-- This value needs to be validated    
    private String COD_CLIENTE;         
    private String NOM_CLIENTE;         // <-- This value needs to be validated
    private String COD_CENCOS;
    private String NOM_CENCOS;
    private String COD_SEDE;
    private int ESTADO;

    /**
     * Constructores. Crean las instancias en tiempo de ejecución de la tabla
     * LABO_ORD
     */
    public LABO_ORD() {
    }

    public LABO_ORD(int IDIn) {
        this.ID = IDIn;
    }

    /**
     * Métodos de encapsulamiento. Para acceso a las variables de clase
     */
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

    public Timestamp getFECHA_RESULTADO() {
        return this.FECHA_RESULTADO;
    }

    public void setFECHA_RESULTADO(Timestamp FECHA_RESULTADOIn) {
        this.FECHA_RESULTADO = FECHA_RESULTADOIn;
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

    public String getAPELLIDO1() {
        return this.APELLIDO1;
    }

    public void setAPELLIDO1(String APELLIDO1In) {
        this.APELLIDO1 = APELLIDO1In;
    }

    public String getAPELLIDO2() {
        return this.APELLIDO2;
    }

    public void setAPELLIDO2(String APELLIDO2In) {
        this.APELLIDO2 = APELLIDO2In;
    }

    public String getNOMBRE1() {
        return this.NOMBRE1;
    }

    public void setNOMBRE1(String NOMBRE1In) {
        this.NOMBRE1 = NOMBRE1In;
    }

    public String getNOMBRE2() {
        return this.NOMBRE2;
    }

    public void setNOMBRE2(String NOMBRE2In) {
        this.NOMBRE2 = NOMBRE2In;
    }

    public String getSEXO() {
        return this.SEXO;
    }

    public void setSEXO(String SEXOIn) {
        this.SEXO = SEXOIn;
    }

    public Timestamp getFECHANAC() {
        return this.FECHANAC;
    }

    public void setFECHANAC(Timestamp FECHANACIn) {
        this.FECHANAC = FECHANACIn;
    }

    public String getDIRECCION() {
        return this.DIRECCION;
    }

    public void setDIRECCION(String DIRECCIONIn) {
        this.DIRECCION = DIRECCIONIn;
    }

    public String getTELEFONO() {
        return this.TELEFONO;
    }

    public void setTELEFONO(String TELEFONOIn) {
        this.TELEFONO = TELEFONOIn;
    }

    public String getCOD_CIUDAD() {
        return this.COD_CIUDAD;
    }

    public void setCOD_CIUDAD(String COD_CIUDADIn) {
        this.COD_CIUDAD = COD_CIUDADIn;
    }

    public String getCOD_ZONA() {
        return this.COD_ZONA;
    }

    public void setCOD_ZONA(String COD_ZONAIn) {
        this.COD_ZONA = COD_ZONAIn;
    }

    public String getCELULAR() {
        return this.CELULAR;
    }

    public void setCELULAR(String CELULARIn) {
        this.CELULAR = CELULARIn;
    }

    public String getEMAIL() {
        return this.EMAIL;
    }

    public void setEMAIL(String EMAILIn) {
        this.EMAIL = EMAILIn;
    }

    public String getCOD_EXAMEN() {
        return this.COD_EXAMEN;
    }

    public void setCOD_EXAMEN(String COD_EXAMENIn) {
        this.COD_EXAMEN = COD_EXAMENIn;
    }

    public String getNOM_EXAMEN() {
        return this.NOM_EXAMEN;
    }

    public void setNOM_EXAMEN(String NOM_EXAMENIn) {
        this.NOM_EXAMEN = NOM_EXAMENIn;
    }

    public int getCANTIDAD() {
        return this.CANTIDAD;
    }

    public void setCANTIDAD(int CANTIDADIn) {
        this.CANTIDAD = CANTIDADIn;
    }

    public int getNUM_PETICION() {
        return this.NUM_PETICION;
    }

    public void setNUM_PETICION(int NUM_PETICIONIn) {
        this.NUM_PETICION = NUM_PETICIONIn;
    }

    public String getPISO() {
        return this.PISO;
    }

    public void setPISO(String PISOIn) {
        this.PISO = PISOIn;
    }

    public int getEN_EMBARAZO() {
        return this.EN_EMBARAZO;
    }

    public void setEN_EMBARAZO(int EN_EMBARAZOIn) {
        this.EN_EMBARAZO = EN_EMBARAZOIn;
    }

    public String getTIPO_USUARIO() {
        return this.TIPO_USUARIO;
    }

    public void setTIPO_USUARIO(String TIPO_USUARIOIn) {
        this.TIPO_USUARIO = TIPO_USUARIOIn;
    }

    public String getTIPOSER() {
        return this.TIPOSER;
    }

    public void setTIPOSER(String TIPOSERIn) {
        this.TIPOSER = TIPOSERIn;
    }

    public String getCOD_MEDICO() {
        return this.COD_MEDICO;
    }

    public void setCOD_MEDICO(String COD_MEDICOIn) {
        this.COD_MEDICO = COD_MEDICOIn;
    }

    public String getNOM_MEDICO() {
        return this.NOM_MEDICO;
    }

    public void setNOM_MEDICO(String NOM_MEDICOIn) {
        this.NOM_MEDICO = NOM_MEDICOIn;
    }

    public String getCOD_CLIENTE() {
        return this.COD_CLIENTE;
    }

    public void setCOD_CLIENTE(String COD_CLIENTEIn) {
        this.COD_CLIENTE = COD_CLIENTEIn;
    }

    public String getNOM_CLIENTE() {
        return this.NOM_CLIENTE;
    }

    public void setNOM_CLIENTE(String NOM_CLIENTEIn) {
        this.NOM_CLIENTE = NOM_CLIENTEIn;
    }

    public String getCOD_CENCOS() {
        return this.COD_CENCOS;
    }

    public void setCOD_CENCOS(String COD_CENCOSIn) {
        this.COD_CENCOS = COD_CENCOSIn;
    }

    public String getNOM_CENCOS() {
        return this.NOM_CENCOS;
    }

    public void setNOM_CENCOS(String NOM_CENCOSIn) {
        this.NOM_CENCOS = NOM_CENCOSIn;
    }

    public String getCOD_SEDE() {
        return this.COD_SEDE;
    }

    public void setCOD_SEDE(String COD_SEDEIn) {
        this.COD_SEDE = COD_SEDEIn;
    }

    public int getESTADO() {
        return this.ESTADO;
    }

    public void setESTADO(int ESTADOIn) {
        this.ESTADO = ESTADOIn;
    }

    /**
     * setAll es un método que asigna valores a todos los campos del registro en
     * una única instrucción
     */
    public void setAll(int IDIn, String NOADMISIONIn, String NUM_ORDENIn, Timestamp FECHA_RESULTADOIn, String TIPO_DOCIn, String DOCUMENTOIn, String APELLIDO1In, String APELLIDO2In,
            String NOMBRE1In, String NOMBRE2In, String SEXOIn, Timestamp FECHANACIn, String DIRECCIONIn, String TELEFONOIn, String COD_CIUDADIn, String COD_ZONAIn, String CELULARIn,
            String EMAILIn, String COD_EXAMENIn, String NOM_EXAMENIn, int CANTIDADIn, int NUM_PETICIONIn, String PISOIn, int EN_EMBARAZOIn, String TIPO_USUARIOIn, String TIPOSERIn,
            String COD_MEDICOIn, String NOM_MEDICOIn, String COD_CLIENTEIn, String NOM_CLIENTEIn, String COD_CENCOSIn, String NOM_CENCOSIn, String COD_SEDEIn, int ESTADOIn) {
        this.ID = IDIn;
        this.NOADMISION = NOADMISIONIn;
        this.NUM_ORDEN = NUM_ORDENIn;
        this.FECHA_RESULTADO = FECHA_RESULTADOIn;
        this.TIPO_DOC = TIPO_DOCIn;
        this.DOCUMENTO = DOCUMENTOIn;
        this.APELLIDO1 = APELLIDO1In;
        this.APELLIDO2 = APELLIDO2In;
        this.NOMBRE1 = NOMBRE1In;
        this.NOMBRE2 = NOMBRE2In;
        this.SEXO = SEXOIn;
        this.FECHANAC = FECHANACIn;
        this.DIRECCION = DIRECCIONIn;
        this.TELEFONO = TELEFONOIn;
        this.COD_CIUDAD = COD_CIUDADIn;
        this.COD_ZONA = COD_ZONAIn;
        this.CELULAR = CELULARIn;
        this.EMAIL = EMAILIn;
        this.COD_EXAMEN = COD_EXAMENIn;
        this.NOM_EXAMEN = NOM_EXAMENIn;
        this.CANTIDAD = CANTIDADIn;
        this.NUM_PETICION = NUM_PETICIONIn;
        this.PISO = PISOIn;
        this.EN_EMBARAZO = EN_EMBARAZOIn;
        this.TIPO_USUARIO = TIPO_USUARIOIn;
        this.TIPOSER = TIPOSERIn;
        this.COD_MEDICO = COD_MEDICOIn;
        this.NOM_MEDICO = NOM_MEDICOIn;
        this.COD_CLIENTE = COD_CLIENTEIn;
        this.NOM_CLIENTE = NOM_CLIENTEIn;
        this.COD_CENCOS = COD_CENCOSIn;
        this.NOM_CENCOS = NOM_CENCOSIn;
        this.COD_SEDE = COD_SEDEIn;
        this.ESTADO = ESTADOIn;
    }

    @Override
    public String toString() {
        return "LABO_ORD{" + "ID=" + ID + ", NOADMISION=" + NOADMISION + ", NUM_ORDEN=" + NUM_ORDEN + ", FECHA_RESULTADO=" + FECHA_RESULTADO
                + ", TIPO_DOC=" + TIPO_DOC + ", DOCUMENTO=" + DOCUMENTO + ", APELLIDO1=" + APELLIDO1 + ", APELLIDO2=" + APELLIDO2 + ", NOMBRE1="
                + NOMBRE1 + ", NOMBRE2=" + NOMBRE2 + ", SEXO=" + SEXO + ", FECHANAC=" + FECHANAC + ", DIRECCION=" + DIRECCION + ", TELEFONO="
                + TELEFONO + ", COD_CIUDAD=" + COD_CIUDAD + ", COD_ZONA=" + COD_ZONA + ", CELULAR=" + CELULAR + ", EMAIL=" + EMAIL + ", COD_EXAMEN="
                + COD_EXAMEN + ", NOM_EXAMEN=" + NOM_EXAMEN + ", CANTIDAD=" + CANTIDAD + ", NUM_PETICION=" + NUM_PETICION + ", PISO=" + PISO
                + ", EN_EMBARAZO=" + EN_EMBARAZO + ", TIPO_USUARIO=" + TIPO_USUARIO + ", TIPOSER=" + TIPOSER + ", COD_MEDICO=" + COD_MEDICO
                + ", NOM_MEDICO=" + NOM_MEDICO + ", COD_CLIENTE=" + COD_CLIENTE + ", NOM_CLIENTE=" + NOM_CLIENTE + ", COD_CENCOS=" + COD_CENCOS
                + ", NOM_CENCOS=" + NOM_CENCOS + ", COD_SEDE=" + COD_SEDE + ", ESTADO=" + ESTADO + '}';
    }
    
    // [07/03/2020] Dev. FMBM
    // Se implemanta para crear copias de la orden
    // Problema de que se multiplican los exámenes de la orden en la BD Winsislab 
    // cuando la cantidad es mayor que 1
    
    public LABO_ORD clonar() {
        LABO_ORD clon = new LABO_ORD();
        
        clon.setID(this.ID);
        clon.setNOADMISION(this.NOADMISION);
        clon.setNUM_ORDEN(this.NUM_ORDEN);
        clon.setFECHA_RESULTADO(this.FECHA_RESULTADO);
        clon.setTIPO_DOC(this.TIPO_DOC);
        clon.setDOCUMENTO(this.DOCUMENTO);
        clon.setAPELLIDO1(this.APELLIDO1);
        clon.setAPELLIDO2(this.APELLIDO2);
        clon.setNOMBRE1(this.NOMBRE1);
        clon.setNOMBRE2(this.NOMBRE2);
        clon.setSEXO(this.SEXO);
        clon.setFECHANAC(this.FECHANAC);
        clon.setDIRECCION(this.DIRECCION);
        clon.setTELEFONO(this.TELEFONO);
        clon.setCOD_CIUDAD(this.COD_CIUDAD);
        clon.setCOD_ZONA(this.COD_ZONA);
        clon.setCELULAR(this.CELULAR);
        clon.setEMAIL(this.EMAIL);
        clon.setCOD_EXAMEN(this.COD_EXAMEN);
        clon.setNOM_EXAMEN(this.NOM_EXAMEN);
        clon.setCANTIDAD(this.CANTIDAD);
        clon.setNUM_PETICION(this.NUM_PETICION);
        clon.setPISO(this.PISO);
        clon.setEN_EMBARAZO(this.EN_EMBARAZO);
        clon.setTIPO_USUARIO(this.TIPO_USUARIO);
        clon.setTIPOSER(this.TIPOSER);
        clon.setCOD_MEDICO(this.COD_MEDICO);
        clon.setNOM_MEDICO(this.NOM_MEDICO);
        clon.setCOD_CLIENTE(this.COD_CLIENTE);
        clon.setNOM_CLIENTE(this.NOM_CLIENTE);
        clon.setCOD_CENCOS(this.COD_CENCOS);
        clon.setNOM_CENCOS(this.NOM_CENCOS);
        clon.setCOD_SEDE(this.COD_SEDE);
        clon.setESTADO(this.ESTADO);
        
        return clon;
    }
    
    // [13/07/2020] Dev. FMBM
    // Se implemanta para limpiar los campos de caracteres especiales
    public void cleanFields() {
        this.DOCUMENTO   = this.DOCUMENTO.replace("[^A-Za-z0-9]", "");
        this.APELLIDO1   = this.APELLIDO1.replace("[^A-Za-z0-9]", "");
        this.APELLIDO2   = this.APELLIDO2.replace("[^A-Za-z0-9]", "");
        this.NOMBRE1     = this.NOMBRE1.replace("[^A-Za-z0-9]", "");
        this.NOMBRE2     = this.NOMBRE2.replace("[^A-Za-z0-9]", "");
        this.DIRECCION   = this.DIRECCION.replace("[^A-Za-z0-9]", "");
        this.TELEFONO    = this.TELEFONO.replace("[^A-Za-z0-9]", "");
        this.CELULAR     = this.CELULAR.replace("[^A-Za-z0-9]", "");
        this.NOM_MEDICO  = this.NOM_MEDICO.replace("[^A-Za-z0-9]", "");
        this.NOM_CLIENTE = this.NOM_CLIENTE.replace("[^A-Za-z0-9]", "");
    }
}
