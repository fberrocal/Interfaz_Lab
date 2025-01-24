 /*
 * HILO DE PROCESO QUE ENVIA DATOS DE AGILIS A WINSISLAB
 */
package Controler;

import Herramientas.DatosConexion;
// import Herramientas.PropertiesIO;
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
// import java.io.File;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
// import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
// import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import view.Prueba1;
/**
 * @author Ing. Francisco Berrocal Machado modificada el 08/01/2015
 * Made in: MyBrain
 */
public class Prototipo_servicio_Agilis implements Runnable {

    SimpleDateFormat dtf  = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dtf1 = new SimpleDateFormat("yyyy-dd-MM");
    public boolean centinela = true;
    public boolean llave;
    Centro_costoDao gCcosto;
    Centro_costo ccosto;
    CiudadesDao gCiudades;
    Ciudades ciudades;
    DatosConexion infoConn;
    Conexion cagilis, cwinsislab;
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
    Paciente pacienteAux;
    Paciente_Adicionales pacAdicionales;
    Paciente_adicionalesDao gPacAdicionales;
    Eventos_paciente_examDao gEventos_paciente_exam;
    Eventos_paciente_exam eventos_paciente_exam;
    medicosDao medicos;
    ClientesDao clientes;
    controladorBD_generico objetoBD;
    IntercambiosDao gIntercambios;
    DefaultTableModel modeloT;
    ArrayList paraEnviar;
    Iterator i;
    Calendar fechaSys, cal;
    String id_examen_winsis, consecutivo_orden;
    int año, mes, dia, hor, minuto, segundo, anios;
    Date fechaOrden, nacio, fechaAct;
    Time horaAct, hora;
    int dif_en_dias, rest, meses, edad;
    String medida_edad;
    String cod_medico, nom_medico, docPaciente, piso, direccion, pacAdicionalEps, codigoCliente;
    String codSede;
    ReproductorAlarma ra;
    long consecutivo,prefijo;
    int diasLab;
    DefaultTableModel tabLab;
    int fac;
    int factor1;
    int cantidadExa;
    int n_peticion;
    int v_ctrl;
    
    String procede;
    
    String last_num_orden;      // Solución bug en envío de órdenes a Winsislab
    String last_codigo_lab;
    int last_nro_peticion;
    String last_num_orden_enlab;
    int last_nro_peticion_enlab;
    
    @Override
    public void run() {
        gCcosto = new Centro_costoDao();
        ccosto = new Centro_costo();
        gCiudades = new CiudadesDao();
        ciudades = new Ciudades();
        gIntercambios = new IntercambiosDao();
        gLabo_ord = new LABO_ORDDao();
        gPacodbc = new PacodbcDao();
        p = new Pacodbc();
        pacienteconsecutivo = new Paciente_consecutivo();
        paciente = new Paciente();
        pacodbc_det = new Pacodbc_det();
        hei = new Homo_exa_imatDao();
        gSedes = new SedesDao();
        gPacienteconsecutivo = new Paciente_consecutivoDao();
        gPaciente = new PacienteDao();
        gpacodbc_det = new Pacodbc_detDao();
        gDet_tarifa = new Det_tarifaDao();
        paciente_examenes = new Paciente_examenes();
        gPaciente_examenes = new Paciente_examenesDao();
        eventos_paciente_exam = new Eventos_paciente_exam();
        gEventos_paciente_exam = new Eventos_paciente_examDao();
        medicos = new medicosDao();
        clientes = new ClientesDao();
        objetoBD = new controladorBD_generico();
        infoConn = new DatosConexion();
        infoConn.datoLabxDias();
        // diasLab = infoConn.getDiasLab();
        pacAdicionales = new Paciente_Adicionales();
        gPacAdicionales = new Paciente_adicionalesDao();
        
        infoConn.datosConexionWinsislab();
        cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                infoConn.getUs(), infoConn.getPas());
        infoConn.datosConexionAgilis();
        cagilis = new Conexion(infoConn.getDriver(), "jdbc:sqlserver://" + infoConn.getUrl(),
                infoConn.getUs(), infoConn.getPas());
        
        ra = new ReproductorAlarma();
        paraEnviar = new ArrayList();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        cal = Calendar.getInstance();
        fac = factor1 = 0;
        // pacAdicionalEps = objetoBD.valorVariable(cagilis.getCon(), "@PACADICIONALESEPS");
        
