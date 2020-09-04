/*
 * HILO DE PROCESO QUE ENVIA DATOS DE AGILIS A WINSISLAB
 */
package Controler;

import Herramientas.DatosConexion;
import Herramientas.PropertiesIO;
import Herramientas.ReproductorAlarma;
import Model.Centro_costo;
import Model.Ciudades;
import Model.Eventos_paciente_exam;
import Model.LABO_ORD;
import Model.Paciente;
import Model.Paciente_Adicionales;
import Model.Paciente_consecutivo;
import Model.Paciente_examenes;
import Model.Pacodbc;
import Model.Pacodbc_det;
import Model.Sedes;
import java.io.File;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import view.Prueba1;
/**
 * @author Ing. Francisco Berrocal Machado modificada el 08/01/2015
 * Made in: MyBrain
 */
public class Prototipo_servicio_Agilis implements Runnable {
                                                                                    //    Properties prop = new Properties();       //    File ruta = new File("etc/config.properties");                                      
    SimpleDateFormat dtf  = new SimpleDateFormat("yyyy-MM-dd");                      // <-- Formato de fecha para PostgreSQL Ex: 2016-01-21
    SimpleDateFormat dtf1 = new SimpleDateFormat("yyyy-dd-MM");                     // <-- Formato de fecha para SQL Server Ex: 2016-21-01
    public boolean centinela = true;                                                // <-- Variable de control de hilo
    public boolean llave;
    Centro_costoDao gCcosto;                                                        // <-- Objetos para acceso a las bases de datos:    
    Centro_costo ccosto;
    CiudadesDao gCiudades;
    Ciudades ciudades;
    DatosConexion infoConn;                                                         // <-- Datos para establecer conexión con las bases de datos
    Conexion cagilis, cwinsislab;                                                   // <-- Conexiones a las bases de datos Agilis y Winsislab
    LABO_ORDDao gLabo_ord;
    LABO_ORD labo_ord;
    LABO_ORD copia_lab;
    PacodbcDao gPacodbc;
    Pacodbc p;
    Homo_exa_imatDao hei;
    Pacodbc_det pacodbc_det;
    Pacodbc_detDao gpacodbc_det;
    Paciente_examenes paciente_examenes;
    Paciente_examenesDao gPaciente_examenes;
    Sedes sedes;
    SedesDao gSedes;
    Det_tarifaDao gDet_tarifa;
    Paciente_consecutivoDao gPacienteconsecutivo;
    Paciente_consecutivo pacienteconsecutivo;
    PacienteDao gPaciente;
    Paciente paciente;
    Paciente_Adicionales pacAdicionales;
    Paciente_adicionalesDao gPacAdicionales;
    Eventos_paciente_examDao gEventos_paciente_exam;
    Eventos_paciente_exam eventos_paciente_exam;
    medicosDao medicos;
    controladorBD_generico objetoBD;
    IntercambiosDao gIntercambios;
    DefaultTableModel modeloT;                                                      // <-- Variables auxiliares para el funcionamiento del sistema:
    ArrayList paraEnviar;
    Iterator i;                                                                     // <-- Objeto del tipo Iterator para manipilar los datos en forma de arreglo de datos
    Calendar fechaSys, cal;
    String id_examen_winsis, consecutivo_orden;
    int año, mes, dia, hor, minuto, segundo, anios;                                 // <-- Manejo de horas, min, seg, dia, mes y año de forma independiente
    Date fechaOrden, nacio, fechaAct;
    Time horaAct, hora;
    int dif_en_dias, rest, meses, edad;
    String medida_edad;
    String cod_medico, nom_medico, docPaciente, piso, direccion, pacAdicionalEps;
    String codSede;
    ReproductorAlarma ra;
    long consecutivo,prefijo;
    int diasLab;                                                                    // <-- Esta parte es nueva se usa para parametrizar la busqueda de laboratorios en cero
    DefaultTableModel tabLab;
    int fac;
    int factor1;
    int cantidadExa;
    int n_peticion;
    int v_ctrl;
    
    String procede;
    
