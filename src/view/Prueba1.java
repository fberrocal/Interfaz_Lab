/**
 * GUI PRINCIPAL DE LA APLICACION DE INTERFAZ PARA LABORATORIO. ESTA PANTALLA SERA SIEMPRE VISIBLE AL USUARIO FINAL Y LE PERMITIRA INTERACTUAR CON EL
 * SISTEMA. EL OBJETIVO PRINCIPLA DE LA PANTALLA ES EL DE MONITOREO DE LA INFORMACIÓN
 */
package view;

import Controler.Conexion;
import Controler.Homo_exa_imatDao;
import Controler.LABO_ORDDao;
import Controler.PacodbcDao;
import Controler.ProcesoActualizaFecha;
import Controler.Prototipo_servicio_Agilis;
import Controler.Prototipo_servicio_Winsislab;
import Controler.controladorBD_generico;
import Herramientas.DatosConexion;
import Herramientas.FromatoTablaLaboratorios;
import Herramientas.PropertiesIO;
import Model.Homo_exa_imat;
import Model.LABO_ORD;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Ing. Francisco Berrocal Machado
 */
public class Prueba1 extends javax.swing.JFrame {

    SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");

    JTable tabAux;

    String aux;
    String seleccion;
    public String clave;

    JPasswordField jpf;
    JLabel etiqueta;
    JPopupMenu popup;
    JMenuItem item;
    JPopupMenu popup2;
    JMenuItem item2;
    JPopupMenu popup3;
    JMenuItem item3;
    JMenuItem item4;

    Prototipo_servicio_Agilis serv;
    Prototipo_servicio_Winsislab serv2;
    ProcesoActualizaFecha serv3;

    Thread hilo;
    Thread hilo2;
    Thread hilo3;

//  OBJETOS DE BASES DE DATOS
    DatosConexion infoConn;                                                         //<-- Datos para establecer conexión con las bases de datos
    Conexion cagilis, cwinsislab;                                                   //<-- Conexiones a las bases de datos Agilis y Winsislab
    PacodbcDao t2;
    Homo_exa_imatDao hei;
    Homo_exa_imat homo_exa_imat;
    private controladorBD_generico objetoBD;
    LABO_ORDDao gLabo_ord;
    LABO_ORD labo_ord;

//  OBJETOS PARA ACCESO A ARCHIVOS DEL SISTEMA
    Properties prop = new Properties();
    File ruta = new File("etc/config.properties");                                  //<-- Las propiedades de conección con la BD se cargan de este archivo

    DefaultTableModel modelo, modAux;

    Iterator i;

//  CONSTRUCTOR DE LA GUI PRINCIPAL
    public Prueba1() {
        initComponents();
        this.setIconImage(getIconImage());

        tabAux = new JTable();
        jMenu1.setMnemonic(KeyEvent.VK_ALT);

// DATOS PARA ARMARA EL JPTIONPANE QUE SOLICITA EL PASSWORD:
        jpf = new JPasswordField();
        etiqueta = new JLabel("Ingrese clave de Administrador para homologar?");

// MENU CONTEXTUAL PARA LA TABLA LABORATORIOS:
        popup = new JPopupMenu();
        item = new JMenuItem("Homologar Laboratorio");
        item.addActionListener((ActionEvent e) -> {
            homologarDesdePanelPrincipal();
        });
        popup.add(item);
        
// MENU CONTEXTUAL PARA TABLA HOMOLOGACIÓN:
        popup2 = new JPopupMenu();
        item2 = new JMenuItem("Homologar Laboratorio");
        item2.addActionListener((ActionEvent e) -> {
            jButton4.doClick();
        });
        popup2.add(item2);

// MENU CONTEXTUAL PARA TABLA LISTA DE LABO_ORD:
        popup3 = new JPopupMenu();
        item3 = new JMenuItem("Homologar Laboratorio");
        item3.addActionListener((ActionEvent e) -> {
            homologarExamenAgilis();
        });
        item4 = new JMenuItem("Ingresar Id Alterna");
        item4.addActionListener((ActionEvent e) -> {
            jButton8.doClick();
        });
        popup3.add(item3);
        popup3.add(item4);

// VARIABLES AUXILIARES DEL SISTEMA:
        infoConn = new DatosConexion();
        infoConn.datosConexionWinsislab();
        cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                infoConn.getUs(), infoConn.getPas());
        infoConn.datosConexionAgilis();
        cagilis = new Conexion(infoConn.getDriver(), "jdbc:sqlserver://" + infoConn.getUrl(),
                infoConn.getUs(), infoConn.getPas());
        clave = "";
        t2 = new PacodbcDao();
        hei = new Homo_exa_imatDao();
        homo_exa_imat = new Homo_exa_imat();
        objetoBD = new controladorBD_generico();
        gLabo_ord = new LABO_ORDDao();
        labo_ord = new LABO_ORD();

// CONFIGURACIÓN TABLA LABORATORIO
        String col[] = {"Paciente", "Apellidos", "Nombres", "Fecha", "Hora", "Código", "Alterno", "Examen", "Contestado", "Validado", "Modificado"};
        modelo = new DefaultTableModel(col, 0);
        modelo.addRow(new Object[]{"", "", "", "", "", "", "Laboratorios", "", "NO", "NO", "NO"});
        laboratorio.setModel(modelo);
        laboratorio.setDefaultRenderer(Object.class, new FromatoTablaLaboratorios());
        laboratorio.getTableHeader().setReorderingAllowed(false);
        
// CONFIGURACION TABLA LISTA
        //lista.setModel(new LABO_ORDDao().modeloTabla());
        lista.getTableHeader().setReorderingAllowed(false);
        
// CONFIGURACION TABLA HOMOLOGACION
        hei.setConn(cwinsislab.getCon());
        homologacion.setModel(hei.listarExamenesHomologados());                                             //<--- Lista la tabla Homo_Exa_Imat (Tabla de homologaciones de Winsislab)
        homologacion.getColumn("Servicio").setPreferredWidth(600);
        homologacion.getTableHeader().setReorderingAllowed(false);
        homologacion.setAutoCreateRowSorter(true);

// HILOS DE PROCESOS WINSILAB Y AGILIS
        serv = new Prototipo_servicio_Agilis();                                                             // <-- Inicia el hilo de proceso de envío Clintos a Winsislab
        hilo = new Thread(serv);
        serv2 = new Prototipo_servicio_Winsislab();                                                         // <-- Inicia el hilo de proceso de envío Winsislab a Clintos
        hilo2 = new Thread(serv2);
        serv3 = new ProcesoActualizaFecha();                                                                // <-- Inicia el hilo de proceso de Actualización de fechas del sistema
        hilo3 = new Thread(serv3);
        
// CONFIGURACION FORMULARIO DE CIERRE DE APLICACION
        Cierre.setTitle("Finalización proceso de intercambio");
        Cierre.setLocation(500, 300);
        Cierre.pack();

        this.setLocationRelativeTo(null);                                                                   // <-- UBICADO AL CENTRO DE LA PANTALLA
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);                                                 // <-- ESTE FORMULARIO NO CIERRA CON EL BOBON DE CIERRE DE VENTANA
  
        btn_actualizaTabLab.setVisible(false);
        btn_actTablaNoHomo.setVisible(false);
        
