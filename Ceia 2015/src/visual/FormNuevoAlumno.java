package visual;

import consultas.AlumnoData;
import consultas.CarreraData;
import java.awt.Color;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import objetos_datos.Alumno;
import objetos_datos.Carrera;
import misc.HintTextFieldUI;
import misc.Tools;

/** Este formulario es el encargado de agregar un nuevo alumno en la base de datos
 * 
 *  @author Vansi Adonay Olivares
 */
public class FormNuevoAlumno extends javax.swing.JDialog {
    private final List<Carrera> carreras;
    private DefaultComboBoxModel modelo;
    private Alumno alumnoCreado;
    
    public FormNuevoAlumno(java.awt.Frame parent, String carnet) {
        super(parent, true);
        initComponents();
        this.setLocationRelativeTo(null);
        jPanel1.setBackground(new Color(250, 250, 250));
        jPanel2.setBackground(new Color(240, 248, 255));
        this.getContentPane().setBackground(new Color(250,250,250));
        
        carreras = CarreraData.getListaCarreras();
        txtCarnet.setText(carnet);
        modelo = new DefaultComboBoxModel();
        leerCarreras();
        this.setIconImage(new ImageIcon(getClass().getResource("/img/ceia 2015.jpg")).getImage());
        txtCarrera.setUI(new HintTextFieldUI(" Escriba su búsqueda de carrera aquí", true, Color.GRAY));
        txtNombres.requestFocusInWindow();
    }
    
    private void leerCarreras(){
        carreras.stream().forEach(carrera -> modelo.addElement("- " + carrera.getCarrera()));
        listaCarreras.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCarnet = new javax.swing.JFormattedTextField();
        txtCarrera = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        listaCarreras = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        output = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar nuevo alumno");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo alumno", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 102, 153))); // NOI18N

        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Apellidos:");

        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Carnet:");

        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });

        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosKeyTyped(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setText("Carrera:");

        try {
            txtCarnet.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UU#####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        txtCarrera.setToolTipText("Escribe aqui el filtro de busqueda para las carreras");
        txtCarrera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCarreraKeyTyped(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nombres:");

        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setText("Filtro:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(listaCarreras, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCarrera)
                        .addComponent(txtApellidos)
                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(listaCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        output.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        output.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jButton2.setForeground(new java.awt.Color(0, 102, 153));
        jButton2.setText("Agregar alumno");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton1.setForeground(new java.awt.Color(0, 102, 153));
        jButton1.setText("Cerrar sin guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(30, 30, 30)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(41, 41, 41))
                    .addComponent(output, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(output, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img7.jpg"))); // NOI18N
        jLabel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    /** Retorna el alumno que se ha ingresado a la base de datos a traves de este formulario.
     *  La idea es usar este mismo alumno para las acciones que se le necesitaba, pero
     *  no estaba registrado aun el la base de datos.
     * 
     * @return el alumno recientemente registrado por el usuario
     * @throws IllegalStateException si el alumno no fue agregado por el usuario, o bien
     *          se llama el metodo antes de mostrar el form
     */
    public Alumno getAlumnoIngresado(){
        if(alumnoCreado == null){
            throw new IllegalStateException("El usuario canceló la petición...");
        }
        return alumnoCreado;
    }
    
    private void txtApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyTyped
        soloLetrasYEspacio(evt);
    }//GEN-LAST:event_txtApellidosKeyTyped
    
    private void soloLetrasYEspacio(java.awt.event.KeyEvent evt){
        char caracter = evt.getKeyChar();
        if(!Character.isLetter(caracter)){
            if(caracter != ' '){
                evt.consume();
            }
        }
    }
    
    private void txtCarreraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarreraKeyTyped
        modelo.removeAllElements();
        String textoActual = txtCarrera.getText().toLowerCase();
        carreras.stream()
            .filter(carrera -> carrera.getCarrera().toLowerCase().contains(textoActual))
            .forEach(carrera -> modelo.addElement("- " + carrera.getCarrera()));
        listaCarreras.setModel(modelo);
    }//GEN-LAST:event_txtCarreraKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(datosIngresadosValidos()){
            if(esAlumnoNuevo()){
                agregarNuevoAlumno();
            }
            else{
                output.setText("Este alumno ya está en la base de datos...");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private boolean datosIngresadosValidos(){
        boolean valido = true;
        if(!txtCarnet.isEditValid()){
            output.setText("Ingrese el carnet de la persona");
            valido = false;
            txtCarnet.requestFocusInWindow();
        }
        else if(txtNombres.getText().isEmpty()){
            output.setText("Ingrese los nombres de la persona");
            valido = false;
            txtNombres.requestFocusInWindow();
        }
        else if(txtApellidos.getText().isEmpty()){
            output.setText("Ingrese los apellidos de la persona");
            valido = false;
            txtApellidos.requestFocusInWindow();
        }
        return valido;
    }
    
    private boolean esAlumnoNuevo(){
        return !AlumnoData.isAlumnoRegistrado(txtCarnet.getText());
    }
    
    private void agregarNuevoAlumno(){
        try{
            procesarAgregarAlumno();
        }
        catch(NullPointerException ex){
            procesarErrorCarreraNoSeleccionada();
        }
    }
    
    private void procesarAgregarAlumno(){
        String carnet = txtCarnet.getText();
        String apellidos = Tools.sanitizarString(txtApellidos.getText());
        String nombres = Tools.sanitizarString(txtNombres.getText());
        Carrera carrera = getCarreraSeleccionada();
        alumnoCreado = new Alumno(carnet, nombres, apellidos, carrera);
        AlumnoData.insertarAlumno(alumnoCreado);
        this.dispose();
    }
    
    private Carrera getCarreraSeleccionada(){
        String seleccionado = listaCarreras.getSelectedItem().toString().substring(2);
        return carreras.stream()
                .filter(carrera -> carrera.getCarrera().equals(seleccionado))
                .findFirst().get();
    }
    
    private void procesarErrorCarreraNoSeleccionada(){
        output.setText("Seleccione una carrera");
        txtCarrera.setText("");
        txtCarreraKeyTyped(null);
        txtCarrera.requestFocusInWindow();
    }
    
    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
        soloLetrasYEspacio(evt);
    }//GEN-LAST:event_txtNombresKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    
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
            java.util.logging.Logger.getLogger(FormNuevoAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormNuevoAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormNuevoAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormNuevoAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormNuevoAlumno dialog = new FormNuevoAlumno(new javax.swing.JFrame(), "OG13008");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox listaCarreras;
    private javax.swing.JLabel output;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JFormattedTextField txtCarnet;
    private javax.swing.JTextField txtCarrera;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables
}