    @Override
    public void run() {                                                             // <-- METODO RUN DEL HILO DE PROCESO
        gCcosto = new Centro_costoDao();                                            // <-- Cración de objetos para manejo de bases de datos:
        ccosto = new Centro_costo();
        gCiudades = new CiudadesDao();
        ciudades = new Ciudades();
        gIntercambios = new IntercambiosDao();
        gLabo_ord = new LABO_ORDDao();                                              // <-- Objeto LABO_ORDDao para trabajar tabla LABO_ORD (Agilis)
        gPacodbc = new PacodbcDao();                                                // <-- Objeto DAO para la tabla pacodbc (Winsislab)
        p = new Pacodbc();                                                          // <-- Objeto de Datos tabla pacodbc
        pacienteconsecutivo = new Paciente_consecutivo();                           // <-- Objeto de datos tabla Paciente_consecutivo (winsislab)
        paciente = new Paciente();                                                  // <-- Objeto de datos tabla paciente
        pacodbc_det = new Pacodbc_det();                                            // <-- Objeto de Datos tabla pacodbc_det
        hei = new Homo_exa_imatDao();                                               // <-- Objeto DAO tabla homo_exa_imat
        gSedes = new SedesDao();                                                    // <-- Objeto DAO tabla sedes
        gPacienteconsecutivo = new Paciente_consecutivoDao();
        gPaciente = new PacienteDao();                                              // <-- Objeto DAO tabla paciente
        gpacodbc_det = new Pacodbc_detDao();                                        // <-- Objeto de datos tabla pacodbc_det
        gDet_tarifa = new Det_tarifaDao();                                          // <-- Objeto de datos tabla det_tarifa
        paciente_examenes = new Paciente_examenes();                                // <-- Objeto de datos tabla paciente_examenes
        gPaciente_examenes = new Paciente_examenesDao();                            // <-- Objeto DAO de la tabal paciente_examenes
        eventos_paciente_exam = new Eventos_paciente_exam();                        // <-- Objeto de datos tabla eventos_paciente_exam
        gEventos_paciente_exam = new Eventos_paciente_examDao();                    // <-- Objeto DAQ tabla eventos_paciente_exam
        medicos = new medicosDao();
        objetoBD = new controladorBD_generico();
        infoConn = new DatosConexion();
        infoConn.datoLabxDias();                                                                            // <-- Carga el número de dias de antiguedad buscados por la interfaz en LABO_ORD
        diasLab = infoConn.getDiasLab();
        pacAdicionales = new Paciente_Adicionales();
        gPacAdicionales = new Paciente_adicionalesDao();
        
        infoConn.datosConexionWinsislab();                                                                  //  <-- CONEXIONES A LAS BASES DE DATOS AGILIS Y DBWINSISLAB
        cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                infoConn.getUs(), infoConn.getPas());
        infoConn.datosConexionAgilis();
        cagilis = new Conexion(infoConn.getDriver(), "jdbc:sqlserver://" + infoConn.getUrl(),
                infoConn.getUs(), infoConn.getPas());
        
        ra = new ReproductorAlarma();                                                                       // <-- Varibales auxiliares del sistema   
        paraEnviar = new ArrayList();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        cal = Calendar.getInstance();
        fac = factor1 = 0;
        pacAdicionalEps = objetoBD.valorVariable(cagilis.getCon(), "@PACADICIONALESEPS");
        