        while (centinela) {
            try {
                try {
                    fechaSys        = new GregorianCalendar();
                    pacAdicionalEps = objetoBD.valorVariable(cagilis.getCon(), "@PACADICIONALESEPS");
                    infoConn.datoLabxDias();
                    diasLab         = infoConn.getDiasLab();
                    
                    last_num_orden    = "";
                    last_codigo_lab   = "";
                    last_nro_peticion = 0;
                    last_num_orden_enlab = "";
                    last_nro_peticion_enlab = 0;
                    
                    setProgress(10);
                    if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {
                        infoConn.datosConexionWinsislab();
                        cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                                infoConn.getUs(), infoConn.getPas());
                    }
                    if (cagilis.getCon() == null || cagilis.getCon().isClosed()) {
                        infoConn.datosConexionAgilis();
                        cagilis = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                                infoConn.getUs(), infoConn.getPas());
                    }
                    setProgress(60);
                    gLabo_ord.setConn(cagilis.getCon());
                    
                    i = gLabo_ord.ordenesNoProcesadas("SELECT * FROM LABO_ORD WHERE ESTADO=0 AND FECHA_RESULTADO > '" 
                                                       + fechaListado() + " 00:00:00.000' ORDER BY ID ASC").iterator();
                    
                    if (i.hasNext()) {
                        while (i.hasNext()) {
                            if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {
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
                            hei.setConn(cwinsislab.getCon());

                            if ( String.valueOf( hei.retornaIdAlterna(labo_ord.getCOD_EXAMEN()) ).equals("")) {
                                actualizarBarraDeEstado("Agilis: No se encontró ID alterna en Winsislab (1)");
                            } else if ( String.valueOf( hei.retornaCodigoWinsisLab(labo_ord.getCOD_EXAMEN()) ).equals("")) {
                                actualizarBarraDeEstado("Agilis: No se encontró Código Winsislab (2)");
                            } else if (!(hei.existeIdWinsislab(hei.retornaCodigoWinsisLab(labo_ord.getCOD_EXAMEN())))) {
                                actualizarBarraDeEstado("Agilis: No se encontró ID Winsislab (3)");
                            } else {
                                gPacodbc.setConn(cwinsislab.getCon());
                                if (gPacodbc.fueTrasladada(labo_ord.getNUM_ORDEN())) {
                                    // [ Permitir que se trasladen items de órdenes que quedaron pendientes ]
                                    p.setCod_odbc(labo_ord.getNUM_ORDEN());
                                    gPacodbc.load(p);
                                    fechaOrden 	     = java.sql.Date.valueOf(formato.format(labo_ord.getFECHA_RESULTADO()));
                                    id_examen_winsis = hei.retornaCodigoWinsisLab(labo_ord.getCOD_EXAMEN());
                                    n_peticion 	     = labo_ord.getNUM_PETICION();

                                    try {
                                        gSedes.setConn(cwinsislab.getCon());
                                        sedes   = new Sedes();
                                        gSedes.getSede(sedes);                                            
                                        codSede = sedes.getSede_codigo();
                                    } catch (NotFoundException ex) {
                                        Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE,
                                                         "Error, el código de sede no existe " + ex.toString(), ex);
                                        System.out.println("Error, el código de sede no existe:\n" + ex.getMessage());
                                    }

                                    gPaciente_examenes.setConn(cwinsislab.getCon());
                                    
                                    if (!(gPaciente_examenes.existe_paciente_examenes(cortaCadena(p.getCod_pac_cruce()), fechaOrden, id_examen_winsis, n_peticion, codSede))) {
                                        
                                        fac         = 0;
                                        v_ctrl      = 0;
                                        cantidadExa = labo_ord.getCANTIDAD();
                                        
                                        if (labo_ord.getNUM_ORDEN().equals(last_num_orden_enlab)) {
                                            factor1 = last_nro_peticion_enlab;
                                        } else {
                                            factor1 = gPaciente_examenes.get_max_RegExam(cortaCadena(p.getCod_pac_cruce()));
                                        }
                                        
                                        while(v_ctrl < cantidadExa){   
                                            if(fac == 0) {
                                                labo_ord.setNUM_PETICION(factor1+1);
                                                paraEnviar.add(labo_ord);
                                                factor1 = factor1 + 1;
                                            } else {
                                                copia_lab = labo_ord.clonar();
                                                copia_lab.setNUM_PETICION(factor1+1);
                                                paraEnviar.add(copia_lab);
                                                factor1 = factor1 + 1;
                                            }

                                            fac = fac + 1;
                                            v_ctrl = v_ctrl + 1;
                                        }

                                        v_ctrl                  = 0;
                                        last_num_orden_enlab    = labo_ord.getNUM_ORDEN();
                                        last_nro_peticion_enlab = factor1;
                                        
                                    } else {
                                        try {
                                            labo_ord.setESTADO(1);
                                            gLabo_ord.setConn(cagilis.getCon());
                                            gLabo_ord.actualizarLABO_ORD(labo_ord);
                                        } catch (NotFoundException ex) {
                                            Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, "Error, tabla LABO_ORD " + ex.toString(), ex);
                                            System.out.print("Error al intentar actualizar la tabla LABO_ORD\n" + ex.toString());
                                        }
                                    }
                                    
                                } else {
                                    fac         = 0;
                                    v_ctrl      = 0;
                                    cantidadExa = labo_ord.getCANTIDAD();
                                    
                                    if (labo_ord.getNUM_ORDEN().equals(last_num_orden)) {
                                        factor1 = last_nro_peticion;
                                    } else {
                                        factor1 = gLabo_ord.getMayPeticion(labo_ord.getNUM_ORDEN());
                                    }
                                    
                                    while(v_ctrl < cantidadExa){
                                        if(fac == 0) {
                                            paraEnviar.add(labo_ord);
                                        } else {
                                            copia_lab = labo_ord.clonar();
                                            copia_lab.setNUM_PETICION(factor1+1);
                                            paraEnviar.add(copia_lab);
                                            factor1 = factor1 + 1;
                                        }
                                        fac    = fac + 1;
                                        v_ctrl = v_ctrl + 1;
                                    }
                                    
                                    v_ctrl            = 0;
                                    last_num_orden    = labo_ord.getNUM_ORDEN();
                                    last_codigo_lab   = labo_ord.getCOD_EXAMEN();
                                    last_nro_peticion = factor1;
                                }
                            }
                        }
                        // presionarBoton();
                        if (paraEnviar.size() > 0) {
                            
                            i = paraEnviar.iterator();
                            while (i.hasNext()) {
                                if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {
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
                                labo_ord.cleanFields();
                                docPaciente = cortaCadena( labo_ord.getDOCUMENTO() );
                                gPacodbc.setConn(cwinsislab.getCon());
                               
                                if (!(gPacodbc.fueTrasladada(labo_ord.getNUM_ORDEN()))) {
                                    gLabo_ord.setConn(cagilis.getCon());
                                    
                                    if (labo_ord.getPISO().length() > 10) {
                                        piso = (labo_ord.getPISO().substring(0, 9));
                                    } else {
                                        piso = labo_ord.getPISO();
                                    }
                                    
                                    if (labo_ord.getDIRECCION().length() > 59) {
                                        direccion = (labo_ord.getDIRECCION().substring(0, 58));
                                    } else {
                                        direccion = labo_ord.getDIRECCION();
                                    }
                                    
                                    try {
                                        gSedes.setConn(cwinsislab.getCon());
                                        sedes = new Sedes();
                                        gSedes.getSede(sedes);
                                        codSede = sedes.getSede_codigo();
                                    } catch (NotFoundException ex) {
                                        Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, "Error el codigo de sede no existe " + ex.toString(), ex);
                                        System.out.println("Error: el código de sede no existe:\n" + ex.getMessage());
                                    }
                                    
                                    // Se actualiza el consecutivo de winsislab
                                    consecutivo = sedes.getOrden();
                                    consecutivo++;
                                    sedes.setOrden(consecutivo);
                                    try {
                                        gSedes.setConn(cwinsislab.getCon());
                                        gSedes.save(sedes);
                                    } catch (NotFoundException ex) {
                                        Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, 
                                                            "Error al intentar actualizar el consecutivo de la sede:\n" + ex.toString(), ex);
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
                                    
                                    dif_en_dias = (int) (((((new Date().getTime() - nacio.getTime()) / 1000) / 60) / 60) / 24);
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
                                    
                                    gPacienteconsecutivo.setConn(cwinsislab.getCon());
                                    if (!(gPacienteconsecutivo.existePaciente_cosecutivo(consecutivo_orden, codSede))) {
                                        pacienteconsecutivo.setAll(consecutivo_orden, fechaOrden, codSede);
                                        gPacienteconsecutivo.create(pacienteconsecutivo);
                                    }
                                    
                                    gPaciente.setConn(cwinsislab.getCon());
                                    if (!(gPaciente.existePaciente(consecutivo_orden, fechaOrden, codSede))) {    
                                        // Se valida la información del Médico
                                        medicos.setConn(cwinsislab.getCon());
                                        
                                        if (labo_ord.getCOD_MEDICO().equals("") || labo_ord.getCOD_MEDICO() == null) {
                                            cod_medico = "NA";
                                            nom_medico = "NO DEFINIDO";
                                        } else {
                                            if (medicos.existe_medico(labo_ord.getCOD_MEDICO())) {
                                                cod_medico = labo_ord.getCOD_MEDICO();
                                                nom_medico = labo_ord.getNOM_MEDICO();
                                            } else {
                                                cod_medico = "NA";
                                                nom_medico = "NO DEFINIDO";
                                            }
                                        }
                                        
                                        // See valida la información de la Aseguradora
                                        clientes.setConn(cwinsislab.getCon());
                                        
                                        if (labo_ord.getCOD_CLIENTE().equals("") || labo_ord.getCOD_CLIENTE() == null) {
                                            codigoCliente = "1";
                                        } else {
                                            if (clientes.existe_cliente(labo_ord.getCOD_CLIENTE())) {
                                                codigoCliente = labo_ord.getCOD_CLIENTE();
                                            } else {
                                                codigoCliente = "1";
                                            }
                                        }
                                        
                                        if (!(objetoBD.existeCiudad(cwinsislab.getCon(), labo_ord.getCOD_CIUDAD()))) {
                                            ciudades.setAll(labo_ord.getCOD_CIUDAD(), objetoBD.retornaNomCiudad(cagilis.getCon(), labo_ord.getCOD_CIUDAD()), true);
                                            gCiudades.create(cwinsislab.getCon(), ciudades);
                                        }
                                        
                                        if (!(gCcosto.existeCentrocosto(cwinsislab.getCon(), labo_ord.getCOD_CENCOS()))) {
                                            ccosto.setAll(labo_ord.getCOD_CENCOS(), objetoBD.retornaCentroCosto(cagilis.getCon(), labo_ord.getCOD_CENCOS()),
                                                    true, "H");
                                            gCcosto.create(cwinsislab.getCon(), ccosto);
                                        }
                                        
                                        procede="";
                                        if(labo_ord.getCOD_CENCOS().equals("10")){
                                            // procede = "2";
                                            procede = "A";
                                        }else{
                                            // procede = "1";
                                            procede = "H";
                                        }                                        
                                        
                                        paciente.setAll(
                                                consecutivo_orden, hora, fechaOrden, codSede, docPaciente, labo_ord.getTIPO_DOC().toUpperCase(), docPaciente,
                                                "", false, "NA", labo_ord.getAPELLIDO1() + " " + labo_ord.getAPELLIDO2(), labo_ord.getNOMBRE1() + " " + labo_ord.getNOMBRE2(), "NA", true,
                                                fechaOrden, fechaOrden, hora, hora, codSede, codSede, direccion, labo_ord.getTELEFONO(), cod_medico,
                                                nom_medico, labo_ord.getEMAIL(), codigoCliente, nacio, anios, medida_edad, labo_ord.getSEXO(), labo_ord.getPISO(),
                                                labo_ord.getCOD_CENCOS(), null, null, false, "1", null, 0, 0, 0, 0, "IMAT", 0, 0, 0, 0, 0, null, null, labo_ord.getNUM_ORDEN(),
                                                null, labo_ord.getNOADMISION(), labo_ord.getCOD_CIUDAD(), labo_ord.getCOD_ZONA(), null, null, null, null, labo_ord.getNUM_ORDEN(),
                                                null, null, "", null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false, false, false, false, true, false, false, false,
                                                false, false, false, false, false, false, false, false, false, false, false, null, "", null, "", null, "1", procede, "NA", fechaAct, horaAct,
                                                null, null, null, null);
                                        gPaciente.create(paciente);
                                    }
                                    
                                    p.setAll(labo_ord.getNUM_ORDEN(), labo_ord.getTIPO_DOC().toUpperCase(), docPaciente, labo_ord.getNOADMISION(), null, null, hora, fechaOrden, docPaciente, false, null,
                                            labo_ord.getAPELLIDO1() + " " + labo_ord.getAPELLIDO2(), labo_ord.getNOMBRE1() + " " + labo_ord.getNOMBRE2(), null, true, direccion, labo_ord.getTELEFONO(), labo_ord.getCOD_MEDICO(),
                                            labo_ord.getNOM_MEDICO(), labo_ord.getEMAIL(), codigoCliente, labo_ord.getNOM_CLIENTE(), nacio, labo_ord.getSEXO(), labo_ord.getCOD_CENCOS(), labo_ord.getNOM_CENCOS(), "", 0, 0, "",
                                            labo_ord.getCOD_CIUDAD(), labo_ord.getCOD_ZONA(), null, null, null, 0, 0, 0, 0, null, null, labo_ord.getCELULAR(), piso, null, fechaOrden,
                                            hora, fechaAct, horaAct, "1", codSede, consecutivo_orden, true);
                                    gPacodbc.setConn(cwinsislab.getCon());
                                    gPacodbc.create(p);
                                    
                                    pacAdicionales.setAll(consecutivo_orden, hora, fechaOrden, codSede, docPaciente, labo_ord.getTIPO_DOC().toUpperCase(), docPaciente,
                                                          pacAdicionalEps, labo_ord.getNOM_CLIENTE(), true);
                                    gPacAdicionales.setConn(cwinsislab.getCon());
                                    gPacAdicionales.create(pacAdicionales);
                                    
                                    n_peticion = labo_ord.getNUM_PETICION();
                                    guardarDetalles(labo_ord, cagilis, cwinsislab, docPaciente, codSede, n_peticion);
                                } else {
                                    consecutivo_orden = gPacodbc.retornaCodigoPaciente(labo_ord.getNUM_ORDEN());
                                    consecutivo_orden = consecutivo_orden.trim();
                                    if (!consecutivo_orden.equals("")) {
                                        pacienteAux = null;
                                        gPaciente.setConn(cwinsislab.getCon());
                                        pacienteAux = gPaciente.getObject(consecutivo_orden);
                                        
                                        if (!(pacienteAux == null)) {
                                            codSede = pacienteAux.getSede_codigo();
                                            fechaOrden = pacienteAux.getFecha();
                                            hora = pacienteAux.getHora();
                                            año = fechaSys.get(Calendar.YEAR);
                                            mes = fechaSys.get(Calendar.MONTH) + 1;
                                            dia = fechaSys.get(Calendar.DAY_OF_MONTH);
                                            hor = fechaSys.get(Calendar.HOUR_OF_DAY);
                                            minuto = fechaSys.get(Calendar.MINUTE);
                                            segundo = fechaSys.get(Calendar.SECOND);
                                            fechaAct = java.sql.Date.valueOf(año + "-" + mes + "-" + dia);
                                            n_peticion = labo_ord.getNUM_PETICION();
                                            horaAct = Time.valueOf(hor + ":" + minuto + ":" + segundo);
                                            guardarDetalles(labo_ord, cagilis, cwinsislab, docPaciente, codSede, n_peticion);
                                        }
                                    }
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
                        setProgress(0);
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
        }
        cagilis.CerrarCon();
        cwinsislab.CerrarCon();
    }

    public void guardarDetalles(LABO_ORD obj, Conexion ca, Conexion cw, String dcto, String cSede, int secuencia) {
        try {
            String documento = dcto;
            cwinsislab       = cw;
            cagilis          = ca;

            if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {
                infoConn.datosConexionWinsislab();
                cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                        infoConn.getUs(), infoConn.getPas());
            }
            if (cagilis.getCon() == null || cagilis.getCon().isClosed()) {
                infoConn.datosConexionAgilis();
                cagilis = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                        infoConn.getUs(), infoConn.getPas());
            }
            
            gpacodbc_det.setConn(cwinsislab.getCon());
            if (!(gpacodbc_det.existeDetalle(obj.getNUM_ORDEN(), obj.getCOD_EXAMEN(), String.valueOf(secuencia)))) {
                String exa, cod_alt;
                double precio;
                hei.setConn(cwinsislab.getCon());
                id_examen_winsis = hei.retornaCodigoWinsisLab(obj.getCOD_EXAMEN());
                gDet_tarifa.setConn(cwinsislab.getCon());
                gDet_tarifa.valorTarifa(id_examen_winsis);
                exa     = gDet_tarifa.getExamen();
                cod_alt = gDet_tarifa.getCodigo_alt();
                precio  = gDet_tarifa.getPrecio();

                gPaciente_examenes.setConn(cwinsislab.getCon());
                // <-- SI NO EXISTE EL REGISTRO SE GUARDA EN LA TABLA PACIENTE_EXAMENES LOS DATOS DE LOS EXAMENES DE DETERMINADO PACIENTE
                if (!(gPaciente_examenes.existe_paciente_examenes(consecutivo_orden, fechaOrden, id_examen_winsis, secuencia, cSede))) {
                    paciente_examenes.setAll(                                                                                                           
                            consecutivo_orden,
                            hora,
                            fechaOrden,
                            cSede,
                            documento,
                            obj.getTIPO_DOC().toUpperCase(),
                            documento,
                            id_examen_winsis,
                            secuencia,
                            true,
                            precio,
                            obj.getCOD_EXAMEN(),
                            null,
                            obj.getNUM_ORDEN(),
                            "E",
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

                gEventos_paciente_exam.setConn(cwinsislab.getCon());
                if (!(gEventos_paciente_exam.existe_eventos_paciente_exam(consecutivo_orden, fechaOrden, id_examen_winsis, secuencia, cSede))) {
                    eventos_paciente_exam.setAll(consecutivo_orden, hora, fechaOrden, cSede, documento, obj.getTIPO_DOC().toUpperCase(), documento,
                            id_examen_winsis, secuencia, "010", fechaOrden, horaAct, "Registro de Clintos (Interfaz)", "1", true, secuencia);
                    gEventos_paciente_exam.create(eventos_paciente_exam);
                }
                
                pacodbc_det.setAll(obj.getNUM_ORDEN(), obj.getTIPO_DOC().toUpperCase(), documento, String.valueOf(secuencia), obj.getCOD_EXAMEN(),
                                    String.valueOf(secuencia), obj.getNOM_EXAMEN(), 1, 0, 0, id_examen_winsis, String.valueOf(secuencia), null, true);
                gpacodbc_det.setConn(cwinsislab.getCon());
                gpacodbc_det.create(pacodbc_det);
                
                // Inicia código de validación del cruce de solicitud de exámenes
                try {
                    if (gPacodbc.fueTrasladada(obj.getNUM_ORDEN())) {
                        gPaciente.setConn(cwinsislab.getCon());
                        if (gPaciente.existePaciente(consecutivo_orden, fechaOrden, cSede)) { 
                            if (gPaciente_examenes.existe_paciente_examenes(consecutivo_orden, fechaOrden, id_examen_winsis, secuencia, cSede)) {
                                obj.setESTADO(1);
                                gLabo_ord.setConn(cagilis.getCon());
                                gLabo_ord.actualizarLABO_ORD(obj);
                            } else {
                                // Eliminar pacodbc_det
                                if (gpacodbc_det.existeDetalle(obj.getNUM_ORDEN(), obj.getCOD_EXAMEN(), String.valueOf(secuencia))) {
                                    pacodbc_det.setCod_odbc(obj.getNUM_ORDEN());
                                    gpacodbc_det.delete(pacodbc_det);
                                }
                            }
                        } else {
                                // Eliminar pacodbc_det
                            if (gpacodbc_det.existeDetalle(obj.getNUM_ORDEN(), obj.getCOD_EXAMEN(), String.valueOf(secuencia))) {
                                pacodbc_det.setCod_odbc(obj.getNUM_ORDEN());
                                gpacodbc_det.delete(pacodbc_det);
                            }

                            // Eliminar pacodbc
                            if (gPacodbc.fueTrasladada(obj.getNUM_ORDEN())) {
                                p.setCod_odbc(obj.getNUM_ORDEN());
                                gPacodbc.delete(p);
                            }
                        }		
                    } else {
                        // Eliminar pacodbc_det:
                        if (gpacodbc_det.existeDetalle(obj.getNUM_ORDEN(), obj.getCOD_EXAMEN(), String.valueOf(secuencia))) {
                            pacodbc_det.setCod_odbc(obj.getNUM_ORDEN());
                            gpacodbc_det.delete(pacodbc_det);
                        }

                        // Eliminar el paciente y sus detalles:
                        gPaciente.setConn(cwinsislab.getCon());
                        if (gPaciente.existePaciente(consecutivo_orden, fechaOrden, cSede)) {
                            if (gPaciente_examenes.existe_paciente_examenes(consecutivo_orden, fechaOrden, id_examen_winsis, secuencia, cSede)) {
                                paciente_examenes.setPaciente_cod(consecutivo_orden);
                                gPaciente_examenes.delete(paciente_examenes);
                            }

                            if (gEventos_paciente_exam.existe_eventos_paciente_exam(consecutivo_orden, fechaOrden, id_examen_winsis, secuencia, cSede)) {
                                eventos_paciente_exam.setPaciente_cod(consecutivo_orden);
                                gEventos_paciente_exam.delete(eventos_paciente_exam);
                            }

                            if(gPacAdicionales.existeCodPacienteAdicionales(consecutivo_orden, pacAdicionalEps)) {
                                pacAdicionales.setPaciente_cod(consecutivo_orden);
                                pacAdicionales.setConcap_cod(pacAdicionalEps);
                                gPacAdicionales.deleteInfoPaciente(pacAdicionales);
                            }

                            paciente.setPaciente_cod(consecutivo_orden);
                            gPaciente.delete(paciente);
                        }
                    } 
                } catch (NotFoundException ex) {
                    Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Finaliza código de validación del cruce de solicitud de exámenes
            } else {
                gPaciente.setConn(cwinsislab.getCon());
                if (gPaciente.existePaciente(consecutivo_orden, fechaOrden, cSede)) {
                    hei.setConn(cwinsislab.getCon());
                    id_examen_winsis = hei.retornaCodigoWinsisLab(obj.getCOD_EXAMEN());

                    if (!(gPaciente_examenes.existe_paciente_examenes(consecutivo_orden, fechaOrden, id_examen_winsis, secuencia, cSede))) {
                        double precio;
                        
                        gDet_tarifa.setConn(cwinsislab.getCon());
                        gDet_tarifa.valorTarifa(id_examen_winsis);
                        
                        precio = gDet_tarifa.getPrecio();
                        // <-- SE ALMACENA EN LA TABLA PACIENTE_EXAMENES
                        paciente_examenes.setAll(                                                                                                           
                                consecutivo_orden,
                                hora,
                                fechaOrden,
                                cSede,
                                documento,
                                obj.getTIPO_DOC().toUpperCase(),
                                documento,
                                id_examen_winsis,
                                secuencia,
                                true,
                                precio,
                                obj.getCOD_EXAMEN(),
                                null,
                                obj.getNUM_ORDEN(),
                                "E",
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
                    
                    gEventos_paciente_exam.setConn(cwinsislab.getCon());
                    if (!(gEventos_paciente_exam.existe_eventos_paciente_exam(consecutivo_orden, fechaOrden, id_examen_winsis, secuencia, cSede))) {
                        eventos_paciente_exam.setAll(consecutivo_orden, hora, fechaOrden, cSede, documento, obj.getTIPO_DOC().toUpperCase(), documento,
                                id_examen_winsis, secuencia, "010", fechaOrden, horaAct, "Registro de Clintos (Interfaz)", "1", true, secuencia);
                        gEventos_paciente_exam.create(eventos_paciente_exam);
                    }
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, "Excepcion de Base de Datos", ex);
        }
    }
    
    /*
        Set the value to the Status Bar
    */
    private void setProgress(int percent) {
        //SwingUtilities.invokeLater(() -> {
        Prueba1.pb_Agilis.setValue(percent);
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Prototipo_servicio_Agilis.class.getName()).log(Level.SEVERE, null, ex);
        }
        //});
    }

    /*
        Take off the space characters from a String variable
    */
    public String cortaCadena(String cad) {
        String cedPaciente = "";
        for (int vCont = 0; vCont < cad.length(); vCont++) {
            if (!(Character.isSpaceChar(cad.charAt(vCont)))) {
                cedPaciente = cedPaciente + Character.toString(cad.charAt(vCont));
            }
        }
        return cedPaciente;
    }

    /*
        Update the caption of the Status Bar
    */
    public void actualizarBarraDeEstado(String msg) {
        SwingUtilities.invokeLater(() -> {
            Prueba1.statusLabel.setText(msg);
        });
    }

    /*
        Press button event
    */
    public void presionarBoton() {
        SwingUtilities.invokeLater(() -> {
            Prueba1.btn_actTablaNoHomo.doClick();
        });
    }
    
    /*
        Set the list date
    */
    private String fechaListado() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add( Calendar.DAY_OF_YEAR, (diasLab * -1) );
        return dtf1.format(calendar.getTime());
    }
}
