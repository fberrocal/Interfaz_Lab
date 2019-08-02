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
    SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");                      // <-- Formato de fecha para PostgreSQL Ex: 2016-01-21
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
    String cod_medico, nom_medico, docPaciente, piso;
    ReproductorAlarma ra;
    long consecutivo,prefijo;
    int diasLab;                                                                    // <-- Esta parte es nueva se usa para parametrizar la busqueda de laboratorios en cero
    DefaultTableModel tabLab;

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
                    
                    if (i.hasNext()) {                                                                                              // <-- ¿Existen registros nuevos sin enviar en LABO_ORD (DB Agilis)?
                        // JOptionPane.showMessageDialog(null, "Hola");
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

                            if (hei.retornaIdAlterna(labo_ord.getCOD_EXAMEN()).equals("")) {                                        // <-- SE VERIFICA QUE LOS LABORATORIOS ESTEN DEBIDAMENTE HOMOLOGADOS
                                actualizarBarraDeEstado("Agilis: Registros pendientes por trasladas a Winsislab");
                            } else if (hei.retornaCodigoWinsisLab(labo_ord.getCOD_EXAMEN()).equals("")) {
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
                                    paraEnviar.add(labo_ord);                                                                       // <-- SI NO FUE TRASLADADO SE ADICIONA A LA LISTA DE REGISTROS QUE SERA ENVIADA WINSISLAB
                                }
                            }
                        }
                        presionarBoton();                                                                   // <-- Se actualiza la tabla de laboratorios no Homologados
                        
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
                                docPaciente = cortaCadena( labo_ord.getDOCUMENTO() );                       // <-- Se quitan los espacios de la cadena original para que se almacene correctamente en la base de datos WINSISLAB
                                gPacodbc.setConn(cwinsislab.getCon());
                                if (!(gPacodbc.fueTrasladada(labo_ord.getNUM_ORDEN()))) {
                                    if (labo_ord.getPISO().length() > 10) {                                 //SE TOMAN LOS DATOS sede_codigo,prefor,orden,preo1,langel_actual DE LA TABLA SEDES (DB Winsislab) CUANDO EL CAMPO sede_codigo SEA '40'
                                        piso = (labo_ord.getPISO().substring(0, 9));                        ///Este paso se realiza siempre que la cadena de piso supere los 10 caracteres
                                    }
                                    try {
                                        gSedes.setConn(cwinsislab.getCon());
                                        sedes = gSedes.getObject("40");
                                    } catch (NotFoundException ex) {
                                        Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, "Error el codigo de sede no existe " + ex.toString(), ex);
                                        System.out.println("Error: el código de sede no existe:\n" + ex.getMessage());
                                    }

                                    consecutivo = sedes.getOrden();
                                    consecutivo++;
                                    sedes.setOrden(consecutivo);                                            // <-- SE INCREMENTA EL CONSECUTIVO DE LAS ORDENES Y SE ACTUALIZA EN LA TABLA SEDES (DB WINSISLAB) CUANDO EL CÓDIGO SEA 40
                                    try {
                                        gSedes.setConn(cwinsislab.getCon());
                                        gSedes.save(sedes);
                                    } catch (NotFoundException ex) {
                                        Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, "Error al intentar actualizar el consecutivo de la sede:\n" + ex.toString(), ex);
                                        System.out.println("Error al intentar actualizar el consecutivo de la sede:\n" + ex.getMessage());
                                    }

                                    prefijo = (Integer.parseInt( sedes.getPrefor() ) * 1000000 ) + consecutivo;                     // <-- Se armar el consecutivo del paciente a ingresar a DB WINSISLAB (paciente_cod)
                                    
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
                                    if (!(gPacienteconsecutivo.existePaciente_cosecutivo(consecutivo_orden, "40"))) {
                                        pacienteconsecutivo.setAll(consecutivo_orden, fechaOrden, "40");
                                        gPacienteconsecutivo.create(pacienteconsecutivo);
                                    }
                                    
                                    gPaciente.setConn(cwinsislab.getCon());                                                                     // <-- Si no existe el registro en la tabla paciente se ingresa    
                                    if (!(gPaciente.existePaciente(consecutivo_orden, fechaOrden, "40"))) {
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
                                        
                                        paciente.setAll(
                                                consecutivo_orden, hora, fechaOrden, "40", docPaciente, labo_ord.getTIPO_DOC(), docPaciente,
                                                "", false, "NA", labo_ord.getAPELLIDO1() + " " + labo_ord.getAPELLIDO2(), labo_ord.getNOMBRE1() + " " + labo_ord.getNOMBRE2(), "NA", true,
                                                fechaOrden, fechaOrden, hora, hora, "40", "40", labo_ord.getDIRECCION(), labo_ord.getTELEFONO(), cod_medico,
                                                nom_medico, labo_ord.getEMAIL(), procede, nacio, anios, medida_edad, labo_ord.getSEXO(), labo_ord.getPISO(),
                                                labo_ord.getCOD_CENCOS(), null, null, false, "1", null, 0, 0, 0, 0, "IMAT", 0, 0, 0, 0, 0, null, null, labo_ord.getNUM_ORDEN(),
                                                null, labo_ord.getNOADMISION(), labo_ord.getCOD_CIUDAD(), labo_ord.getCOD_ZONA(), null, null, null, null, labo_ord.getNUM_ORDEN(),
                                                null, null, "", null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false, false, false, false, true, false, false, false,
                                                false, false, false, false, false, false, false, false, false, false, false, null, "", null, "", null, "1", null, "NA", fechaAct, horaAct,
                                                null, null, null, null);
                                        gPaciente.create(paciente);
                                    }
                                    // <-- SE GUARDA EN LA TABLA PACODBC DE LA BASE DE DATOS DBWINSISLAB
                                    p.setAll(labo_ord.getNUM_ORDEN(), labo_ord.getTIPO_DOC(), docPaciente, labo_ord.getNOADMISION(), null, null, hora, fechaOrden, docPaciente, false, null,
                                            labo_ord.getAPELLIDO1() + " " + labo_ord.getAPELLIDO2(), labo_ord.getNOMBRE1() + " " + labo_ord.getNOMBRE2(), null, true, labo_ord.getDIRECCION(), labo_ord.getTELEFONO(), labo_ord.getCOD_MEDICO(),
                                            labo_ord.getNOM_MEDICO(), labo_ord.getEMAIL(),"1", labo_ord.getNOM_CLIENTE(), nacio, labo_ord.getSEXO(), labo_ord.getCOD_CENCOS(), labo_ord.getNOM_CENCOS(), "", 0, 0, "",
                                            labo_ord.getCOD_CIUDAD(), labo_ord.getCOD_ZONA(), null, null, null, 0, 0, 0, 0, null, null, labo_ord.getCELULAR(), piso, null, fechaOrden,
                                            hora, fechaAct, horaAct, "1", "40", consecutivo_orden, true);
                                    gPacodbc.setConn(cwinsislab.getCon());
                                    gPacodbc.create(p);
                                    guardarDetalles(labo_ord, cagilis, cwinsislab, docPaciente); //Se llama al método que guarda los detalles del examen            // <-- SE GUARDAN LOS DETALLES DEL EXAMEN
                                } else {
                                    guardarDetalles(labo_ord, cagilis, cwinsislab, docPaciente);                                    // <-- SE GUARDAN LOS DETALLES DEL LABORATORIO
                                }
                                actualizarBarraDeEstado("Agilis: Registros enviados a Winsislab");
                            }
                            ra.reproducir();
                            paraEnviar.clear();
                        }
                        setProgress(100);
                    } else {
                        actualizarBarraDeEstado("Agilis: No existen registros para procesar");
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

    public void guardarDetalles(LABO_ORD obj, Conexion ca, Conexion cw, String dcto) {                          // <-- METODO ENCARGADO DE GUARDAR TODOS LOS DETALLES DE LOS DIFERENTES LABORATORIOS
        try {
            String documento = dcto;
            cwinsislab = cw;
            cagilis = ca;

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
            if (!(gpacodbc_det.existeDetalle(obj.getNUM_ORDEN(), obj.getCOD_EXAMEN(), String.valueOf(obj.getNUM_PETICION())))) {
                String exa, cod_alt;
                double precio;
                hei.setConn(cwinsislab.getCon());                                                               // <-- Se toma el id de examen correspondiente a la DB Winsislab equivalente al que viene de la DB de Agilis
                id_examen_winsis = hei.retornaCodigoWinsisLab(obj.getCOD_EXAMEN()); //H4
                gDet_tarifa.setConn(cwinsislab.getCon());
                gDet_tarifa.valorTarifa(id_examen_winsis);
                exa = gDet_tarifa.getExamen();//H4
                cod_alt = gDet_tarifa.getCodigo_alt();//19304
                precio = gDet_tarifa.getPrecio();//11015
                
                gPaciente_examenes.setConn(cwinsislab.getCon());                                                                                        // <-- SI NO EXISTE EL REGISTRO SE GUARDA EN LA TABLA PACIENTE_EXAMENES LOS DATOS DE LOS EXAMENES DE DETERMINADO PACIENTE
                if (!(gPaciente_examenes.existe_paciente_examenes(consecutivo_orden, fechaOrden, id_examen_winsis, obj.getNUM_PETICION()))) {
                    paciente_examenes.setAll(                                                                                                           // <-- SE ALMACENA EN LA TABLA PACIENTE_EXAMENES
                            consecutivo_orden,
                            hora,
                            fechaOrden,
                            "40",
                            documento,
                            obj.getTIPO_DOC(),
                            documento,
                            id_examen_winsis,
                            obj.getNUM_PETICION(),
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
                            "40",
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
                
                gEventos_paciente_exam.setConn(cwinsislab.getCon());                                                // <-- SI NO EXISTE EL REGISTRO EN LA TABLA, SE INGRESAN DATOS EN LA TABLA EVENTOS PACIENTE EXAMEN
                if (!(gEventos_paciente_exam.existe_eventos_paciente_exam(consecutivo_orden, fechaOrden, id_examen_winsis, obj.getNUM_PETICION()))) {
                    eventos_paciente_exam.setAll(consecutivo_orden, hora, fechaOrden, "40", documento, obj.getTIPO_DOC(), documento,
                            id_examen_winsis, obj.getNUM_PETICION(), "010", fechaOrden, horaAct, "REGISTRO DESDE IMAT", "1", true, obj.getNUM_PETICION());
                    gEventos_paciente_exam.create(eventos_paciente_exam);
                }
                
                pacodbc_det.setAll(obj.getNUM_ORDEN(), obj.getTIPO_DOC(), documento, String.valueOf(obj.getNUM_PETICION()), obj.getCOD_EXAMEN(), String.valueOf(obj.getNUM_PETICION()),             // <-- SE ALMACENA EN LA TABLA PACODBC_DET:
                        obj.getNOM_EXAMEN(), obj.getCANTIDAD(), 0, 0, id_examen_winsis, String.valueOf(obj.getNUM_PETICION()), null, true);
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