package view;

import Controler.Conexion;
import Controler.Homo_exa_imatDao;
import Controler.NotFoundException;
import Herramientas.DatosConexion;
import Model.Homo_exa_imat;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author fberrocalm
 */
public class NuevoIdAlterno extends javax.swing.JFrame {

    Homo_exa_imat hei, existe;
    Homo_exa_imatDao gHomo_exa_imat;
    DatosConexion infoConn; //Datos para establecer conexión con las bases de datos
    Conexion cwinsislab; //Conexiones a las bases de datos Agilis y Winsislab

    public NuevoIdAlterno() {
        initComponents();
        infoConn = new DatosConexion();
        this.setLocationRelativeTo(null);
        this.setIconImage(getIconImage());
        hei = new Homo_exa_imat();
        gHomo_exa_imat = new Homo_exa_imatDao();
        infoConn.datosConexionWinsislab();
        cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(), infoConn.getUs(), infoConn.getPas());
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_idservicio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_laboratorio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_idalterna = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_idwinsislab = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_idcompwinsis = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CREACION DE NUEVA ID ALTERNA DE EXAMEN EN WINSISLAB");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Homologación de Exámenes Winsislab"));

        jLabel1.setText("Id Servicio (Requerido):");

        txt_idservicio.setDisabledTextColor(new java.awt.Color(0, 0, 255));

        jLabel2.setText("Nombre del Laboratorio (Requerido):");

        txt_laboratorio.setDisabledTextColor(new java.awt.Color(0, 0, 255));

        jLabel3.setText("Id Alterna (Requerido):");

        txt_idalterna.setDisabledTextColor(new java.awt.Color(0, 0, 255));

        jLabel4.setText("Id Winsislab (Homologación):");

        jLabel5.setText("Id Compwinsis:");

        txt_idcompwinsis.setDisabledTextColor(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_idservicio)
                    .addComponent(txt_laboratorio)
                    .addComponent(txt_idalterna)
                    .addComponent(txt_idwinsislab)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txt_idcompwinsis))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_idservicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_laboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_idalterna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_idwinsislab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_idcompwinsis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/IDALTERNA1.png"))); // NOI18N
        jButton1.setText("Grabar nueva Id Alterna");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/CANCELAR.png"))); // NOI18N
        jButton2.setText("Salir");
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(286, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cwinsislab.CerrarCon();
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
        cwinsislab.CerrarCon();
        dispose();
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (txt_idservicio.getText().isEmpty() || txt_laboratorio.getText().isEmpty() || txt_idalterna.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete los datos requeridos para la creación");
        } else {
            gHomo_exa_imat.conectar();
            try {
                existe = gHomo_exa_imat.getObject(gHomo_exa_imat.getConn(), txt_idservicio.getText());
            } catch (NotFoundException | SQLException ex) {
                //JOptionPane.showMessageDialog(null, "Advertencia: Esta id alterna no existe: \n " + ex.getMessage());
            }
            gHomo_exa_imat.desconectar();

            int confirmado = JOptionPane.showConfirmDialog(this, "Confirme: ¿Desea grabar la nueva idAlterna?");
            if (JOptionPane.OK_OPTION == confirmado) {
                if (existe == null) {
                    try {
                        hei.setId_servicio(txt_idservicio.getText());
                        hei.setDetalle_servicio(txt_laboratorio.getText());
                        hei.setId_alterna(txt_idalterna.getText());
                        hei.setId_winsis(txt_idwinsislab.getText());
                        hei.setId_compwinsis(txt_idcompwinsis.getText());
                        try {
                            if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {
                                infoConn.datosConexionWinsislab();
                                cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                                        infoConn.getUs(), infoConn.getPas());
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Error Form Homologación\nAl tratar la conexion a Winsislab\n" + ex.getMessage());
                        }
                        gHomo_exa_imat.setConn(cwinsislab.getCon());
                        gHomo_exa_imat.create(hei);
                        Prueba1.homologacion.setModel(gHomo_exa_imat.listarExamenesHomologados());
                        limpiar();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error: no se pudo realizar la creación: \n " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Esta id Alterna ya existe en la Base de Datos...");
                    existe = null;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ha cancelado la homologación");
                limpiar();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
        if (txt_idservicio.getText().isEmpty() || txt_laboratorio.getText().isEmpty() || txt_idalterna.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete los datos requeridos para la creación");
        } else {
            gHomo_exa_imat.conectar();
            try {
                existe = gHomo_exa_imat.getObject(gHomo_exa_imat.getConn(), txt_idservicio.getText());
            } catch (NotFoundException | SQLException ex) {
                //JOptionPane.showMessageDialog(null, "Advertencia: Esta id alterna no existe: \n " + ex.getMessage());
            }
            gHomo_exa_imat.desconectar();

            int confirmado = JOptionPane.showConfirmDialog(this, "Confirme: ¿Desea grabar la nueva idAlterna?");
            if (JOptionPane.OK_OPTION == confirmado) {
                if (existe == null) {
                    try {
                        hei.setId_servicio(txt_idservicio.getText());
                        hei.setDetalle_servicio(txt_laboratorio.getText());
                        hei.setId_alterna(txt_idalterna.getText());
                        hei.setId_winsis(txt_idwinsislab.getText());
                        hei.setId_compwinsis(txt_idcompwinsis.getText());
                        try {
                            if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {
                                infoConn.datosConexionWinsislab();
                                cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                                        infoConn.getUs(), infoConn.getPas());
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Error Form Homologación\nAl tratar la conexion a Winsislab\n" + ex.getMessage());
                        }
                        gHomo_exa_imat.setConn(cwinsislab.getCon());
                        gHomo_exa_imat.create(hei);
                        Prueba1.homologacion.setModel(gHomo_exa_imat.listarExamenesHomologados());
                        limpiar();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error: no se pudo realizar la creación: \n " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Esta id Alterna ya existe en la Base de Datos...");
                    existe = null;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ha cancelado la homologación");
                limpiar();
            }
        }
    }//GEN-LAST:event_jButton1KeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txt_idalterna;
    private javax.swing.JTextField txt_idcompwinsis;
    private javax.swing.JTextField txt_idservicio;
    private javax.swing.JTextField txt_idwinsislab;
    private javax.swing.JTextField txt_laboratorio;
    // End of variables declaration//GEN-END:variables

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Utiles/FORMULARIOS.png"));
        return retValue;
    }

    public void limpiar() {
        txt_idalterna.setText(null);
        txt_idcompwinsis.setText(null);
        txt_idservicio.setText(null);
        txt_idwinsislab.setText(null);
        txt_laboratorio.setText(null);
        existe = null;
    }

}
