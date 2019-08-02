/**
 * formulario FrmLogin: En este punto de la App se le permite al usuario
 * Configurar los datos de conexión de la Interfaz, cambiar la contraseña del
 * usuario que controla la Interfaz e iniciar el funcionamiento de la Interfaz
 */
package view;

import Controler.Conexion;
import Herramientas.PropertiesAdmin;
import Herramientas.PropertiesIO;
import com.sun.awt.AWTUtilities;
import interfaz_lab.Control;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class FrmLogin extends javax.swing.JFrame {

    //Este objeto me permite administrar los atributos de conexiones y usuarios alamacenados en un archivo plano
    PropertiesAdmin gProperties;
    public static Connection conn;//-->CONEXION
    public static int a;
    public String usu, clave;
    private final SplashFrame splashFrame;

    Timer timer;

//    CONTROL CON USO DE CONTRASEÑA
    JPasswordField jpf;
    JLabel etiqueta;

//    ACCESO AL ARCHIVO DE PROPIEDADES DEL SISTEMA
    Properties prop = new Properties();
    File ruta = new File("etc/config.properties");

    @SuppressWarnings({"static-access"})
    /**
     * Creates new form FrmLogin
     */
    public FrmLogin(SplashFrame splashFrame) {
        jpf = new JPasswordField();
        etiqueta = new JLabel("Ingrese clave de Administrador?");
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        this.splashFrame = splashFrame;
        //ahora empiezo a setear el porcentaje, 0 al iniciar el constructor
        //todas las acciones en teoria se cargan en el constructor
        //y son esas acciones las que hacen que una aplicacion tarde en cargarse
        setProgress(0, "Cargando componentes...");
        setProgress(5, "Cargando componentes...");
        setProgress(10, "Cargando componentes...");
        setProgress(15, "Cargando componentes...");
        setProgress(20, "Componentes Cargados!!!");
        //en este caso como no tengo acciones que me demoren la aplicacion
        //invento algunas acciones
        setProgress(40, "Conectandose a los servidores...");

        gProperties = new PropertiesAdmin();
        gProperties.loadConfiguraciónSERVERS();

        //Varibles de configuración de la Aplicación usadas para el control del flujo del proceso
        String dms, urlms, ums, pms, dp, urlp, up, pp;
        dms = gProperties.getDriverMS();
        urlms = gProperties.getUrlMS();
        ums = gProperties.getUsuarioMS();
        pms = gProperties.getPasswordMS();
        dp = gProperties.getDriverPos();
        urlp = gProperties.getUrlPos();
        up = gProperties.getUsuarioPos();
        pp = gProperties.getPasswordPos();

//      CONEXION CON LA BASE DE DATOS DE WINSISLAB  
        Conexion c = new Conexion(dp, "jdbc:postgresql://" + urlp, up, pp);
        //Probando la conexion con Postgresql
        if (c.testCon()) {
            //conn = new Conexion(dms,urlms,ums,pms).getCon();
            //entre cada porcentaje que seteo deberian acciones que se ejecutan
            setProgress(50, "Conectado al servidor POSTGRESQL!!!");
//      CONEXION CON LA BASE DE DATOS AGILIS
            Conexion ca = new Conexion(dms, "jdbc:sqlserver://" + urlms, ums, pms);
            //Probando la conexion con SQL Server
            if (ca.testCon()) {
                //conn = new Conexion(dms,urlms,ums,pms).getCon();
                //esto solo es un demo
                setProgress(60, "Conectado al servidor MS SERVER!!!");
                setProgress(80, "Cargando interfaz grafica...");
                initComponents();
                Shape forma = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 20, 20);
                AWTUtilities.setWindowShape(this, forma);
                //de lo que seria un Splash Screen
                ca.CerrarCon();
                setProgress(90, "Interfaz grafica cargada!!!");
                setProgress(100, "Bienvenido al sistema");
            } else {
                ca.CerrarCon();
                FrmConfig cfg = new FrmConfig();
                cfg.setVisible(true);
                dispose();
            }
            c.CerrarCon();
        } else {
            c.CerrarCon();
            FrmConfig cfg = new FrmConfig();
            cfg.setVisible(true);
            dispose();
        }
        //los porcentajes los calculo segun las acciones que se ejecuten
        // usuario.setDocument(new Mayusculas());
        timer = new Timer(10000, new MyTimerActionListener());
        timer.start();
    }

    private void setProgress(int percent, String information) {
        splashFrame.getLabel().setText(information);
        splashFrame.getProgressBar().setValue(percent);
        try {
            Thread.sleep(400);
        } catch (InterruptedException ex) {
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btn_Configurar = new javax.swing.JButton();
        jcMousePanel1 = new jcMousePanel.jcMousePanel();
        jLabel2 = new javax.swing.JLabel();
        btn_CambioClave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        btnAceptar.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/OK1.png"))); // NOI18N
        btnAceptar.setMnemonic('A');
        btnAceptar.setText("Ejecutar la Interfaz");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        btnAceptar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAceptarKeyPressed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/CANCELAR.png"))); // NOI18N
        btnCancelar.setMnemonic('C');
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        btnCancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelarKeyPressed(evt);
            }
        });

        btn_Configurar.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btn_Configurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/IDALTERNA1.png"))); // NOI18N
        btn_Configurar.setMnemonic('o');
        btn_Configurar.setText("Configuración de la Aplicación");
        btn_Configurar.setToolTipText("Configure el servidor, base de datos y usuarios");
        btn_Configurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ConfigurarActionPerformed(evt);
            }
        });
        btn_Configurar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_ConfigurarKeyPressed(evt);
            }
        });

        jcMousePanel1.setColor1(new java.awt.Color(102, 102, 255));
        jcMousePanel1.setColor2(new java.awt.Color(255, 153, 255));
        jcMousePanel1.setModo(3);
        jcMousePanel1.setVisibleLogo(false);

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Iniciar Interfaz Lab");

        javax.swing.GroupLayout jcMousePanel1Layout = new javax.swing.GroupLayout(jcMousePanel1);
        jcMousePanel1.setLayout(jcMousePanel1Layout);
        jcMousePanel1Layout.setHorizontalGroup(
            jcMousePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jcMousePanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jcMousePanel1Layout.setVerticalGroup(
            jcMousePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jcMousePanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        btn_CambioClave.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btn_CambioClave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/usuario.png"))); // NOI18N
        btn_CambioClave.setMnemonic('o');
        btn_CambioClave.setText("Cambiar contraseña de Administrador");
        btn_CambioClave.setToolTipText("Configure el servidor, base de datos y usuarios");
        btn_CambioClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CambioClaveActionPerformed(evt);
            }
        });
        btn_CambioClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_CambioClaveKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Configurar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btn_CambioClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jcMousePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jcMousePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Configurar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_CambioClave)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAceptar, btnCancelar, btn_CambioClave, btn_Configurar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