// AGREGAMOS LA AYUDA DEL SISTEMA
        ponLaAyuda();
    }                                                                                                       // <-- FIN CONSTRUCTOR DE INTERFAZ

// FUNCION QUE LANZA LOS HILOS DESDE EL FORMULARIO PRINCIPAL
    public void lanzar() {
        hilo.start();                                                                                       // <-- Se lanzan los tres hilos Clintos, Winsislab y Actualiza fechas
        hilo2.start();
        hilo3.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cierre = new javax.swing.JFrame();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista = new javax.swing.JTable();
        CBOX_NoHomolog = new javax.swing.JCheckBox();
        BTN_LimpiarNoHomo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        laboratorio = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btn_finalizar = new javax.swing.JButton();
        chb_visualizar = new javax.swing.JCheckBox();
        fechaLab = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        btn_actualizar = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btn_actualizaTabLab = new javax.swing.JButton();
        btn_actTablaNoHomo = new javax.swing.JButton();
        STC_VisualizaLabs = new javax.swing.JLabel();
        EDT_Dias = new javax.swing.JFormattedTextField();
        STC_Dias = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        statusLabel2 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pb_Agilis = new javax.swing.JProgressBar();
        jLabel10 = new javax.swing.JLabel();
        pb_winsis = new javax.swing.JProgressBar();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        homologacion = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        btn_aexcel = new javax.swing.JButton();
        btn_desdeExcel = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        cb_filtro = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        item_conexion = new javax.swing.JMenuItem();
        item_contrasena = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        men_ayuda = new javax.swing.JMenu();
        item_ayuda = new javax.swing.JMenuItem();

        jLabel1.setText("Ingrese clave de cierre:");

        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/ACEPTAR1.png"))); // NOI18N
        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/CANCELAR.png"))); // NOI18N
        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pass)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CierreLayout = new javax.swing.GroupLayout(Cierre.getContentPane());
        Cierre.getContentPane().setLayout(CierreLayout);
        CierreLayout.setHorizontalGroup(
            CierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CierreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        CierreLayout.setVerticalGroup(
            CierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CierreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proceso - LABO_ORD");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Agilis - LABO_ORD (Exámenes no homologados)"));

        lista.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lista.setForeground(new java.awt.Color(0, 0, 255));
        lista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        lista.setToolTipText("Listado de laboratorios sin homologar");
        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(lista);

        CBOX_NoHomolog.setText("Visualizar Labs. no homologados");

        BTN_LimpiarNoHomo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/PAPELERA.png"))); // NOI18N
        BTN_LimpiarNoHomo.setText("Limpiar tabla");
        BTN_LimpiarNoHomo.setToolTipText("Quitar los registros listados");
        BTN_LimpiarNoHomo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_LimpiarNoHomoActionPerformed(evt);
            }
        });
        BTN_LimpiarNoHomo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BTN_LimpiarNoHomoKeyPressed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/excel1.png"))); // NOI18N
        jButton1.setText("a Excel No Hom");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CBOX_NoHomolog, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_LimpiarNoHomo, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(CBOX_NoHomolog)
                    .addComponent(BTN_LimpiarNoHomo)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("WINSISLAB - Laboratorios"));

        laboratorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        laboratorio.setToolTipText("Listado de laboratorios enviados a Winsislab");
        laboratorio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                laboratorioMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(laboratorio);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(202, 202, 202))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_finalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/CANCELAR.png"))); // NOI18N
        btn_finalizar.setText("Finalizar Proceso");
        btn_finalizar.setToolTipText("Finaliza la ejecución de la App");
        btn_finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_finalizarActionPerformed(evt);
            }
        });
        btn_finalizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_finalizarKeyPressed(evt);
            }
        });

        chb_visualizar.setText("Visualizar solo pendientes por procesar");
        chb_visualizar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chb_visualizarStateChanged(evt);
            }
        });
        chb_visualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chb_visualizarActionPerformed(evt);
            }
        });

        fechaLab.setDate(new Date());
        fechaLab.setDateFormatString("yyyy-MM-dd");

        jLabel2.setText("Listar laboratorios desde:");

        btn_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/update.png"))); // NOI18N
        btn_actualizar.setText("Actualizar");
        btn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizarActionPerformed(evt);
            }
        });
        btn_actualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_actualizarKeyPressed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(244,70,74));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(0, 191, 0));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(0, 51, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );

        jLabel3.setText("Laboratorios sin procesar");

        jLabel4.setText("Laboratorios procesados");

        jLabel5.setText("Laboratorios sin homologar");

        jPanel14.setBackground(Color.yellow);
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );

        jLabel8.setText("Laboratorios no Validados");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel11, jPanel9});

        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addContainerGap())
        );

        btn_actualizaTabLab.setBorder(null);
        btn_actualizaTabLab.setOpaque(false);
        btn_actualizaTabLab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_actualizaTabLabMouseClicked(evt);
            }
        });
        btn_actualizaTabLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizaTabLabActionPerformed(evt);
            }
        });

        btn_actTablaNoHomo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_actTablaNoHomoMouseClicked(evt);
            }
        });
        btn_actTablaNoHomo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actTablaNoHomoActionPerformed(evt);
            }
        });

        STC_VisualizaLabs.setText("Ver Labs. de hace:");

        EDT_Dias.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        STC_Dias.setText("Días");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_finalizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chb_visualizar)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaLab, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(STC_VisualizaLabs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EDT_Dias, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(STC_Dias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_actualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_actualizaTabLab, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_actTablaNoHomo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(125, 125, 125)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_finalizar)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(fechaLab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(STC_VisualizaLabs)
                            .addComponent(EDT_Dias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(STC_Dias)
                            .addComponent(btn_actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btn_actTablaNoHomo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_actualizaTabLab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addComponent(chb_visualizar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        statusLabel2.setForeground(new java.awt.Color(51, 51, 255));
        statusLabel2.setText("Winsislab: ");

        statusLabel.setForeground(new java.awt.Color(51, 51, 255));
        statusLabel.setText("Estado del proceso...");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(statusLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(statusLabel2)
                .addComponent(statusLabel))
        );

        jLabel9.setText("Progreso Agilis:");

        pb_Agilis.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        pb_Agilis.setForeground(new java.awt.Color(255, 51, 51));
        pb_Agilis.setToolTipText("Avance proceso Agilis");
        pb_Agilis.setStringPainted(true);

        jLabel10.setText("Prog Winsislab:");

        pb_winsis.setBackground(new java.awt.Color(51, 51, 255));
        pb_winsis.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        pb_winsis.setForeground(new java.awt.Color(255, 0, 0));
        pb_winsis.setStringPainted(true);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pb_winsis, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pb_Agilis, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(pb_Agilis, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(pb_winsis, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Interfaz Agilis - Winsislab", jPanel6);

        homologacion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        homologacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        homologacion.setToolTipText("Listado laboratorios Winsislab");
        homologacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                homologacionMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(homologacion);

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/database.png"))); // NOI18N
        jButton4.setText("Homologar");
        jButton4.setToolTipText("Homologar laboratorios");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jButton4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton4KeyPressed(evt);
            }
        });

        btn_aexcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/excel1.png"))); // NOI18N
        btn_aexcel.setText("Descargar a Excel");
        btn_aexcel.setToolTipText("Descarga a Excel listado de laboratorios");
        btn_aexcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aexcelActionPerformed(evt);
            }
        });
        btn_aexcel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_aexcelKeyPressed(evt);
            }
        });

        btn_desdeExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/CARGAR.png"))); // NOI18N
        btn_desdeExcel.setText("Subir desde Excel");
        btn_desdeExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_desdeExcelActionPerformed(evt);
            }
        });
        btn_desdeExcel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_desdeExcelKeyPressed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/IDALTERNA1.png"))); // NOI18N
        jButton8.setText("Agregar Id Alterna");
        jButton8.setToolTipText("Agregar y homologar id de servicio");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jButton8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton8KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_aexcel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_desdeExcel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_aexcel, btn_desdeExcel, jButton4, jButton8});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8)
                    .addComponent(btn_desdeExcel)
                    .addComponent(btn_aexcel))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_aexcel, btn_desdeExcel, jButton4, jButton8});

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/NoFiltra.png"))); // NOI18N
        jButton11.setText("Quitar filtro");
        jButton11.setEnabled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jButton11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton11KeyPressed(evt);
            }
        });

        jTextField1.setEnabled(false);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        cb_filtro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "id_servicio", "detalle_servicio", "id_alterna", "id_winsis" }));
        cb_filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_filtroActionPerformed(evt);
            }
        });

        jLabel7.setText("Filtrar por:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addGap(883, 883, 883))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cb_filtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11))))
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Homologación de Exámenes", jPanel7);

        jMenu1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jMenu1.setText("Configuración");
        jMenu1.setToolTipText("Parámetros de configuración del sistema");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N

        item_conexion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        item_conexion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/IDALTERNA1.png"))); // NOI18N
        item_conexion.setText("Cambiar datos de conexión a Bases de Datos");
        item_conexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_conexionActionPerformed(evt);
            }
        });
        jMenu1.add(item_conexion);

        item_contrasena.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        item_contrasena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/usuario.png"))); // NOI18N
        item_contrasena.setText("Cambiar constraseña de usuario Administrador");
        item_contrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_contrasenaActionPerformed(evt);
            }
        });
        jMenu1.add(item_contrasena);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/time.png"))); // NOI18N
        jMenuItem2.setText("Cambiar tiempo de intercambio de datos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/CANCELAR.png"))); // NOI18N
        jMenuItem1.setText("Finalizar Proceso");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        men_ayuda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        men_ayuda.setText("Ayuda");
        men_ayuda.setToolTipText("Manual de usuarios");
        men_ayuda.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N

        item_ayuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        item_ayuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/manual.png"))); // NOI18N
        item_ayuda.setText("Manual de Usuario");
        men_ayuda.add(item_ayuda);

        jMenuBar1.add(men_ayuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_finalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_finalizarActionPerformed
        // TODO add your handling code here:
        Cierre.setVisible(true);
    }//GEN-LAST:event_btn_finalizarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Cierre.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
        Cierre.dispose();
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cerrar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_finalizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_finalizarKeyPressed
        // TODO add your handling code here:
        Cierre.setVisible(true);
    }//GEN-LAST:event_btn_finalizarKeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
        cerrar();
    }//GEN-LAST:event_jButton2KeyPressed

    private void passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passActionPerformed
        // TODO add your handling code here:
        cerrar();
    }//GEN-LAST:event_passActionPerformed

    private void chb_visualizarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chb_visualizarStateChanged

    }//GEN-LAST:event_chb_visualizarStateChanged

    private void chb_visualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chb_visualizarActionPerformed
        // TODO add your handling code here:
        listadoLabs();
    }//GEN-LAST:event_chb_visualizarActionPerformed