        while (centinela) {                                                                                 // <-- CICLO INFINITO DE EJECUCION DE LA INTERFAZ (HILO DE PROCESO)
            try {
                try {
                    fechaSys = new GregorianCalendar();
                    setProgress(10);
                    if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {                                            // <-- SE VERIFICAN LAS CONEXIONES Y SE REESTABLECEN EN CASO DE HABER PROBLEMAS
                        infoConn.datosConexionWinsislab();
                        cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                                infoConn.getUs(), infoConn.getPas());
                    }
                    if (cagilis.getCon() == null || cagilis.getCon().isClosed()) {
                        infoConn.datosConexionAgilis();
                        cagilis = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                                infoConn.getUs(), infoConn.getPas());
                    }
                    setProgress(60);                                                                                                //    <--   SE BUSCA EN LABO_ORD (AGILIS) REGISTROS QUE NO HAYAN SIDO ENVIADOS A BD WINSISLAB DE 5 DIAS DE ANTIGUEDAD
                    gLabo_ord.setConn(cagilis.getCon());
                    
                    i = gLabo_ord.ordenesNoProcesadas("SELECT * FROM LABO_ORD WHERE ESTADO=0 AND FECHA_RESULTADO > '" + fechaListado() + " 00:00:00.000' ORDER BY ID ASC").iterator();
                    
                    // Se obtiene el número de la sede configurada en el sistema Winsislab ----------------------------------
//                    try {
//                        gSedes.setConn(cwinsislab.getCon());
//                        // [18/01/2020] Nuevo código para obtener el número de la sede.  codigo anterior (sedes = gSedes.getObject("60"))
//                        sedes = new Sedes();
//                        gSedes.getSede(sedes);                                              
//                        codSede = sedes.getSede_codigo();
//                    } catch (NotFoundException ex) {
//                            Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, "Error el codigo de sede no existe " + ex.toString(), ex);
//                            // System.out.println("Error: el código de sede no existe:\n" + ex.getMessage());
//                            JOptionPane.showMessageDialog(null,"Error: el código de sede no existe:\n" + ex.getMessage());
//                    } catch (SQLException ex) {
//                        Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, null, ex);
//                        JOptionPane.showMessageDialog(null,"Error: SQL Exception:\n" + ex.getMessage());
//                    }
                    // ----------------------------------------------------------------------------------------------------
                    
                    if (i.hasNext()) {                                                                                              // <-- ¿Existen registros nuevos sin enviar en LABO_ORD (DB Agilis)?
                        while (i.hasNext()) {
                            if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {                                    // <-- SE REVISAN LAS CONEXIONES Y SE REESTABLECEN EN CASO DE EXIXTIR ERRORES
                                infoConn.datosConexionWinsislab();
                                cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                                        infoConn.getUs(), infoConn.getPas());
                            }
                            if (cagilis.getCon() == null || cagilis.getCon().isClosed()) {
                                infoConn.datosConexionAgilis();
                                cagilis = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                                        infoConn.getUs(), infoConn.getPas());
                            }
                            labo_ord = (LABO_ORD) i.next();
                            hei.setConn(cwinsislab.getCon());                                                                       // <-- Conexion a Winsislab
                                
                            if ( String.valueOf( hei.retornaIdAlterna(labo_ord.getCOD_EXAMEN()) ).equals("")) {                                        // <-- SE VERIFICA QUE LOS LABORATORIOS ESTEN DEBIDAMENTE HOMOLOGADOS
                                actualizarBarraDeEstado("Agilis: Registros pendientes por trasladas a Winsislab");
                            } else if ( String.valueOf( hei.retornaCodigoWinsisLab(labo_ord.getCOD_EXAMEN()) ).equals("")) {
                                actualizarBarraDeEstado("Agilis: Registros pendientes por trasladas a Winsislab");
                            } else if (!(hei.existeIdWinsislab(hei.retornaCodigoWinsisLab(labo_ord.getCOD_EXAMEN())))) {
                                actualizarBarraDeEstado("Agilis: Registros pendientes por trasladas a Winsislab");
                            } else {
                                gPacodbc.setConn(cwinsislab.getCon());                                                              // <-- SE VERIFICA QUE ESTA ORDEN NO HAYA SIDO TRASLADADA A PACODBC (WINSISLAB)  
                                if (gPacodbc.fueTrasladada(labo_ord.getNUM_ORDEN())) {
                                    labo_ord.setESTADO(1);                                                                          // <-- SI FUE TRASLADADO SE ACTUALIZA SU ESTADO A 1  
                                    try {
                                        gLabo_ord.setConn(cagilis.getCon());
                                        gLabo_ord.actualizarLABO_ORD(labo_ord);
                                    } catch (NotFoundException ex) {
                                        Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, "Error tabla LABO_ORD " + ex.toString(), ex);
                                        System.out.print("Error al intentar actualizar la tabla LABO_ORD\n" + ex.toString());
                                    }
                                } else {
                        
                                    fac         = 0;
                                    v_ctrl      = 0;
                                    cantidadExa = labo_ord.getCANTIDAD();
                                    factor1     = gLabo_ord.getMayPeticion(labo_ord.getNUM_ORDEN());
                                    // n_peticion  = labo_ord.getNUM_PETICION();
                                    
                                    while(v_ctrl < cantidadExa){   
                                        // for(int vc=0; vc < cantidadExa; vc++) {
                                        // System.out.println("v_ctrl = " + v_ctrl + " Cantidad=" + cantidadExa + "Ciclo 2");
                                        if(fac == 0) {
                                            paraEnviar.add(labo_ord);
                                        } else {
                                            copia_lab = labo_ord.clonar();
                                            copia_lab.setNUM_PETICION(factor1+1);
                                            paraEnviar.add(copia_lab);
                                            factor1 = factor1 + 1;
                                        }
                                        fac = fac + 1;
                                        v_ctrl = v_ctrl + 1;
                                    }
                                    v_ctrl = 0;
                                    // paraEnviar.add(labo_ord);                     // <-- SI NO FUE TRASLADADO SE ADICIONA A LA LISTA DE REGISTROS QUE SERA ENVIADA WINSISLAB
                                }
                            }
                        }
                        // presionarBoton();                                                                   // <-- Se actualiza la tabla de laboratorios no Homologados
                        if (paraEnviar.size() > 0) {                                                        // <-- MIENTRAS HAYAN REGISTROS PARA ENVIAR
                            
                            i = paraEnviar.iterator();
                            while (i.hasNext()) {                                                           // <-- PORCEDIMIENTO PARA ENVIAR REGISTROS DE LABO_ORD A WINSISLAB
                                if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {        // <-- SE VERIFICAN LAS CONEXIONES EXISTENTES Y SE REESTABLECEN EN CASO DE EXISTIR PROBLEMAS
                                    infoConn.datosConexionWinsislab();
                                    cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                                            infoConn.getUs(), infoConn.getPas());
                                }
                                if (cagilis.getCon() == null || cagilis.getCon().isClosed()) {
                                    infoConn.datosConexionAgilis();
                                    cagilis = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                                            infoConn.getUs(), infoConn.getPas());
                                }
                                labo_ord = (LABO_ORD) i.next();
                                labo_ord.cleanFields(); // NUEVA LINEA PARA LIMPIAR LOS CAMPOS ANTES DE ENVIAR A POSTGRESQL
                                docPaciente = cortaCadena( labo_ord.getDOCUMENTO() );                       // <-- Se quitan los espacios de la cadena original para que se almacene correctamente en la base de datos WINSISLAB
                                gPacodbc.setConn(cwinsislab.getCon());
                                
                                // SE CORTA DE ACA EL CODIGO PARA OBTENER LA SEDE Y SE PASA A ANTES DE INCIAR EL CICLO REPETITIVO
                                if (!(gPacodbc.fueTrasladada(labo_ord.getNUM_ORDEN()))) {
                                    
                                    gLabo_ord.setConn(cagilis.getCon());
                                    // factor1 = gLabo_ord.getMayPeticion(labo_ord.getNUM_ORDEN());
                                    
                                    if (labo_ord.getPISO().length() > 10) {                                 //SE TOMAN LOS DATOS sede_codigo,prefor,orden,preo1,langel_actual DE LA TABLA SEDES (DB Winsislab) CUANDO EL CAMPO sede_codigo SEA '60'
                                        piso = (labo_ord.getPISO().substring(0, 9));                        ///Este paso se realiza siempre que la cadena de piso supere los 10 caracteres
                                    } else {
                                        piso = labo_ord.getPISO();
                                    }
                                    
                                    if (labo_ord.getDIRECCION().length() > 60) {
                                        direccion = (labo_ord.getDIRECCION().substring(0, 59));
                                    } else {
                                        direccion = labo_ord.getDIRECCION();
                                    }
                                    
                                    try {
                                        gSedes.setConn(cwinsislab.getCon());
                                       // 18/01/2019 Nuevo código para obtener el número de la sede
                                        sedes = new Sedes();
                                        gSedes.getSede(sedes);                                              // sedes = gSedes.getObject("60");
                                        codSede = sedes.getSede_codigo();
                                    } catch (NotFoundException ex) {
                                        Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, "Error el codigo de sede no existe " + ex.toString(), ex);
                                        System.out.println("Error: el código de sede no existe:\n" + ex.getMessage());
                                    }
                                    
                                    // Se actualiza el consecutivo de winsislab
                                    consecutivo = sedes.getOrden();
                                    consecutivo++;
                                    sedes.setOrden(consecutivo);                                            // <-- SE INCREMENTA EL CONSECUTIVO DE LAS ORDENES Y SE ACTUALIZA EN LA TABLA SEDES (DB WINSISLAB) CUANDO EL CÓDIGO SEA 60
                                    try {
                                        gSedes.setConn(cwinsislab.getCon());
                                        gSedes.save(sedes); // <-- El sistema actualiza la base de datos
                                    } catch (NotFoundException ex) {
                                        Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, "Error al intentar actualizar el consecutivo de la sede:\n" + ex.toString(), ex);
                                        System.out.println("Error al intentar actualizar el consecutivo de la sede:\n" + ex.getMessage());
                                    }
                                    
                                    // Se armar el consecutivo del paciente a ingresar a DB WINSISLAB (paciente_cod):
                                    prefijo = (Integer.parseInt( sedes.getPrefor() ) * 1000000 ) + consecutivo;                     
                                    
                                    if ((consecutivo / 10000) < 10) {
                                        consecutivo_orden = String.valueOf(prefijo); //sedes.getPrefor() + "0" + consecutivo;
                                    } else {
                                        consecutivo_orden = String.valueOf(prefijo); //sedes.getPrefor() + consecutivo;
                                    }

                                    año = fechaSys.get(Calendar.YEAR);
                                    mes = fechaSys.get(Calendar.MONTH) + 1;
                                    dia = fechaSys.get(Calendar.DAY_OF_MONTH);
                                    hor = fechaSys.get(Calendar.HOUR_OF_DAY);
                                    minuto = fechaSys.get(Calendar.MINUTE);
                                    segundo = fechaSys.get(Calendar.SECOND);

                                    fechaOrden = java.sql.Date.valueOf(formato.format(labo_ord.getFECHA_RESULTADO()));
                                    nacio = java.sql.Date.valueOf(formato.format(labo_ord.getFECHANAC()));
                                    fechaAct = java.sql.Date.valueOf(año + "-" + mes + "-" + dia);
                                    horaAct = Time.valueOf(hor + ":" + minuto + ":" + segundo);

                                    cal.setTimeInMillis(labo_ord.getFECHA_RESULTADO().getTime());
                                    hor = cal.get(Calendar.HOUR_OF_DAY);
                                    minuto = cal.get(Calendar.MINUTE);
                                    segundo = cal.get(Calendar.SECOND);
                                    hora = Time.valueOf(hor + ":" + minuto + ":" + segundo);
                                    
                                    dif_en_dias = (int) (((((new Date().getTime() - nacio.getTime()) / 1000) / 60) / 60) / 24);                 // <-- SE CALCULA LA EDAD DE UNA PERSONA (EDAD EXHACTA) A PARTIR DE SU FECHA DE NACIMIENTO Y LA FECHA ACTUAL
                                    anios = dif_en_dias / 365;
                                    rest = dif_en_dias % 365;
                                    meses = rest / 30;
                                    edad = 0;

                                    if (anios >= 1) {
                                        edad = anios;
                                        medida_edad = "A";
                                    } else if (meses >= 1) {
                                        edad = meses;
                                        medida_edad = "M";
                                    } else {
                                        edad = dif_en_dias;
                                        medida_edad = "D";
                                    }
                                    
                                    gPacienteconsecutivo.setConn(cwinsislab.getCon());                                                          // <-- Si no existe el registro en la tabla paciente_consecutivo se ingresa
                                    // if (!(gPacienteconsecutivo.existePaciente_cosecutivo(consecutivo_orden, "60"))) {
                                    if (!(gPacienteconsecutivo.existePaciente_cosecutivo(consecutivo_orden, codSede))) {
                                        // pacienteconsecutivo.setAll(consecutivo_orden, fechaOrden, "60");
                                        pacienteconsecutivo.setAll(consecutivo_orden, fechaOrden, codSede);
                                        gPacienteconsecutivo.create(pacienteconsecutivo);
                                    }
                                    
                                    gPaciente.setConn(cwinsislab.getCon());                                                                     // <-- Si no existe el registro en la tabla paciente se ingresa    
                                    //if (!(gPaciente.existePaciente(consecutivo_orden, fechaOrden, "60"))) {
                                    if (!(gPaciente.existePaciente(consecutivo_orden, fechaOrden, codSede))) {    
                                        medicos.setConn(cwinsislab.getCon());                                                                   // <-- SE CONTROLA LA LLAVE FORANEA COD_MEDICOS PARA EVITAR ERRORES DE INTEGRIDAD REFERENCIAL
                                        if (medicos.existe_medico(labo_ord.getCOD_MEDICO())) {
                                            cod_medico = labo_ord.getCOD_MEDICO();
                                            nom_medico = labo_ord.getNOM_MEDICO();
                                        } else {
                                            cod_medico = "NA";
                                            nom_medico = "NO DEFINIDO";
                                        }
                                        
                                        if (!(objetoBD.existeCiudad(cwinsislab.getCon(), labo_ord.getCOD_CIUDAD()))) {                                                      // <-- SE CONTROLA LA LLAVE FORANEA COD_CIUDAD PARA EVITAR ERRORES DE INTEGRIDAD REFERENCIAL
                                            ciudades.setAll(labo_ord.getCOD_CIUDAD(), objetoBD.retornaNomCiudad(cagilis.getCon(), labo_ord.getCOD_CIUDAD()), true);
                                            gCiudades.create(cwinsislab.getCon(), ciudades);
                                        }
                                        
                                        if (!(gCcosto.existeCentrocosto(cwinsislab.getCon(), labo_ord.getCOD_CENCOS()))) {                                                  // <-- SE CONTROLA LA LLAVE FORANEA ccosto_cod PARA EVITAR ERRORES DE INTEGRIDAD
                                            ccosto.setAll(labo_ord.getCOD_CENCOS(), objetoBD.retornaCentroCosto(cagilis.getCon(), labo_ord.getCOD_CENCOS()),
                                                    true, "H");
                                            gCcosto.create(cwinsislab.getCon(), ccosto);
                                        }
                                        procede="";
                                        if(labo_ord.getCOD_CENCOS().equals("05")){
                                            procede = "2";
                                        }else{
                                            procede = "1";
                                        }                                        
                                        
                                        // 18/01/2019: se cambia la sede quemada 60 por la variable codSede
                                        paciente.setAll(
                                                consecutivo_orden, hora, fechaOrden, codSede, docPaciente, labo_ord.getTIPO_DOC(), docPaciente,
                                                "", false, "NA", labo_ord.getAPELLIDO1() + " " + labo_ord.getAPELLIDO2(), labo_ord.getNOMBRE1() + " " + labo_ord.getNOMBRE2(), "NA", true,
                                                fechaOrden, fechaOrden, hora, hora, codSede, codSede, direccion, labo_ord.getTELEFONO(), cod_medico,
                                                nom_medico, labo_ord.getEMAIL(), procede, nacio, anios, medida_edad, labo_ord.getSEXO(), labo_ord.getPISO(),
                                                labo_ord.getCOD_CENCOS(), null, null, false, "1", null, 0, 0, 0, 0, "IMAT", 0, 0, 0, 0, 0, null, null, labo_ord.getNUM_ORDEN(),
                                                null, labo_ord.getNOADMISION(), labo_ord.getCOD_CIUDAD(), labo_ord.getCOD_ZONA(), null, null, null, null, labo_ord.getNUM_ORDEN(),
                                                null, null, "", null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false, false, false, false, true, false, false, false,
                                                false, false, false, false, false, false, false, false, false, false, false, null, "", null, "", null, "1", null, "NA", fechaAct, horaAct,
                                                null, null, null, null);
                                        gPaciente.create(paciente);
                                    }
                                    // <-- SE GUARDA EN LA TABLA PACODBC DE LA BASE DE DATOS DBWINSISLAB
                                    // Se cambia la sede quemada 60 por la variable codSede
                                    p.setAll(labo_ord.getNUM_ORDEN(), labo_ord.getTIPO_DOC(), docPaciente, labo_ord.getNOADMISION(), null, null, hora, fechaOrden, docPaciente, false, null,
                                            labo_ord.getAPELLIDO1() + " " + labo_ord.getAPELLIDO2(), labo_ord.getNOMBRE1() + " " + labo_ord.getNOMBRE2(), null, true, direccion, labo_ord.getTELEFONO(), labo_ord.getCOD_MEDICO(),
                                            labo_ord.getNOM_MEDICO(), labo_ord.getEMAIL(),"1", labo_ord.getNOM_CLIENTE(), nacio, labo_ord.getSEXO(), labo_ord.getCOD_CENCOS(), labo_ord.getNOM_CENCOS(), "", 0, 0, "",
                                            labo_ord.getCOD_CIUDAD(), labo_ord.getCOD_ZONA(), null, null, null, 0, 0, 0, 0, null, null, labo_ord.getCELULAR(), piso, null, fechaOrden,
                                            hora, fechaAct, horaAct, "1", codSede, consecutivo_orden, true);
                                    gPacodbc.setConn(cwinsislab.getCon());
                                    gPacodbc.create(p);
                                    
                                    // [05/03/2020] La variable de control para el ciclo declarada como i interfería con el bojeto global i
                                    // lo cual causaba el mal funcionamiento del ciclo
                                    // guardarDetalles(labo_ord, cagilis, cwinsislab, docPaciente, codSede, labo_ord.getNUM_PETICION());
                                    // fac         = 0;
                                    // v_ctrl      = 0;
                                    // cantidadExa = labo_ord.getCANTIDAD();
                                    
                                    pacAdicionales.setAll(consecutivo_orden, hora, fechaOrden, codSede, docPaciente, labo_ord.getTIPO_DOC(), docPaciente, pacAdicionalEps, labo_ord.getNOM_CLIENTE(), true);
                                    gPacAdicionales.setConn(cwinsislab.getCon());
                                    gPacAdicionales.create(pacAdicionales);
                                    
                                    n_peticion = labo_ord.getNUM_PETICION();
                                    guardarDetalles(labo_ord, cagilis, cwinsislab, docPaciente, codSede, n_peticion);
                                    
