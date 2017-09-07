package visual;

import consultas.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.List;
import javax.swing.*;
import misc.Tools;
import objetos_datos.*;

public class FormTalleres extends javax.swing.JFrame {
    private List<Taller> talleresHoy;
    private Taller tallerActual;
    private ActionButtom accionActual;
    private Alumno alumno;
    private String textoAnterior;
    
    public FormTalleres() {
        initComponents();
        mostrarTalleres();
        
        this.setLocationRelativeTo(null);
        jPanel1.setBackground(new Color(250, 250, 250));
        jPanel2.setBackground(new Color(250, 250, 250));
        jPanel3.setBackground(new Color(240, 248, 255));
        jPanel5.setBackground(new Color(240, 248, 255));
        this.getContentPane().setBackground(new Color(250,250,250));
    }
    
    private void mostrarTalleres(){
        talleresHoy = TalleresData.getTalleresHoy();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for(Taller taller : talleresHoy){
            modelo.addElement(taller.getNombreTaller());
        }
        listaTalleres.setModel(modelo);
        tallerActual = talleresHoy.get(0);
        txtCupos.setText(String.valueOf(tallerActual.getCuposActuales()));
        accionActual = getActionVerificar();
        textoAnterior = "";
        this.setIconImage(new ImageIcon(getClass().getResource("/img/ceia 2015.jpg")).getImage());
        
        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        String action = "action";
        txtCarnet.getInputMap().put(key, action);
        txtCarnet.getActionMap().put(action, onEnter());
    }
    
