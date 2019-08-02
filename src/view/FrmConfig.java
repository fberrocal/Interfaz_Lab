package view;

import Controler.Conexion;
import Herramientas.PropertiesIO;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMINIMAT Creado en: 23/02/2015
 */
public class FrmConfig extends javax.swing.JFrame {

    //URL ruta = this.getClass().getResource("/Config/config.properties");
    Properties prop = new Properties();
    File ruta = new File("etc/config.properties");

    /**
     * Creates new form FrmCofngi
     */
    public FrmConfig() {
        initComponents();
        this.setLocationRelativeTo(null);
        try {
            prop = PropertiesIO.read(ruta);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
        }
        driverMS.setText(prop.getProperty("driverMS"));
        urlMs.setText(prop.getProperty("urlMS"));
        usuarioMS.setText(prop.getProperty("usuarioMS"));
        passMS.setText(prop.getProperty("passwordMS"));
        driverPos.setText(prop.getProperty("driverPos"));
        urlPos.setText(prop.getProperty("urlPos"));
        usuarioPos.setText(prop.getProperty("usuarioPos"));
        passPos.setText(prop.getProperty("passwordPos"));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        jLabel4 = new javax.swing.JLabel();
        urlMs = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        driverMS = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passMS = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        usuarioMS = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnProbar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        driverPos = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        urlPos = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        usuarioPos = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        passPos = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnGuardar1 = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel4.setText("Usuario:");

        urlMs.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel3.setText("Url Base de Datos:");

        driverMS.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel2.setText("Driver:");

        passMS.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 26, 135));
        jLabel1.setText("Configuración Servidor y Base de Datos");

        usuarioMS.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel5.setText("Password:");

        btnProbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/conn.png"))); // NOI18N
        btnProbar.setText("Probar Conexión");
        btnProbar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProbarActionPerformed(evt);
            }
        });
        btnProbar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnProbarKeyPressed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/OK.png"))); // NOI18N
        btnGuardar.setText("Guardar Cambios");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        btnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGuardarKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel7.setText("Driver:");

        driverPos.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel9.setText("Url Base de Datos:");

        urlPos.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel10.setText("Usuario:");

        usuarioPos.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel11.setText("Password:");

        passPos.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Configuración servidor AGILIS:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Configuración servidor WINSISLAB:");

        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/CANCELAR.png"))); // NOI18N
        btnGuardar1.setText("Cancelar");
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        btnGuardar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGuardar1KeyPressed(evt);
            }
        });

        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utiles/Edit.png"))); // NOI18N
        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });
        btn_editar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_editarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnProbar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(usuarioMS)
                                        .addComponent(urlMs, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                        .addComponent(passMS)
                                        .addComponent(driverMS, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_editar))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(usuarioPos)
                                .addComponent(driverPos)
                                .addComponent(urlPos)
                                .addComponent(passPos, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel12)
                        .addComponent(jLabel13)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {driverMS, driverPos, passMS, passPos, urlMs, urlPos, usuarioMS, usuarioPos});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnGuardar, btnGuardar1, btnProbar, btn_editar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(driverMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(urlMs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(usuarioMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(passMS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(driverPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(urlPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(usuarioPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(passPos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProbar)
                    .addComponent(btnGuardar)
                    .addComponent(btnGuardar1))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //PROBAR LOS DATOS DE CONEXION A LAS BASES DE DATOS DE AGILIS Y WINSISLAB
    private void btnProbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProbarActionPerformed
        // TODO add your handling code here:
        Conexion con1 = new Conexion(driverMS.getText(), "jdbc:sqlserver://" + urlMs.getText(),
                usuarioMS.getText(), String.valueOf(passMS.getPassword()));
        Conexion con2 = new Conexion(driverPos.getText(), "jdbc:postgresql://" + urlPos.getText(),
                usuarioPos.getText(), String.valueOf(passPos.getPassword()));

        if (con1.getCon() != null) {
            JOptionPane.showMessageDialog(null, "Conexión con Agilis exitosa");
            if (con2.getCon() != null) {
                JOptionPane.showMessageDialog(null, "Conexión con WINSISLAB exitosa");
            } else {
                JOptionPane.showMessageDialog(null, "Error en la conexión con WINSISLAB");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error en la conexión con AGILIS");
        }
//      SE FINALIZAN LAS CONEXIONES ESTABLECIDAS CON LAS BASES DE DATOS
        con1.CerrarCon();
        con2.CerrarCon();
    }//GEN-LAST:event_btnProbarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        int op = JOptionPane.showConfirmDialog(this, "Se dispone a modificar datos de conexión\na las bases de datos del sistema \n"
                + "¿desea continuar?", "Cambio de datos de Conexión", JOptionPane.OK_CANCEL_OPTION);
        if (JOptionPane.OK_OPTION == op) {
            op = JOptionPane.showConfirmDialog(this, "Confirme, ¿Desea hacer permanentes los cambios?"
                    + "¿desea continuar?", "Cambio de datos de Conexión", JOptionPane.OK_CANCEL_OPTION);
            if (JOptionPane.OK_OPTION == op) {
                prop.setProperty("driverMS", driverMS.getText());
                prop.setProperty("urlMS", urlMs.getText());
                prop.setProperty("usuarioMS", usuarioMS.getText());
                prop.setProperty("passwordMS", String.valueOf(passMS.getPassword()));
                prop.setProperty("driverPos", driverPos.getText());
                prop.setProperty("urlPos", urlPos.getText());
                prop.setProperty("usuarioPos", usuarioPos.getText());
                prop.setProperty("passwordPos", String.valueOf(passPos.getPassword()));
                try {
                    PropertiesIO.write(prop, ruta);
                    JOptionPane.showMessageDialog(null, "Guardado");
                    btnGuardar.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Necesita reiniciar la aplicación para aplicar los cambios");
                    restartApplication();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Se canceló el proceso de cambio de datos");
                btnGuardar.setEnabled(false);
                driverMS.setText(prop.getProperty("driverMS"));
                urlMs.setText(prop.getProperty("urlMS"));
                usuarioMS.setText(prop.getProperty("usuarioMS"));
                passMS.setText(prop.getProperty("passwordMS"));
                driverPos.setText(prop.getProperty("driverPos"));
                urlPos.setText(prop.getProperty("urlPos"));
                usuarioPos.setText(prop.getProperty("usuarioPos"));
                passPos.setText(prop.getProperty("passwordPos"));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Se canceló el proceso de cambio de datos");
            btnGuardar.setEnabled(false);
            driverMS.setText(prop.getProperty("driverMS"));
            urlMs.setText(prop.getProperty("urlMS"));
            usuarioMS.setText(prop.getProperty("usuarioMS"));
            passMS.setText(prop.getProperty("passwordMS"));
            driverPos.setText(prop.getProperty("driverPos"));
            urlPos.setText(prop.getProperty("urlPos"));
            usuarioPos.setText(prop.getProperty("usuarioPos"));
            passPos.setText(prop.getProperty("passwordPos"));
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnGuardar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardar1KeyPressed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnGuardar1KeyPressed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        // TODO add your handling code here:
        if (driverMS.isEnabled()) {
            driverMS.setEnabled(false);
            driverPos.setEnabled(false);
            passMS.setEnabled(false);
            passPos.setEnabled(false);
            urlMs.setEnabled(false);
            urlPos.setEnabled(false);
            usuarioMS.setEnabled(false);
            usuarioPos.setEnabled(false);
            btnGuardar.setEnabled(false);
        } else {
            driverMS.setEnabled(true);
            driverPos.setEnabled(true);
            passMS.setEnabled(true);
            passPos.setEnabled(true);
            urlMs.setEnabled(true);
            urlPos.setEnabled(true);
            usuarioMS.setEnabled(true);
            usuarioPos.setEnabled(true);
            btnGuardar.setEnabled(true);
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_editarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_editarKeyPressed
        // TODO add your handling code here:
        if (driverMS.isEnabled()) {
            driverMS.setEnabled(false);
            driverPos.setEnabled(false);
            passMS.setEnabled(false);
            passPos.setEnabled(false);
            urlMs.setEnabled(false);
            urlPos.setEnabled(false);
            usuarioMS.setEnabled(false);
            usuarioPos.setEnabled(false);
            btnGuardar.setEnabled(false);
        } else {
            driverMS.setEnabled(true);
            driverPos.setEnabled(true);
            passMS.setEnabled(true);
            passPos.setEnabled(true);
            urlMs.setEnabled(true);
            urlPos.setEnabled(true);
            usuarioMS.setEnabled(true);
            usuarioPos.setEnabled(true);
            btnGuardar.setEnabled(true);
        }
    }//GEN-LAST:event_btn_editarKeyPressed

    private void btnGuardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyPressed
        // TODO add your handling code here:
        int op = JOptionPane.showConfirmDialog(this, "Se dispone a modificar datos de conexión\na las bases de datos del sistema \n"
                + "¿desea continuar?", "Cambio de datos de Conexión", JOptionPane.OK_CANCEL_OPTION);

        if (JOptionPane.OK_OPTION == op) {
            op = JOptionPane.showConfirmDialog(this, "Confirme, ¿Desea hacer permanentes los cambios?"
                    + "¿desea continuar?", "Cambio de datos de Conexión", JOptionPane.OK_CANCEL_OPTION);
            if (JOptionPane.OK_OPTION == op) {
                prop.setProperty("driverMS", driverMS.getText());
                prop.setProperty("urlMS", urlMs.getText());
                prop.setProperty("usuarioMS", usuarioMS.getText());
                prop.setProperty("passwordMS", String.valueOf(passMS.getPassword()));
                prop.setProperty("driverPos", driverPos.getText());
                prop.setProperty("urlPos", urlPos.getText());
                prop.setProperty("usuarioPos", usuarioPos.getText());
                prop.setProperty("passwordPos", String.valueOf(passPos.getPassword()));

                try {
                    PropertiesIO.write(prop, ruta);
                    JOptionPane.showMessageDialog(null, "Guardado");
                    btnGuardar.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Necesita reiniciar la aplicación para aplicar los cambios");
                    restartApplication();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Se canceló en proceso de cambio de datos");
                btnGuardar.setEnabled(false);
                driverMS.setText(prop.getProperty("driverMS"));
                urlMs.setText(prop.getProperty("urlMS"));
                usuarioMS.setText(prop.getProperty("usuarioMS"));
                passMS.setText(prop.getProperty("passwordMS"));
                driverPos.setText(prop.getProperty("driverPos"));
                urlPos.setText(prop.getProperty("urlPos"));
                usuarioPos.setText(prop.getProperty("usuarioPos"));
                passPos.setText(prop.getProperty("passwordPos"));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Se canceló en proceso de cambio de datos");
            btnGuardar.setEnabled(false);
            driverMS.setText(prop.getProperty("driverMS"));
            urlMs.setText(prop.getProperty("urlMS"));
            usuarioMS.setText(prop.getProperty("usuarioMS"));
            passMS.setText(prop.getProperty("passwordMS"));
            driverPos.setText(prop.getProperty("driverPos"));
            urlPos.setText(prop.getProperty("urlPos"));
            usuarioPos.setText(prop.getProperty("usuarioPos"));
            passPos.setText(prop.getProperty("passwordPos"));
        }
    }//GEN-LAST:event_btnGuardarKeyPressed

    private void btnProbarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnProbarKeyPressed
        // TODO add your handling code here:
        // TODO add your handling code here:
        Conexion con1 = new Conexion(driverMS.getText(), "jdbc:sqlserver://" + urlMs.getText(),
                usuarioMS.getText(), String.valueOf(passMS.getPassword()));
        Conexion con2 = new Conexion(driverPos.getText(), "jdbc:postgresql://" + urlPos.getText(),
                usuarioPos.getText(), String.valueOf(passPos.getPassword()));

        if (con1.getCon() != null) {
            JOptionPane.showMessageDialog(null, "Conexión con Agilis exitosa");
            if (con2.getCon() != null) {
                JOptionPane.showMessageDialog(null, "Conexión con WINSISLAB exitosa");
            } else {
                JOptionPane.showMessageDialog(null, "Error en la conexión con WINSISLAB");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error en la conexión con AGILIS");
        }
//      SE FINALIZAN LAS CONEXIONES ESTABLECIDAS CON LAS BASES DE DATOS
        con1.CerrarCon();
        con2.CerrarCon();
    }//GEN-LAST:event_btnProbarKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnProbar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JTextField driverMS;
    private javax.swing.JTextField driverPos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField passMS;
    private javax.swing.JPasswordField passPos;
    private javax.swing.JTextField urlMs;
    private javax.swing.JTextField urlPos;
    private javax.swing.JTextField usuarioMS;
    private javax.swing.JTextField usuarioPos;
    // End of variables declaration//GEN-END:variables

    /**
     * Sun property pointing the main class and its arguments. Might not be
     * defined on non Hotspot VM implementations.
     */
    public static final String SUN_JAVA_COMMAND = "sun.java.command";

    /**
     * Restart the current Java application
     *
     * @param runBeforeRestart some custom code to be run before restarting
     * @throws IOException
     */
    public static void restartApplication(Runnable runBeforeRestart) throws IOException {
        try {
            // java binary
            String java = System.getProperty("java.home") + "/bin/java";
            // vm arguments
            List<String> vmArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
            StringBuffer vmArgsOneLine = new StringBuffer();
            for (String arg : vmArguments) {
                // if it's the agent argument : we ignore it otherwise the
                // address of the old application and the new one will be in conflict
                if (!arg.contains("-agentlib")) {
                    vmArgsOneLine.append(arg);
                    vmArgsOneLine.append(" ");
                }
            }
            // init the command to execute, add the vm args
            final StringBuffer cmd = new StringBuffer("\"" + java + "\" " + vmArgsOneLine);

            // program main and program arguments
            String[] mainCommand = System.getProperty(SUN_JAVA_COMMAND).split(" ");
            // program main is a jar
            if (mainCommand[0].endsWith(".jar")) {
                // if it's a jar, add -jar mainJar
                cmd.append("-jar ").append(new File(mainCommand[0]).getPath());
            } else {
                // else it's a .class, add the classpath and mainClass
                cmd.append("-cp \"").append(System.getProperty("java.class.path")).append("\" ").append(mainCommand[0]);
            }
            // finally add program arguments
            for (int i = 1; i < mainCommand.length; i++) {
                cmd.append(" ");
                cmd.append(mainCommand[i]);
            }
            // execute the command in a shutdown hook, to be sure that all the
            // resources have been disposed before restarting the application
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    try {
                        Runtime.getRuntime().exec(cmd.toString());
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            });
            // execute some custom code before restarting
            if (runBeforeRestart != null) {
                runBeforeRestart.run();
            }
            // exit
            System.exit(0);
        } catch (Exception e) {
            // something went wrong
            throw new IOException("Error while trying to restart the application", e);
        }
    }

    public void restartApplication() {
        final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        final File currentJar = new File(System.getProperty("user.dir") + "/AppInventario.jar");

        /* is it a jar file? */
        if (!currentJar.getName().endsWith(".jar")) {
            return;
        }

        /* Build command: java -jar application.jar */
        final ArrayList<String> command = new ArrayList<String>();
        command.add(javaBin);
        command.add("-jar");
        command.add(currentJar.getPath());

        final ProcessBuilder builder = new ProcessBuilder(command);
        try {
            builder.start();
        } catch (IOException ex) {
            Logger.getLogger(FrmConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }
}