// Iniciar proceso de homologación
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        int i = homologacion.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado una fila de la tabla");
        } else {
            try {
                jpf.setText(null);
                jpf.requestFocus();
                int op = JOptionPane.showConfirmDialog(this, new Object[]{etiqueta, jpf}, "Proceso de homologación", JOptionPane.OK_CANCEL_OPTION);
                try {
                    prop = PropertiesIO.read(ruta);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
                }

                if (JOptionPane.OK_OPTION == op) {
                    if (String.valueOf(homologacion.getValueAt(i, 4)).equals("null")) {
                        aux = "";
                    } else {
                        aux = String.valueOf(homologacion.getValueAt(i, 4));
                    }

                    if (String.valueOf(jpf.getPassword()).equals(prop.getProperty("claveSis"))) {
                        homo_exa_imat.setAll(
                                String.valueOf(homologacion.getValueAt(i, 0)),
                                String.valueOf(homologacion.getValueAt(i, 1)),
                                String.valueOf(homologacion.getValueAt(i, 2)),
                                String.valueOf(homologacion.getValueAt(i, 3)),
                                aux);
                        new HomologacionHexamenes(homo_exa_imat).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error en directiva de seguridad", "Clave incorrecta, intente nuevamente", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Proceso de homologación cancelado");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: Proceso de homologación cancelado: " + e.toString());
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

// Inicia proceso de homologación
    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        // TODO add your handling code here:
        int i = homologacion.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado una fila de la tabla");
        } else {
            try {
                jpf.setText(null);
                jpf.requestFocus();
                int op = JOptionPane.showConfirmDialog(this, new Object[]{etiqueta, jpf}, "Proceso de homologación", JOptionPane.OK_CANCEL_OPTION);
                try {
                    prop = PropertiesIO.read(ruta);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
                }

                if (JOptionPane.OK_OPTION == op) {
                    if (String.valueOf(homologacion.getValueAt(i, 4)).equals("null")) {
                        aux = "";
                    } else {
                        aux = String.valueOf(homologacion.getValueAt(i, 4));
                    }

                    if (String.valueOf(jpf.getPassword()).equals(prop.getProperty("claveSis"))) {
                        homo_exa_imat.setAll(
                                String.valueOf(homologacion.getValueAt(i, 0)),
                                String.valueOf(homologacion.getValueAt(i, 1)),
                                String.valueOf(homologacion.getValueAt(i, 2)),
                                String.valueOf(homologacion.getValueAt(i, 3)),
                                aux);
                        new HomologacionHexamenes(homo_exa_imat).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error en directiva de seguridad", "Clave incorrecta, intente nuevamente", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Proceso de homologación cancelado");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Proceso de homologación cancelado");
            }
        }
    }//GEN-LAST:event_jButton4KeyPressed

    private void btn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarActionPerformed
        // TODO add your handling code here:
        listadoLabs();
    }//GEN-LAST:event_btn_actualizarActionPerformed

    private void btn_actualizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_actualizarKeyPressed
        // TODO add your handling code here:
        listadoLabs();
    }//GEN-LAST:event_btn_actualizarKeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // Filtro sobre la tabla de Homologaciones de Winsislab
        try {
            if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {
                infoConn.datosConexionWinsislab();
                cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(), infoConn.getUs(), infoConn.getPas());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Form principal\nAl tratar la conexion a Winsislab\n" + ex.getMessage());
        }
        hei.setConn(cwinsislab.getCon());
        homologacion.setModel(hei.filtroTabla(jTextField1.getText(), String.valueOf(cb_filtro.getSelectedItem())));
        homologacion.getColumn("Servicio").setPreferredWidth(600);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // Activar el filtro sobre la tabla de homologaciones de Winsislab
        if (jTextField1.isEnabled()) {
            cb_filtro.setSelectedIndex(0);
            jTextField1.setText(null);
            jTextField1.setEnabled(false);
            homologacion.setModel(hei.listarExamenesHomologados());
            homologacion.getColumn("Servicio").setPreferredWidth(600);
            jButton11.setEnabled(false);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton11KeyPressed
    // Activar el filtro sobre la tabla de homologaciones de Winsislab
        if (jTextField1.isEnabled()) {
            cb_filtro.setSelectedIndex(0);
            jTextField1.setText(null);
            jTextField1.setEnabled(false);
            homologacion.setModel(hei.listarExamenesHomologados());
            homologacion.getColumn("Servicio").setPreferredWidth(600);
            jButton11.setEnabled(false);
        }
    }//GEN-LAST:event_jButton11KeyPressed

    private void laboratorioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laboratorioMousePressed
    // Programar las acciones sobre la tabla de Labx
        if (SwingUtilities.isLeftMouseButton(evt)) {
            //ACA SE PROGRAMA EL CLIC NORMAL SOBRE LA TABLA 
            if (evt.getClickCount() == 2) {
                homologarDesdePanelPrincipal();
            }
        } else {
            if (SwingUtilities.isRightMouseButton(evt)) {
                Point p = evt.getPoint();
                int rowNumber = laboratorio.rowAtPoint(p);
                ListSelectionModel modE = laboratorio.getSelectionModel();
                modE.setSelectionInterval(rowNumber, rowNumber);
                popup.show(evt.getComponent(),
                        evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_laboratorioMousePressed

    private void homologacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homologacionMousePressed
    // Programar acciones sobre la tabla de homologaciones
        if (evt.getClickCount() == 2) {
            jButton4.doClick();
        } else {
            if (SwingUtilities.isRightMouseButton(evt)) {
                Point p = evt.getPoint();
                int rowNumber = homologacion.rowAtPoint(p);
                ListSelectionModel modE = homologacion.getSelectionModel();
                modE.setSelectionInterval(rowNumber, rowNumber);
                popup2.show(evt.getComponent(),
                        evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_homologacionMousePressed

    private void cb_filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_filtroActionPerformed
        if (String.valueOf(cb_filtro.getSelectedItem()).equals(" ") && !jButton11.isEnabled()) {
            JOptionPane.showMessageDialog(null, "Seleccione un criterio para Filtrar");
        } else {
            jTextField1.setText(null);
            jTextField1.setEnabled(true);
            jTextField1.requestFocus();
            jButton11.setEnabled(true);
        }
    }//GEN-LAST:event_cb_filtroActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    // Agregar una ID Alterna a la base de datos de Winsislab
        try {
            jpf.setText(null);
            jpf.requestFocus();
            int op = JOptionPane.showConfirmDialog(this, new Object[]{etiqueta, jpf}, "Nueva Id Alterna", JOptionPane.OK_CANCEL_OPTION);
            try {
                prop = PropertiesIO.read(ruta);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
            }

            if (JOptionPane.OK_OPTION == op) {
                if (String.valueOf(jpf.getPassword()).equals(prop.getProperty("claveSis"))) {
                    new NuevoIdAlterno().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Error en directiva de seguridad", "Clave incorrecta, intente nuevamente", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Proceso de creación cancelado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Proceso de homologación cancelado " + e.toString());
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton8KeyPressed
    // Agregar una ID Alterna a la base de datos de Winsislab
        try {
            jpf.setText(null);
            jpf.requestFocus();
            int op = JOptionPane.showConfirmDialog(this, new Object[]{etiqueta, jpf}, "Nueva Id Alterna", JOptionPane.OK_CANCEL_OPTION);
            try {
                prop = PropertiesIO.read(ruta);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
            }

            if (JOptionPane.OK_OPTION == op) {
                if (String.valueOf(jpf.getPassword()).equals(prop.getProperty("claveSis"))) {
                    new NuevoIdAlterno().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Error en directiva de seguridad", "Clave incorrecta, intente nuevamente", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Proceso de creación cancelado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Proceso de homologación cancelado " + e.toString());
        }
    }//GEN-LAST:event_jButton8KeyPressed

    private void btn_aexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aexcelActionPerformed
    // Descargar a Excel los datos de Exámenes homologados de winsislab
        if (homologacion.getRowCount() > 0) {
            JFileChooser ch = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Excel", "xls");
            ch.setFileFilter(filter);
            ch.setDialogTitle("Descargar Información de Homo_Exa_Imat");
            ch.setAcceptAllFileFilterUsed(false);
            if (ch.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String rta = ch.getSelectedFile().toString().concat(".xls");
                    File f = new File(rta);
                    if (f.exists()) {
                        f.delete();
                    }
                    f.createNewFile();
                    HSSFWorkbook libro = new HSSFWorkbook();
                    FileOutputStream arch = new FileOutputStream(f);
                    Sheet hoja = libro.createSheet("Homo_Exa_Imat");
                    //ENCABEZADOS DE COLUMNAS DE LA TABLA
                    Row fila0 = hoja.createRow(0);
                    //DEFINIMOS LOS ESTILOS PARA LOS ENCABEZADOS
                    HSSFFont fuente = libro.createFont();
                    fuente.setFontHeightInPoints((short) 10);
                    fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                    // Luego creamos el objeto que se encargará de aplicar el estilo a la celda
                    CellStyle estiloCelda = libro.createCellStyle();
                    estiloCelda.setWrapText(false);
                    estiloCelda.setShrinkToFit(false);
                    estiloCelda.setFont(fuente);

                    for (int k = 0; k < homologacion.getColumnCount(); k++) {
                        Cell celda0 = fila0.createCell(k);
                        celda0.setCellStyle(estiloCelda);
                        celda0.setCellValue(homologacion.getColumnName(k));
                    }

                    for (int fil = 0; fil < homologacion.getRowCount(); fil++) {
                        Row fila = hoja.createRow(fil + 1);
                        for (int cel = 0; cel < homologacion.getColumnCount(); cel++) {
                            Cell celda = fila.createCell(cel);
                            celda.setCellValue(String.valueOf(homologacion.getValueAt(fil, cel)));
                        }
                    }
                    libro.write(arch);
                    arch.close();
                    Desktop.getDesktop().open(f);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al intentar crear el archivo:" + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "La tabla no contiene datos");
        }
    }//GEN-LAST:event_btn_aexcelActionPerformed

    private void btn_aexcelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_aexcelKeyPressed
    // Descargar a Excel los datos de Exámenes homologados de winsislab
        if (homologacion.getRowCount() > 0) {
            JFileChooser ch = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Excel", "xls");
            ch.setFileFilter(filter);
            ch.setDialogTitle("Descargar Información de Homo_Exa_Imat");
            ch.setAcceptAllFileFilterUsed(false);
            if (ch.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String rta = ch.getSelectedFile().toString().concat(".xls");
                    File f = new File(rta);
                    if (f.exists()) {
                        f.delete();
                    }
                    f.createNewFile();
                    HSSFWorkbook libro = new HSSFWorkbook();
                    FileOutputStream arch = new FileOutputStream(f);
                    Sheet hoja = libro.createSheet("Homo_Exa_Imat");
                    //ENCABEZADOS DE COLUMNAS DE LA TABLA
                    Row fila0 = hoja.createRow(0);
                    //DEFINIMOS LOS ESTILOS PARA LOS ENCABEZADOS
                    HSSFFont fuente = libro.createFont();
                    fuente.setFontHeightInPoints((short) 10);
                    fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                    // Luego creamos el objeto que se encargará de aplicar el estilo a la celda
                    CellStyle estiloCelda = libro.createCellStyle();
                    estiloCelda.setWrapText(false);
                    estiloCelda.setShrinkToFit(false);
                    estiloCelda.setFont(fuente);

                    for (int k = 0; k < homologacion.getColumnCount(); k++) {
                        Cell celda0 = fila0.createCell(k);
                        celda0.setCellStyle(estiloCelda);
                        celda0.setCellValue(homologacion.getColumnName(k));
                    }

                    for (int fil = 0; fil < homologacion.getRowCount(); fil++) {
                        Row fila = hoja.createRow(fil + 1);
                        for (int cel = 0; cel < homologacion.getColumnCount(); cel++) {
                            Cell celda = fila.createCell(cel);
                            celda.setCellValue(String.valueOf(homologacion.getValueAt(fil, cel)));
                        }
                    }
                    libro.write(arch);
                    arch.close();
                    Desktop.getDesktop().open(f);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al intentar crear el archivo:" + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "La tabla no contiene datos");
        }
    }//GEN-LAST:event_btn_aexcelKeyPressed

    private void btn_desdeExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_desdeExcelActionPerformed
    // Cargar datos desde Excel a la base de datos de Winsislab tabla Homo_exa_Imat
        int op = JOptionPane.showConfirmDialog(this, new Object[]{etiqueta, jpf}, "Proceso de homologación", JOptionPane.OK_CANCEL_OPTION);
        try {
            prop = PropertiesIO.read(ruta);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
        }
        if (JOptionPane.OK_OPTION == op) {
            op = JOptionPane.showConfirmDialog(this, "Se dispone a modificar el contenido\nde la tabal homo_exa_imat de la \n"
                    + "base de datos DB winsislab\n"
                    + "¿desea continuar?", "Cargar datos a homo_exa_imat", JOptionPane.OK_CANCEL_OPTION);
            if (JOptionPane.OK_OPTION == op) {
                JFileChooser ch = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV delimitado por comas", "csv");
                ch.setFileFilter(filter);
                ch.setDialogTitle("Cargar Información a Homo_Exa_Imat");
                ch.setAcceptAllFileFilterUsed(false);
                if (ch.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        String rta = ch.getSelectedFile().toString();
                        JOptionPane.showMessageDialog(null, "RUTA -> " + rta);

                        try {
                            if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {
                                infoConn.datosConexionWinsislab();
                                cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                                        infoConn.getUs(), infoConn.getPas());
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Error Form principal\nAl tratar la conexion a Winsislab\n" + ex.getMessage());
                        }

                        hei.setConn(cwinsislab.getCon());
                        if (hei.cargar_desde_archivo_CSV(rta)) {
                            JOptionPane.showMessageDialog(null, "Datos cargados a homo_exa_imat desde: \n" + rta);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo cargar datos desde: \n" + rta);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al cargar datos:" + ex.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Se canceló en proceso de carga de datos");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Proceso de carga de datos cancelado");
        }

    }//GEN-LAST:event_btn_desdeExcelActionPerformed

    private void btn_desdeExcelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_desdeExcelKeyPressed
    // Cargar datos desde Excel a la base de datos de Winsislab tabla Homo_exa_Imat
        int op = JOptionPane.showConfirmDialog(this, new Object[]{etiqueta, jpf}, "Proceso de homologación", JOptionPane.OK_CANCEL_OPTION);

        try {
            prop = PropertiesIO.read(ruta);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
        }

        if (JOptionPane.OK_OPTION == op) {
            op = JOptionPane.showConfirmDialog(this, "Se dispone a modificar el contenido\nde la tabal homo_exa_imat de la \n"
                    + "base de datos DB winsislab\n"
                    + "¿desea continuar?", "Cargar datos a homo_exa_imat", JOptionPane.OK_CANCEL_OPTION);

            if (JOptionPane.OK_OPTION == op) {
                JFileChooser ch = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV delimitado por comas", "csv");
                ch.setFileFilter(filter);
                ch.setDialogTitle("Cargar Información a Homo_Exa_Imat");
                ch.setAcceptAllFileFilterUsed(false);
                if (ch.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        String rta = ch.getSelectedFile().toString();
                        JOptionPane.showMessageDialog(null, "RUTA -> " + rta);
                        try {
                            if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {
                                infoConn.datosConexionWinsislab();
                                cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                                        infoConn.getUs(), infoConn.getPas());
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Error Form principal\nAl tratar la conexion a Winsislab\n" + ex.getMessage());
                        }

                        hei.setConn(cwinsislab.getCon());
                        if (hei.cargar_desde_archivo_CSV(rta)) {
                            JOptionPane.showMessageDialog(null, "Datos cargados a homo_exa_imat desde: \n" + rta);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo cargar datos desde: \n" + rta);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al cargar datos:" + ex.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Se canceló en proceso de carga de datos");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Proceso de carga de datos cancelado");
        }
    }//GEN-LAST:event_btn_desdeExcelKeyPressed

    private void listaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMousePressed
    // Programación del evento de hacer clic derecho sobre la tabla Lista
        if (SwingUtilities.isRightMouseButton(evt)) {
            Point p = evt.getPoint();
            int rowNumber = lista.rowAtPoint(p);
            ListSelectionModel modE = lista.getSelectionModel();
            modE.setSelectionInterval(rowNumber, rowNumber);
            popup3.show(evt.getComponent(),
                    evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_listaMousePressed

    private void item_conexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_conexionActionPerformed
    // Opción de menú para abrir el archivo de configuracion de las conexiones de la interfaz
        jpf.setText(null);
        jpf.requestFocus();
        jpf.setEchoChar('*');
        int op = JOptionPane.showConfirmDialog(this, new Object[]{etiqueta, jpf}, "Proceso de configuración", JOptionPane.OK_CANCEL_OPTION);
        try {
            prop = PropertiesIO.read(ruta);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
        }
        if (JOptionPane.OK_OPTION == op) {
            if (String.valueOf(jpf.getPassword()).equals(prop.getProperty("claveSis"))) {
                FrmConfig cfg = new FrmConfig();
                cfg.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Error en directiva de seguridad", "Clave incorrecta, intente nuevamente", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Proceso de configuración cancelado");
        }
    }//GEN-LAST:event_item_conexionActionPerformed

    private void item_contrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_contrasenaActionPerformed
    // Abre el formulario para cambio de contraseña del usuario de la Interfaz
        FrmClaveUsuario ccu = new FrmClaveUsuario();
        ccu.setVisible(true);
    }//GEN-LAST:event_item_contrasenaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    // Finalizar la ejecución de la Interfaz
        btn_finalizar.doClick();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    // Código para cambiar los tiempos de ejecución de la interfaz
        jpf.setText(null);
        jpf.requestFocus();
        int op = JOptionPane.showConfirmDialog(this, new Object[]{etiqueta, jpf}, "Proceso de configuración", JOptionPane.OK_CANCEL_OPTION);
        try {
            prop = PropertiesIO.read(ruta);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
        }
        if (JOptionPane.OK_OPTION == op) {
            if (String.valueOf(jpf.getPassword()).equals(prop.getProperty("claveSis"))) {
                FrmTiemposEjecucion ftiempo = new FrmTiemposEjecucion();
                ftiempo.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Error en directiva de seguridad", "Clave incorrecta, intente nuevamente", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Proceso de configuración cancelado");
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btn_actualizaTabLabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_actualizaTabLabMouseClicked
    // Se utiliza para cambiar manualmente los tiempos de ejecución de los hilos de proceso de la interfaz
        try {
            if (Prueba1.chb_visualizar.isSelected()) {
                //laboratorio.setModel(objetoBD.actualizaTablaLaboratorio(cwinsislab.getCon(), true, dtf.format(fechaLab.getDate())));
                laboratorio.setModel(objetoBD.actualizaTablaLaboratorio(cwinsislab.getCon(), true, fechaListado()));
            } else {
                //laboratorio.setModel(objetoBD.actualizaTablaLaboratorio(cwinsislab.getCon(), false, dtf.format(fechaLab.getDate())));
                laboratorio.setModel(objetoBD.actualizaTablaLaboratorio(cwinsislab.getCon(), false, fechaListado()));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exception: " + ex.toString());
        }
        if (laboratorio.getRowCount() == 0) {
            modelo.setRowCount(0);
            modelo.addRow(new Object[]{"", "", "", "", "", "", "Laboratorios", "", "NO", "NO", "NO"});
            laboratorio.setModel(modelo);
        }
    }//GEN-LAST:event_btn_actualizaTabLabMouseClicked

    private void btn_actualizaTabLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizaTabLabActionPerformed
    // Se utiliza para cambiar manualmente los tiempos de ejecución de los hilos de proceso de la interfaz
        try {
            if (Prueba1.chb_visualizar.isSelected()) {
                //laboratorio.setModel(objetoBD.actualizaTablaLaboratorio(cwinsislab.getCon(), true, dtf.format(fechaLab.getDate())));
                laboratorio.setModel(objetoBD.actualizaTablaLaboratorio(cwinsislab.getCon(), true, fechaListado()));
        
            } else {
                //laboratorio.setModel(objetoBD.actualizaTablaLaboratorio(cwinsislab.getCon(), false, dtf.format(fechaLab.getDate())));
                laboratorio.setModel(objetoBD.actualizaTablaLaboratorio(cwinsislab.getCon(), false, fechaListado()));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exception: " + ex.toString());
        }
        if (laboratorio.getRowCount() == 0) {
            modelo.setRowCount(0);
            modelo.addRow(new Object[]{"", "", "", "", "", "", "Laboratorios", "", "NO", "NO", "NO"});
            laboratorio.setModel(modelo);
        }
    }//GEN-LAST:event_btn_actualizaTabLabActionPerformed

    private void btn_actTablaNoHomoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actTablaNoHomoActionPerformed
    // Actializar la tabla de exámenes no homologados
        actualizaTablaNoHomologados();
    }//GEN-LAST:event_btn_actTablaNoHomoActionPerformed

    private void btn_actTablaNoHomoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_actTablaNoHomoMouseClicked
    // Actializar la tabla de exámenes no homologados
        actualizaTablaNoHomologados();
    }//GEN-LAST:event_btn_actTablaNoHomoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    // Descargar a Excel información de la tabla de Labxs no homologados
        if (lista.getRowCount() > 0) {
            JFileChooser ch = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Excel", "xls");
            ch.setFileFilter(filter);
            ch.setDialogTitle("Descargar Información Lab. no homologados");
            ch.setAcceptAllFileFilterUsed(false);
            if (ch.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String rta = ch.getSelectedFile().toString().concat(".xls");
                    File f = new File(rta);
                    if (f.exists()) {
                        f.delete();
                    }
                    f.createNewFile();
                    HSSFWorkbook libro = new HSSFWorkbook();
                    FileOutputStream arch = new FileOutputStream(f);
                    Sheet hoja = libro.createSheet("Homologacion Lab");
                    //ENCABEZADOS DE COLUMNAS DE LA TABLA
                    Row fila0 = hoja.createRow(0);
                    //DEFINIMOS LOS ESTILOS PARA LOS ENCABEZADOS
                    HSSFFont fuente = libro.createFont();
                    fuente.setFontHeightInPoints((short) 10);
                    fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                    // Luego creamos el objeto que se encargará de aplicar el estilo a la celda
                    CellStyle estiloCelda = libro.createCellStyle();
                    estiloCelda.setWrapText(false);
                    estiloCelda.setShrinkToFit(false);
                    estiloCelda.setFont(fuente);

                    for (int k = 0; k < lista.getColumnCount(); k++) {
                        Cell celda0 = fila0.createCell(k);
                        celda0.setCellStyle(estiloCelda);
                        celda0.setCellValue(lista.getColumnName(k));
                    }

                    for (int fil = 0; fil < lista.getRowCount(); fil++) {
                        Row fila = hoja.createRow(fil + 1);
                        for (int cel = 0; cel < lista.getColumnCount(); cel++) {
                            Cell celda = fila.createCell(cel);
                            celda.setCellValue(String.valueOf(lista.getValueAt(fil, cel)));
                        }
                    }
                    libro.write(arch);
                    arch.close();
                    Desktop.getDesktop().open(f);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al intentar crear el archivo:" + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "La tabla no contiene datos");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
    // Descargar a Excel información de la tabla de Labxs no homologados
        if (lista.getRowCount() > 0) {
            JFileChooser ch = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Excel", "xls");
            ch.setFileFilter(filter);
            ch.setDialogTitle("Descargar Información Lab. no homologados");
            ch.setAcceptAllFileFilterUsed(false);
            if (ch.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String rta = ch.getSelectedFile().toString().concat(".xls");
                    File f = new File(rta);
                    if (f.exists()) {
                        f.delete();
                    }
                    f.createNewFile();
                    HSSFWorkbook libro = new HSSFWorkbook();
                    FileOutputStream arch = new FileOutputStream(f);
                    Sheet hoja = libro.createSheet("Homologacion Lab");
                    //ENCABEZADOS DE COLUMNAS DE LA TABLA
                    Row fila0 = hoja.createRow(0);
                    //DEFINIMOS LOS ESTILOS PARA LOS ENCABEZADOS
                    HSSFFont fuente = libro.createFont();
                    fuente.setFontHeightInPoints((short) 10);
                    fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                    // Luego creamos el objeto que se encargará de aplicar el estilo a la celda
                    CellStyle estiloCelda = libro.createCellStyle();
                    estiloCelda.setWrapText(false);
                    estiloCelda.setShrinkToFit(false);
                    estiloCelda.setFont(fuente);

                    for (int k = 0; k < lista.getColumnCount(); k++) {
                        Cell celda0 = fila0.createCell(k);
                        celda0.setCellStyle(estiloCelda);
                        celda0.setCellValue(lista.getColumnName(k));
                    }

                    for (int fil = 0; fil < lista.getRowCount(); fil++) {
                        Row fila = hoja.createRow(fil + 1);
                        for (int cel = 0; cel < lista.getColumnCount(); cel++) {
                            Cell celda = fila.createCell(cel);
                            celda.setCellValue(String.valueOf(lista.getValueAt(fil, cel)));
                        }
                    }
                    libro.write(arch);
                    arch.close();
                    Desktop.getDesktop().open(f);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al intentar crear el archivo:" + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "La tabla no contiene datos");
        }
    }//GEN-LAST:event_jButton1KeyPressed

    private void BTN_LimpiarNoHomoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_LimpiarNoHomoActionPerformed
        // TODO add your handling code here:
        LimpiarTablaNoHomologados();
    }//GEN-LAST:event_BTN_LimpiarNoHomoActionPerformed

    private void BTN_LimpiarNoHomoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BTN_LimpiarNoHomoKeyPressed
        LimpiarTablaNoHomologados();
    }//GEN-LAST:event_BTN_LimpiarNoHomoKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_LimpiarNoHomo;
    private javax.swing.JCheckBox CBOX_NoHomolog;
    private javax.swing.JFrame Cierre;
    private javax.swing.JFormattedTextField EDT_Dias;
    private javax.swing.JLabel STC_Dias;
    private javax.swing.JLabel STC_VisualizaLabs;
    public static javax.swing.JButton btn_actTablaNoHomo;
    public static javax.swing.JButton btn_actualizaTabLab;
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_aexcel;
    private javax.swing.JButton btn_desdeExcel;
    private javax.swing.JButton btn_finalizar;
    private javax.swing.JComboBox cb_filtro;
    public static javax.swing.JCheckBox chb_visualizar;
    public static com.toedter.calendar.JDateChooser fechaLab;
    public static javax.swing.JTable homologacion;
    private javax.swing.JMenuItem item_ayuda;
    private javax.swing.JMenuItem item_conexion;
    private javax.swing.JMenuItem item_contrasena;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    public static javax.swing.JTable laboratorio;
    public static javax.swing.JTable lista;
    private javax.swing.JMenu men_ayuda;
    private javax.swing.JPasswordField pass;
    public static javax.swing.JProgressBar pb_Agilis;
    public static javax.swing.JProgressBar pb_winsis;
    public static javax.swing.JLabel statusLabel;
    public static javax.swing.JLabel statusLabel2;
    // End of variables declaration//GEN-END:variables

// METODO ENCARGADO DE CERRAR LA APLICACION COMPLETA CON EL USO DE CONTRASEÑAS
    public void cerrar() {
        try {
            prop = PropertiesIO.read(ruta);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
        }

        if (String.valueOf(pass.getPassword()).equals(prop.getProperty("claveSis"))) {
            clave = prop.getProperty("claveSis");
            Cierre.dispose();
            serv.centinela = false;
            serv2.centinela = false;
            statusLabel.setText("Agilis: Proceso detenido...");
            statusLabel2.setText("Winsislab: Proceso detenido...");
            cwinsislab.CerrarCon();
            cagilis.CerrarCon();
            System.exit(0);                                                                                                         // <-- ESTA LINEA SE REMPLAZARÁ POSTERIORMENTE
        } else {
            JOptionPane.showMessageDialog(this, "Error en directiva de seguridad", "Clave de cierre incorrecta..", JOptionPane.ERROR_MESSAGE);
            pass.grabFocus();
            pass.selectAll();
        }
    }

// ESTABLECE LA IMAGEN O LOGO DE LA APP EN LA BARRA DE LOS FORMULARIOS
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Utiles/FORMULARIOS.png"));
        return retValue;
    }

// TABLA DE LABORATORIOS: ESTE METODO ACTUALIZA EL CONTENIDO DE LA TABLA LABORATORIOS
    public void listadoLabs() {
        try {                                                                                                                           // <-- Solo se listan Labx de 15 días
            if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {
                infoConn.datosConexionWinsislab();
                cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                        infoConn.getUs(), infoConn.getPas());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Form principal\nAl tratar la conexion a Winsislab\n" + ex.getMessage());
        }

        if (Prueba1.chb_visualizar.isSelected()) {
            try {
                synchronized (this) {
                    //laboratorio.setModel(objetoBD.actualizaTablaLaboratorio(cwinsislab.getCon(), true, dtf.format(fechaLab.getDate())));
                    laboratorio.setModel(objetoBD.actualizaTablaLaboratorio(cwinsislab.getCon(), true, fechaListado() ));            // <-- Solo lista 15 días de Labs o lo que indique días
                    //Logger.getLogger(Prueba1.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Formulario principal\nError al actualizar tabla Laboratorios: \n" + ex.getMessage());
            }
        } else {
            try {
                synchronized (this) {
                    //laboratorio.setModel(objetoBD.actualizaTablaLaboratorio(cwinsislab.getCon(), false, dtf.format(fechaLab.getDate())));
                    laboratorio.setModel(objetoBD.actualizaTablaLaboratorio(cwinsislab.getCon(), false,  fechaListado() ));         // <-- Solo lista 15 días de Labs o lo que indique días
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Formulario principal\nError al actualizar tabla Laboratorios: \n" + ex.getMessage());
            }
        }
    }

// SE PUEDE HOMOLOGAR DESDE LA TABLA DE LABORATORIO CON ESTE METODO
    public void homologarDesdePanelPrincipal() {
        String alterno = String.valueOf(laboratorio.getValueAt(laboratorio.getSelectedRow(), 6));
        if (alterno.equals("")) {
            JOptionPane.showMessageDialog(null, "No se ha determinado código alterno");
        } else {
            for (int i = 0; i < homologacion.getRowCount(); i++) {
                if (alterno.equals(homologacion.getValueAt(i, 0).toString())) {
                    try {
                        jpf.setText(null);
                        jpf.requestFocus();
                        int op = JOptionPane.showConfirmDialog(this, new Object[]{etiqueta, jpf}, "Proceso de homologación", JOptionPane.OK_CANCEL_OPTION);
                        try {
                            prop = PropertiesIO.read(ruta);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
                        }

                        if (JOptionPane.OK_OPTION == op) {

                            if (String.valueOf(jpf.getPassword()).equals(prop.getProperty("claveSis"))) {
                                homo_exa_imat.setAll(
                                        String.valueOf(homologacion.getValueAt(i, 0)),
                                        String.valueOf(homologacion.getValueAt(i, 1)),
                                        String.valueOf(homologacion.getValueAt(i, 2)),
                                        String.valueOf(homologacion.getValueAt(i, 3)),
                                        String.valueOf(homologacion.getValueAt(i, 4)));
                                new HomologacionHexamenes(homo_exa_imat).setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(this, "Error en directiva de seguridad", "Clave incorrecta, intente nuevamente", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Proceso de homologación cancelado");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Proceso de homologación cancelado");
                    }
                    break;
                }
            }
        }
    }

// SE PUEDE HOMOLOGAR DESDE LA TABLA DE NO HOMOLOGADOS CON ESTE METODO    
    public void homologarExamenAgilis() {
        String alterno = String.valueOf(lista.getValueAt(lista.getSelectedRow(), 8));
        if (alterno.equals("")) {
            JOptionPane.showMessageDialog(null, "No se ha determinado código alterno");
        } else {
            for (int i = 0; i < homologacion.getRowCount(); i++) {
                if (alterno.equals(homologacion.getValueAt(i, 0).toString())) {
                    try {
                        jpf.setText(null);
                        jpf.requestFocus();
                        int op = JOptionPane.showConfirmDialog(this, new Object[]{etiqueta, jpf}, "Proceso de homologación", JOptionPane.OK_CANCEL_OPTION);
                        try {
                            prop = PropertiesIO.read(ruta);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
                        }

                        if (JOptionPane.OK_OPTION == op) {

                            if (String.valueOf(jpf.getPassword()).equals(prop.getProperty("claveSis"))) {
                                homo_exa_imat.setAll(
                                        String.valueOf(homologacion.getValueAt(i, 0)),
                                        String.valueOf(homologacion.getValueAt(i, 1)),
                                        String.valueOf(homologacion.getValueAt(i, 2)),
                                        String.valueOf(homologacion.getValueAt(i, 3)),
                                        String.valueOf(homologacion.getValueAt(i, 4)));
                                new HomologacionHexamenes(homo_exa_imat).setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(this, "Error en directiva de seguridad", "Clave incorrecta, intente nuevamente", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Proceso de homologación cancelado");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Proceso de homologación cancelado");
                    }
                    break;
                }
            }
        }
    }

// AGREGAR LA AYUDA A LA INTERFAZ PARA ORIENTACIÓN DEL USUARIO    
    private void ponLaAyuda() {
        try {
            // Carga el fichero de ayuda
            File fichero = new File("./help/help_set.hs");
            URL hsURL = fichero.toURI().toURL();

            // Crea el HelpSet y el HelpBroker
            HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
            HelpBroker hb = helpset.createHelpBroker();
            // Pone ayuda a item de menu al pulsarlo y a F1 en ventana
            // principal y secundaria.
            hb.enableHelpOnButton(item_ayuda, "aplicacion", helpset);
            hb.enableHelpKey(this.getContentPane(), "ventana_principal",
                    helpset);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// SE ACTUALIZA EL LISTADO DE LABx QUE NO ESTAN HOMOLOGADOS EN EL CONTROL TABLA LISTA    
    public void actualizaTablaNoHomologados() {
        
        if( CBOX_NoHomolog.isSelected() ){                                                                                                    //<-- En esta parte se controla el listado de Labs. no homologados
        
            try {

                if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {
                    infoConn.datosConexionWinsislab();
                    cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                            infoConn.getUs(), infoConn.getPas());
                }
                if (cagilis.getCon() == null || cagilis.getCon().isClosed()) {
                    infoConn.datosConexionAgilis();
                    cagilis = new Conexion(infoConn.getDriver(), "jdbc:sqlserver://" + infoConn.getUrl(),
                            infoConn.getUs(), infoConn.getPas());
                }

                modAux = gLabo_ord.modeloTabla();
                modAux.setNumRows(0);

                gLabo_ord.setConn(cagilis.getCon());
                i = gLabo_ord.ordenesNoProcesadas("SELECT * FROM LABO_ORD WHERE ESTADO=0 ORDER BY ID ASC").iterator();

                hei.setConn(cwinsislab.getCon());
                while (i.hasNext()) {
                    labo_ord = (LABO_ORD) i.next();
                    if (hei.retornaIdAlterna(labo_ord.getCOD_EXAMEN()).equals("")) {
                        modAux.addRow(new Object[]{labo_ord.getID(), labo_ord.getNOADMISION(), labo_ord.getNUM_ORDEN(), labo_ord.getFECHA_RESULTADO(), labo_ord.getTIPO_DOC(),
                            labo_ord.getDOCUMENTO(), labo_ord.getAPELLIDO1() + " " + labo_ord.getAPELLIDO2(), labo_ord.getNOMBRE1() + " " + labo_ord.getNOMBRE2(),
                            labo_ord.getCOD_EXAMEN(), labo_ord.getNOM_EXAMEN(), labo_ord.getCOD_CENCOS(), labo_ord.getNOM_CENCOS(), "No existe ID_ALTERNA"});
                    } else if (hei.retornaCodigoWinsisLab(labo_ord.getCOD_EXAMEN()).equals("")) {
                        modAux.addRow(new Object[]{labo_ord.getID(), labo_ord.getNOADMISION(), labo_ord.getNUM_ORDEN(), labo_ord.getFECHA_RESULTADO(), labo_ord.getTIPO_DOC(),
                            labo_ord.getDOCUMENTO(), labo_ord.getAPELLIDO1() + " " + labo_ord.getAPELLIDO2(), labo_ord.getNOMBRE1() + " " + labo_ord.getNOMBRE2(),
                            labo_ord.getCOD_EXAMEN(), labo_ord.getNOM_EXAMEN(), labo_ord.getCOD_CENCOS(), labo_ord.getNOM_CENCOS(), "No está homologado"});
                    } else if (!(hei.existeIdWinsislab(hei.retornaCodigoWinsisLab(labo_ord.getCOD_EXAMEN())))) {
                        modAux.addRow(new Object[]{labo_ord.getID(), labo_ord.getNOADMISION(), labo_ord.getNUM_ORDEN(), labo_ord.getFECHA_RESULTADO(), labo_ord.getTIPO_DOC(),
                            labo_ord.getDOCUMENTO(), labo_ord.getAPELLIDO1() + " " + labo_ord.getAPELLIDO2(), labo_ord.getNOMBRE1() + " " + labo_ord.getNOMBRE2(),
                            labo_ord.getCOD_EXAMEN(), labo_ord.getNOM_EXAMEN(), labo_ord.getCOD_CENCOS(), labo_ord.getNOM_CENCOS(), "No existe id Winsislab"});
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Prueba1.class.getName()).log(Level.SEVERE, null, ex);
            }
            lista.setModel(modAux);
            CBOX_NoHomolog.setSelected(false);
        }
    }    

// LIMPIA LA TABLA DE LABx NO HOMOLOGADOS   
    private void LimpiarTablaNoHomologados() {
        try {
            
            if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {
                infoConn.datosConexionWinsislab();
                cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                        infoConn.getUs(), infoConn.getPas());
            }
            if (cagilis.getCon() == null || cagilis.getCon().isClosed()) {
                infoConn.datosConexionAgilis();
                cagilis = new Conexion(infoConn.getDriver(), "jdbc:sqlserver://" + infoConn.getUrl(),
                        infoConn.getUs(), infoConn.getPas());
            }

            modAux = gLabo_ord.modeloTabla();
            modAux.setNumRows(0);
            lista.setModel(modAux);
            CBOX_NoHomolog.setSelected(false);
          
        } catch (SQLException ex) {
            Logger.getLogger(Prueba1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// CONTROLA EL LISTADO DE LABx, EN LA TABLA DE TRAPASOS A WINSISLAB SOLO SE VERAN 15 DIAS DE LABORATORIOS O LO QUE INDIQUE LA CAJA DE TEXTO [EDT_Dias]    
    private String fechaListado() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        
        if( EDT_Dias.getValue() == null ){
            calendar.add(Calendar.DAY_OF_YEAR, -15);
        }else{        
            calendar.add(Calendar.DAY_OF_YEAR, ( Integer.parseInt(EDT_Dias.getValue().toString()) * -1 ) );
        }
        
        return dtf.format(calendar.getTime());
    }
}