    private Action onEnter(){
        return new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtCarnet.isEditValid()){
                    try {
                        txtCarnet.getFormatter().valueToString(txtCarnet.getText());
                        accionActual.exec();
                    } catch (ParseException ex) {
                        txtCarnet.setText("");
                        txtCarnet.requestFocusInWindow();
                    }
                }
            }
        };
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtCarnet = new javax.swing.JFormattedTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnAccion = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        output = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        listaTalleres = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtCupos = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtApellidos = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCarrera = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulario para talleres");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Carnet:");

        try {
            txtCarnet.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UU#####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCarnet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCarnetKeyReleased(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnAccion.setText("Verificar si esta registrado");
        btnAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(241, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAccion)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(txtCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        output.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        output.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(output, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(output, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos sobre el taller", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N

        jLabel8.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8.setText("Cupos disponibles:");

        listaTalleres.setForeground(new java.awt.Color(0, 102, 102));
        listaTalleres.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listaTalleresItemStateChanged(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(0, 102, 153));
        jLabel9.setText("Nombre del taller");

        txtCupos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCupos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCupos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtCupos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtCupos.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtCupos))
                    .addComponent(jLabel9)
                    .addComponent(listaTalleres, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(listaTalleres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img2.jpg"))); // NOI18N
        jLabel10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos sobre el estudiante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 102, 153));

        txtApellidos.setEnabled(false);

        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setText("Carerra:");

        jLabel12.setForeground(new java.awt.Color(0, 102, 153));
        jLabel12.setText("Nombres:");

        txtCarrera.setEnabled(false);

        txtNombres.setEnabled(false);

        jLabel13.setForeground(new java.awt.Color(0, 102, 153));
        jLabel13.setText("Apellidos:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombres)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(txtCarrera))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Tools.mostrarCierreSecion();
        new FormLogin().setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void txtCarnetKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarnetKeyReleased
        if(evt.getKeyCode() != KeyEvent.VK_ENTER){
            String texto = txtCarnet.getText();
            if(!textoAnterior.equals(texto)){
                accionActual = getActionVerificar();
                textoAnterior = texto;
                txtApellidos.setText("");
                txtNombres.setText("");
                txtCarrera.setText("");
                output.setText("");
                btnAccion.setText("Verificar si esta registrado");
            }
        }
    }//GEN-LAST:event_txtCarnetKeyReleased

    private void btnAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccionActionPerformed
        accionActual.exec();
    }//GEN-LAST:event_btnAccionActionPerformed

    private void listaTalleresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listaTalleresItemStateChanged
        String nombreTaller = listaTalleres.getSelectedItem().toString();
        tallerActual = talleresHoy.stream()
        .filter(taller -> nombreTaller.equals(taller.getNombreTaller()))
        .findFirst().get();
        txtCupos.setText(String.valueOf(tallerActual.getCuposActuales()));
    }//GEN-LAST:event_listaTalleresItemStateChanged
    
    private ActionButtom getActionVerificar(){
        return new ActionButtom() {

            @Override
            public void exec() {
                if(txtCarnet.isEditValid()){
                    if(existeAlumnoIngresado()){
                        cambiarAction();
                        mostrarAlumno();
                    }
                    else{
                        procesarIngresoNuevoAlumno();
                    }
                }
            }
            
            private boolean existeAlumnoIngresado(){
                return AlumnoData.isAlumnoRegistrado(txtCarnet.getText());
            }
            
            private void cambiarAction(){
                alumno = AlumnoData.getAlumno(txtCarnet.getText());
                accionActual = getActionInsertar();
                btnAccion.setText("Registrar alumno en el taller");
            }
            
            private void procesarIngresoNuevoAlumno(){
                FormNuevoAlumno dialog = new FormNuevoAlumno(null, txtCarnet.getText());
                dialog.setVisible(true);
                try{
                    alumno = dialog.getAlumnoIngresado();
                    insertar();
                }
                catch(IndexOutOfBoundsException ex){
                    output.setText(ex.getMessage());
                }
                catch(IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    limpiar();
                }
                catch(UnsupportedOperationException ex){
                    JOptionPane.showMessageDialog(null, "Esta persona ya se ha registrado en este taller");
                    limpiar();
                }
                //Excepcion arrojada por el dialog para saber si no se agrego alumno nuevo
                catch(IllegalStateException ex){}
            }
            
        };
    }
    
    private ActionButtom getActionInsertar(){
        return new ActionButtom() {

            @Override
            public void exec() {
                try{
                    insertar();
                    cambiarAction();
                }
                catch(IndexOutOfBoundsException ex){
                    output.setText(ex.getMessage());
                }
                catch(IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    limpiar();
                }
                catch(UnsupportedOperationException ex){
                    JOptionPane.showMessageDialog(null, "Esta persona ya se ha registrado en este taller");
                    limpiar();
                }
            }
            
            private void cambiarAction(){
                accionActual = getActionVerificar();
                btnAccion.setText("Verificar si esta registrado");
            }
        };
    }
    
    private void insertar(){
        AsistenciaPorTallerData.ingresarAlumnoEnTaller(alumno, tallerActual);
        limpiar();
        printExito();
        actualizarCupos();
    }
    
    private void mostrarAlumno(){
        txtApellidos.setText(alumno.getApellidos());
        txtNombres.setText(alumno.getNombres());
        txtCarrera.setText(alumno.getCarrera().getCarrera());
    }
    
    private void limpiar(){
        txtCarnet.setText("");
        txtApellidos.setText("");
        txtNombres.setText("");
        txtCarrera.setText("");
        output.setText("");
        txtCarnet.requestFocusInWindow();
    }
    
    private void printExito(){
        output.setText("Alumno registrado en el taller \"" + tallerActual.getNombreTaller() + "\" con exito");
    }
    
    private void actualizarCupos(){
        txtCupos.setText(String.valueOf(TalleresData.getCupos(tallerActual)));
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormTalleres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTalleres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTalleres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTalleres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTalleres().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccion;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox listaTalleres;
    private javax.swing.JLabel output;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JFormattedTextField txtCarnet;
    private javax.swing.JTextField txtCarrera;
    private javax.swing.JTextField txtCupos;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables
}