//  METODO PARA EJECUTAR LA INTERFAZ 
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        //TODO add your handling code here:
        //usu.setUsuario(usuario.getText());
        //usu.setPassword(String.valueOf(password.getPassword()));
        //gProperties.loadConfigutrationSystemUser();
        //String usu = gProperties.getUsuarioSis();
        //String clave = gProperties.getClaveSis();
        SwingUtilities.invokeLater(() -> {
            try {
                if (new Control().comprobar()) {
                    Prueba1 P = new Prueba1();
                    P.setVisible(true);
                    P.lanzar();
                    timer.stop();
                    dispose();
                } else {
                    System.exit(0);
                }
            } catch (Exception ex) {
                reporte("Error al intentar iniciar la Aplicación: \n contacte al Administrador de sistemas" + ex.getMessage());
                ex.printStackTrace();
            }
        });

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        timer.stop();
        System.exit(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btn_ConfigurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConfigurarActionPerformed
        // TODO add your handling code here:
        timer.stop();
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
    }//GEN-LAST:event_btn_ConfigurarActionPerformed

    private void btnCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyPressed
        // TODO add your handling code here:
        timer.stop();
        System.exit(0);
    }//GEN-LAST:event_btnCancelarKeyPressed

    private void btnAceptarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAceptarKeyPressed
        // TODO add your handling code here:
        /*
         try {
         Prueba1 P = new Prueba1();
         P.setVisible(true);
         P.lanzar();
         dispose();
         } catch (Exception ex) {
         reporte("Error al intentar iniciar la Aplicación: \n contacte al Administrador de sistemas" + ex);
         }*/
    }//GEN-LAST:event_btnAceptarKeyPressed

    private void btn_ConfigurarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_ConfigurarKeyPressed
        // TODO add your handling code here:
        timer.stop();
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
    }//GEN-LAST:event_btn_ConfigurarKeyPressed

    private void btn_CambioClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CambioClaveActionPerformed
        // TODO add your handling code here:
        timer.stop();
        FrmClaveUsuario ccu = new FrmClaveUsuario();
        ccu.setVisible(true);
    }//GEN-LAST:event_btn_CambioClaveActionPerformed

    private void btn_CambioClaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_CambioClaveKeyPressed
        // TODO add your handling code here:
        timer.stop();
        FrmClaveUsuario ccu = new FrmClaveUsuario();
        ccu.setVisible(true);
    }//GEN-LAST:event_btn_CambioClaveKeyPressed
    //NETBEANS ME CREA ESTE METODO, QUE HACE QUE EL FRAME SEA AUTO INSTANCIABLE
    //LO PUEDO ELIMINAR PORQUE NO LO NESECITO Y ME CREARIA UN ERROR EN EL CONSTRUCTOR

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btn_CambioClave;
    private javax.swing.JButton btn_Configurar;
    private javax.swing.JLabel jLabel2;
    private jcMousePanel.jcMousePanel jcMousePanel1;
    // End of variables declaration//GEN-END:variables

    private void reporte(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Utiles/FORMULARIOS.png"));
        return retValue;
    }

    class MyTimerActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            btnAceptar.doClick();
            // new NewJFrame().setVisible(true);
            //System.out.println("asdf");

        }
    }
}