//                                    while(v_ctrl < cantidadExa){      
//                                        // for(int vc=0; vc < cantidadExa; vc++) {
//                                        System.out.println("v_ctrl = " + v_ctrl + " Cantidad=" + cantidadExa + "Ciclo 1");
//                                        if(fac == 0) {
//                                            guardarDetalles(labo_ord, cagilis, cwinsislab, docPaciente, codSede, n_peticion);
//                                        } else {
//                                           guardarDetalles(labo_ord, cagilis, cwinsislab, docPaciente, codSede, (factor1+1));
//                                            factor1 = factor1 + 1;
//                                        }
//                                        fac = fac + 1;
//                                        v_ctrl = v_ctrl + 1;
//                                    }
                                
                                } else {
                                    // SE CONSTRUYE EL CICLO PARA EL GUARDADO DE ORDENES CON CANTIDAD MAYOR A 1
                                    // guardarDetalles(labo_ord, cagilis, cwinsislab, docPaciente, codSede, labo_ord.getNUM_PETICION());
                                    // [05/03/2020] La variable de control para el ciclo declarada como i interfería con el bojeto global i
                                    // lo cual causaba el mal funcionamiento del ciclo
                                    // guardarDetalles(labo_ord, cagilis, cwinsislab, docPaciente, codSede, labo_ord.getNUM_PETICION());

//                                    fac         = 0;
//                                    v_ctrl      = 0;
//                                    cantidadExa = labo_ord.getCANTIDAD();
                                    n_peticion = labo_ord.getNUM_PETICION();
                                    guardarDetalles(labo_ord, cagilis, cwinsislab, docPaciente, codSede, n_peticion);
                                    
//                                    while(v_ctrl < cantidadExa){ 
//                                        //for(int vc=0; vc < cantidadExa; vc++) {
//                                        System.out.println("v_ctrl = " + v_ctrl + " Cantidad=" + cantidadExa + "Ciclo 2");
//                                        if(fac == 0) {
//                                            guardarDetalles(labo_ord, cagilis, cwinsislab, docPaciente, codSede, n_peticion);
//                                        } else {
//                                            guardarDetalles(labo_ord, cagilis, cwinsislab, docPaciente, codSede, (factor1+1));
//                                            factor1 = factor1 + 1;
//                                       }
//                                        fac = fac + 1;
//                                        v_ctrl = v_ctrl + 1;
//                                    }
                                }
                                actualizarBarraDeEstado("Agilis: Registros enviados a Winsislab");
                            }
                            ra.reproducir();
                            paraEnviar.clear();
                        }
                        setProgress(100);
                        presionarBoton();
                    } else {
                        actualizarBarraDeEstado("Agilis: No existen registros para procesar");
                        setProgress(100);
                        presionarBoton();
                    }
                    try {
                        setProgress(0);                                                                           // <-- OJO UN SLEEP A UN TREAD DENTRO DE UN LOOP PUEDE CAUSAR PROBLEMAS DE FUNCIONAMIENTO  
                        infoConn.datoTimeAgilis();
                        Thread.sleep(Integer.parseInt(infoConn.getTimeAgilis()));
                    } catch (NumberFormatException | InterruptedException e) {
                        Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, "ocurrió la siguiente excepción:\n" + e.toString(), e);
                        System.out.print("ocurrió la siguiente excepción:\n" + e.toString());
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, "Error de Base de datos " + ex.toString(), ex);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, "Error de sonido " + ex.toString(), ex);
                }
            } catch (Exception e) {
                Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, "Error del sistema " + e.toString(), e);
                System.out.print("Ocurrió la siguiente excepción:\n" + e.toString());
                infoConn.datosConexionWinsislab();
                cwinsislab = null;
                cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                        infoConn.getUs(), infoConn.getPas());
                infoConn.datosConexionAgilis();
                cagilis = null;
                cagilis = new Conexion(infoConn.getDriver(), "jdbc:sqlserver://" + infoConn.getUrl(),
                        infoConn.getUs(), infoConn.getPas());
            }
        }                                                                                                       // <- FIN DEL CICLO WHILE
        cagilis.CerrarCon();
        cwinsislab.CerrarCon();
    }

    public void guardarDetalles(LABO_ORD obj, Conexion ca, Conexion cw, String dcto, String cSede, int secuencia) {                          // <-- METODO ENCARGADO DE GUARDAR TODOS LOS DETALLES DE LOS DIFERENTES LABORATORIOS
        try {
            String documento = dcto;
            cwinsislab       = cw;
            cagilis          = ca;

            if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {                                // <-- Verificamos que las conexiones sean válidas
                infoConn.datosConexionWinsislab();
                cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                        infoConn.getUs(), infoConn.getPas());
            }
            if (cagilis.getCon() == null || cagilis.getCon().isClosed()) {
                infoConn.datosConexionAgilis();
                cagilis = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                        infoConn.getUs(), infoConn.getPas());
            }
            
            gpacodbc_det.setConn(cwinsislab.getCon());                                                          // <-- Si no existe el detalle de la orden de laboratorio en la tabla pacodbc_det
            // if (!(gpacodbc_det.existeDetalle(obj.getNUM_ORDEN(), obj.getCOD_EXAMEN(), String.valueOf(obj.getNUM_PETICION())))) {
            if (!(gpacodbc_det.existeDetalle(obj.getNUM_ORDEN(), obj.getCOD_EXAMEN(), String.valueOf(secuencia)))) {
                String exa, cod_alt;
                double precio;
                hei.setConn(cwinsislab.getCon());                                                               // <-- Se toma el id de examen correspondiente a la DB Winsislab equivalente al que viene de la DB de Agilis
                id_examen_winsis = hei.retornaCodigoWinsisLab(obj.getCOD_EXAMEN()); //H4
                gDet_tarifa.setConn(cwinsislab.getCon());
                gDet_tarifa.valorTarifa(id_examen_winsis);
                exa     = gDet_tarifa.getExamen();//H4
                cod_alt = gDet_tarifa.getCodigo_alt();//19304
                precio  = gDet_tarifa.getPrecio();//11015
                // Se reemplaza la sede quemada 60 por el parámetro cSede
                gPaciente_examenes.setConn(cwinsislab.getCon());
                // <-- SI NO EXISTE EL REGISTRO SE GUARDA EN LA TABLA PACIENTE_EXAMENES LOS DATOS DE LOS EXAMENES DE DETERMINADO PACIENTE
                // if (!(gPaciente_examenes.existe_paciente_examenes(consecutivo_orden, fechaOrden, id_examen_winsis, obj.getNUM_PETICION(),cSede))) {
                if (!(gPaciente_examenes.existe_paciente_examenes(consecutivo_orden, fechaOrden, id_examen_winsis, secuencia, cSede))) {
                    // <-- SE ALMACENA EN LA TABLA PACIENTE_EXAMENES
                    paciente_examenes.setAll(                                                                                                           
                            consecutivo_orden,
                            hora,
                            fechaOrden,
                            cSede,
                            documento,
                            obj.getTIPO_DOC(),
                            documento,
                            id_examen_winsis,
                            secuencia, // obj.getNUM_PETICION(),
                            true,
                            precio,
                            obj.getCOD_EXAMEN(),
                            null,
                            obj.getNUM_ORDEN(),
                            "E",                                                                                //// <-- Este es el tipo de cliente que va por interfaz en este caso E - Empresa
                            "1",
                            true,
                            false,
                            false,
                            false,
                            false,
                            false,
                            false,
                            false,
                            false,
                            false,
                            0,
                            1,
                            false,
                            false,
                            false,
                            false,
                            false,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            0,
                            cSede,
                            null,
                            null,
                            null,
                            false,
                            null,
                            fechaOrden,
                            0,
                            false,
                            null,
                            null,
                            null,
                            "1",
                            null,
                            null,
                            fechaAct);
                    gPaciente_examenes.create(paciente_examenes);
                }
                // Se cambia la sede quemada 60 por el parámetro cSede
                gEventos_paciente_exam.setConn(cwinsislab.getCon());                                                // <-- SI NO EXISTE EL REGISTRO EN LA TABLA, SE INGRESAN DATOS EN LA TABLA EVENTOS PACIENTE EXAMEN
                // if (!(gEventos_paciente_exam.existe_eventos_paciente_exam(consecutivo_orden, fechaOrden, id_examen_winsis, obj.getNUM_PETICION()))) {
                if (!(gEventos_paciente_exam.existe_eventos_paciente_exam(consecutivo_orden, fechaOrden, id_examen_winsis, secuencia))) {
                    eventos_paciente_exam.setAll(consecutivo_orden, hora, fechaOrden, cSede, documento, obj.getTIPO_DOC(), documento,
                            id_examen_winsis, secuencia, "010", fechaOrden, horaAct, "Registro de Clintos (Interfaz)", "1", true, secuencia);
                    gEventos_paciente_exam.create(eventos_paciente_exam);
                }
                
                pacodbc_det.setAll(obj.getNUM_ORDEN(), obj.getTIPO_DOC(), documento, String.valueOf(secuencia), obj.getCOD_EXAMEN(), String.valueOf(secuencia),             // <-- SE ALMACENA EN LA TABLA PACODBC_DET:
                        obj.getNOM_EXAMEN(), 1, 0, 0, id_examen_winsis, String.valueOf(secuencia), null, true);
                gpacodbc_det.setConn(cwinsislab.getCon());
                gpacodbc_det.create(pacodbc_det);
                obj.setESTADO(1);
                try {
                    gLabo_ord.setConn(cagilis.getCon());
                    gLabo_ord.actualizarLABO_ORD(obj);
                } catch (NotFoundException ex) {
                    Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, "Error al intentar actualizar la tabla LABO_ORD\n", ex);
                    System.out.print("Error al intentar actualizar la tabla LABO_ORD\n" + ex.toString());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, "Excepcion de Base de Datos", ex);
        }
    }
    
    private void setProgress(int percent) {                                                                     // <-- METODOS DE CONTROL DE INTERFAZ FRAGICA DE LA VENTANA PRINCIPAL    
        //SwingUtilities.invokeLater(() -> {
            Prueba1.pb_Agilis.setValue(percent);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, null, ex);
            }
        //});
    }

    public String cortaCadena(String cad) {                                                                     // <-- QUITA LOS ESPACIOS DE LA CADENA NUM_DOCUMENTO
        String cedPaciente = "";
        for (int i = 0; i < cad.length(); i++) {
            if (!(Character.isSpaceChar(cad.charAt(i)))) {
                cedPaciente = cedPaciente + Character.toString(cad.charAt(i));
            }
        }
        return cedPaciente;
    }

    public void actualizarBarraDeEstado(String msg) {
        SwingUtilities.invokeLater(() -> {
            Prueba1.statusLabel.setText(msg);
        });
    }

    public void presionarBoton() {
        SwingUtilities.invokeLater(() -> {
            Prueba1.btn_actTablaNoHomo.doClick();
        });
    }
    
    private String fechaListado() {                                                                             // <-- RETORNA UNA FECHA ANTERIOR A LA DEL SISTEMA DEFINIDA POR EL NUMERO DE DIAS ESTIPULADO POR EL PARAMETRO diasLab
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add( Calendar.DAY_OF_YEAR, (diasLab * -1) );
        return dtf1.format(calendar.getTime());
    }
}